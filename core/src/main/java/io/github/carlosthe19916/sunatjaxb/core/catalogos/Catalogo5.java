package io.github.carlosthe19916.sunatjaxb.core.catalogos;

public enum Catalogo5 implements Catalogo {

    IGV("1000", "VAT", "S", "IGV", "IMPUESTO GENERAL A LAS VENTAS"),
    ISC("2000", "EXC", "S", "ISC", "IMPUESTO SELECTIVO AL CONSUMO"),
    OTROS("9999", "OTH", "S", "OTROS", "OTROS CONCEPTOS DE PAGO");

    private final String id;
    private final String code;
    private final String categoria;
    private final String abreviatura;
    private final String denominacion;

    public String getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getCategoria() {
        return categoria;
    }

    Catalogo5(String id, String codigo, String categoria, String abreviatura, String denominacion) {
        this.id = id;
        this.abreviatura = abreviatura;
        this.categoria = categoria;
        this.code = codigo;
        this.denominacion = denominacion;
    }

}
