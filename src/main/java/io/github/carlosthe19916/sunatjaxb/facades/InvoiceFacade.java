package io.github.carlosthe19916.sunatjaxb.facades;

import io.github.carlosthe19916.sunatjaxb.beans.beans20.Invoice20Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans21.Invoice21Bean;
import io.github.carlosthe19916.sunatjaxb.config.GlobalUBL20Defaults;
import io.github.carlosthe19916.sunatjaxb.config.GlobalUBL21Defaults;
import io.github.carlosthe19916.sunatjaxb.exceptions.Invoice20BeanValidacionException;
import io.github.carlosthe19916.sunatjaxb.exceptions.Invoice21BeanValidacionException;
import io.github.carlosthe19916.sunatjaxb.mappers.MapperManager;
import io.github.carlosthe19916.sunatjaxb.mappers.Invoice20Mapper;
import io.github.carlosthe19916.sunatjaxb.mappers.Invoice21Mapper;
import io.github.carlosthe19916.sunatjaxb.types.Bean20ToType;
import io.github.carlosthe19916.sunatjaxb.types.Bean21ToType;

import java.util.List;

public class InvoiceFacade {

    private InvoiceFacade() {
        // Just static methods
    }

    public static Invoice20Bean fillOut(Invoice20Bean invoice, Invoice20Mapper... fillOuts) {
        GlobalUBL20Defaults defaults = GlobalUBL20Defaults.getInstance();

        for (Invoice20Mapper fillOut : fillOuts) {
            invoice = fillOut.map(invoice);
        }

        if (defaults.isInternalMappersApplied()) {
            List<Invoice20Mapper> invoice20FillOuts = MapperManager.getInstance().getInvoice20Mappers();
            for (Invoice20Mapper fillOut : invoice20FillOuts) {
                invoice = fillOut.map(invoice);
            }
        }

        return invoice;
    }

    public static Invoice21Bean fillOut(Invoice21Bean invoice, Invoice21Mapper... fillOuts) {
        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

        for (Invoice21Mapper fillOut : fillOuts) {
            invoice = fillOut.map(invoice);
        }

        if (defaults.isInternalMappersApplied()) {
            List<Invoice21Mapper> invoice21FillOuts = MapperManager.getInstance().getInvoice21Mappers();
            for (Invoice21Mapper fillOut : invoice21FillOuts) {
                invoice = fillOut.map(invoice);
            }
        }

        return invoice;
    }

    public static oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType toInvoiceType(Invoice20Bean invoice) throws Invoice20BeanValidacionException {
        return Bean20ToType.toInvoiceType(invoice);
    }

    public static oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType toInvoiceType(Invoice21Bean invoice) throws Invoice21BeanValidacionException {
        return Bean21ToType.toInvoiceType(invoice);
    }

}
