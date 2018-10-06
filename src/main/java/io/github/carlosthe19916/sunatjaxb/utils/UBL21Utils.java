package io.github.carlosthe19916.sunatjaxb.utils;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.*;

import java.math.BigDecimal;

public class UBL21Utils {

    private UBL21Utils() {
        // Just static methods
    }

    // Version

    public static UBLVersionIDType buildUBLVersionID(String value) {
        UBLVersionIDType versionIDType = new UBLVersionIDType();
        versionIDType.setValue(value);
        return versionIDType;
    }

    public static CustomizationIDType buildCustomizationIDType(String value) {
        CustomizationIDType customizationIDType = new CustomizationIDType();
        customizationIDType.setValue(value);
        return customizationIDType;
    }

    // Profile

    public static ProfileIDType toProfileIDType(String value) {
        ProfileIDType profileIDType = new ProfileIDType();
        profileIDType.setSchemeName("SUNAT:Identificador de Tipo de Operación");
        profileIDType.setSchemeAgencyName("PE:SUNAT");
        profileIDType.setSchemeURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo17");
        profileIDType.setValue(value);
        return profileIDType;
    }


    // Serie y numero

    public static IDType buildIDType(String value) {
        IDType idType = new IDType();
        idType.setValue(value);
        return idType;
    }


    // Fecha vencimiento


    public static InvoiceTypeCodeType buildInvoiceTypeCodeType(String value, String listId) {
        InvoiceTypeCodeType invoiceTypeCodeType = new InvoiceTypeCodeType();
        invoiceTypeCodeType.setValue(value);
        invoiceTypeCodeType.setListID(listId);
        invoiceTypeCodeType.setListAgencyName("PE:SUNAT");
        invoiceTypeCodeType.setListName("SUNAT:Identificador de Tipo de Documento");
        invoiceTypeCodeType.setListURI("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
        return invoiceTypeCodeType;
    }


    // builder

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
        partyNameType.setName(buildNameType(value));
        return partyNameType;
    }

    public static NameType buildNameType(String value) {
        NameType nameType = new NameType();
        nameType.setValue(value);
        return nameType;
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
        itemType.getDescription().add(buildDescriptionType(description));
        return itemType;
    }

