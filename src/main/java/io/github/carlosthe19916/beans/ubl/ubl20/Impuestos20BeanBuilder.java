package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.AbstractImpuestosBeanBuilder;

public class Impuestos20BeanBuilder extends AbstractImpuestosBeanBuilder<Impuestos20BeanBuilder, Impuestos20Bean> {

    public static Impuestos20BeanBuilder builder() {
        return new Impuestos20BeanBuilder();
    }

    private Impuestos20BeanBuilder() {
        super(new Impuestos20Bean());
    }

    public Impuestos20Bean build() {
        return getBean();
    }

    @Override
    public Impuestos20BeanBuilder getBuilder() {
        return this;
    }

}
