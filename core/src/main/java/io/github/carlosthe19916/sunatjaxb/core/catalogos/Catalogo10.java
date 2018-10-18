package io.github.carlosthe19916.sunatjaxb.core.catalogos;

public enum Catalogo10 implements Catalogo {
    INTERES_POR_MORA("01"),
    AUMENTO_EN_EL_VALOR("02"),
    PENALIDAD_Y_OTROS_CONCEPTOS("03");

    private final String code;

    Catalogo10(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

}
