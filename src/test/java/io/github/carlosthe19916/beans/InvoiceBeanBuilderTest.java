package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;

public class InvoiceBeanBuilderTest {

    @Test
    public void testBuilder() {
        InvoiceBean invoiceBean = InvoiceBeanBuilder.InvoiceBean()
                .serie("F001")
                .numero(1)
                .codigoTipoComprobante("01")
                .observaciones("Sin observaciones")
                .fecha(
                        FechaBeanBuilder.FechaBean()
                                .fechaEmision(Calendar.getInstance().getTime())
                                .fechaVencimiento(Calendar.getInstance().getTime())
                                .build()
                )
                .moneda(
                        MonedaBeanBuilder.Moneda()
                                .codigo("PEN")
                                .tipoCambio(new BigDecimal("3.21"))
                                .build()
                )
                .impuestos(
                        ImpuestosBeanBuilder.Impuestos()
                                .igv(new BigDecimal("10"))
                                .isc(new BigDecimal("1"))
                                .build()
                )
                .total(
                        TotalBeanBuilder.Total()
                                .pagar(new BigDecimal("5"))
                                .descuentoGlobal(new BigDecimal("6"))
                                .otrosCargos(new BigDecimal("5"))
                                .build()
                )
                .totalInformacionAdicional(
                        TotalInformacionAdicionalBeanBuilder.TotalInformacionAdicionalBean()
                                .gravado(BigDecimal.ZERO)
                                .inafecto(BigDecimal.ZERO)
                                .exonerado(BigDecimal.ZERO)
                                .gratuito(BigDecimal.ZERO)
                                .build()
                )
                .proveedor(
                        ProveedorBeanBuilder.ProveedorBean()
                                .codigoTipoDocumento("6")
                                .numeroDocumento("10467793549")
                                .nombreComercial("Wolsnut4 S.A.")
                                .razonSocial("Wolsnut4 Consultores")
                                .codigoPostal("050101")
                                .direccion("Jr. ayacucho 123")
                                .region("Ayacucho")
                                .provincia("Huamanga")
                                .distrito("Jesus Nazareno")
                                .codigoPais("PE")
                                .build()
                )
                .cliente(
                        ClienteBeanBuilder.ClienteBean()
                                .codigoTipoDocumento("3")
                                .numeroDocumento("46779354")
                                .nombre("Carlos Esteban Feria Vila")
                                .email("carlosthe19916@gmail.com")
                                .direccion("Jr. carlos 997")
                                .build()
                )
                .build();

        Assert.assertNotNull(invoiceBean);
        Assert.assertEquals(invoiceBean.getSerie(), "F001");
        Assert.assertEquals(invoiceBean.getNumero(), Integer.valueOf(1));
        Assert.assertEquals(invoiceBean.getCodigoTipoComprobante(), "01");
        Assert.assertEquals(invoiceBean.getObservaciones(), "Sin observaciones");
        Assert.assertNotNull(invoiceBean.getFecha());
        Assert.assertNotNull(invoiceBean.getMoneda());
        Assert.assertNotNull(invoiceBean.getImpuestos());
        Assert.assertNotNull(invoiceBean.getTotal());
        Assert.assertNotNull(invoiceBean.getTotalInformacionAdicional());
        Assert.assertNotNull(invoiceBean.getProveedor());
        Assert.assertNotNull(invoiceBean.getCliente());
    }
}
