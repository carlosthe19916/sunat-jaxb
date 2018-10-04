package io.github.carlosthe19916.beans.fillouts;

import io.github.carlosthe19916.beans.FechaBean;
import io.github.carlosthe19916.beans.InvoiceBean;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.config.ubl21.UBL21Defaults;

public abstract class AbstractInvoiceFillOut {

    protected UBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

    protected void setTimeZoneDefaults(InvoiceBean invoice) {
        if (invoice.getFecha() == null) {
            invoice.setFecha(new FechaBean());
        }

        if (invoice.getFecha().getTimeZone() == null) {
            invoice.getFecha().setTimeZone(defaults.getTimeZone());
        }
    }

}
