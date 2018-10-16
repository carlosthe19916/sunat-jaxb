package io.github.carlosthe19916.sunatjaxb.core.builders;

import io.github.carlosthe19916.sunatjaxb.core.beans.FechaBean;

import java.util.Date;

public abstract class AbstractFechaBeanBuilder<Builder extends AbstractFechaBeanBuilder, Bean extends FechaBean> {

    protected final FechaBean fecha;

    protected AbstractFechaBeanBuilder(Bean bean) {
        this.fecha = bean;
    }

    protected abstract Builder getFechaBuilder();

    public Builder fechaEmision(Date fechaEmision) {
        fecha.setFechaEmision(fechaEmision);
        return getFechaBuilder();
    }

    public Builder fechaVencimiento(Date fechaVencimiento) {
        fecha.setFechaVencimiento(fechaVencimiento);
        return getFechaBuilder();
    }

}
