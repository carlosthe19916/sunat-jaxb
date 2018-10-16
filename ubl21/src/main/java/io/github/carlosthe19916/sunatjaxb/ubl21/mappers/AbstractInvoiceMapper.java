package io.github.carlosthe19916.sunatjaxb.ubl21.mappers;

import io.github.carlosthe19916.sunatjaxb.beans.AbstractInvoiceBean;
import io.github.carlosthe19916.sunatjaxb.beans.FechaBean;

import java.util.Date;

public abstract class AbstractInvoiceMapper {

    protected Core21MapperDefaults defaults = GlobalCore21MapperDefaults.getInstance();

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
