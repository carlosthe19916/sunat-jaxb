package io.github.carlosthe19916.sunatjaxb.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class JaxbUtils {

    public static <T> Element marshalToElement(Class classToBeBound, JAXBElement<T> jaxbElement) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classToBeBound);
        Marshaller marshaller = context.createMarshaller();

        DOMResult res = new DOMResult();
        marshaller.marshal(jaxbElement, res);
        return ((Document) res.getNode()).getDocumentElement();
    }

}
