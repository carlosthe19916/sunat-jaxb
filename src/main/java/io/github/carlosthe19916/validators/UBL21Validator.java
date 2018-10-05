package io.github.carlosthe19916.validators;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class UBL21Validator {

     static final String INVOICE_SCHEMA_FILE = "UBL21/xsd/maindoc/UBL-Invoice-2.1.xsd";
     static final String CREDID_NOTE_SCHEMA_FILE = "UBL21/xsd/maindoc/UBL-CreditNote-2.1.xsd";
     static final String DEBIT_NOTE_SCHEMA_FILE = "UBL21/xsd/maindoc/UBL-DebitNote-2.1.xsd";
     static final String DESPATCH_ADVICE_SCHEMA_FILE = "UBL21/xsd/maindoc/UBL-DespatchAdvice-2.1.xsd";

    public boolean invoice(File xml) {
        try {
            return validator(xml, INVOICE_SCHEMA_FILE);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean creditNote(File xml) {
        try {
            return validator(xml, CREDID_NOTE_SCHEMA_FILE);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean debitNote(File xml) {
        try {
            return validator(xml, DEBIT_NOTE_SCHEMA_FILE);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean despatchAdvice(File xml) {
        try {
            return validator(xml, DESPATCH_ADVICE_SCHEMA_FILE);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validator(File xml, String xsd) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(getResource(xsd)));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));
        return true;
    }

    private String getResource(String filename) {
        URL resource = getClass().getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);
        return resource.getFile();
    }
}