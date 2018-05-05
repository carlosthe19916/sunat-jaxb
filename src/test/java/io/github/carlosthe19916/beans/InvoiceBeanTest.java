package io.github.carlosthe19916.beans;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;

public class InvoiceBeanTest {

    @Test
    public void testBuilder() {
        InvoiceBean.InvoiceBuilder builder = new InvoiceBean.InvoiceBuilder();
        builder
                .serie("F001")
                .numero(1)
                .codigoTipoComprobante("01")

                .fechaEmision(Calendar.getInstance().getTime())
                .fechaVencimiento(Calendar.getInstance().getTime())

                .moneda("PEN")
                .tipoCambio(BigDecimal.ONE)

                .totalIgv(BigDecimal.ONE)
                .totalIsc(BigDecimal.ZERO)

                .totalPagar(BigDecimal.TEN)
                .totalDescuentoGlobal(BigDecimal.ONE)
                .totalOtrosCargos(BigDecimal.ZERO)

                .totalGravado(BigDecimal.ZERO)
                .totalInafecto(BigDecimal.ONE)
                .totalExonerado(BigDecimal.TEN)
                .totalGratuito(BigDecimal.ONE)

                .observaciones("Sin observaciones")

                .cliente()
                .codigoTipoDocumento("6")
                .numeroDocumento("46779354")
                .nombre("Carlos Esteban Feria Vila")
                .email("carlosthe19916@gmail.com")
                .direccion("Jr. carlos 997")
                .end();

        builder.cliente().end().codigoTipoComprobante()


    }
}
