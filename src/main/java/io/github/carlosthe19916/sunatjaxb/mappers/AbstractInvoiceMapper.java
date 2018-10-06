package io.github.carlosthe19916.sunatjaxb.mappers;

import io.github.carlosthe19916.sunatjaxb.beans.AbstractInvoiceBean;
import io.github.carlosthe19916.sunatjaxb.beans.FechaBean;
import io.github.carlosthe19916.sunatjaxb.config.GlobalUBL21Defaults;
import io.github.carlosthe19916.sunatjaxb.config.UBL21Defaults;

import java.util.Date;

public abstract class AbstractInvoiceMapper {

    protected UBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

    protected void setFechaDefaults(AbstractInvoiceBean invoice) {
        if (invoice.getFecha() == null) {
            invoice.setFecha(new FechaBean());
        }

        FechaBean fecha = invoice.getFecha();
        if (fecha.getFechaEmision() == null) {
            fecha.setFechaEmision(new Date());
        }
        if (fecha.getTimeZone() == null) {
            fecha.setTimeZone(defaults.getTimeZone());
        }
    }

}
