package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public abstract class ImpuestosBeanBuilder<T> {

    protected final ImpuestosBean impuestos;

    public ImpuestosBeanBuilder(ImpuestosBean impuestos) {
        this.impuestos = impuestos;
    }

    public T igv(BigDecimal igv) {
        impuestos.setIgv(igv);
        return getThis();
    }

    public T isc(BigDecimal isc) {
        impuestos.setIsc(isc);
        return getThis();
    }

    public T otros(BigDecimal otros) {
        impuestos.setOtros(otros);
        return getThis();
    }

    public abstract T getThis();

}
