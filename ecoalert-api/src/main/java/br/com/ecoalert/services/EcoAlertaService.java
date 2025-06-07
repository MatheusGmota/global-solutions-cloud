package br.com.ecoalert.services;

import br.com.ecoalert.domain.entities.AlertaClimatico;
import br.com.ecoalert.domain.entities.DadosIoT;
import br.com.ecoalert.domain.entities.LimiarClimatico;
import br.com.ecoalert.domain.entities.Localizacao;
import br.com.ecoalert.domain.entities.StatusClimatico;
import br.com.ecoalert.domain.enums.Gravidade;
import br.com.ecoalert.domain.enums.StatusEvento;
import br.com.ecoalert.domain.enums.TipoEvento;
import br.com.ecoalert.dto.AlertaClimaticoResponse;
import br.com.ecoalert.dto.DadosIoTRequest;
import br.com.ecoalert.dto.StatusClimaticoResponse;
import br.com.ecoalert.repositories.AlertaClimaticoRepository;
import br.com.ecoalert.repositories.DadosIoTRepository;
import br.com.ecoalert.repositories.LimiarClimaticoRepository;
import br.com.ecoalert.repositories.LocalizacaoRepository;
import br.com.ecoalert.repositories.StatusClimaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EcoAlertaService {

    @Autowired
    private DadosIoTRepository dadosIoTRepository;

    @Autowired
    private AlertaClimaticoRepository alertaClimaticoRepository;

    @Autowired
    private StatusClimaticoRepository statusClimaticoRepository;

    @Autowired
    private LimiarClimaticoRepository limiarClimaticoRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Transactional
    public void processarDados(DadosIoTRequest request) {
        // 1. Encontrar ou criar a Localizacao
        Localizacao localizacao = findOrCreateLocalizacao(
                request.getCidade(),
                request.getEstado(),
                request.getLatitude(),
                request.getLongitude()
        );

        // 2. Criar DadosIoT e associar a Localizacao encontrada
        DadosIoT dadosIoT = new DadosIoT(request); // Construtor preenche dados do sensor e dataHora
        dadosIoT.setLocalizacao(localizacao); // Associa o objeto Localizacao
        dadosIoTRepository.save(dadosIoT);

        // 3. Atualizar StatusClimatico
        StatusClimatico statusClimatico = new StatusClimatico();
        statusClimatico.setDataHoraAtualizacao(LocalDateTime.now());
        statusClimatico.setDescricao((localizacao.getId()%2 != 0) ? "Nublado": "Calor");
        statusClimatico.setLocalizacao(localizacao);
        statusClimatico.setTemperatura(dadosIoT.getTemperatura());
        statusClimatico.setUmidade(dadosIoT.getUmidade());

        statusClimaticoRepository.save(statusClimatico);

        // 4. Verificar e emitir alertas (passando a localização)
        List<LimiarClimatico> limiares = limiarClimaticoRepository.findAll();
        verificarAlertas(dadosIoT, limiares);
    }

    private Localizacao findOrCreateLocalizacao(String cidade, String estado, String latitude, String longitude) {
        Optional<Localizacao> existingLocation = localizacaoRepository.findByCidadeAndEstadoAndLatitudeAndLongitude(cidade, estado, latitude, longitude);

        return existingLocation.orElseGet(() -> {
            // Se não encontrou, cria uma nova Localizacao e a salva
            Localizacao newLocation = new Localizacao(cidade, estado, latitude, longitude);
            return localizacaoRepository.save(newLocation);
        });
    }

    private Localizacao getLocalizacaoByString(String localizacaoString) {
        // Assume que a string de localização é o nome da cidade.
        // Se for um formato como "Cidade/Estado", você precisará parsear a string aqui.
        return localizacaoRepository.findByCidade(localizacaoString)
                .orElseThrow(() -> new IllegalArgumentException("Localização '" + localizacaoString + "' não encontrada."));
    }

    public AlertaClimaticoResponse obterAlertaClimatico(String localizacaoRecebida) {
        Localizacao localizacao = getLocalizacaoByString(localizacaoRecebida);
        AlertaClimatico alertaClimatico = alertaClimaticoRepository.findFirstByLocalizacaoAndStatusEventoOrderByDataHoraEmissaoDesc(localizacao, StatusEvento.ATIVO)
                .orElse(null);
        return (alertaClimatico != null) ? new AlertaClimaticoResponse(alertaClimatico) : null;
    }

    public StatusClimaticoResponse obterStatusClimatico(String localizacaoRecebida) {
        Localizacao localizacao = getLocalizacaoByString(localizacaoRecebida);

        StatusClimatico status = statusClimaticoRepository.findFirstByLocalizacaoOrderByDataHoraAtualizacaoDesc(localizacao)
                .orElse(null);

        return (status != null) ? new StatusClimaticoResponse(status) : null;
    }

    public List<AlertaClimatico> obterHistoricoAlerta(String localizacaoRecebida) {
        Localizacao localizacao = getLocalizacaoByString(localizacaoRecebida);
        return alertaClimaticoRepository.findByLocalizacaoOrderByDataHoraEmissaoDesc(localizacao);
    }

    private void verificarAlertas(DadosIoT dadosIoT, List<LimiarClimatico> limiares) {
        Localizacao localizacao = dadosIoT.getLocalizacao();

        StatusClimatico status = statusClimaticoRepository.findFirstByLocalizacaoOrderByDataHoraAtualizacaoDesc(dadosIoT.getLocalizacao()).orElse(null);

        for (LimiarClimatico limiar : limiares) {
            double valorSensor = obterValorSensor(dadosIoT, limiar.getParametroSensor());

            if (valorSensor <= limiar.getValorMin()) {
                if (alertaClimaticoRepository.findFirstByLocalizacaoAndTipoEventoAndStatusEvento(localizacao, mapearTipoEvento(false, limiar.getParametroSensor()), StatusEvento.ATIVO).isEmpty()) {
                    TipoEvento tipoEvento = mapearTipoEvento(false, limiar.getParametroSensor());

                    AlertaClimatico alertaMin = new AlertaClimatico();
                    alertaMin.setDataHoraEmissao(LocalDateTime.now());
                    alertaMin.setGravidade(Gravidade.ALTA);
                    alertaMin.setLocalizacao(localizacao);
                    alertaMin.setStatusEvento(StatusEvento.ATIVO);
                    alertaMin.setRecomendacoes(limiar.getRecomendacaoAlerta());
                    alertaMin.setMensagem(limiar.getMsgMin());
                    alertaMin.setTipoEvento(tipoEvento);
                    alertaClimaticoRepository.save(alertaMin);
                }
            }
            else if (valorSensor >= limiar.getValorMax()) {
                if (alertaClimaticoRepository.findFirstByLocalizacaoAndTipoEventoAndStatusEvento(localizacao, mapearTipoEvento(true, limiar.getParametroSensor()), StatusEvento.ATIVO).isEmpty()) {
                    TipoEvento tipoEvento = mapearTipoEvento(true, limiar.getParametroSensor());
                    AlertaClimatico alertaMax = new AlertaClimatico();
                    alertaMax.setDataHoraEmissao(LocalDateTime.now());
                    alertaMax.setGravidade(Gravidade.ALTA);
                    alertaMax.setLocalizacao(localizacao);
                    alertaMax.setStatusEvento(StatusEvento.ATIVO);
                    alertaMax.setRecomendacoes(limiar.getRecomendacaoAlerta());
                    alertaMax.setMensagem(limiar.getMsgMax());
                    alertaMax.setTipoEvento(tipoEvento);
                    alertaClimaticoRepository.save(alertaMax);
                }
            }
            else {
                alertaClimaticoRepository.findFirstByLocalizacaoAndTipoEventoAndStatusEventoOrderByDataHoraEmissaoDesc(
                        localizacao, mapearTipoEvento(false, limiar.getParametroSensor()), StatusEvento.ATIVO
                ).ifPresent(alerta -> {
                    alerta.setStatusEvento(StatusEvento.INATIVO);
                    alertaClimaticoRepository.save(alerta);
                });
            }
        }
    }

    private TipoEvento mapearTipoEvento(boolean max, String parametroSensor) {
        return switch (parametroSensor.toLowerCase()) {
            case "umidade" -> (max) ? TipoEvento.ALTA_UMIDADE : TipoEvento.BAIXA_UMIDADE;
            case "temperatura" -> (max) ? TipoEvento.ONDA_CALOR : TipoEvento.FRIO_EXTREMO;
            case "nivelagua", "porcentagemnivel" -> TipoEvento.INUNDACAO_CRITICA;
            case "vento" -> TipoEvento.VENTO_FORTE;
            case "chuva" -> TipoEvento.CHUVA_FORTE;
            case "tremor" -> TipoEvento.TREMOR_TERRA;
            case "incendio" -> TipoEvento.INCENDIO;
            default -> TipoEvento.OUTROS;
        };
    }

    private double obterValorSensor(DadosIoT dados, String parametro) {
        return switch (parametro.toLowerCase()) {
            case "temperatura" -> dados.getTemperatura();
            case "umidade" -> dados.getUmidade();
            case "nivelagua" -> dados.getNivelAguaCm();
            case "porcentagemnivel" -> dados.getPorcentagemNivel();
            default -> 0;
        };
    }
}