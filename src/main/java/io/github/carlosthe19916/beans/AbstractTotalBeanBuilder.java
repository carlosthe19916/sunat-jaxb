package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public abstract class AbstractTotalBeanBuilder<Builder extends AbstractTotalBeanBuilder, Bean extends TotalBean> {

    private final Bean bean;

    public AbstractTotalBeanBuilder(Bean bean) {
        this.bean = bean;
    }

    public Bean getBean() {
        return bean;
    }

    public abstract Builder getBuilder();

    public Builder pagar(BigDecimal pagar) {
        bean.setPagar(pagar);
        return getBuilder();
    }

    public Builder otrosCargos(BigDecimal otrosCargos) {
        bean.setOtrosCargos(otrosCargos);
        return getBuilder();
    }

    public Builder descuentoGlobal(BigDecimal descuento) {
        bean.setDescuentoGlobal(descuento);
        return getBuilder();
    }

}
