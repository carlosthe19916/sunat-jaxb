package io.github.carlosthe19916.sunatjaxb.ubl21.utils;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.*;

import java.math.BigDecimal;

public class UBL21Utils {

    private UBL21Utils() {
        // Just static methods
    }

    // Tipo Comprobante

    public static InvoiceTypeCodeType buildInvoiceTypeCodeType(String value, String listId) {
        InvoiceTypeCodeType invoiceTypeCodeType = new InvoiceTypeCodeType();
        invoiceTypeCodeType.setValue(value);
        invoiceTypeCodeType.setListID(listId);
        invoiceTypeCodeType.setListAgencyName("PE:SUNAT");
        invoiceTypeCodeType.setListName("SUNAT:Identificador de Tipo de Documento");
        invoiceTypeCodeType.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
        return invoiceTypeCodeType;
    }


    // Moneda

    public static DocumentCurrencyCodeType buildDocumentCurrencyCodeType(String value) {
        DocumentCurrencyCodeType documentCurrencyCodeType = new DocumentCurrencyCodeType();
        documentCurrencyCodeType.setValue(value);
        documentCurrencyCodeType.setListID("ISO 4217 Alpha");
        documentCurrencyCodeType.setListName("Currency");
        documentCurrencyCodeType.setListAgencyName("United Nations Economic Commission for Europe");
        return documentCurrencyCodeType;
    }


    // Proveedor

    public static PartyNameType buildPartyNameType(String value) {
        PartyNameType partyNameType = new PartyNameType();
        partyNameType.setName(new NameType(value));
        return partyNameType;
    }

    public static IDType buildIDTypeCatalogo6(String value, String schemeID) {
        IDType idType = new IDType();
        idType.setValue(value);
        idType.setSchemeID(schemeID);
        idType.setSchemeName("SUNAT:Identificador de Documento de Identidad");
        idType.setSchemeAgencyName("PE:SUNAT");
        idType.setSchemeURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06");
        return idType;
    }

    // Total a pagar

    public static AllowanceTotalAmountType buildAllowanceTotalAmountType(BigDecimal value, String currency) {
        AllowanceTotalAmountType allowanceTotalAmountType = new AllowanceTotalAmountType();
        allowanceTotalAmountType.setCurrencyID(currency);
        allowanceTotalAmountType.setValue(value);
        return allowanceTotalAmountType;
    }

    public static ChargeTotalAmountType buildChargeTotalAmountType(BigDecimal value, String currency) {
        ChargeTotalAmountType chargeTotalAmountType = new ChargeTotalAmountType();
        chargeTotalAmountType.setCurrencyID(currency);
        chargeTotalAmountType.setValue(value);
        return chargeTotalAmountType;
    }

    public static PayableAmountType buildPayableAmountType(BigDecimal value, String currency) {
        PayableAmountType payableAmountType = new PayableAmountType();
        payableAmountType.setCurrencyID(currency);
        payableAmountType.setValue(value);
        return payableAmountType;
    }

    public static LineExtensionAmountType buildLineExtensionAmountType(BigDecimal value, String currency) {
        LineExtensionAmountType lineExtensionAmountType = new LineExtensionAmountType();
        lineExtensionAmountType.setCurrencyID(currency);
        lineExtensionAmountType.setValue(value);
        return lineExtensionAmountType;
    }

    public static TaxInclusiveAmountType buildTaxInclusiveAmountType(BigDecimal value, String currency) {
        TaxInclusiveAmountType taxInclusiveAmountType = new TaxInclusiveAmountType();
        taxInclusiveAmountType.setCurrencyID(currency);
        taxInclusiveAmountType.setValue(value);
        return taxInclusiveAmountType;
    }

    public static PrepaidAmountType buildPrepaidAmountType(BigDecimal value, String currency) {
        PrepaidAmountType prepaidAmountType = new PrepaidAmountType();
        prepaidAmountType.setCurrencyID(currency);
        prepaidAmountType.setValue(value);
        return prepaidAmountType;
    }


    // Impuestos

    public static TaxAmountType buildTaxAmountType(BigDecimal value, String currency) {
        TaxAmountType taxAmountType = new TaxAmountType();
        taxAmountType.setCurrencyID(currency);
        taxAmountType.setValue(value);
        return taxAmountType;
    }

