package io.github.carlosthe19916.sunatjaxb.ubl21.mappers;

import io.github.carlosthe19916.sunatjaxb.beans.ClienteBean;
import io.github.carlosthe19916.sunatjaxb.beans.DetalleBean;
import io.github.carlosthe19916.sunatjaxb.beans.MonedaBean;
import io.github.carlosthe19916.sunatjaxb.beans.TotalInformacionAdicionalBean;
import io.github.carlosthe19916.sunatjaxb.beans.beans21.Impuestos21Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans21.Invoice21Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans21.Proveedor21Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans21.Total21Bean;
import io.github.carlosthe19916.sunatjaxb.mappers.core.AbstractInvoiceMapper;
import io.github.carlosthe19916.sunatjaxb.mappers.core.GlobalCore21MapperDefaults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class DefaultInvoice21Mapper extends AbstractInvoiceMapper implements Invoice21Mapper {

    private final static RoundingMode METODO_REDONDEO = RoundingMode.HALF_EVEN;

    @Override
    public Invoice21Bean map(Invoice21Bean invoice) {
        GlobalCore21MapperDefaults defaults = GlobalCore21MapperDefaults.getInstance();


        setFechaDefaults(invoice);

        if (invoice.getMoneda() == null) {
            invoice.setMoneda(new MonedaBean());
        }
        if (invoice.getTotalInformacionAdicional() == null) {
            invoice.setTotalInformacionAdicional(new TotalInformacionAdicionalBean());
        }

        setDetalleDefaults(invoice);

        if (invoice.getTipoOperacion() == null) {
            invoice.setTipoOperacion(defaults.getTipoOperacion());
        }

        setTotalDefaults(invoice);
        setImpuestosDefaults(invoice);

        if (invoice.getCliente() == null) {
            invoice.setCliente(new ClienteBean());
        }
        if (invoice.getProveedor() == null) {
            invoice.setProveedor(new Proveedor21Bean());
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

    private void setTotalDefaults(Invoice21Bean invoice) {
        if (invoice.getTotal() == null) {
            invoice.setTotal(new Total21Bean());
        }

        Total21Bean total = invoice.getTotal();

        if (total.getExtensionAmount() == null && total.getInclusiveAmount() == null) {
            BigDecimal anticipos = total.getAnticipos() != null ? total.getAnticipos() : BigDecimal.ZERO;
            BigDecimal totalPagar = total.getPagar() != null ? total.getPagar() : BigDecimal.ZERO;
            BigDecimal descuentos = total.getDescuentoGlobal() != null ? total.getDescuentoGlobal() : BigDecimal.ZERO;
            BigDecimal otrosCargos = total.getOtrosCargos() != null ? total.getOtrosCargos() : BigDecimal.ZERO;

            BigDecimal igv = defaults.getIgvDecimalValue().add(BigDecimal.ONE);

            BigDecimal extensionAmount =
                    (
                            totalPagar
                                    .subtract(otrosCargos)
                                    .subtract(descuentos)
                                    .subtract(anticipos)
                    ).divide(igv, 2, METODO_REDONDEO);

//            BigDecimal inclusiveAmount = extensionAmount.multiply(igv).setScale(2, METODO_REDONDEO);

            total.setExtensionAmount(extensionAmount);
//            total.setInclusiveAmount(inclusiveAmount);
        }
    }

    private void setImpuestosDefaults(Invoice21Bean invoice) {
        if (invoice.getImpuestos() == null) {
            invoice.setImpuestos(new Impuestos21Bean());
        }

        Impuestos21Bean impuestos = invoice.getImpuestos();
        if (impuestos.getIgv() != null && impuestos.getIgvAfecto() == null) {
            BigDecimal igv = impuestos.getIgv();
            BigDecimal taxableAmount = igv.divide(defaults.getIgvDecimalValue(), 2, METODO_REDONDEO);
            impuestos.setIgvAfecto(taxableAmount);
        }
    }

    private void setDetalleDefaults(Invoice21Bean invoice) {
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
                GlobalCore21MapperDefaults instance = GlobalCore21MapperDefaults.getInstance();
                detalle.setValorIgv(instance.getIgvDecimalValue().multiply(BigDecimal.valueOf(100)));
            }
        }
    }

}
