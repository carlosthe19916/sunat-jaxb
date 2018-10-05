package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.AbstractInvoiceBeanBuilder;
import io.github.carlosthe19916.beans.AbstractMonedaBeanBuilder;
import io.github.carlosthe19916.beans.ClienteBean;
import io.github.carlosthe19916.beans.MonedaBean;

public class Invoice21BeanBuilder extends AbstractInvoiceBeanBuilder<Invoice21BeanBuilder, Invoice21Bean> {

    public static Invoice21BeanBuilder builder() {
        return new Invoice21BeanBuilder();
    }

    private Invoice21BeanBuilder() {
        super(new Invoice21Bean());
    }

    public Invoice21Bean build() {
        return invoice;
    }

    @Override
    public Invoice21BeanBuilder getInvoiceBuilder() {
        return this;
    }

    public Invoice21BeanBuilder total(Total21Bean total) {
        invoice.setTotal(total);
        return this;
    }

    public Invoice21BeanBuilder impuestos(Impuestos21Bean impuestos) {
        invoice.setImpuestos(impuestos);
        return this;
    }

    public Invoice21BeanBuilder proveedor(Proveedor21Bean proveedor) {
        invoice.setProveedor(proveedor);
        return getInvoiceBuilder();
    }

    public Invoice21BeanBuilder cliente(ClienteBean cliente) {
        invoice.setCliente(cliente);
        return getInvoiceBuilder();
    }

    public Moneda21BeanBuilder moneda() {
        return new Moneda21BeanBuilder();
    }

    public class Moneda21BeanBuilder extends AbstractMonedaBeanBuilder<Moneda21BeanBuilder, MonedaBean> {

        private Moneda21BeanBuilder() {
            super(new MonedaBean());
        }

        @Override
        protected Moneda21BeanBuilder getMonedaBuilder() {
            return this;
        }

        public Invoice21BeanBuilder fin() {
            invoice.setMoneda(moneda);
            return Invoice21BeanBuilder.this;
        }

    }

}
