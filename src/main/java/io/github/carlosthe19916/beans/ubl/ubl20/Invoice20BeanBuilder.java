package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21BeanBuilder;
import io.github.carlosthe19916.beans.ubl.ubl21.Proveedor21Bean;

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

    public Total20BeanBuilder total() {
        return new Total20BeanBuilder();
    }

    public Invoice20BeanBuilder impuestos(Impuestos20Bean impuestos) {
        invoice.setImpuestos(impuestos);
        return this;
    }

    public Proveedor20BeanBuilder proveedor() {
        return new Proveedor20BeanBuilder();
    }

    public Cliente20BeanBuilder cliente() {
        return new Cliente20BeanBuilder();
    }

    public Moneda20BeanBuilder moneda() {
        return new Moneda20BeanBuilder();
    }

    public class Total20BeanBuilder extends AbstractTotal20BeanBuilder<Total20BeanBuilder, Total20Bean> {
        private Total20BeanBuilder() {
            super(new Total20Bean());
        }

        @Override
        protected Total20BeanBuilder getTotalBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setTotal(total);
            return Invoice20BeanBuilder.this;
        }

    }

    public class Proveedor20BeanBuilder extends AbstractProveedor20BeanBuilder<Proveedor20BeanBuilder, Proveedor20Bean> {
        private Proveedor20BeanBuilder() {
            super(new Proveedor20Bean());
        }

        @Override
        protected Proveedor20BeanBuilder getProveedorBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setProveedor(proveedor);
            return Invoice20BeanBuilder.this;
        }
    }

    public class Cliente20BeanBuilder extends AbstractClienteBeanBuilder<Cliente20BeanBuilder, ClienteBean> {
        private Cliente20BeanBuilder() {
            super(new ClienteBean());
        }

        @Override
        protected Cliente20BeanBuilder getClienteBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setCliente(cliente);
            return Invoice20BeanBuilder.this;
        }
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
