package io.github.carlosthe19916.factories;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class Ubl21NamespaceMapper extends NamespacePrefixMapper {
    private static final String SAC_PREFIX = "sac";
    private static final String SAC_URI = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1";

    private static final String CAC_PREFIX = "cac";
    private static final String CAC_URI = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2";

    private static final String CBC_PREFIX = "cbc";
    private static final String CBC_URI = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2";

    private static final String UDT_PREFIX = "udt";
    private static final String UDT_URI = "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2";

    private static final String CCTS_PREFIX = "ccts";
    private static final String CCTS_URI = "urn:un:unece:uncefact:documentation:2";

    private static final String EXT_PREFIX = "ext";
    private static final String EXT_URI = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2";

    private static final String QDT_PREFIX = "qdt";
    private static final String QDT_URI = "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2";

    private static final String DS_PREFIX = "ds";
    private static final String DS_URI = "http://www.w3.org/2000/09/xmldsig#";

    private static final String INVOICE_PREFIX = "";
    private static final String INVOICE_URI = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";

    private static final String XSI_PREFIX = "xsi";
    private static final String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";


    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        if (SAC_URI.equals(namespaceUri)) {
            return SAC_PREFIX;
        } else if (CAC_URI.equals(namespaceUri)) {
            return CAC_PREFIX;
        } else if (CBC_URI.equals(namespaceUri)) {
            return CBC_PREFIX;
        } else if (UDT_URI.equals(namespaceUri)) {
            return UDT_PREFIX;
        } else if (CCTS_URI.equals(namespaceUri)) {
            return CCTS_PREFIX;
        } else if (EXT_URI.equals(namespaceUri)) {
            return EXT_PREFIX;
        } else if (QDT_URI.equals(namespaceUri)) {
            return QDT_PREFIX;
        } else if (DS_URI.equals(namespaceUri)) {
            return DS_PREFIX;
        } else if (INVOICE_URI.equals(namespaceUri)) {
            return INVOICE_PREFIX;
        } else if (XSI_URI.equals(namespaceUri)) {
            return XSI_PREFIX;
        }
        return suggestion;
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[]{SAC_URI, CAC_URI, CBC_URI, UDT_URI, CCTS_URI, EXT_URI, QDT_URI, DS_URI, INVOICE_URI, XSI_URI};
    }
}
