package io.github.carlosthe19916.sunatjaxb.facades;

import io.github.carlosthe19916.sunatjaxb.config.GlobalUBL21Defaults;
import org.junit.Before;

import java.util.Date;
import java.util.TimeZone;

public class InvoiceFacadeTest {

    TimeZone defaultTimeZone = TimeZone.getTimeZone("Europe/Rome");
    Date defaultFechaEmision = new Date();
    Date defaultFechaVencimiento = new Date();

    @Before
    public void before() {
        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();
        defaults.setTimeZone(defaultTimeZone);
        defaults.setInternalMappersApplied(true);
    }

//    @Test
//    public void fillOut() {
//        Invoice21Bean invoice1 = AbstractInvoiceBeanBuilder.InvoiceBean().
//                fecha(
//                        FechaBeanBuilder.FechaBean()
//                                .fechaEmision(defaultFechaEmision)
//                                .fechaVencimiento(defaultFechaVencimiento)
//                                .build()
//                )
//                .ubl21()
//                .build();
//
//        Invoice21Bean invoice2 = InvoiceFacade.fillOut(invoice1);
//
//        Assert.assertNotNull(invoice2.getFecha());
//        Assert.assertNotNull(invoice2.getTotal());
//        Assert.assertNotNull(invoice2.getImpuestos());
//
//        Assert.assertEquals(defaultTimeZone, invoice2.getFecha().getTimeZone());
//        Assert.assertEquals(defaultFechaEmision, invoice2.getFecha().getFechaEmision());
//        Assert.assertEquals(defaultFechaVencimiento, invoice2.getFecha().getFechaVencimiento());
//    }

//    @Test
//    public void InvoiceFacade() {
//        Calendar fechaEmision = Calendar.getInstance();
//        fechaEmision.set(2018, Calendar.MAY, 14, 23, 59, 59);
//        fechaEmision.setTimeZone(defaultTimeZone);
//
//        Calendar fechaVencimiento = Calendar.getInstance();
//        fechaVencimiento.set(2018, Calendar.MAY, 15, 23, 59, 59);
//        fechaVencimiento.setTimeZone(defaultTimeZone);
//
//        Invoice21Bean invoice1 = Invoice21BeanBuilder.builder()
//                .serie("F001")
//                .numero(1)
//                .tipoComprobante(Catalogo1.FACTURA)
//                .firmante(
//                        AbstractMonedaBeanBuilder.builder()
//                                .codigo("PEN")
//                                .build()
//                )
//                .proveedor(
//                        AbstractProveedor21BeanBuilder.builder()
//                                .codigoTipoDocumento("6")
//                                .numeroDocumento("10467793549")
//                                .nombreComercial("Wolsnut4 Consultores")
//                                .razonSocial("Wolsnut4 S.A.")
//                                .codigoPostal("050101")
//                                .build()
//                )
//                .cliente(
//                        AbstractClienteBeanBuilder.ClienteBean()
//                                .codigoTipoDocumento("3")
//                                .numeroDocumento("46779354")
//                                .nombre("Carlos Esteban Feria Vila")
//                                .email("carlosthe19916@gmail.com")
//                                .direccion("Jr. carlos 997")
//                                .build()
//                )
//                .total(
//                        AbstractTotal21BeanBuilder.builder()
//                                .pagar(new BigDecimal("100"))
//                                .descuentoGlobal(new BigDecimal("200"))
//                                .otrosCargos(new BigDecimal("300"))
//                                .build()
//                )
//                .totalInformacionAdicional(
//                        AbstractTotalInformacionAdicionalBeanBuilder.TotalInformacionAdicionalBean()
//                                .gravado(new BigDecimal("400"))
//                                .inafecto(new BigDecimal("500"))
//                                .exonerado(new BigDecimal("600"))
//                                .gratuito(new BigDecimal("700"))
//                                .build()
//                )
//                .impuestos(
//                        AbstractImpuestos21BeanBuilder.builder()
//                                .igv(new BigDecimal("800"))
//                                .build()
//                )
//                .addDetalle(
//                        AbstractDetalleBeanBuilder.Detalle()
//                                .unidadMedida("NIU")
//                                .descripcion("Bolsa de arroz")
//                                .codigoTipoIgv(Catalogo7.GRAVADO_OPERACION_ONEROSA)
//                                .codigoTipoIsc("00")
//                                .cantidad(new BigDecimal("100"))
//                                .valorUnitario(new BigDecimal("10"))
//                                .precioUnitario(new BigDecimal("11.8"))
//                                .subtotal(new BigDecimal("1000"))
//                                .total(new BigDecimal("1180"))
//                                .totalIgv(new BigDecimal("180"))
//                                .totalIsc(new BigDecimal("0"))
//                                .build()
//                )
//                .build();
//
//
//        Invoice21Bean invoice2 = InvoiceFacade.fillOut(invoice1);
//
//        try {
//            InvoiceType invoiceType = InvoiceFacade.toInvoiceType(invoice2);
//
//
//            MapBasedNamespaceContext mapBasedNamespace = getBasedNamespaceContext("urn:oasis:names:specification:ubl21:schema:xsd:Invoice-2");
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//            IMicroDocument asMicroDocument = UBL21Writer.invoice().getAsMicroDocument(invoiceType);
//            XMLWriterSettings xmlWriterSettings = new XMLWriterSettings().setNamespaceContext(mapBasedNamespace).setPutNamespaceContextPrefixesInRoot(true);
//            MicroWriter.writeToStream(asMicroDocument, out, xmlWriterSettings);
//
//            Document document = JaxbUtils.toDocument(out.toByteArray());
//
//            DOMSource source = new DOMSource(document);
//            FileWriter writer = new FileWriter(new File("d:/miarchivott.xml"));
//            StreamResult result = new StreamResult(writer);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.transform(source, result);
//
////            UBL21PEWriter.invoice().write(invoiceType, new File("/home/admin/git/sunat-jaxb/miarchivo.xml"));
//            Assert.assertNotNull(invoiceType);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public static MapBasedNamespaceContext getBasedNamespaceContext(String defaultNamespace) {
//        UBL21NamespaceContext namespace = UBL21NamespaceContext.getInstance();
//        namespace.setMapping("ccts", "urn:un:unece:uncefact:documentation:2");
//        namespace.setMapping("ds", "http://www.w3.org/2000/09/xmldsig#");
//        namespace.setMapping("ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
//        namespace.setMapping("qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
//        namespace.setMapping("sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
//        namespace.setMapping("udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
//        MapBasedNamespaceContext mapBasedNamespace = new MapBasedNamespaceContext();
//        mapBasedNamespace.addMappings(namespace);
//        mapBasedNamespace.setDefaultNamespaceURI(defaultNamespace);
//        return mapBasedNamespace;
//    }
}