package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.AbstractInvoiceBeanBuilder;
import io.github.carlosthe19916.beans.ClienteBean;

public class Invoice20BeanBuilder extends AbstractInvoiceBeanBuilder<Invoice20BeanBuilder, Invoice20Bean> {

    public static Invoice20BeanBuilder builder() {
        return new Invoice20BeanBuilder();
    }

    private Invoice20BeanBuilder() {
        super(new Invoice20Bean());
    }

    public Invoice20Bean build() {
        return getBean();
    }

    @Override
    public Invoice20BeanBuilder getBuilder() {
        return this;
    }

    public Invoice20BeanBuilder observaciones(String observaciones) {
        getBean().setObservaciones(observaciones);
        return this;
    }

    public Invoice20BeanBuilder total(Total20Bean total) {
        getBean().setTotal(total);
        return this;
    }

    public Invoice20BeanBuilder impuestos(Impuestos20Bean impuestos) {
        getBean().setImpuestos(impuestos);
        return this;
    }

    Invoice20BeanBuilder proveedor(Proveedor20Bean proveedor) {
        getBean().setProveedor(proveedor);
        return getBuilder();
    }

    Invoice20BeanBuilder cliente(ClienteBean cliente) {
        getBean().setCliente(cliente);
        return getBuilder();
    }

}
