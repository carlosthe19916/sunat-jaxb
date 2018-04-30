package io.github.carlosthe19916;

import io.github.carlosthe19916.type.utils.JaxbUtils;
import oasis.names.specification.ubl.schema.xsd.creditnote_2.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws JAXBException, TransformerException, IOException {
        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
        InvoiceType invoiceType = factory.createInvoiceType();

        JAXBElement<InvoiceType> jaxbElement = factory.createInvoice(invoiceType);
        Document xmlDocument = JaxbUtils.marshalToDocument(InvoiceType.class, jaxbElement);

        byte[] xmlDocumentBytes = JaxbUtils.toBytes(xmlDocument);
        Files.write(Paths.get("/home/admin/git/sunat-jaxb/carlos.xml"), xmlDocumentBytes);
    }

    public static byte[] toBytes(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(bos);
        transformer.transform(source, result);
        return bos.toByteArray();
    }

    public static <T> Document marshalToDocument(Class classToBeBound, JAXBElement<T> jaxbElement) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classToBeBound);
        Marshaller marshaller = context.createMarshaller();

        DOMResult res = new DOMResult();
        marshaller.marshal(jaxbElement, res);
        return ((Document) res.getNode());
    }
}
