package io.github.carlosthe19916.beans.catalogs;

public enum Catalog51 {

    VENTA_INTERNA("0101"),
    EXPORTACION("0102"),
    NO_DOMICILIADOS("0103"),
    VENTA_INTERNA_ANTICIPOS("0104"),
    VENTA_ITINERANTE("0105"),
    FACTURA_GUIA("0106"),
    VENTA_ARROZ_PILADO("0107"),
    FACTURA_COMPROBANTE_PERCEPCION("0108"),
    FACTURA_GUIA_REMITENTE("0110");

    private final String code;

    Catalog51(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
