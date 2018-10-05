package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.AbstractProveedorBeanBuilder;

public class Proveedor20BeanBuilder extends AbstractProveedorBeanBuilder<Proveedor20BeanBuilder, Proveedor20Bean> {

    public static Proveedor20BeanBuilder builder() {
        return new Proveedor20BeanBuilder();
    }

    private Proveedor20BeanBuilder() {
        super(new Proveedor20Bean());
    }

    public Proveedor20Bean build() {
        return getBean();
    }

    @Override
    public Proveedor20BeanBuilder getBuilder() {
        return this;
    }

    public Proveedor20BeanBuilder direccion(String direccion) {
        getBean().setDireccion(direccion);
        return this;
    }

    public Proveedor20BeanBuilder provincia(String provincia) {
        getBean().setProvincia(provincia);
        return this;
    }

    public Proveedor20BeanBuilder distrito(String distrito) {
        getBean().setDistrito(distrito);
        return this;
    }

    public Proveedor20BeanBuilder region(String region) {
        getBean().setRegion(region);
        return this;
    }

    public Proveedor20BeanBuilder codigoPais(String codigoPais) {
        getBean().setCodigoPais(codigoPais);
        return this;
    }

}
