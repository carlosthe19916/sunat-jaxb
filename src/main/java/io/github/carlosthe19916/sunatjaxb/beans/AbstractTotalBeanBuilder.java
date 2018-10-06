package io.github.carlosthe19916.sunatjaxb.beans;

import java.math.BigDecimal;

public abstract class AbstractTotalBeanBuilder<Builder extends AbstractTotalBeanBuilder, Bean extends AbstractTotalBean> {

    protected final Bean total;

    protected AbstractTotalBeanBuilder(Bean total) {
        this.total = total;
    }

    protected abstract Builder getTotalBuilder();

    public Builder pagar(BigDecimal pagar) {
        total.setPagar(pagar);
        return getTotalBuilder();
    }

    public Builder otrosCargos(BigDecimal otrosCargos) {
        total.setOtrosCargos(otrosCargos);
        return getTotalBuilder();
    }

    public Builder descuentoGlobal(BigDecimal descuento) {
        total.setDescuentoGlobal(descuento);
        return getTotalBuilder();
    }

    public Builder pagarLetras(String pagarLetras) {
        total.setPagarLetras(pagarLetras);
        return getTotalBuilder();
    }

}
