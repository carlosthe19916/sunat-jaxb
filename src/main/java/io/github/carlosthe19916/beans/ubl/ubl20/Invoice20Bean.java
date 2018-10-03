package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Invoice20Bean extends InvoiceBean {

    private String observaciones;

    @Valid
    @NotNull
    private Total20Bean total;

    @Valid
    @NotNull
    private Impuestos20Bean impuestos;

    public Invoice20Bean() {
    }

    public Invoice20Bean(InvoiceBean invoice) {
        super(invoice);
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Total20Bean getTotal() {
        return total;
    }

    public void setTotal(Total20Bean total) {
        this.total = total;
    }

    public Impuestos20Bean getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Impuestos20Bean impuestos) {
        this.impuestos = impuestos;
    }
}
