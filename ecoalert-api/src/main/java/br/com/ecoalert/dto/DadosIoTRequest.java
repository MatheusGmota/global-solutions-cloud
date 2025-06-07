package br.com.ecoalert.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DadosIoTRequest {

    @NotNull(message = "O campo temperatura é obrigatorio.")
    private Double temperatura;

    @NotNull(message = "O campo umidade é obrigatorio.")
    private Double umidade;

    @NotNull(message = "O campo nivelAguaCm é obrigatorio.")
    private Double nivelAguaCm;

    @NotNull(message = "O campo porcentagemNivel é obrigatorio.")
    private Double porcentagemNivel;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(max = 50, message = "O estado deve ter no máximo 50 caracteres.")
    private String estado;

    @NotBlank(message = "A cidade é obrigatória.")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
    private String cidade;

    @NotBlank(message = "A latitude é obrigatória.")
    @Size(max = 20, message = "A latitude deve ter no máximo 20 caracteres.") // Ajuste o tamanho conforme necessário
    private String latitude;

    @NotBlank(message = "A longitude é obrigatória.")
    @Size(max = 20, message = "A longitude deve ter no máximo 20 caracteres.")
    private String longitude;

    public DadosIoTRequest() {}

    public DadosIoTRequest(Double temperatura, Double umidade, Double nivelAguaCm, Double porcentagemNivel,
                           String estado, String cidade, String latitude, String longitude) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.nivelAguaCm = nivelAguaCm;
        this.porcentagemNivel = porcentagemNivel;
        this.estado = estado;
        this.cidade = cidade;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getTemperatura() { return temperatura; }

    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public Double getUmidade() { return umidade; }

    public void setUmidade(Double umidade) { this.umidade = umidade; }

    public Double getNivelAguaCm() { return nivelAguaCm; }

    public void setNivelAguaCm(Double nivelAguaCm) { this.nivelAguaCm = nivelAguaCm; }

    public Double getPorcentagemNivel() { return porcentagemNivel; }

    public void setPorcentagemNivel(Double porcentagemNivel) { this.porcentagemNivel = porcentagemNivel; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }
}