package io.github.carlosthe19916.facades;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignFacade {

    public SignFacade() {
        // Just static methods
    }

    public Document sign(Document document, String signatureID, X509Certificate certificate, PrivateKey privateKey) {
        Document newdocument = addUBLExtensions(document);
        Node parentNode = addExtensionContent(newdocument);

        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance();
        try {
            Reference reference = signatureFactory.newReference("",
                    signatureFactory.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);

            SignedInfo signedInfo = signatureFactory.newSignedInfo(
                    signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(reference));

            KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
            List<X509Certificate> x509Content = new ArrayList<>();

            // Certificate
            x509Content.add(certificate);

            X509Data xdata = keyInfoFactory.newX509Data(x509Content);
            KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(xdata));

            DOMSignContext signContext = new DOMSignContext(privateKey, newdocument.getDocumentElement());
            XMLSignature signature = signatureFactory.newXMLSignature(signedInfo, keyInfo);
            if (parentNode != null) {
                signContext.setParent(parentNode);
            }
            signContext.setDefaultNamespacePrefix("ds");
            signature.sign(signContext);
            Element elementParent = (Element) signContext.getParent();
            if ((signatureID != null) && (elementParent.getElementsByTagName("ds:Signature") != null)) {
                Element elementSignature = (Element) elementParent.getElementsByTagName("ds:Signature").item(0);
                elementSignature.setAttribute("Id", signatureID);
            }
            return newdocument;
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | MarshalException | XMLSignatureException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Document addUBLExtensions(Document document) {
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("ext:UBLExtensions");
        Node extensions = nodeList.item(0);
        if (extensions == null) {
            Element element = document.getDocumentElement();
            extensions = document.createElement("ext:UBLExtensions");
            element.insertBefore(extensions, element.getFirstChild());
            extensions.appendChild(document.createTextNode("\n"));
            return document;
        } else {
            return document;
        }
    }

    private static Node addExtensionContent(Document document) {
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("ext:UBLExtensions");
        Node extensions = nodeList.item(0);
        Node content = null;
        if (extensions != null) {
            NodeList previousSignature = extensions.getOwnerDocument().getElementsByTagName("ds:Signature");
            if (previousSignature != null && previousSignature.getLength() > 0) {
                Node previousContent = previousSignature.item(0).getParentNode();
                removeAll(previousContent);
                content = previousContent;
            } else {
                Node extension = document.createElement("ext:UBLExtension");
                content = document.createElement("ext:ExtensionContent");
                extension.appendChild(content);
                extensions.appendChild(extension);
            }
        }
        return content;
    }

    private static void removeAll(Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes != null) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node n = node.getChildNodes().item(i);
                if (n.hasChildNodes()) {
                    removeAll(n);
                    node.removeChild(n);
                } else {
                    node.removeChild(n);
                }
            }
        }
    }

}