    public static TaxCategoryType buildTaxCategoryType(String ID, String schemeID, String name, String code) {
        TaxCategoryType taxCategoryType = new TaxCategoryType();


        IDType idType = new IDType();
        taxCategoryType.setID(idType);


        idType.setValue(ID);
        idType.setSchemeID("UN/ECE 5305");
        idType.setSchemeName("Tax Category Identifie");
        idType.setSchemeAgencyName("United Nations Economic Commission for Europe");


        taxCategoryType.setTaxScheme(buildTaxSchemeType(schemeID, name, code));
        return taxCategoryType;
    }

    public static TaxSchemeType buildTaxSchemeType(String ID, String name, String code) {
        TaxSchemeType taxSchemeType = new TaxSchemeType();

        IDType idType = new IDType();
        idType.setValue(ID);
        idType.setSchemeID("UN/ECE 5153");
        idType.setSchemeName("Codigo de tributos");
        idType.setSchemeAgencyName("PE:SUNAT");

        taxSchemeType.setID(idType);

        taxSchemeType.setName(name);
        taxSchemeType.setTaxTypeCode(code);
        return taxSchemeType;
    }

    public static TaxableAmountType buildTaxableAmountType(BigDecimal value, String currency) {
        TaxableAmountType taxableAmountType = new TaxableAmountType();
        taxableAmountType.setValue(value);
        taxableAmountType.setCurrencyID(currency);
        return taxableAmountType;
    }


    // Line

    public static InvoicedQuantityType buildInvoicedQuantityType(String unitCode, BigDecimal value) {
        InvoicedQuantityType invoicedQuantityType = new InvoicedQuantityType();
        invoicedQuantityType.setValue(value);
        invoicedQuantityType.setUnitCode(unitCode);
        invoicedQuantityType.setUnitCodeListID("UN/ECE rec 20");
        invoicedQuantityType.setUnitCodeListAgencyName("United Nations Economic Commission for Europe");
        return invoicedQuantityType;
    }

    public static ItemType buildItemType(String description) {
        ItemType itemType = new ItemType();
        itemType.getDescription().add(new DescriptionType(description));
        return itemType;
    }

    public static PriceType buildPriceType(String currency, BigDecimal value) {
        PriceType priceType = new PriceType();
        priceType.setPriceAmount(buildPriceAmountType(currency, value));
        return priceType;
    }

    public static PriceType buildPriceType(String currency, BigDecimal value, String priceTypeCode) {
        PriceType priceType = new PriceType();
        priceType.setPriceAmount(buildPriceAmountType(currency, value));
        priceType.setPriceTypeCode(buildPriceTypeCodeType(priceTypeCode));
        return priceType;
    }

    public static PriceAmountType buildPriceAmountType(String currency, BigDecimal value) {
        PriceAmountType priceAmountType = new PriceAmountType();
        priceAmountType.setCurrencyID(currency);
        priceAmountType.setValue(value);
        return priceAmountType;
    }

    public static PriceTypeCodeType buildPriceTypeCodeType(String value) {
        PriceTypeCodeType priceTypeCodeType = new PriceTypeCodeType();
        priceTypeCodeType.setValue(value);
        priceTypeCodeType.setListName("SUNAT:Indicador de Tipo de Precio");
        priceTypeCodeType.setListAgencyName("PE:SUNAT");
        priceTypeCodeType.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16");
        return priceTypeCodeType;
    }

    public static TaxExemptionReasonCodeType buildTaxExemptionReasonCodeType(String value) {
        TaxExemptionReasonCodeType taxExemptionReasonCodeType = new TaxExemptionReasonCodeType();
        taxExemptionReasonCodeType.setValue(value);
        taxExemptionReasonCodeType.setListAgencyName("PE:SUNAT");
        taxExemptionReasonCodeType.setListName("SUNAT:Codigo de Tipo de Afectación del IGV");
        taxExemptionReasonCodeType.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07");
        return taxExemptionReasonCodeType;
    }


    // Leyendas

    public static NoteType buildNoteType(String value, String languageLocaleID) {
        NoteType noteType = new NoteType();
        noteType.setValue(value);
        noteType.setLanguageLocaleID(languageLocaleID);
        return noteType;
    }


    // Guias de remision relacionada

    public static DocumentReferenceType buildDocumentReferenceTypeGuiaRemisionRelacionada(String ID, String documentTypeCode) {
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        documentReferenceType.setID(ID);
        documentReferenceType.setDocumentTypeCode(buildDocumentTypeCodeType1(documentTypeCode));
        return documentReferenceType;
    }

