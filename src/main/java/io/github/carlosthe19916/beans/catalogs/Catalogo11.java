package io.github.carlosthe19916.beans.catalogs;

public enum Catalogo11 implements Catalogo {

    GRAVADO("01", "GRAVADO"),
    EXONERADO("02", "EXONERADO"),
    INAFECTO("03", "INAFECTO"),
    EXPORTACION("04", "EXPORTACION"),
    GRATUITA("05", "GRATUITAS");

    private final String code;
    private final String denominacion;

    @Override
    public String getCode() {
        return code;
    }

    public String getDenominacion() {
        return denominacion;
    }

    Catalogo11(String code, String denominacion) {
        this.code = code;
        this.denominacion = denominacion;
    }

}
