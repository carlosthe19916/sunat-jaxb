package io.github.carlosthe19916.sunatjaxb.ubl21.builders;

import io.github.carlosthe19916.sunatjaxb.core.builders.AbstractProveedorBeanBuilder;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.Proveedor21Bean;

public abstract class AbstractProveedor21BeanBuilder<Builder extends AbstractProveedor21BeanBuilder, Bean extends Proveedor21Bean> extends AbstractProveedorBeanBuilder<Builder, Bean> {

    protected AbstractProveedor21BeanBuilder(Bean proveedor) {
        super(proveedor);
    }

}
