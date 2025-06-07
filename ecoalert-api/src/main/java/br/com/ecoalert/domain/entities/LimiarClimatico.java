package br.com.ecoalert.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_LIMIAR_CLIMATICO")
@SequenceGenerator(name = "limiar", sequenceName = "SEQ_LIMIAR_CLIMATICO", allocationSize = 1)
public class LimiarClimatico {

    @Id
    @GeneratedValue(generator = "limiar", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_limiar")
    private Long id;

    @Column(name = "parametro_sensor", nullable = false)
    private String parametroSensor;

    @Column(name = "valor_max", nullable = false)
    private Double valorMax;

    @Column(name = "valor_min", nullable = false)
    private Double valorMin;

    @Column(name = "msg_max")
    private String msgMax;

    @Column(name = "msg_min")
    private String msgMin;

    @Column(name = "recomendacao_alerta")
    private String recomendacaoAlerta;

    public LimiarClimatico() {
    }

    public LimiarClimatico(String parametroSensor, Double valorMax, Double valorMin, String msgMax, String msgMin, String recomendacaoAlerta) {
        this.parametroSensor = parametroSensor;
        this.valorMax = valorMax;
        this.valorMin = valorMin;
        this.msgMax = msgMax;
        this.msgMin = msgMin;
        this.recomendacaoAlerta = recomendacaoAlerta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParametroSensor() {
        return parametroSensor;
    }

    public void setParametroSensor(String parametroSensor) {
        this.parametroSensor = parametroSensor;
    }

    public Double getValorMax() {
        return valorMax;
    }

    public void setValorMax(Double valorMax) {
        this.valorMax = valorMax;
    }

    public Double getValorMin() {
        return valorMin;
    }

    public void setValorMin(Double valorMin) {
        this.valorMin = valorMin;
    }

    public String getMsgMax() {
        return msgMax;
    }

    public void setMsgMax(String msgMax) {
        this.msgMax = msgMax;
    }

    public String getMsgMin() {
        return msgMin;
    }

    public void setMsgMin(String msgMin) {
        this.msgMin = msgMin;
    }
    
    public String getRecomendacaoAlerta() {
        return recomendacaoAlerta;
    }

    public void setRecomendacaoAlerta(String recomendacaoAlerta) {
        this.recomendacaoAlerta = recomendacaoAlerta;
    }
}
