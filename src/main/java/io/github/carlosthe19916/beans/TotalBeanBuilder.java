package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class TotalBeanBuilder {

    private final TotalBean total;

    public TotalBeanBuilder() {
        total = new TotalBean();
    }

    public static TotalBeanBuilder Total() {
        return new TotalBeanBuilder();
    }

    public TotalBeanBuilder pagar(BigDecimal pagar) {
        total.setPagar(pagar);
        return this;
    }

    public TotalBeanBuilder otrosCargos(BigDecimal otrosCargos) {
        total.setOtrosCargos(otrosCargos);
        return this;
    }

    public TotalBeanBuilder descuentoGlobal(BigDecimal descuento) {
        total.setDescuentoGlobal(descuento);
        return this;
    }

    public TotalBean build() {
        return total;
    }
}
