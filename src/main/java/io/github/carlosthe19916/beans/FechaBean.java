package io.github.carlosthe19916.beans;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.TimeZone;

public class FechaBean {

    @NotNull
    private Date fechaEmision;

    private Date fechaVencimiento;

    @NotNull
    private TimeZone timeZone;

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}
