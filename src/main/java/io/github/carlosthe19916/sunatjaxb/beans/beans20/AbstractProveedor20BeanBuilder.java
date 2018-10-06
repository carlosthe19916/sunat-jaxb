package io.github.carlosthe19916.sunatjaxb.beans.beans20;

import io.github.carlosthe19916.sunatjaxb.beans.AbstractProveedorBeanBuilder;

public abstract class AbstractProveedor20BeanBuilder<Builder extends AbstractProveedor20BeanBuilder, Bean extends Proveedor20Bean> extends AbstractProveedorBeanBuilder<Builder, Bean> {

    protected AbstractProveedor20BeanBuilder(Bean proveedor) {
        super(proveedor);
    }

    public AbstractProveedor20BeanBuilder direccion(String direccion) {
        proveedor.setDireccion(direccion);
        return this;
    }

    public AbstractProveedor20BeanBuilder provincia(String provincia) {
        proveedor.setProvincia(provincia);
        return this;
    }

    public AbstractProveedor20BeanBuilder distrito(String distrito) {
        proveedor.setDistrito(distrito);
        return this;
    }

    public AbstractProveedor20BeanBuilder region(String region) {
        proveedor.setRegion(region);
        return this;
    }

    public AbstractProveedor20BeanBuilder codigoPais(String codigoPais) {
        proveedor.setCodigoPais(codigoPais);
        return this;
    }

}
