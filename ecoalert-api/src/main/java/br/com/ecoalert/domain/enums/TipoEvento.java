package br.com.ecoalert.domain.enums;

public enum TipoEvento {
    CHUVA_FORTE("Chuva Forte"),
    ONDA_CALOR("Onda de Calor"),
    FRIO_EXTREMO("FRIO EXTREMO"),
    PRE_INUNDACAO("Pré-Inundação"),
    INUNDACAO_CRITICA("Inundação Crítica"),
    VENTO_FORTE("Vento Forte"),
    BAIXA_UMIDADE("Baixa Umidade"),
    ALTA_UMIDADE("Alta Umidade"),
    TREMOR_TERRA("Tremor de Terra"),
    INCENDIO("Incêndio"),
    OUTROS("Outros");

    private final String descricao;

    TipoEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