    public static DocumentTypeCodeType buildDocumentTypeCodeType1(String value) {
        DocumentTypeCodeType documentTypeCodeType = new DocumentTypeCodeType();
        documentTypeCodeType.setValue(value);
        documentTypeCodeType.setListAgencyName("PE:SUNAT");
        documentTypeCodeType.setListName("SUNAT:Identificador de guía relacionada");
        documentTypeCodeType.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
        return documentTypeCodeType;
    }


    // Otro documento relacionado

    public static DocumentReferenceType buildDocumentReferenceTypeOtroDocumentoRelacionado(String ID, String documentTypeCode) {
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        documentReferenceType.setID(ID);
        documentReferenceType.setDocumentTypeCode(buildDocumentTypeCodeType2(documentTypeCode));
        return documentReferenceType;
    }

    public static DocumentTypeCodeType buildDocumentTypeCodeType2(String value) {
        DocumentTypeCodeType documentTypeCodeType = new DocumentTypeCodeType();
        documentTypeCodeType.setValue(value);
        documentTypeCodeType.setListAgencyName("PE:SUNAT");
        documentTypeCodeType.setListName("SUNAT: Identificador de documento relacionado");
        documentTypeCodeType.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo12");
        return documentTypeCodeType;
    }


    // Signature

    public static PartyIdentificationType buildPartyIdentificationType(String ID) {
        PartyIdentificationType partyIdentificationType = new PartyIdentificationType();
        partyIdentificationType.setID(new IDType(ID));
        return partyIdentificationType;
    }

    public static ExternalReferenceType buildExternalReferenceType(String value) {
        ExternalReferenceType externalReferenceType = new ExternalReferenceType();
        externalReferenceType.setURI(new URIType(value));
        return externalReferenceType;
    }

    // Discrepancy response

    public static ResponseCodeType buildCreditNoteResponseCode(String value) {
        ResponseCodeType response = new ResponseCodeType();
        response.setValue(value);
        response.setListAgencyName("PE:SUNAT");
        response.setListName("SUNAT: Identificador de tipo de nota de credito");
        response.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo09");
        return response;
    }

    public static ResponseCodeType buildDebitNoteResponseCode(String value) {
        ResponseCodeType response = new ResponseCodeType();
        response.setValue(value);
        response.setListAgencyName("PE:SUNAT");
        response.setListName("SUNAT: Identificador de tipo de nota de debito");
        response.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo10");
        return response;
    }


    public static CreditedQuantityType buildCreditNoteQuantityType(String unitCode, BigDecimal value) {
        CreditedQuantityType creditedQuantityType = new CreditedQuantityType();
        creditedQuantityType.setValue(value);
        creditedQuantityType.setUnitCode(unitCode);
        creditedQuantityType.setUnitCodeListID("UN/ECE rec 20");
        creditedQuantityType.setUnitCodeListAgencyName("United Nations Economic Commission for Europe");
        return creditedQuantityType;
    }

    public static DebitedQuantityType buildDebitNoteQuantityType(String unitCode, BigDecimal value) {
        DebitedQuantityType debitedQuantityType = new DebitedQuantityType();
        debitedQuantityType.setValue(value);
        debitedQuantityType.setUnitCode(unitCode);
        debitedQuantityType.setUnitCodeListID("UN/ECE rec 20");
        debitedQuantityType.setUnitCodeListAgencyName("United Nations Economic Commission for Europe");
        return debitedQuantityType;
    }

    public static DocumentReferenceType buildDocumentReference(String ID, String documentTypeCode) {
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        documentReferenceType.setID(new IDType(ID));
        documentReferenceType.setDocumentTypeCode(buildDocumentTypeCodeType(documentTypeCode));
        return documentReferenceType;
    }

    public static DocumentTypeCodeType buildDocumentTypeCodeType(String value) {
        DocumentTypeCodeType documentTypeCodeType = new DocumentTypeCodeType();
        documentTypeCodeType.setValue(value);
        documentTypeCodeType.setListAgencyName("PE:SUNAT");
        documentTypeCodeType.setListName("SUNAT:Identificador de Tipo de Documento");
        documentTypeCodeType.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
        return documentTypeCodeType;
    }

}
