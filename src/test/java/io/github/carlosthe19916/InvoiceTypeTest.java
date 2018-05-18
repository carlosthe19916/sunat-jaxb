package io.github.carlosthe19916;

import io.github.carlosthe19916.utils.JaxbUtils;
import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

public class InvoiceTypeTest {

    @Test
    public void test() throws JAXBException {
        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
        InvoiceType invoiceType = factory.createInvoiceType();

        JAXBElement<InvoiceType> jaxbElement = factory.createInvoice(invoiceType);
        Document xmlDocument = JaxbUtils.toDocument(InvoiceType.class, jaxbElement);

        Assert.assertTrue(xmlDocument.isDefaultNamespace("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"));
    }
}
