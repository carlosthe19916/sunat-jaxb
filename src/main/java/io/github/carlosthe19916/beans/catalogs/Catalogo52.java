package io.github.carlosthe19916.beans.catalogs;

public enum Catalogo52 implements Catalogo {

    MONTO_EXPRESADO_EN_LETRAS("1000"),
    CODIGO_INTERNO_GENERADO_POR_EL_SOFTWARE_DE_FACTURACION("3000"),
    ;

    private final String code;

    public String getCode() {
        return code;
    }

    Catalogo52(String code) {
        this.code = code;

    }

}
