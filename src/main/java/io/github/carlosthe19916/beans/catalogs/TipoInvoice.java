package io.github.carlosthe19916.beans.catalogs;

public enum TipoInvoice {

    FACTURA("01"),
    BOLETA("03");

    private final String codigo;

    TipoInvoice(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
