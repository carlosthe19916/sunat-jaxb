package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.beans.ubl.ubl20.*;

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

    public Total21BeanBuilder total() {
        return new Total21BeanBuilder();
    }

    public Invoice21BeanBuilder impuestos(Impuestos21Bean impuestos) {
        invoice.setImpuestos(impuestos);
        return this;
    }

    public Proveedor21BeanBuilder proveedor() {
        return new Proveedor21BeanBuilder();
    }

    public Cliente21BeanBuilder cliente() {
        return new Cliente21BeanBuilder();
    }

    public Moneda21BeanBuilder moneda() {
        return new Moneda21BeanBuilder();
    }

    public class Total21BeanBuilder extends AbstractTotal21BeanBuilder<Total21BeanBuilder, Total21Bean> {
        private Total21BeanBuilder() {
            super(new Total21Bean());
        }

        @Override
        protected Total21BeanBuilder getTotalBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setTotal(total);
            return Invoice21BeanBuilder.this;
        }

    }

    public class Proveedor21BeanBuilder extends AbstractProveedor21BeanBuilder<Proveedor21BeanBuilder, Proveedor21Bean> {
        private Proveedor21BeanBuilder() {
            super(new Proveedor21Bean());
        }

        @Override
        protected Proveedor21BeanBuilder getProveedorBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setProveedor(proveedor);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Cliente21BeanBuilder extends AbstractClienteBeanBuilder<Cliente21BeanBuilder, ClienteBean> {
        private Cliente21BeanBuilder() {
            super(new ClienteBean());
        }

        @Override
        protected Cliente21BeanBuilder getClienteBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setCliente(cliente);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Moneda21BeanBuilder extends AbstractMonedaBeanBuilder<Moneda21BeanBuilder, MonedaBean> {

        private Moneda21BeanBuilder() {
            super(new MonedaBean());
        }

        @Override
        protected Moneda21BeanBuilder getMonedaBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setMoneda(moneda);
            return Invoice21BeanBuilder.this;
        }

    }

}
