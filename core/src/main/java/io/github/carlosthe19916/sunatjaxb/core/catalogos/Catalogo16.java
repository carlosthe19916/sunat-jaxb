package io.github.carlosthe19916.sunatjaxb.core.catalogos;

public enum Catalogo16 implements Catalogo {

    PRECIO_UNITARIO_INCLUYE_IGV("01"),
    VALOR_FERENCIAL_UNITARIO_EN_OPERACIONES_NO_ONEROSAS("02");

    private final String code;

    Catalogo16(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

}
