package io.github.carlosthe19916.beans.fillouts;

import io.github.carlosthe19916.beans.FechaBean;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.config.ubl21.UBL21Defaults;
import io.github.carlosthe19916.beans.AbstractNoteBean;

import java.util.Date;

public abstract class AbstractCreditNoteFillOut {

    protected UBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

    protected void setFechaDefaults(AbstractNoteBean noteBean) {
        if (noteBean.getFecha() == null) {
            noteBean.setFecha(new FechaBean());
        }

        if (noteBean.getFecha().getFechaEmision() == null) {
            noteBean.getFecha().setFechaEmision(new Date());
        }

        if (noteBean.getFecha().getTimeZone() == null) {
            noteBean.getFecha().setTimeZone(defaults.getTimeZone());
        }
    }

}
