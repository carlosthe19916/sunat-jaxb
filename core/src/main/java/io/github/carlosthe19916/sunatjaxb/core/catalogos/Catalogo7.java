package io.github.carlosthe19916.sunatjaxb.core.catalogos;

public enum Catalogo7 implements Catalogo {

    GRAVADO_OPERACION_ONEROSA("10", false),
    GRAVADO_RETIRO_POR_PREMIO("11", true),
    GRAVADO_RETIRO_POR_DONACION("12", true),
    GRAVADO_RETIRO("13", true),
    GRAVADO_RETIRO_POR_PUBLICIDAD("14", true),
    GRAVADO_BONIFICACIONES("15", true),
    GRAVADO_RETIRO_POR_ENTREGA_A_TRABAJADORES("16", true),
    GRAVADO_IVAP("17", true),
    EXONERADO_OPERACION_ONEROSA("20", false),
    EXONERADO_TRANSFERENCIA_GRATUITA("21", false),
    INAFECTO_OPERACION_ONEROSA("30", false),
    INAFECTO_RETIRO_POR_BONIFICACION("31", true),
    INAFECTO_RETIRO("32", true),
    INAFECTO_RETIRO_POR_MUESTRAS_MEDICAS("33", true),
    INAFECTO_RETIRO_POR_CONVENIO_COLECTIVO("34", true),
    INAFECTO_RETIRO_POR_PREMIO("35", true),
    INAFECTO_RETIRO_POR_PUBLICIDAD("36", true),
    EXPORTACION("40", true);

    private final String code;
    private final boolean operacionNoOnerosa;

    @Override
    public String getCode() {
        return code;
    }

    public boolean isOperacionNoOnerosa() {
        return this.operacionNoOnerosa;
    }

    Catalogo7(String code, boolean operacionNoOnerosa) {
        this.code = code;
        this.operacionNoOnerosa = operacionNoOnerosa;
    }

}
