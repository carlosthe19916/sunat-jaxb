[![Build Status](https://travis-ci.org/carlosthe19916/sunat-jaxb.svg?branch=master)](https://travis-ci.org/w4tracking/w4tracking)

[![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=carlosthe19916&metric=alert_status)](https://sonarcloud.io/dashboard?id=carlosthe19916)

# sunat-jaxb
Libreria que permite la creación de comprobantes de pago electrónicos de acuerdo al estadar UBL 2.

## Crear Bean

```
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
```


Ahora que ya tienes el bean, conviértelo:

```
InvoiceType invoiceType = BeanToType.toInvoiceType(InvoiceBean bean, TimeZone timeZone);
```
