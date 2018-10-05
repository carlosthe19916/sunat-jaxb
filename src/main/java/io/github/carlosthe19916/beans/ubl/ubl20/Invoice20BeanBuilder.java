package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.AbstractInvoiceBeanBuilder;
import io.github.carlosthe19916.beans.AbstractMonedaBeanBuilder;
import io.github.carlosthe19916.beans.ClienteBean;
import io.github.carlosthe19916.beans.MonedaBean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21BeanBuilder;

public class Invoice20BeanBuilder extends AbstractInvoiceBeanBuilder<Invoice20BeanBuilder, Invoice20Bean> {

    public static Invoice20BeanBuilder builder() {
        return new Invoice20BeanBuilder();
    }

    private Invoice20BeanBuilder() {
        super(new Invoice20Bean());
    }

    public Invoice20Bean build() {
        return invoice;
    }

    @Override
    public Invoice20BeanBuilder getInvoiceBuilder() {
        return this;
    }

    public Invoice20BeanBuilder observaciones(String observaciones) {
        invoice.setObservaciones(observaciones);
        return this;
    }

    public Invoice20BeanBuilder total(Total20Bean total) {
        invoice.setTotal(total);
        return this;
    }

    public Invoice20BeanBuilder impuestos(Impuestos20Bean impuestos) {
        invoice.setImpuestos(impuestos);
        return this;
    }

    public Invoice20BeanBuilder proveedor(Proveedor20Bean proveedor) {
        invoice.setProveedor(proveedor);
        return getInvoiceBuilder();
    }

    public Invoice20BeanBuilder cliente(ClienteBean cliente) {
        invoice.setCliente(cliente);
        return getInvoiceBuilder();
    }

    public Moneda20BeanBuilder moneda() {
        return new Moneda20BeanBuilder();
    }

    public class Moneda20BeanBuilder extends AbstractMonedaBeanBuilder<Moneda20BeanBuilder, MonedaBean> {

        private Moneda20BeanBuilder() {
            super(new MonedaBean());
        }

        @Override
        protected Moneda20BeanBuilder getMonedaBuilder() {
            return this;
        }

        public Invoice20BeanBuilder fin() {
            invoice.setMoneda(moneda);
            return Invoice20BeanBuilder.this;
        }

    }

}
