package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.ImpuestosBeanBuilder;
import io.github.carlosthe19916.beans.ubl.ubl21.Impuestos21Bean;

import java.math.BigDecimal;

public class Impuestos21BeanBuilder extends ImpuestosBeanBuilder<Impuestos21BeanBuilder> {

    private final Impuestos21Bean impuestos;

    private Impuestos21BeanBuilder() {
        super(new Impuestos21Bean());
        impuestos = (Impuestos21Bean) super.impuestos;
    }

    public static Impuestos21BeanBuilder builder() {
        return new Impuestos21BeanBuilder();
    }

    public Impuestos21Bean build() {
        return impuestos;
    }

    @Override
    public Impuestos21BeanBuilder getThis() {
        return this;
    }

    public Impuestos21BeanBuilder igvAfecto(BigDecimal igvAfecto) {
        impuestos.setIgvAfecto(igvAfecto);
        return this;
    }

    public Impuestos21BeanBuilder iscAfecto(BigDecimal iscAfecto) {
        impuestos.setIscAfecto(iscAfecto);
        return this;
    }

    public Impuestos21BeanBuilder otrosAfecto(BigDecimal otrosAfecto) {
        impuestos.setOtrosAfecto(otrosAfecto);
        return this;
    }

}
