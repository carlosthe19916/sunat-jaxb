package io.github.carlosthe19916.validators;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class InvoiceValidator {

    public static final String XML_FILE = "20100454523-01-F001-4355.xml";
    public static final String SCHEMA_FILE = "UBL21/xsd/maindoc/UBL-Invoice-2.1.xsd";

    public static void main(String[] args) {
        InvoiceValidator XMLValidator = new InvoiceValidator();
        boolean valid = XMLValidator.validate(XML_FILE, SCHEMA_FILE);

        System.out.printf("%s validation = %b.", XML_FILE, valid);
    }

    public boolean validate(String xml, String xsd) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(getResource(xsd)));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(getResource(xml))));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getResource(String filename) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }
}
