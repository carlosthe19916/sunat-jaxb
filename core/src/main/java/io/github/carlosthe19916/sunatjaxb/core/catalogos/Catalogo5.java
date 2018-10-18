package io.github.carlosthe19916.sunatjaxb.core.catalogos;

public enum Catalogo5 implements Catalogo {

    IGV("1000", "VAT", "S", "IGV"),
    ISC("2000", "EXC", "S", "ISC"),
    OTROS("9999", "OTH", "S", "OTROS");

    private final String id;
    private final String code;
    private final String categoria;
    private final String abreviatura;

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

    public String getCategoria() {
        return categoria;
    }

    Catalogo5(String id, String codigo, String categoria, String abreviatura) {
        this.id = id;
        this.abreviatura = abreviatura;
        this.categoria = categoria;
        this.code = codigo;
    }

}
