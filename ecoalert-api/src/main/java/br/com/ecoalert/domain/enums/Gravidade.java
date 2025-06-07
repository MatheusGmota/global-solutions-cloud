package br.com.ecoalert.domain.enums;

public enum Gravidade {
    BAIXA("Baixa"),
    MODERADA("Moderada"),
    ALTA("Alta"),
    CRITICA("Crítica");

    private final String descricao;

    Gravidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
