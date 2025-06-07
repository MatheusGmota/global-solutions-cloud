package br.com.ecoalert.domain.entities;

import br.com.ecoalert.dto.DadosIoTRequest;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_DADOS_IOT")
@SequenceGenerator(name = "dados", sequenceName = "SEQ_TB_DADOS_IOT", allocationSize = 1)
public class DadosIoT {

    @Id
    @GeneratedValue(generator = "dados", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_dados_iot")
    private Long id;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "umidade")
    private Double umidade;

    @Column(name = "nivelAguaCm")
    private Double nivelAguaCm;

    @Column(name = "porcentagemNivel")
    private Double porcentagemNivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public DadosIoT() {
    }

    public DadosIoT(DadosIoTRequest dto) {
        this.temperatura = dto.getTemperatura();
        this.umidade = dto.getUmidade();
        this.nivelAguaCm = dto.getNivelAguaCm();
        this.porcentagemNivel = dto.getPorcentagemNivel();
        this.dataHora = LocalDateTime.now();
    }

    public DadosIoT(Double temperatura, Double umidade, Double nivelAguaCm, Double porcentagemNivel, Localizacao localizacao,LocalDateTime dataHora) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.nivelAguaCm = nivelAguaCm;
        this.porcentagemNivel = porcentagemNivel;
        this.localizacao = localizacao;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public Double getNivelAguaCm() {
        return nivelAguaCm;
    }

    public void setNivelAguaCm(Double nivelAguaCm) {
        this.nivelAguaCm = nivelAguaCm;
    }

    public Double getPorcentagemNivel() {
        return porcentagemNivel;
    }

    public void setPorcentagemNivel(Double porcentagemNivel) {
        this.porcentagemNivel = porcentagemNivel;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}