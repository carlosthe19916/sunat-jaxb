package io.github.carlosthe19916.sunatjaxb.utils;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import un.unece.uncefact.codelist.specification._54217._2001.CurrencyCodeContentType;
import un.unece.uncefact.codelist.specification._66411._2001.UnitCodeContentType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

public class UBL20Utils {

    private UBL20Utils() {
        // Just static methods
    }

    // Observaciones

    public static NoteType buildNoteType(String value) {
        NoteType noteType = new NoteType();
        noteType.setValue(value);
        return noteType;
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


    // Serie y numero

    public static IDType buildIDType(String value) {
        IDType idType = new IDType();
        idType.setValue(value);
        return idType;
    }


    // Fecha vencimiento


    public static PaymentMeansType buildPaymentMeansType(XMLGregorianCalendar value) {
        PaymentMeansType paymentMeansType = new PaymentMeansType();
        paymentMeansType.setPaymentDueDate(buildPaymentDueDateType(value));
        return paymentMeansType;
    }

    public static PaymentDueDateType buildPaymentDueDateType(XMLGregorianCalendar value) {
        PaymentDueDateType paymentDueDateType = new PaymentDueDateType();
        paymentDueDateType.setValue(value);
        return paymentDueDateType;
    }


    // Tipo de comprobante


    public static InvoiceTypeCodeType buildInvoiceTypeCodeType(String value) {
        InvoiceTypeCodeType invoiceTypeCodeType = new InvoiceTypeCodeType();
        invoiceTypeCodeType.setValue(value);
        return invoiceTypeCodeType;
    }


    // builder

    public static DocumentCurrencyCodeType buildDocumentCurrencyCodeType(String value) {
        DocumentCurrencyCodeType documentCurrencyCodeType = new DocumentCurrencyCodeType();
        documentCurrencyCodeType.setValue(value);
        return documentCurrencyCodeType;
    }


    // Proveedor

    public static CustomerAssignedAccountIDType buildCustomerAssignedAccountIDType(String value) {
        CustomerAssignedAccountIDType customerAssignedAccountIDType = new CustomerAssignedAccountIDType();
        customerAssignedAccountIDType.setValue(value);
        return customerAssignedAccountIDType;
    }

    public static AdditionalAccountIDType buildAdditionalAccountIDType(String value) {
        AdditionalAccountIDType additionalAccountIDType = new AdditionalAccountIDType();
        additionalAccountIDType.setValue(value);
        return additionalAccountIDType;
    }

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

    public static PartyLegalEntityType buildPartyLegalEntityType(String value) {
        PartyLegalEntityType partyLegalEntityType = new PartyLegalEntityType();
        partyLegalEntityType.setRegistrationName(buildRegistrationNameType(value));
        return partyLegalEntityType;
    }

    public static RegistrationNameType buildRegistrationNameType(String value) {
        RegistrationNameType registrationNameType = new RegistrationNameType();
        registrationNameType.setValue(value);
        return registrationNameType;
    }


    // Total pagar

    public static AllowanceTotalAmountType buildAllowanceTotalAmountType(BigDecimal value, String currency) {
        AllowanceTotalAmountType allowanceTotalAmountType = new AllowanceTotalAmountType();
        allowanceTotalAmountType.setCurrencyID(CurrencyCodeContentType.fromValue(currency));
        allowanceTotalAmountType.setValue(value);
        return allowanceTotalAmountType;
    }

    public static ChargeTotalAmountType buildChargeTotalAmountType(BigDecimal value, String currency) {
        ChargeTotalAmountType chargeTotalAmountType = new ChargeTotalAmountType();
        chargeTotalAmountType.setCurrencyID(CurrencyCodeContentType.valueOf(currency));
        chargeTotalAmountType.setValue(value);
        return chargeTotalAmountType;
    }

    public static PayableAmountType buildPayableAmountType(BigDecimal value, String currency) {
        PayableAmountType payableAmountType = new PayableAmountType();
        payableAmountType.setCurrencyID(CurrencyCodeContentType.valueOf(currency));
        payableAmountType.setValue(value);
        return payableAmountType;
    }


    // Impuestos

    public static TaxAmountType buildTaxAmountType(BigDecimal value, String currency) {
        TaxAmountType taxAmountType = new TaxAmountType();
        taxAmountType.setCurrencyID(CurrencyCodeContentType.valueOf(currency));
        taxAmountType.setValue(value);
        return taxAmountType;
    }

    public static TaxCategoryType buildTaxCategoryType(String ID, String name, String code) {
        TaxCategoryType taxCategoryType = new TaxCategoryType();
        taxCategoryType.setTaxScheme(buildTaxSchemeType(ID, name, code));
        return taxCategoryType;
    }

    public static TaxSchemeType buildTaxSchemeType(String ID, String name, String code) {
        TaxSchemeType taxSchemeType = new TaxSchemeType();
        taxSchemeType.setID(buildIDType(ID));
        taxSchemeType.setName(name);
        taxSchemeType.setTaxTypeCode(code);
        return taxSchemeType;
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


    // Line

    public static InvoicedQuantityType buildInvoicedQuantityType(String unitCode, BigDecimal value) {
        InvoicedQuantityType invoicedQuantityType = new InvoicedQuantityType();
        invoicedQuantityType.setValue(value);
        invoicedQuantityType.setUnitCode(UnitCodeContentType.fromValue(unitCode));
        return invoicedQuantityType;
    }

    public static LineExtensionAmountType buildLineExtensionAmountType(String currency, BigDecimal value) {
        LineExtensionAmountType lineExtensionAmountType = new LineExtensionAmountType();
        lineExtensionAmountType.setCurrencyID(CurrencyCodeContentType.valueOf(currency));
        lineExtensionAmountType.setValue(value);
        return lineExtensionAmountType;
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

    public static PriceAmountType buildPriceAmountType(String currency, BigDecimal value) {
        PriceAmountType priceAmountType = new PriceAmountType();
        priceAmountType.setCurrencyID(CurrencyCodeContentType.valueOf(currency));
        priceAmountType.setValue(value);
        return priceAmountType;
    }

    public static PriceType buildPriceType(String currency, BigDecimal value, String priceTypeCode) {
        PriceType priceType = new PriceType();
        priceType.setPriceAmount(buildPriceAmountType(currency, value));
        priceType.setPriceTypeCode(buildPriceTypeCodeType(priceTypeCode));
        return priceType;
    }

    public static PriceTypeCodeType buildPriceTypeCodeType(String value) {
        PriceTypeCodeType priceTypeCodeType = new PriceTypeCodeType();
        priceTypeCodeType.setValue(value);
        return priceTypeCodeType;
    }

    public static TaxExemptionReasonCodeType buildTaxExemptionReasonCodeType(String value) {
        TaxExemptionReasonCodeType taxExemptionReasonCodeType = new TaxExemptionReasonCodeType();
        taxExemptionReasonCodeType.setValue(value);
        return taxExemptionReasonCodeType;
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
//
//
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
//

//
//
//
//
//

//
//
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
//    public static DocumentTypeCodeType buildDocumentTypeCodeType(String value) {
//        DocumentTypeCodeType documentTypeCodeType = new DocumentTypeCodeType();
//        documentTypeCodeType.setValue(value);
//        return documentTypeCodeType;
//    }
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
