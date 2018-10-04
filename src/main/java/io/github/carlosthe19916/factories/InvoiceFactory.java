package io.github.carlosthe19916.factories;

import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class InvoiceFactory {
    private final static QName _Invoice_QNAME = new QName(
            "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2", "Invoice");

    public InvoiceFactory() {
    }

    @XmlElementDecl(namespace = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2", name = "Invoice")
    public JAXBElement<InvoiceType> createInvoice(InvoiceType value) {
        return new JAXBElement<InvoiceType>(_Invoice_QNAME, InvoiceType.class, null, value);
    }
}
