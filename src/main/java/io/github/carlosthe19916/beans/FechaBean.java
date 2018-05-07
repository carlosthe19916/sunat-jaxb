package io.github.carlosthe19916.beans;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class FechaBean {

    @NotNull
    private Date fechaEmision;
    private Date fechaVencimiento;

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
}
