package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.AbstractImpuestosBeanBuilder;

import java.math.BigDecimal;

public class Impuestos21BeanBuilder extends AbstractImpuestosBeanBuilder<Impuestos21BeanBuilder, Impuestos21Bean> {

    public static Impuestos21BeanBuilder builder() {
        return new Impuestos21BeanBuilder();
    }

    private Impuestos21BeanBuilder() {
        super(new Impuestos21Bean());
    }

    public Impuestos21Bean build() {
        return getBean();
    }

    @Override
    public Impuestos21BeanBuilder getBuilder() {
        return this;
    }

    public Impuestos21BeanBuilder igvAfecto(BigDecimal igvAfecto) {
        getBean().setIgvAfecto(igvAfecto);
        return this;
    }

    public Impuestos21BeanBuilder iscAfecto(BigDecimal iscAfecto) {
        getBean().setIscAfecto(iscAfecto);
        return this;
    }

    public Impuestos21BeanBuilder otrosAfecto(BigDecimal otrosAfecto) {
        getBean().setOtrosAfecto(otrosAfecto);
        return this;
    }

}
