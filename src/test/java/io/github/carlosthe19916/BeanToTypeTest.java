package io.github.carlosthe19916;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.utils.JaxbUtils;
import oasis.names.specification.ubl.schema.xsd.SimpleNamespaceContext;
import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBElement;
import javax.xml.xpath.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

public class BeanToTypeTest {

    private TimeZone timeZone;
    private InvoiceBean invoiceBean;

    public static NodeList getNodesWithXPath(Node aNode, String aXPath) throws XPathExpressionException {
        SimpleNamespaceContext namespaceContext = new SimpleNamespaceContext("ns", "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
        namespaceContext.addPrefixMapping("cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        namespaceContext.addPrefixMapping("cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        xPath.setNamespaceContext(namespaceContext);

        XPathExpression xPathExpression = xPath.compile(aXPath);
        return (NodeList) xPathExpression.evaluate(aNode, XPathConstants.NODESET);
    }

    @Before
    public void before() {
        timeZone = TimeZone.getTimeZone("America/Lima");

        Calendar fechaEmision = Calendar.getInstance();
        fechaEmision.set(2018, Calendar.MAY, 14, 23, 59, 59);
        fechaEmision.setTimeZone(timeZone);

        Calendar fechaVencimiento = Calendar.getInstance();
        fechaVencimiento.set(2018, Calendar.MAY, 15, 23, 59, 59);
        fechaVencimiento.setTimeZone(timeZone);

        invoiceBean = InvoiceBeanBuilder.InvoiceBean()
                .serie("F001")
                .numero(1)
                .codigoTipoComprobante("01")
                .observaciones("Sin observaciones")
                .fecha(
                        FechaBeanBuilder.FechaBean()
                                .fechaEmision(fechaEmision.getTime())
                                .fechaVencimiento(fechaEmision.getTime())
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
                                .descuento(new BigDecimal("6"))
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
                                .nombreComercial("Wolsnut4 Consultores")
                                .razonSocial("Wolsnut4 S.A.")
                                .codigoPostal("050101")
                                .direccion("Jr. ayacucho 123")
                                .region("Ayacucho")
                                .provincia("Huamanga")
                                .distrito("Jesús Nazareno")
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
                                .unidadMedida("Kg")
                                .descripcion("Bolsa de arroz")
                                .codigoTipoIgv("10")
                                .codigoTipoIsc("00")
                                .cantidad(BigDecimal.ONE)
                                .valorUnitario(new BigDecimal("8.2"))
                                .precioUnitario(BigDecimal.TEN)
                                .subtotal(new BigDecimal("11"))
                                .total(new BigDecimal("12"))
                                .totalIgv(new BigDecimal("1.8"))
                                .totalIsc(new BigDecimal("0.7"))
                                .build()
                )
                .build();
    }

    @Test
    public void toInvoiceTypeTest() throws Exception {
        InvoiceType invoiceType = BeanToType.toInvoiceType(invoiceBean, TimeZone.getDefault());

        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
        JAXBElement<InvoiceType> jaxbElement = factory.createInvoice(invoiceType);
        Document xmlDocument = JaxbUtils.toDocument(InvoiceType.class, jaxbElement);

        Assert.assertEquals("2.0", getNodesWithXPath(xmlDocument, "//ns:Invoice/cbc:UBLVersionID/text()").item(0).getTextContent());
        Assert.assertEquals("1.0", getNodesWithXPath(xmlDocument, "//ns:Invoice/cbc:CustomizationID/text()").item(0).getTextContent());
        Assert.assertEquals("F001-1", getNodesWithXPath(xmlDocument, "//ns:Invoice/cbc:ID/text()").item(0).getTextContent());
        Assert.assertEquals("2018-05-14", getNodesWithXPath(xmlDocument, "//ns:Invoice/cbc:IssueDate/text()").item(0).getTextContent());
        Assert.assertEquals("01", getNodesWithXPath(xmlDocument, "//ns:Invoice/cbc:InvoiceTypeCode/text()").item(0).getTextContent());
        Assert.assertEquals("PEN", getNodesWithXPath(xmlDocument, "//ns:Invoice/cbc:DocumentCurrencyCode/text()").item(0).getTextContent());

        // Signature
        Assert.assertEquals("IDSignWolsnut4Consultores", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:Signature/cbc:ID/text()").item(0).getTextContent());
        Assert.assertEquals("10467793549", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:Signature/cac:SignatoryParty/cac:PartyIdentification/cbc:ID/text()").item(0).getTextContent());
        Assert.assertEquals("Wolsnut4 Consultores", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:Signature/cac:SignatoryParty/cac:PartyName/cbc:Name/text()").item(0).getTextContent());
        Assert.assertEquals("#signatureWolsnut4Consultores", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:Signature/cac:DigitalSignatureAttachment/cac:ExternalReference/cbc:URI/text()").item(0).getTextContent());

        // Supplier
        Assert.assertEquals("10467793549", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cbc:CustomerAssignedAccountID/text()").item(0).getTextContent());
        Assert.assertEquals("6", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cbc:AdditionalAccountID/text()").item(0).getTextContent());

        Assert.assertEquals("050101", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:ID/text()").item(0).getTextContent());
        Assert.assertEquals("Jr. ayacucho 123", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:StreetName/text()").item(0).getTextContent());
        Assert.assertEquals("Jesús Nazareno", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:District/text()").item(0).getTextContent());
        Assert.assertEquals("Huamanga", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:CityName/text()").item(0).getTextContent());
        Assert.assertEquals("Ayacucho", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cbc:CountrySubentity/text()").item(0).getTextContent());
        Assert.assertEquals("PE", getNodesWithXPath(xmlDocument, "//ns:Invoice/cac:AccountingSupplierParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode/text()").item(0).getTextContent());

        // Customer
    }

}
