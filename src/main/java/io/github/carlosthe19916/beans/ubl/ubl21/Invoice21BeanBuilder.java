package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.AbstractInvoiceBeanBuilder;
import io.github.carlosthe19916.beans.ClienteBean;

public class Invoice21BeanBuilder extends AbstractInvoiceBeanBuilder<Invoice21BeanBuilder, Invoice21Bean> {

    public static Invoice21BeanBuilder builder() {
        return new Invoice21BeanBuilder();
    }

    private Invoice21BeanBuilder() {
        super(new Invoice21Bean());
    }

    public Invoice21Bean build() {
        return getBean();
    }

    @Override
    public Invoice21BeanBuilder getBuilder() {
        return this;
    }

    public Invoice21BeanBuilder total(Total21Bean total) {
        getBean().setTotal(total);
        return this;
    }

    public Invoice21BeanBuilder impuestos(Impuestos21Bean impuestos) {
        getBean().setImpuestos(impuestos);
        return this;
    }

    public Invoice21BeanBuilder proveedor(Proveedor21Bean proveedor) {
        getBean().setProveedor(proveedor);
        return getBuilder();
    }

    public Invoice21BeanBuilder cliente(ClienteBean cliente) {
        getBean().setCliente(cliente);
        return getBuilder();
    }

}
