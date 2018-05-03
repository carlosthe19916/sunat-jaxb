package io.github.carlosthe19916;

import io.github.carlosthe19916.utils.JaxbUtils;
import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import org.w3c.dom.Document;
import sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.VoidedDocumentsType;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws JAXBException, TransformerException, IOException {
//        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
//        InvoiceType invoiceType = factory.createInvoiceType();
//
//        JAXBElement<InvoiceType> jaxbElement = factory.createInvoice(invoiceType);
//        Document xmlDocument = JaxbUtils.marshalToDocument(InvoiceType.class, jaxbElement);
//
//        byte[] xmlDocumentBytes = JaxbUtils.toBytes(xmlDocument);
//        Files.write(Paths.get("/home/admin/git/sunat-jaxb/carlos.xml"), xmlDocumentBytes);

        sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.ObjectFactory factory = new sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.ObjectFactory();
        VoidedDocumentsType voidedDocumentsType = factory.createVoidedDocumentsType();

        JAXBElement<VoidedDocumentsType> jaxbElement = factory.createVoidedDocuments(voidedDocumentsType);
        Document xmlDocument = JaxbUtils.marshalToDocument(VoidedDocumentsType.class, jaxbElement);

        byte[] xmlDocumentBytes = JaxbUtils.toBytes(xmlDocument);
        Files.write(Paths.get("/home/admin/git/sunat-jaxb/voided-document.xml"), xmlDocumentBytes);
    }

}
