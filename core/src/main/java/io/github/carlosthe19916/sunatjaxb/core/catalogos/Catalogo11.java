package io.github.carlosthe19916.sunatjaxb.core.catalogos;

public enum Catalogo11 implements Catalogo {

    GRAVADO("01"),
    EXONERADO("02"),
    INAFECTO("03"),
    EXPORTACION("04"),
    GRATUITA("05");

    private final String code;

    @Override
    public String getCode() {
        return code;
    }

    Catalogo11(String code) {
        this.code = code;
    }

}
