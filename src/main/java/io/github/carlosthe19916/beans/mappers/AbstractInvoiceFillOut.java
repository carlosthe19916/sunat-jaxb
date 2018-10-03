package io.github.carlosthe19916.beans.mappers;

import io.github.carlosthe19916.beans.FechaBean;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.config.ubl21.UBL21Defaults;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;

public abstract class AbstractInvoiceFillOut {

    protected UBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

    protected void setTimeZoneDefaults(Invoice21Bean invoice) {
        if (invoice.getFecha() == null) {
            invoice.setFecha(new FechaBean());
        }

        if (invoice.getFecha().getTimeZone() == null) {
            invoice.getFecha().setTimeZone(defaults.getTimeZone());
        }
    }

}
