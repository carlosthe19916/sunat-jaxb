package io.github.carlosthe19916;

import io.github.carlosthe19916.beans.InvoiceBean;
import io.github.carlosthe19916.beans.InvoiceBeanBuilder;
import io.github.carlosthe19916.beans.ProveedorBeanBuilder;

public class HS {

    public static void main(String[] args) {
        InvoiceBean invoiceBean = InvoiceBeanBuilder.InvoiceBean()
                .serie("F001")
                .numero(1)
                .proveedor(
                        ProveedorBeanBuilder.ProveedorBean()
                                .codigoPostal("")
                                .ubl20()
                                    .codigoPais("")
                                    .build().
                )
                .ubl20()
                    .observaciones("")
    }
}
