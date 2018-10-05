package io.github.carlosthe19916.facades;

import io.github.carlosthe19916.validators.UBL21Validator;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class InvoiceValidatorTest {

    @Test
    public void InvoiceValidator() {
      //  String XML_FILE = "D:/miarchivott.xml";
        URL resource = getClass().getClassLoader().getResource("20100454523-01-F001-4355.xml");
        UBL21Validator XMLValidator = new UBL21Validator();
        //File xmlFile = FileUtils.getFile(XML_FILE);
      //  boolean valid = XMLValidator.invoice(xmlFile);
        boolean valid = XMLValidator.invoice(new File(resource.getFile()));
        System.out.printf("%s validation = %b.", "20100454523-01-F001-4355.xml", valid);
    }
}
