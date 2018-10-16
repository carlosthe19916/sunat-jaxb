package io.github.carlosthe19916.sunatjaxb.core.catalogos;

public enum Catalogo12 implements Catalogo {

    TICKET_DE_SALIDA("04"),
    CODIGO_SCOP("05"),
    OTROS("99"),
    FACTURA_EMITIDA_PARA_CORREGIR_ERROR_EN_RUC("01"),
    FACTURA_EMITIDA_POR_ANTICIPOS("02"),
    BOLETA_DE_VENTA_EMITIDA_POR_ANTICIPOS("02");

    private final String code;

    Catalogo12(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

}
