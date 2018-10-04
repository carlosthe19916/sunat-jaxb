package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.ubl.ubl20.Impuestos20Bean;
import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;
import io.github.carlosthe19916.beans.ubl.ubl20.Total20Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Impuestos21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;

import java.util.ArrayList;
import java.util.List;

public class InvoiceBeanBuilder {

    private final InvoiceBean invoice;

    /*
     * Get a new instance
     */
    public static InvoiceBeanBuilder InvoiceBean() {
        return new InvoiceBeanBuilder();
    }


    /**
     * Constructor
     */
    private InvoiceBeanBuilder() {
        this.invoice = new InvoiceBean();
    }


    // Specializations

    public Invoice20BeanBuilder ubl20() {
        return new Invoice20BeanBuilder();
    }

    public Invoice21BeanBuilder ubl21() {
        return new Invoice21BeanBuilder();
    }


    // Build process

    public InvoiceBeanBuilder serie(String serie) {
        invoice.setSerie(serie);
        return this;
    }

    public InvoiceBeanBuilder numero(Integer numero) {
        invoice.setNumero(numero);
        return this;
    }

    public InvoiceBeanBuilder tipoComprobante(InvoiceBean.InvoiceType tipoComprobante) {
        invoice.setTipoComprobante(tipoComprobante);
        return this;
    }

    public InvoiceBeanBuilder fecha(FechaBean fecha) {
        invoice.setFecha(fecha);
        return this;
    }

    public InvoiceBeanBuilder moneda(MonedaBean moneda) {
        invoice.setMoneda(moneda);
        return this;
    }

    public InvoiceBeanBuilder totalInformacionAdicional(TotalInformacionAdicionalBean totalInformacionAdicional) {
        invoice.setTotalInformacionAdicional(totalInformacionAdicional);
        return this;
    }

    public InvoiceBeanBuilder proveedor(ProveedorBean proveedor) {
        invoice.setProveedor(proveedor);
        return this;
    }

    public InvoiceBeanBuilder cliente(ClienteBean cliente) {
        invoice.setCliente(cliente);
        return this;
    }

    public InvoiceBeanBuilder addDetalle(DetalleBean detalle) {
        List<DetalleBean> list = invoice.getDetalle();
        if (list == null) {
            list = new ArrayList<>();
            invoice.setDetalle(list);
        }
        invoice.getDetalle().add(detalle);
        return this;
    }


    // UBL20

    public class Invoice20BeanBuilder {
        private final Invoice20Bean invoice20;

        private Invoice20BeanBuilder() {
            invoice20 = new Invoice20Bean(invoice);
        }

        public Invoice20Bean build() {
            return invoice20;
        }

        public Invoice20BeanBuilder observaciones(String observaciones) {
            invoice20.setObservaciones(observaciones);
            return this;
        }

        public Invoice20BeanBuilder total(Total20Bean total) {
            invoice20.setTotal(total);
            return this;
        }

        public Invoice20BeanBuilder impuestos(Impuestos20Bean impuestos) {
            invoice20.setImpuestos(impuestos);
            return this;
        }
    }


    // UBL21

    public class Invoice21BeanBuilder {

        private final Invoice21Bean invoice21;

        private Invoice21BeanBuilder() {
            invoice21 = new Invoice21Bean(invoice);
        }

        public Invoice21Bean build() {
            return invoice21;
        }

        public Invoice21BeanBuilder total(Total21Bean total) {
            invoice21.setTotal(total);
            return this;
        }

        public Invoice21BeanBuilder impuestos(Impuestos21Bean impuestos) {
            invoice21.setImpuestos(impuestos);
            return this;
        }

    }
}
