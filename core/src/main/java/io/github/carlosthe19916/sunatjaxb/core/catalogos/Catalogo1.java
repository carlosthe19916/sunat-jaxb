package io.github.carlosthe19916.sunatjaxb.core.catalogos;

import java.util.stream.Stream;

public enum Catalogo1 implements Catalogo {

    FACTURA("01"),
    BOLETA("03"),
    NOTA_CREDITO("07"),
    NOTA_DEBITO("08"),
    GUIA_REMISION_REMITENTE("09"),
    TICKET_MAQUINA_REGISTRADORA("12"),
    DOCUMENTOS_FINANCIEROS("13"),
    DOCUMENTOS_AFP("18"),
    GUIA_REMISION_TRANSPORTISTA("31"),
    COMPROBANTE_PAGO_SEAE("56"),
    GUIA_REMISION_REMITENTE_COMPLEMENTARIA("71"),
    GUIA_REMISION_TRANSPORTISTA_COMPLEMENTARIA("72"),

    RETENCION("20"),
    PERCEPCION("40"),
    PERCEPCION_VENTA_INTERNA("21");

    private final String code;

    Catalogo1(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static Catalogo1 valueOfCode(String code) {
        return Stream.of(Catalogo1.values())
                .filter(p -> p.code.equals(code))
                .findFirst()
                .orElse(null);
    }

}
