package io.github.carlosthe19916.beans.catalogs;

public enum TipoTributo {

    IGV("1000", "VAT", "S", "IGV", "IMPUESTO GENERAL A LAS VENTAS"),
    ISC("2000", "EXC", "S", "ISC", "IMPUESTO SELECTIVO AL CONSUMO"),
    OTROS("9999", "OTH", "S", "OTROS", "OTROS CONCEPTOS DE PAGO");

    private final String id;
    private final String codigo;
    private final String categoria;
    private final String abreviatura;
    private final String denominacion;

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getDenominacion() {
        return denominacion;
    }

    TipoTributo(String id, String codigo, String categoria, String abreviatura, String denominacion) {
        this.id = id;
        this.abreviatura = abreviatura;
        this.categoria = categoria;
        this.codigo = codigo;
        this.denominacion = denominacion;
    }

    public String getCategoria() {
        return categoria;
    }
}
