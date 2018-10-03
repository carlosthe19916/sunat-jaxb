package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.ImpuestosBean;
import io.github.carlosthe19916.beans.InvoiceBean;
import io.github.carlosthe19916.beans.MonedaBean;
import io.github.carlosthe19916.beans.TotalBean;
import io.github.carlosthe19916.beans.ubl.ubl20.Total20Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Invoice21Bean extends InvoiceBean {

    @NotNull
    private String tipoOperacion;

    @Valid
    @NotNull
    private Total21Bean total;

    @Valid
    @NotNull
    private Impuestos21Bean impuestos;

    public Invoice21Bean(InvoiceBean invoice) {
        super(invoice);
    }

    // Overrides

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Total21Bean getTotal() {
        return total;
    }

    public void setTotal(Total21Bean total) {
        this.total = total;
    }

    public Impuestos21Bean getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Impuestos21Bean impuestos) {
        this.impuestos = impuestos;
    }
}
