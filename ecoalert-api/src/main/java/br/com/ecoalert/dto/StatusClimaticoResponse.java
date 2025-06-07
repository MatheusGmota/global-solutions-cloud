package br.com.ecoalert.dto;

import br.com.ecoalert.domain.entities.StatusClimatico;
import java.time.LocalDateTime;

public class StatusClimaticoResponse {

    private Long id;
    private String descricao;
    private Double temperatura;
    private Double umidade;
    private String cidade;
    private String estado;
    private String latitude;
    private String longitude;
    private LocalDateTime dataHoraAtualizacao;

    public StatusClimaticoResponse(StatusClimatico status) {
        this.id = status.getId();
        this.descricao = status.getDescricao();
        this.temperatura = status.getTemperatura();
        this.umidade = status.getUmidade();
        this.dataHoraAtualizacao = status.getDataHoraAtualizacao();

        if (status.getLocalizacao() != null) {
            this.cidade = status.getLocalizacao().getCidade();
            this.estado = status.getLocalizacao().getEstado();
            this.latitude = status.getLocalizacao().getLatitude();
            this.longitude = status.getLocalizacao().getLongitude();
        }
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public Double getTemperatura() { return temperatura; }
    public Double getUmidade() { return umidade; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
    public LocalDateTime getDataHoraAtualizacao() { return dataHoraAtualizacao; }
}