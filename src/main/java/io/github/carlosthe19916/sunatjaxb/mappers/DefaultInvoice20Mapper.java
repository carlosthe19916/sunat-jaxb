package io.github.carlosthe19916.sunatjaxb.mappers;

import io.github.carlosthe19916.sunatjaxb.beans.ClienteBean;
import io.github.carlosthe19916.sunatjaxb.beans.DetalleBean;
import io.github.carlosthe19916.sunatjaxb.beans.MonedaBean;
import io.github.carlosthe19916.sunatjaxb.beans.TotalInformacionAdicionalBean;
import io.github.carlosthe19916.sunatjaxb.beans.beans20.Impuestos20Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans20.Invoice20Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans20.Proveedor20Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans20.Total20Bean;
import io.github.carlosthe19916.sunatjaxb.mappers.Invoice20Mapper;
import io.github.carlosthe19916.sunatjaxb.mappers.core.AbstractInvoiceMapper;
import io.github.carlosthe19916.sunatjaxb.mappers.core.GlobalCore20MapperDefaults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class DefaultInvoice20Mapper extends AbstractInvoiceMapper implements Invoice20Mapper {

    private final static RoundingMode METODO_REDONDEO = RoundingMode.HALF_EVEN;

    @Override
    public Invoice20Bean map(Invoice20Bean invoice) {
        GlobalCore20MapperDefaults defaults = GlobalCore20MapperDefaults.getInstance();


        setFechaDefaults(invoice);

        if (invoice.getMoneda() == null) {
            invoice.setMoneda(new MonedaBean());
        }
        if (invoice.getTotalInformacionAdicional() == null) {
            invoice.setTotalInformacionAdicional(new TotalInformacionAdicionalBean());
        }

        setDetalleDefaults(invoice);
        setTotalDefaults(invoice);
        setImpuestosDefaults(invoice);

        if (invoice.getCliente() == null) {
            invoice.setCliente(new ClienteBean());
        }
        if (invoice.getProveedor() == null) {
            invoice.setProveedor(new Proveedor20Bean());
        }

        return invoice;
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public int order() {
        return 0;
    }

    private void setTotalDefaults(Invoice20Bean invoice) {
        if (invoice.getTotal() == null) {
            invoice.setTotal(new Total20Bean());
        }
    }

    private void setImpuestosDefaults(Invoice20Bean invoice) {
        if (invoice.getImpuestos() == null) {
            invoice.setImpuestos(new Impuestos20Bean());
        }
    }

    private void setDetalleDefaults(Invoice20Bean invoice) {
        if (invoice.getDetalle() == null) {
            invoice.setDetalle(new ArrayList<>());
        }

        for (DetalleBean detalle : invoice.getDetalle()) {
            if (detalle.getTotalIgv() != null && detalle.getTotalIgvAfectado() == null) {
                BigDecimal igv = detalle.getTotalIgv();
                BigDecimal taxableAmount = igv.divide(defaults.getIgvDecimalValue(), 2, METODO_REDONDEO);
                detalle.setTotalIgvAfectado(taxableAmount);
            }

            if (detalle.getValorIgv() == null) {
                GlobalCore20MapperDefaults instance = GlobalCore20MapperDefaults.getInstance();
                detalle.setValorIgv(instance.getIgvDecimalValue().multiply(BigDecimal.valueOf(100)));
            }
        }
    }

}
