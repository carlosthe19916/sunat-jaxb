package io.github.carlosthe19916.sunatjaxb.ubl21.managers;

import com.helger.ubl21.UBL21NamespaceContext;
import com.helger.ubl21.UBL21Writer;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class UBL21DocumentManager {

    private UBL21DocumentManager() {
        // Just static methods
    }

    public UBL21InvoiceManager invoice() {
        return new UBL21InvoiceManager();
    }

    public UBL21CreditNoteManager creditNote() {
        return new UBL21CreditNoteManager();
    }

    public UBL21DebitNoteManager debitNote() {
        return new UBL21DebitNoteManager();
    }

    protected static Document byteToDocument(byte[] document) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(document));
    }

    protected static MapBasedNamespaceContext getBasedNamespaceContext(String defaultNamespace) {
        UBL21NamespaceContext namespace = UBL21NamespaceContext.getInstance();
        namespace.setMapping("ccts", "urn:un:unece:uncefact:documentation:2");
        namespace.setMapping("ds", "http://www.w3.org/2000/09/xmldsig#");
        namespace.setMapping("ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        namespace.setMapping("qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
        namespace.setMapping("sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
        namespace.setMapping("udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
        MapBasedNamespaceContext mapBasedNamespace = new MapBasedNamespaceContext();
        mapBasedNamespace.addMappings(namespace);
        mapBasedNamespace.setDefaultNamespaceURI(defaultNamespace);
        return mapBasedNamespace;
    }

}
