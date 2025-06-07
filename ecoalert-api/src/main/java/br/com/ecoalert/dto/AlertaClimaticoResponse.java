package br.com.ecoalert.dto;

import br.com.ecoalert.domain.entities.AlertaClimatico;
import br.com.ecoalert.domain.enums.Gravidade;
import br.com.ecoalert.domain.enums.StatusEvento;
import br.com.ecoalert.domain.enums.TipoEvento;

import java.time.LocalDateTime;

public class AlertaClimaticoResponse {
    private Long id;
    private TipoEvento tipoEvento;
    private Gravidade gravidade;
    private String cidade;
    private String estado;
    private String latitude;
    private String longitude;
    private String mensagem;
    private String recomendacoes;
    private LocalDateTime dataHoraEmissao;
    private StatusEvento statusEvento;

    public AlertaClimaticoResponse(AlertaClimatico alerta) {
        this.id = alerta.getId();
        this.tipoEvento = alerta.getTipoEvento();
        this.gravidade = alerta.getGravidade();
        if (alerta.getLocalizacao() != null) {
            this.cidade = alerta.getLocalizacao().getCidade();
            this.estado = alerta.getLocalizacao().getEstado();
            this.latitude = alerta.getLocalizacao().getLatitude();
            this.longitude = alerta.getLocalizacao().getLongitude();
        }
        this.mensagem = alerta.getMensagem();
        this.recomendacoes = alerta.getRecomendacoes();
        this.dataHoraEmissao = alerta.getDataHoraEmissao();
        this.statusEvento = alerta.getStatusEvento();
    }

    public Long getId() { return id; }
    public TipoEvento getTipoEvento() { return tipoEvento; }
    public Gravidade getGravidade() { return gravidade; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
    public String getMensagem() { return mensagem; }
    public String getRecomendacoes() { return recomendacoes; }
    public LocalDateTime getDataHoraEmissao() { return dataHoraEmissao; }
    public StatusEvento getStatusEvento() { return statusEvento; }
}