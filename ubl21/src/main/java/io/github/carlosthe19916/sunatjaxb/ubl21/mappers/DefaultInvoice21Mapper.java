package io.github.carlosthe19916.sunatjaxb.ubl21.mappers;

import io.github.carlosthe19916.sunatjaxb.core.beans.*;
import io.github.carlosthe19916.sunatjaxb.ubl21.UBL21GlobalConfig;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.Impuestos21Bean;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.Invoice21Bean;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.Proveedor21Bean;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.Total21Bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

public class DefaultInvoice21Mapper implements Invoice21Mapper {

    private final static RoundingMode METODO_REDONDEO = RoundingMode.HALF_EVEN;

    private UBL21GlobalConfig config = UBL21GlobalConfig.getInstance();

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public Invoice21Bean map(Invoice21Bean invoice) {
        if (invoice.getMoneda() == null) {
            invoice.setMoneda(new MonedaBean());
        }
        if (invoice.getTotalInformacionAdicional() == null) {
            invoice.setTotalInformacionAdicional(new TotalInformacionAdicionalBean());
        }
        if (invoice.getCliente() == null) {
            invoice.setCliente(new ClienteBean());
        }
        if (invoice.getProveedor() == null) {
            invoice.setProveedor(new Proveedor21Bean());
        }

        if (invoice.getTipoOperacion() == null) {
            invoice.setTipoOperacion(config.getInvoiceTipoOperacion());
        }

        setFechaDefaults(invoice);
        setTotalDefaults(invoice);
        setImpuestosDefaults(invoice);
        setDetalleDefaults(invoice);

        return invoice;
    }

    protected void setFechaDefaults(AbstractInvoiceBean invoice) {
        if (invoice.getFecha() == null) {
            invoice.setFecha(new FechaBean());
        }

        FechaBean fecha = invoice.getFecha();
        if (fecha.getFechaEmision() == null) {
            fecha.setFechaEmision(new Date());
        }
        if (fecha.getTimeZone() == null) {
            fecha.setTimeZone(config.getTimeZone());
        }
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

            BigDecimal igv = config.getIgvDecimalValue().add(BigDecimal.ONE);

            BigDecimal extensionAmount =
                    (
                            totalPagar
                                    .subtract(otrosCargos)
                                    .subtract(descuentos)
                                    .subtract(anticipos)
                    ).divide(igv, 2, METODO_REDONDEO);

            total.setExtensionAmount(extensionAmount);
        }
    }

    private void setImpuestosDefaults(Invoice21Bean invoice) {
        if (invoice.getImpuestos() == null) {
            invoice.setImpuestos(new Impuestos21Bean());
        }

        Impuestos21Bean impuestos = invoice.getImpuestos();
        if (impuestos.getIgv() != null && impuestos.getIgvAfecto() == null) {
            BigDecimal igv = impuestos.getIgv();
            BigDecimal taxableAmount = igv.divide(config.getIgvDecimalValue(), 2, METODO_REDONDEO);
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
                BigDecimal taxableAmount = igv.divide(config.getIgvDecimalValue(), 2, METODO_REDONDEO);
                detalle.setTotalIgvAfectado(taxableAmount);
            }

            if (detalle.getValorIgv() == null) {
                detalle.setValorIgv(config.getIgvDecimalValue().multiply(BigDecimal.valueOf(100)));
            }
        }
    }

}
