package io.github.carlosthe19916.sunatjaxb.catalogos;

import java.util.stream.Stream;

public enum Catalogo9 implements Catalogo {
    ANULACION_DE_LA_OPERACION("01", "ANULACION DE LA OPERACION"),
    ANULACION_POR_ERROR_EN_EL_RUC("02", "ANULACION POR ERROR EN EL RUC"),
    CORRECCION_POR_ERROR_EN_LA_DESCRIPCION("03", "CORRECCION POR ERROR EN LA DESCRIPCION"),
    DECUENTO_GLOBAL("04", "DESCUENTO GLOBAL"),
    DESCUENTO_POR_ITEM("05", "DESCUENTO POR ITEM"),
    DEVOLUCION_TOTAL("06", "DEVOLUCION TOTAL"),
    DEVOLUCION_POR_ITEM("07", "DEVOLUCION POR ITEM"),
    BONIFICACION("08", "BONIFICACION"),
    DISMINUCION_EN_EL_VALOR("09", "DISMINUCION EN EL VALOR"),
    OTROS_CONCEPTOS("10", "OTROS CONCEPTOS");

    private final String code;
    private final String denominacion;

    Catalogo9(String code, String denominacion) {
        this.denominacion = denominacion;
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public static Catalogo9 valueOfCode(String code) {
        return Stream.of(Catalogo9.values())
                .filter(p -> p.code.equals(code))
                .findFirst()
                .orElse(null);
    }
}
