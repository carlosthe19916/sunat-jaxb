package io.github.carlosthe19916.beans;

import java.util.Date;

public class FechaBeanBuilder {

    private final FechaBean fecha;

    public FechaBeanBuilder() {
        this.fecha = new FechaBean();
    }

    public static FechaBeanBuilder FechaBean() {
        return new FechaBeanBuilder();
    }

    public FechaBeanBuilder fechaEmision(Date fechaEmision) {
        fecha.setFechaEmision(fechaEmision);
        return this;
    }

    public FechaBeanBuilder fechaVencimiento(Date fechaVencimiento) {
        fecha.setFechaVencimiento(fechaVencimiento);
        return this;
    }

    public FechaBean build() {
        return fecha;
    }

}
