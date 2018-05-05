package io.github.carlosthe19916.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InvoiceBuilder {

    private final InvoiceBean invoice;

    public InvoiceBuilder() {
        this.invoice = new InvoiceBean();
    }

    public static InvoiceBuilder InvoiceBean() {
        return new InvoiceBuilder();
    }

    public ClienteBeanBuilder cliente() {
        ClienteBeanBuilder builder = new ClienteBeanBuilder(this);
        getInvoice().setCliente(builder.getCliente());
    }

    public InvoiceBean getInvoice() {
        return invoice;
    }

}
