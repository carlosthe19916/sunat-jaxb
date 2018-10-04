package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public abstract class TotalBeanBuilder<T extends TotalBeanBuilder> {

    protected final TotalBean total;

    protected TotalBeanBuilder(TotalBean total) {
        this.total = total;
    }

    public T pagar(BigDecimal pagar) {
        total.setPagar(pagar);
        return getThis();
    }

    public T otrosCargos(BigDecimal otrosCargos) {
        total.setOtrosCargos(otrosCargos);
        return getThis();
    }

    public T descuentoGlobal(BigDecimal descuento) {
        total.setDescuentoGlobal(descuento);
        return getThis();
    }

    public abstract T getThis();
}
