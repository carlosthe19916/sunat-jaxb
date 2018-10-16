package io.github.carlosthe19916.sunatjaxb.core.catalogos;

import java.util.stream.Stream;

public enum Catalogo7 implements Catalogo {

    GRAVADO_OPERACION_ONEROSA("10", "Gravado - Operación Onerosa", true, Catalogo11.GRAVADO, false),
    GRAVADO_RETIRO_POR_PREMIO("11", "Gravado - Retiro por premio", true, Catalogo11.GRAVADO, true),
    GRAVADO_RETIRO_POR_DONACION("12", "Gravado - Retiro por donación", true, Catalogo11.GRAVADO, true),
    GRAVADO_RETIRO("13", "Gravado - Retiro", true, Catalogo11.GRAVADO, true),
    GRAVADO_RETIRO_POR_PUBLICIDAD("14", "Gravado - Retiro por publicidad", true, Catalogo11.GRAVADO, true),
    GRAVADO_BONIFICACIONES("15", "Gravado - Bonificaciones", true, Catalogo11.GRAVADO, true),
    GRAVADO_RETIRO_POR_ENTREGA_A_TRABAJADORES("16", "Gravado – Retiro por entrega a trabajadores", true, Catalogo11.GRAVADO, true),
    GRAVADO_IVAP("17", "Gravado – IVAP", false, Catalogo11.GRAVADO, true),
    EXONERADO_OPERACION_ONEROSA("20", "Exonerado - Operación Onerosa", false, Catalogo11.EXONERADO, false),
    EXONERADO_TRANSFERENCIA_GRATUITA("21", "Exonerado – Transferencia Gratuita", false, Catalogo11.EXONERADO, false),
    INAFECTO_OPERACION_ONEROSA("30", "Inafecto - Operación Onerosa", false, Catalogo11.INAFECTO, false),
    INAFECTO_RETIRO_POR_BONIFICACION("31", "Inafecto - Retiro por Bonificación", false, Catalogo11.INAFECTO, true),
    INAFECTO_RETIRO("32", "Inafecto - Retiro", false, Catalogo11.INAFECTO, true),
    INAFECTO_RETIRO_POR_MUESTRAS_MEDICAS("33", "Inafecto - Retiro por Muestras Médicas", false, Catalogo11.INAFECTO, true),
    INAFECTO_RETIRO_POR_CONVENIO_COLECTIVO("34", "Inafecto - Retiro por Convenio Colectivo", false, Catalogo11.INAFECTO, true),
    INAFECTO_RETIRO_POR_PREMIO("35", "Inafecto - Retiro por premio", false, Catalogo11.INAFECTO, true),
    INAFECTO_RETIRO_POR_PUBLICIDAD("36", "Inafecto - Retiro por publicidad", false, Catalogo11.INAFECTO, true),
    EXPORTACION("40", "Exportacion", false, Catalogo11.INAFECTO, true);

    private final String code;
    private final String denominacion;
    private final boolean afectaIgv;
    private final Catalogo11 grupo;

    private final boolean operacionNoOnerosa;

    @Override
    public String getCode() {
        return code;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public boolean getAfectaIgv() {
        return afectaIgv;
    }

    public boolean isOperacionNoOnerosa() {
        return this.operacionNoOnerosa;
    }

    public Catalogo11 getGrupo() {
        return grupo;
    }

    Catalogo7(String code, String denominacion, boolean afectaIgv, Catalogo11 grupo, boolean hasAlternativeConditionPrice) {
        this.code = code;
        this.denominacion = denominacion;
        this.afectaIgv = afectaIgv;
        this.grupo = grupo;

        this.operacionNoOnerosa = hasAlternativeConditionPrice;
    }

    public static Catalogo7 valueOfCode(String code) {
        return Stream.of(Catalogo7.values())
                .filter(p -> p.code.equals(code))
                .findFirst()
                .orElse(null);
    }
}
