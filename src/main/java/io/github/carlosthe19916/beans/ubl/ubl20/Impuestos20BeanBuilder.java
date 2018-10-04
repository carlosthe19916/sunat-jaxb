package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.ImpuestosBeanBuilder;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;

public class Impuestos20BeanBuilder extends ImpuestosBeanBuilder<Impuestos20BeanBuilder> {

    private final Impuestos20Bean impuestos;

    private Impuestos20BeanBuilder() {
        super(new Impuestos20Bean());
        impuestos = (Impuestos20Bean) super.impuestos;
    }

    public static Impuestos20BeanBuilder builder() {
        return new Impuestos20BeanBuilder();
    }

    public Impuestos20Bean build() {
        return impuestos;
    }

    @Override
    public Impuestos20BeanBuilder getThis() {
        return this;
    }

}
