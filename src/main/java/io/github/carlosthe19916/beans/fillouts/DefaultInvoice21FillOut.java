package io.github.carlosthe19916.beans.fillouts;

import io.github.carlosthe19916.beans.catalogs.Catalogo17;
import io.github.carlosthe19916.beans.ubl.ubl21.Impuestos21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DefaultInvoice21FillOut extends AbstractInvoiceFillOut implements Invoice21FillOut {

    private final static RoundingMode METODO_REDONDEO = RoundingMode.UP;

    @Override
    public Invoice21Bean fillIn(Invoice21Bean invoice21Bean) {
        if (invoice21Bean.getTipoOperacion() == null) {
            invoice21Bean.setTipoOperacion(Catalogo17.VENTA_INTERNA);
        }

        setFechaDefaults(invoice21Bean);

        setTotalDefaults(invoice21Bean);
        setImpuestosDefaults(invoice21Bean);

        return invoice21Bean;
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

//        if ((
//                total.getPagar() != null && total.getOtrosCargos() != null && total.getDescuentoGlobal() != null
//        ) && (
//                total.getExtensionAmount() == null && total.getInclusiveAmount() == null
//        )) {
//            BigDecimal anticipos = total.getAnticipos() != null ? total.getAnticipos() : BigDecimal.ZERO;
//            BigDecimal totalPagar = total.getPagar();
//            BigDecimal descuentos = total.getDescuentoGlobal();
//            BigDecimal otrosCargos = total.getOtrosCargos();
//
//            BigDecimal igv = defaults.getIgvValue().add(BigDecimal.ONE);
//
//            BigDecimal extensionAmount =
//                    (
//                            totalPagar
//                                    .subtract(otrosCargos)
//                                    .subtract(descuentos)
//                                    .subtract(anticipos)
//                    ).divide(igv, METODO_REDONDEO);
//
//            BigDecimal inclusiveAmount = extensionAmount.multiply(igv);
//
//            total.setExtensionAmount(extensionAmount);
//            total.setInclusiveAmount(inclusiveAmount);
//        }
    }

    private void setImpuestosDefaults(Invoice21Bean invoice) {
        if (invoice.getImpuestos() == null) {
            invoice.setImpuestos(new Impuestos21Bean());
        }

        Impuestos21Bean impuestos = invoice.getImpuestos();

        if (impuestos.getIgv() != null) {
            BigDecimal igv = impuestos.getIgv();

            //: [Total valor de venta operaciones gravadas] + [Sumatoria ISC].
            BigDecimal taxableAmount = igv.divide(defaults.getIgvValue(), METODO_REDONDEO);
            impuestos.setIgvAfecto(taxableAmount);
        }
    }
}
