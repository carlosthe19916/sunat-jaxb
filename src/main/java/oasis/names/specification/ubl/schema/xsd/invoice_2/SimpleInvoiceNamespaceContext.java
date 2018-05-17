package oasis.names.specification.ubl.schema.xsd.invoice_2;

import oasis.names.specification.ubl.schema.xsd.SimpleNamespaceContext;

public class SimpleInvoiceNamespaceContext extends SimpleNamespaceContext {

    public SimpleInvoiceNamespaceContext() {
        super("ns", "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
        addPrefixMapping("cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        addPrefixMapping("cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        addPrefixMapping("ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        addPrefixMapping("sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
    }

}
