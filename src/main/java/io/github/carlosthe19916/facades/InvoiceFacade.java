package io.github.carlosthe19916.facades;

import io.github.carlosthe19916.beans.config.ubl20.GlobalUBL20Defaults;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.exceptions.Invoice20BeanValidacionException;
import io.github.carlosthe19916.beans.exceptions.Invoice21BeanValidacionException;
import io.github.carlosthe19916.beans.fillouts.FillOutManager;
import io.github.carlosthe19916.beans.fillouts.Invoice20FillOut;
import io.github.carlosthe19916.beans.fillouts.Invoice21FillOut;
import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.types.BeanToType20;
import io.github.carlosthe19916.types.BeanToType21;

import java.util.List;

public class InvoiceFacade {

    private InvoiceFacade() {
        // Just static methods
    }

    public static Invoice20Bean fillOut(Invoice20Bean invoice, Invoice20FillOut... fillOuts) {
        GlobalUBL20Defaults defaults = GlobalUBL20Defaults.getInstance();

        for (Invoice20FillOut fillOut : fillOuts) {
            invoice = fillOut.fillIn(invoice);
        }

        if (defaults.getAplicarCalculosInternosAutomaticos()) {
            List<Invoice20FillOut> invoice20FillOuts = FillOutManager.getInstance().getInvoice20FillOuts();
            for (Invoice20FillOut fillOut : invoice20FillOuts) {
                invoice = fillOut.fillIn(invoice);
            }
        }

        return invoice;
    }

    public static Invoice21Bean fillOut(Invoice21Bean invoice, Invoice21FillOut... fillOuts) {
        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

        for (Invoice21FillOut fillOut : fillOuts) {
            invoice = fillOut.fillIn(invoice);
        }

        if (defaults.getAplicarCalculosInternosAutomaticos()) {
            List<Invoice21FillOut> invoice21FillOuts = FillOutManager.getInstance().getInvoice21FillOuts();
            for (Invoice21FillOut fillOut : invoice21FillOuts) {
                invoice = fillOut.fillIn(invoice);
            }
        }

        return invoice;
    }

    public static oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType toInvoiceType(Invoice20Bean invoice) throws Invoice20BeanValidacionException {
        return BeanToType20.toInvoiceType(invoice);
    }

    public static oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType toInvoiceType(Invoice21Bean invoice) throws Invoice21BeanValidacionException {
        return BeanToType21.toInvoiceType(invoice);
    }

}
