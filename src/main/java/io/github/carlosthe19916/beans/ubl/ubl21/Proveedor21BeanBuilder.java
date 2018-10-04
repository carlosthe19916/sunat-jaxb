package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.AbstractProveedorBeanBuilder;
import io.github.carlosthe19916.beans.ubl.ubl20.Proveedor20Bean;

public class Proveedor21BeanBuilder extends AbstractProveedorBeanBuilder<Proveedor21BeanBuilder, Proveedor21Bean> {

    public static Proveedor21BeanBuilder builder() {
        return new Proveedor21BeanBuilder();
    }

    private Proveedor21BeanBuilder() {
        super(new Proveedor21Bean());
    }

    public Proveedor21Bean build() {
        return getBean();
    }

    @Override
    public Proveedor21BeanBuilder getBuilder() {
        return this;
    }

}
