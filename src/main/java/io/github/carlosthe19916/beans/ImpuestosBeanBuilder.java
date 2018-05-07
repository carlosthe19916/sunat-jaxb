package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class ImpuestosBeanBuilder {

    private final ImpuestosBean impuestos;

    public ImpuestosBeanBuilder() {
        impuestos = new ImpuestosBean();
    }

    public static ImpuestosBeanBuilder Impuestos() {
        return new ImpuestosBeanBuilder();
    }

    public ImpuestosBeanBuilder igv(BigDecimal igv) {
        impuestos.setIgv(igv);
        return this;
    }

    public ImpuestosBeanBuilder isc(BigDecimal isc) {
        impuestos.setIsc(isc);
        return this;
    }

    public ImpuestosBean build() {
        return impuestos;
    }
}
