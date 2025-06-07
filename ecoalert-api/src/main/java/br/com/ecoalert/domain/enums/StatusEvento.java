package br.com.ecoalert.domain.enums;

public enum StatusEvento {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    StatusEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
