package io.github.carlosthe19916.beans.fillouts;

import io.github.carlosthe19916.beans.DetalleBean;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.ubl.ubl21.CreditNote21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Impuestos21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class DefaultCreditNote21FillOut extends AbstractCreditNoteFillOut implements CreditNote21FillOut {

    private final static RoundingMode METODO_REDONDEO = RoundingMode.HALF_EVEN;


    @Override
    public CreditNote21Bean fillIn(CreditNote21Bean creditNote21Bean) {

        setFechaDefaults(creditNote21Bean);

        setTotalDefaults(creditNote21Bean);
        setImpuestosDefaults(creditNote21Bean);

        return creditNote21Bean;
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public int order() {
        return 0;
    }

    private void setTotalDefaults(CreditNote21Bean note21Bean) {
        if (note21Bean.getTotal() == null) {
            note21Bean.setTotal(new Total21Bean());
        }
        Total21Bean total = note21Bean.getTotal();
        if (total.getExtensionAmount() == null && total.getInclusiveAmount() == null) {
            BigDecimal anticipos = total.getAnticipos() != null ? total.getAnticipos() : BigDecimal.ZERO;
            BigDecimal totalPagar = total.getPagar() != null ? total.getPagar() : BigDecimal.ZERO;
            BigDecimal descuentos = total.getDescuentoGlobal() != null ? total.getDescuentoGlobal() : BigDecimal.ZERO;
            BigDecimal otrosCargos = total.getOtrosCargos() != null ? total.getOtrosCargos() : BigDecimal.ZERO;

            BigDecimal igv = defaults.getIgvValue().add(BigDecimal.ONE);

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

    private void setImpuestosDefaults(CreditNote21Bean note21Bean) {
        if (note21Bean.getImpuestos() == null) {
            note21Bean.setImpuestos(new Impuestos21Bean());
        }

        Impuestos21Bean impuestos = note21Bean.getImpuestos();

        if (impuestos.getIgv() != null && impuestos.getIgvAfecto() == null) {
            BigDecimal igv = impuestos.getIgv();

            //: [Total valor de venta operaciones gravadas] + [Sumatoria ISC].
            BigDecimal taxableAmount = igv.divide(defaults.getIgvValue(), 2, METODO_REDONDEO);
            impuestos.setIgvAfecto(taxableAmount);
        }

        // Detalle

        if (note21Bean.getDetalle() == null) {
            note21Bean.setDetalle(new ArrayList<>());
        }

        for (DetalleBean detalle : note21Bean.getDetalle()) {
            if (detalle.getTotalIgv() != null && detalle.getTotalIgvAfectado() == null) {
                BigDecimal igv = detalle.getTotalIgv();
                BigDecimal taxableAmount = igv.divide(defaults.getIgvValue(), 2, METODO_REDONDEO);
                detalle.setTotalIgvAfectado(taxableAmount);
            }

            if (detalle.getValorIgv() == null) {
                GlobalUBL21Defaults instance = GlobalUBL21Defaults.getInstance();
                detalle.setValorIgv(instance.getIgvValue().multiply(BigDecimal.valueOf(100)));
            }
        }
    }

}
