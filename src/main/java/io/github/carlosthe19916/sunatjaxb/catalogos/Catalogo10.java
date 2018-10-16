package io.github.carlosthe19916.sunatjaxb.catalogos;

import java.util.stream.Stream;

public enum Catalogo10 implements Catalogo {
    INTERES("01", "INTERES POR MORA"),
    AUMENTO("02", "AUMENTO EN EL VALOR"),
    PENALIDAD("03", "PENALIDADES / OTROS CONCEPTOS");

    private final String code;
    private final String denominacion;

    Catalogo10(String code, String denominacion) {
        this.code = code;
        this.denominacion = denominacion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static Catalogo10 valueOfCode(String code) {
        return Stream.of(Catalogo10.values())
                .filter(p -> p.code.equals(code))
                .findFirst()
                .orElse(null);
    }
}