    public static DescriptionType buildDescriptionType(String value) {
        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(value);
        return descriptionType;
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

    public static DocumentReferenceType buildDocumentReferenceType1(String ID, String documentTypeCode) {
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

    public static DocumentReferenceType buildDocumentReferenceType2(String ID, String documentTypeCode) {
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


    // Firma

    public static PartyIdentificationType buildPartyIdentificationType(String ID) {
        PartyIdentificationType partyIdentificationType = new PartyIdentificationType();
        partyIdentificationType.setID(buildIDType(ID));
        return partyIdentificationType;
    }

    public static ExternalReferenceType buildExternalReferenceType(String value) {
        ExternalReferenceType externalReferenceType = new ExternalReferenceType();
        externalReferenceType.setURI(buildURIType(value));
        return externalReferenceType;
    }

    public static URIType buildURIType(String value) {
        URIType uriType = new URIType();
        uriType.setValue(value);
        return uriType;
    }

//    public static IssueDateType buildIssueDateType(XMLGregorianCalendar value) {
//        IssueDateType issueDateType = new IssueDateType();
//        issueDateType.setValue(value);
//        return issueDateType;
//    }
//
//    public static ReferenceDateType buildReferenceDateType(XMLGregorianCalendar value) {
//        ReferenceDateType referenceDateType = new ReferenceDateType();
//        referenceDateType.setValue(value);
//        return referenceDateType;
//    }
//
//    public static StreetNameType buildStreetNameType(String value) {
//        StreetNameType streetNameType = new StreetNameType();
//        streetNameType.setValue(value);
//        return streetNameType;
//    }
//    public static CitySubdivisionNameType buildCitySubdivisionNameType(String value) {
//        CitySubdivisionNameType citySubdivisionNameType = new CitySubdivisionNameType();
//        citySubdivisionNameType.setValue(value);
//        return citySubdivisionNameType;
//    }
//
//    public static DistrictType buildDistrictType(String value) {
//        DistrictType districtType = new DistrictType();
//        districtType.setValue(value);
//        return districtType;
//    }
//
//    public static CityNameType buildCityNameType(String value) {
//        CityNameType cityNameType = new CityNameType();
//        cityNameType.setValue(value);
//        return cityNameType;
//    }
//
//    public static CountrySubentityType buildCountrySubEntityType(String value) {
//        CountrySubentityType countrySubentityType = new CountrySubentityType();
//        countrySubentityType.setValue(value);
//        return countrySubentityType;
//    }
//
//    public static IdentificationCodeType buildIdentificationCodeType(String value) {
//        IdentificationCodeType identificationCodeType = new IdentificationCodeType();
//        identificationCodeType.setValue(value);
//        return identificationCodeType;
//    }
//
//
//    public static RegistrationNameType buildRegistrationNameType(String value) {
//        RegistrationNameType registrationNameType = new RegistrationNameType();
//        registrationNameType.setValue(value);
//        return registrationNameType;
//    }
//
//    public static CustomerAssignedAccountIDType buildCustomerAssignedAccountIDType(String value) {
//        CustomerAssignedAccountIDType customerAssignedAccountIDType = new CustomerAssignedAccountIDType();
//        customerAssignedAccountIDType.setValue(value);
//        return customerAssignedAccountIDType;
//    }
//
//    public static AdditionalAccountIDType buildAdditionalAccountIDType(String value) {
//        AdditionalAccountIDType additionalAccountIDType = new AdditionalAccountIDType();
//        additionalAccountIDType.setValue(value);
//        return additionalAccountIDType;
//    }
//
//
//    public static NoteType buildNoteType(String value) {
//        NoteType noteType = new NoteType();
//        noteType.setValue(value);
//        return noteType;
//    }
//
//
//
//
//    public static CreditedQuantityType buildCreditedQuantityType(String unitCode, BigDecimal value) {
//        CreditedQuantityType creditedQuantityType = new CreditedQuantityType();
//        creditedQuantityType.setValue(value);
//        creditedQuantityType.setUnitCode(unitCode);
//        return creditedQuantityType;
//    }
//
//    public static DebitedQuantityType buildDebitedQuantityType(String unitCode, BigDecimal value) {
//        DebitedQuantityType debitedQuantityType = new DebitedQuantityType();
//        debitedQuantityType.setValue(value);
//        debitedQuantityType.setUnitCode(unitCode);
//        return debitedQuantityType;
//    }
//
//    public static LineExtensionAmountType buildLineExtensionAmountType(String currency, BigDecimal value) {
//        LineExtensionAmountType lineExtensionAmountType = new LineExtensionAmountType();
//        lineExtensionAmountType.setCurrencyID(CurrencyCodeContentType.valueOf(currency));
//        lineExtensionAmountType.setValue(value);
//        return lineExtensionAmountType;
//    }
//
//
//
//
//
//
//    public static PartyLegalEntityType buildPartyLegalEntityType(String value) {
//        PartyLegalEntityType partyLegalEntityType = new PartyLegalEntityType();
//        partyLegalEntityType.setRegistrationName(buildRegistrationNameType(value));
//        return partyLegalEntityType;
//    }
//
//
//
//
//
//    public static CountryType buildCountryType(String value) {
//        CountryType countryType = new CountryType();
//        countryType.setIdentificationCode(buildIdentificationCodeType(value));
//        return countryType;
//    }
//
//
//
//    public static ValueType buildValueType(String value) {
//        ValueType valueType = new ValueType();
//        valueType.setValue(value);
//        return valueType;
//    }
//
//    public static ReferenceIDType buildReferenceIDType(String value) {
//        ReferenceIDType referenceIDType = new ReferenceIDType();
//        referenceIDType.setValue(value);
//        return referenceIDType;
//    }
//
//    public static ResponseCodeType buildResponseCodeType(String value) {
//        ResponseCodeType responseCodeType = new ResponseCodeType();
//        responseCodeType.setValue(value);
//        return responseCodeType;
//    }
//
//    public static ResponseType buildResponseType(String referenecID, String responseCode, String descripcion) {
//        ResponseType responseType = new ResponseType();
//        responseType.setReferenceID(UBLUtils.buildReferenceIDType(referenecID));
//        responseType.setResponseCode(UBLUtils.buildResponseCodeType(responseCode));
//        responseType.getDescription().add(UBLUtils.buildDescriptionType(descripcion));
//        return responseType;
//    }
//
//
//    public static DocumentReferenceType buildDocumentReferenceType(String ID, String documentTypeCode) {
//        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
//        documentReferenceType.setID(buildIDType(ID));
//        documentReferenceType.setDocumentTypeCode(buildDocumentTypeCodeType(documentTypeCode));
//        return documentReferenceType;
//    }
//
//    public static LineIDType buildLineIDType(String value) {
//        LineIDType lineIDType = new LineIDType();
//        lineIDType.setValue(value);
//        return lineIDType;
//    }
//
//    public static IdentifierType buildIdentifierType(String value) {
//        IdentifierType identifierType = new IdentifierType();
//        identifierType.setValue(value);
//        return identifierType;
//    }
//
//    public static TextType buildTextType(String value) {
//        TextType textType = new TextType();
//        textType.setValue(value);
//        return textType;
//    }

}
