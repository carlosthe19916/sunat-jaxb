[![Build Status](https://travis-ci.org/carlosthe19916/sunat-jaxb.svg?branch=master)](https://travis-ci.org/carlosthe19916/sunat-jaxb)
[![Coverage Status](https://coveralls.io/repos/github/carlosthe19916/sunat-jaxb/badge.svg?branch=master)](https://coveralls.io/github/carlosthe19916/sunat-jaxb?branch=master)
[![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=sunat-jaxb&metric=alert_status)](https://sonarcloud.io/dashboard?id=sunat-jaxb)

# Project OpenUBL
**Este proyecto sentó las bases para el proyecto https://github.com/project-openubl/xml-builder. Este repositorio será, de hoy en adelante, de solo lectura. Vease xml-builder como la segunda generacion de creador de XMLs para la facturacion electronica. https://github.com/project-openubl/xml-builder**

# sunat-jaxb
Libreria que permite la creación de comprobantes de pago electrónicos de acuerdo al estadar UBL 2.

## Crear Bean

```
InvoiceBean invoiceBean = InvoiceBeanBuilder.InvoiceBean()
                .serie("F001")
                .numero(1)
                .tipoDocumento("01")
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
                .addDetalle(
                        DetalleBeanBuilder.Detalle()
                                .cantidad(BigDecimal.ONE)
                                .precioUnitario(BigDecimal.TEN)
                                .totalIgv(new BigDecimal("1.8"))
                                .codigoTipoIgv(TipoAfectacionIgv.GRAVADO_OPERACION_ONEROSA.getCodigo())
                                .descripcion("Lapiceros de color azul")
                                .unidadMedida("NIU")
                        .build()
                )
                .build();
```


Ahora que ya tienes el bean, conviértelo:

```
InvoiceType invoiceType = BeanToType.toInvoiceType(invoiceBean, TimeZone.getDefault());
```

Puedes crear tambien un xml:
```
oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
JAXBElement<InvoiceType> jaxbElement = factory.createInvoice(invoiceType);
Document xmlDocument = JaxbUtils.toDocument(InvoiceType.class, jaxbElement);
```

Puedes crear el archivo:
```
Transformer transformer = TransformerFactory.newInstance().newTransformer();
Result output = new StreamResult(new File("output.xml"));
Source input = new DOMSource(xmlDocument);
transformer.transform(input, output);
```
