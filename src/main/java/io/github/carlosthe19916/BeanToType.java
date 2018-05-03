package io.github.carlosthe19916;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.finance.MoneyConverters;
import io.github.carlosthe19916.sunat.*;
import io.github.carlosthe19916.utils.JaxbUtils;
import io.github.carlosthe19916.utils.TypeUtils;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionContentType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionsType;
import oasis.names.specification.ubl.schema.xsd.creditnote_2.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.debitnote_2.DebitNoteType;
import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import org.w3c.dom.Element;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.*;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.ObjectFactory;
import sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.VoidedDocumentsType;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class BeanToType {

    private BeanToType() {
        // Just util class
    }

    public static InvoiceType toInvoiceType(InvoiceBean bean, TimeZone timeZone) {
        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
        InvoiceType invoiceType = factory.createInvoiceType();

        // General config
        invoiceType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        invoiceType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        invoiceType.setID(TypeUtils.buildIDType(bean.getSerie() + "-" + bean.getNumero()));

        // Tipo comprobante
        invoiceType.setInvoiceTypeCode(TypeUtils.buildInvoiceTypeCodeType(bean.getCodigoTipoComprobante()));

        // Fechas
        XMLGregorianCalendar issueDate = toGregorianCalendar(bean.getFechaEmision(), timeZone);
        invoiceType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));
        if (bean.getFechaVencimiento() != null) {
            XMLGregorianCalendar paymentDate = toGregorianCalendar(bean.getFechaVencimiento(), timeZone);
            invoiceType.getPaymentMeans().add(TypeUtils.buildPaymentMeansType(paymentDate));
        }

        // Proveedor
        invoiceType.setAccountingSupplierParty(buildSupplierPartyType(bean.getSupplier()));

        // Cliente
        invoiceType.setAccountingCustomerParty(buildCustomerPartyType(bean));

        // Moneda
        invoiceType.setDocumentCurrencyCode(TypeUtils.buildDocumentCurrencyCodeType(bean.getMoneda()));

        // Totales pagar/descuentos/otros cargos
        invoiceType.setLegalMonetaryTotal(buildMonetaryTotalType(bean));

        // Total impuestos IGV/ISC
        invoiceType.getTaxTotal().addAll(buildTaxTotalType(bean));

        // Firma
        invoiceType.getSignature().add(buildSignatureType(bean.getSupplier()));
        invoiceType.setUBLExtensions(buildUBLExtensionsType(bean));

        // Observaciones
        invoiceType.getNote().add(TypeUtils.buildNoteType(bean.getObservaciones()));

        // Detalle
        int i = 1;
        for (TypeLineBean lineBean : bean.getDetalle()) {
            invoiceType.getInvoiceLine().add(buildInvoiceLineType(i, bean.getMoneda(), lineBean));
            i++;
        }

        return invoiceType;
    }

    public static CreditNoteType toCreditNoteType(NoteBean bean, TimeZone timeZone) {
        oasis.names.specification.ubl.schema.xsd.creditnote_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.creditnote_2.ObjectFactory();
        CreditNoteType creditNoteType = factory.createCreditNoteType();

        // General config
        creditNoteType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        creditNoteType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        creditNoteType.setID(TypeUtils.buildIDType(bean.getSerie() + "-" + bean.getNumero()));

        // Fechas
        XMLGregorianCalendar issueDate = toGregorianCalendar(bean.getFechaEmision(), timeZone);
        creditNoteType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));

        // Proveedor
        creditNoteType.setAccountingSupplierParty(buildSupplierPartyType(bean.getSupplier()));

        // Cliente
        creditNoteType.setAccountingCustomerParty(buildCustomerPartyType(bean));

        // Moneda
        creditNoteType.setDocumentCurrencyCode(TypeUtils.buildDocumentCurrencyCodeType(bean.getMoneda()));

        // Totales pagar/descuentos/otros cargos
        creditNoteType.setLegalMonetaryTotal(buildMonetaryTotalType(bean));

        // Total impuestos IGV/ISC
        creditNoteType.getTaxTotal().addAll(buildTaxTotalType(bean));

        // Firma
        creditNoteType.getSignature().add(buildSignatureType(bean.getSupplier()));
        creditNoteType.setUBLExtensions(buildUBLExtensionsType(bean));

        // Observaciones
        creditNoteType.getNote().add(TypeUtils.buildNoteType(bean.getObservaciones()));

        // Invoice asociado
        InvoiceBean invoiceAfectado = bean.getInvoiceAfectado();
        String referenceID = invoiceAfectado.getSerie() + "-" + invoiceAfectado.getNumero();
        String descripcion = bean.getObservaciones() != null ? bean.getObservaciones() : "Sin observación";
        creditNoteType.getDiscrepancyResponse().add(TypeUtils.buildResponseType(referenceID, bean.getCodigoMotivo(), descripcion));


        BillingReferenceType billingReferenceType = new BillingReferenceType();
        billingReferenceType.setInvoiceDocumentReference(TypeUtils.buildDocumentReferenceType(referenceID, invoiceAfectado.getCodigoTipoComprobante()));
        creditNoteType.getBillingReference().add(billingReferenceType);

        // Detalle
        int i = 1;
        for (TypeLineBean lineBean : bean.getDetalle()) {
            creditNoteType.getCreditNoteLine().add(buildCreditNoteLineType(i, bean.getMoneda(), lineBean));
            i++;
        }

        return creditNoteType;
    }

    public static DebitNoteType toDebitNoteType(NoteBean bean, TimeZone timeZone) {
        oasis.names.specification.ubl.schema.xsd.debitnote_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.debitnote_2.ObjectFactory();
        DebitNoteType debitNoteType = factory.createDebitNoteType();

        // General config
        debitNoteType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        debitNoteType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        debitNoteType.setID(TypeUtils.buildIDType(bean.getSerie() + "-" + bean.getNumero()));

        // Fechas
        XMLGregorianCalendar issueDate = toGregorianCalendar(bean.getFechaEmision(), timeZone);
        debitNoteType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));

        // Proveedor
        debitNoteType.setAccountingSupplierParty(buildSupplierPartyType(bean.getSupplier()));

        // Cliente
        debitNoteType.setAccountingCustomerParty(buildCustomerPartyType(bean));

        // Moneda
        debitNoteType.setDocumentCurrencyCode(TypeUtils.buildDocumentCurrencyCodeType(bean.getMoneda()));

        // Totales pagar/descuentos/otros cargos
        debitNoteType.setRequestedMonetaryTotal(buildMonetaryTotalType(bean));

        // Total impuestos IGV/ISC
        debitNoteType.getTaxTotal().addAll(buildTaxTotalType(bean));

        // Firma
        debitNoteType.getSignature().add(buildSignatureType(bean.getSupplier()));
        debitNoteType.setUBLExtensions(buildUBLExtensionsType(bean));

        // Observaciones
        debitNoteType.getNote().add(TypeUtils.buildNoteType(bean.getObservaciones()));

        // Invoice asociado
        InvoiceBean invoiceAfectado = bean.getInvoiceAfectado();
        String referenceID = invoiceAfectado.getSerie() + "-" + invoiceAfectado.getNumero();
        String descripcion = bean.getObservaciones() != null ? bean.getObservaciones() : "Sin observación";
        debitNoteType.getDiscrepancyResponse().add(TypeUtils.buildResponseType(referenceID, bean.getCodigoMotivo(), descripcion));


        BillingReferenceType billingReferenceType = new BillingReferenceType();
        billingReferenceType.setInvoiceDocumentReference(TypeUtils.buildDocumentReferenceType(referenceID, invoiceAfectado.getCodigoTipoComprobante()));
        debitNoteType.getBillingReference().add(billingReferenceType);

        // Detalle
        int i = 1;
        for (TypeLineBean lineBean : bean.getDetalle()) {
            debitNoteType.getDebitNoteLine().add(buildDebitNoteLineType(i, bean.getMoneda(), lineBean));
            i++;
        }

        return debitNoteType;
    }

    public static VoidedDocumentsType toVoidedDocumentsType(BajaBean bean, TimeZone timeZone) {
        sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.ObjectFactory factory = new sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.ObjectFactory();
        VoidedDocumentsType voidedDocumentsType = factory.createVoidedDocumentsType();

        // General config
        voidedDocumentsType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        voidedDocumentsType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        voidedDocumentsType.setID(TypeUtils.buildIDType(bean.getSerie() + "-" + bean.getNumero()));

        // Fechas
        XMLGregorianCalendar issueDate = toGregorianCalendar(bean.getFechaEmision(), timeZone);
        voidedDocumentsType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));

        // Fecha de emisión del documento relacionado
        XMLGregorianCalendar referenceDate = toGregorianCalendar(bean.getInvoiceAfectado().getFechaEmision(), timeZone);
        voidedDocumentsType.setReferenceDate(TypeUtils.buildReferenceDateType(referenceDate));

        // Proveedor
        voidedDocumentsType.setAccountingSupplierParty(buildSupplierPartyType(bean.getSupplier()));

        // Detalle
        VoidedDocumentsLineType voidedDocumentsLineType = new VoidedDocumentsLineType();

        voidedDocumentsLineType.setLineID(TypeUtils.buildLineIDType("1"));
        voidedDocumentsLineType.setDocumentTypeCode(TypeUtils.buildDocumentTypeCodeType(bean.getInvoiceAfectado().getCodigoTipoComprobante()));
        voidedDocumentsLineType.setDocumentSerialID(TypeUtils.buildIdentifierType(bean.getInvoiceAfectado().getSerie()));
        voidedDocumentsLineType.setDocumentNumberID(TypeUtils.buildIdentifierType(String.valueOf(bean.getInvoiceAfectado().getNumero())));
        voidedDocumentsLineType.setVoidReasonDescription(TypeUtils.buildTextType(bean.getMotivoBaja()));

        return voidedDocumentsType;
    }

    private static XMLGregorianCalendar toGregorianCalendar(Date date, TimeZone zone) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(zone);
            String locale = sdf.format(date);

            return DatatypeFactory.newInstance().newXMLGregorianCalendar(locale);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static SupplierPartyType buildSupplierPartyType(SupplierBean bean) {
        SupplierPartyType supplierPartyType = new SupplierPartyType();

        // Numero DNI/RUC
        supplierPartyType.setCustomerAssignedAccountID(TypeUtils.buildCustomerAssignedAccountIDType(bean.getNumeroDocumento()));

        // Codigo de DNI/RUC
        supplierPartyType.getAdditionalAccountID().add(TypeUtils.buildAdditionalAccountIDType(bean.getCodigoTipoDocumento()));

        // Party
        PartyType partyType = new PartyType();

        // Party Name
        partyType.getPartyName().add(TypeUtils.buildPartyNameType(bean.getNombreComercial()));

        // Party Legal Entity
        partyType.getPartyLegalEntity().add(TypeUtils.buildPartyLegalEntityType(bean.getRazonSocial()));

        // Party Address
        AddressType addressType = new AddressType();
        addressType.setID(TypeUtils.buildIDType(bean.getCodigoTipoDocumento()));
        addressType.setStreetName(TypeUtils.buildStreetNameType(bean.getDireccion()));
        addressType.setCitySubdivisionName(TypeUtils.buildCitySubdivisionNameType(bean.getProvincia()));
        addressType.setCityName(TypeUtils.buildCityNameType(bean.getDistrito()));
        addressType.setCountrySubentity(TypeUtils.buildCountrySubEntityType(bean.getRegion()));
        addressType.setCountry(TypeUtils.buildCountryType(bean.getCodigoPais()));
        partyType.setPostalAddress(addressType);

        supplierPartyType.setParty(partyType);
        return supplierPartyType;
    }

    private static CustomerPartyType buildCustomerPartyType(TypeBean bean) {
        CustomerPartyType customerPartyType = new CustomerPartyType();

        // Numero DNI/RUC
        customerPartyType.setCustomerAssignedAccountID(TypeUtils.buildCustomerAssignedAccountIDType(bean.getNumeroDocumentoCliente()));

        // Código de DNI/RUC
        customerPartyType.getAdditionalAccountID().add(TypeUtils.buildAdditionalAccountIDType(bean.getCodigoTipoDocumentoCliente()));

        // Party
        PartyType partyType = new PartyType();

        // Party Name
        partyType.getPartyName().add(TypeUtils.buildPartyNameType(bean.getNombreCliente()));

        // Party Legal Entity
        partyType.getPartyLegalEntity().add(TypeUtils.buildPartyLegalEntityType(bean.getNombreCliente()));

        // Party Address
        AddressType addressType = new AddressType();
        addressType.setStreetName(TypeUtils.buildStreetNameType(bean.getDireccionCliente()));

        customerPartyType.setParty(partyType);
        return customerPartyType;
    }

    private static MonetaryTotalType buildMonetaryTotalType(TypeBean bean) {
        MonetaryTotalType monetaryTotalType = new MonetaryTotalType();
        monetaryTotalType.setAllowanceTotalAmount(TypeUtils.buildAllowanceTotalAmountType(bean.getMoneda(), bean.getTotalDescuentoGlobal()));
        monetaryTotalType.setChargeTotalAmount(TypeUtils.buildChargeTotalAmountType(bean.getMoneda(), bean.getTotalOtrosCargos()));
        monetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(bean.getMoneda(), bean.getTotalPagar()));
        return monetaryTotalType;
    }

    private static List<TaxTotalType> buildTaxTotalType(TypeBean bean) {
        List<TaxTotalType> result = new ArrayList<>();

        if (bean.getTotalIgv() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(TypeUtils.buildTaxAmountType(bean.getMoneda(), bean.getTotalIgv()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(bean.getMoneda(), bean.getTotalIgv(), TipoTributo.IGV));

            result.add(taxTotalType);
        }
        if (bean.getTotalIsc() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(TypeUtils.buildTaxAmountType(bean.getMoneda(), bean.getTotalIsc()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(bean.getMoneda(), bean.getTotalIsc(), TipoTributo.ISC));

            result.add(taxTotalType);
        }

        return result;
    }

    private static TaxSubtotalType buildTaxSubtotalType(String currency, BigDecimal value, TipoTributo tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(TypeUtils.buildTaxAmountType(currency, value));
        taxSubtotalType.setTaxCategory(TypeUtils.buildTaxCategoryType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCodigo()));
        return taxSubtotalType;
    }

    private static ExtensionContentType buildExtensionContentType() {
        return new ExtensionContentType();
    }

    private static UBLExtensionType buildUBLExtensionType() {
        UBLExtensionType ublExtensionType = new UBLExtensionType();
        ublExtensionType.setExtensionContent(buildExtensionContentType());
        return ublExtensionType;
    }

    private static UBLExtensionsType buildUBLExtensionsType(TypeBean bean) {
        UBLExtensionsType ublExtensionsType = new UBLExtensionsType();

        // Totales
        UBLExtensionType ublExtensionType1 = new UBLExtensionType();
        ExtensionContentType extensionContentType1 = new ExtensionContentType();
        ublExtensionType1.setExtensionContent(extensionContentType1);
        ublExtensionsType.getUBLExtension().add(ublExtensionType1);

        AdditionalInformationType additionalInformationType = buildAdditionalInformationType(bean);

        sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.ObjectFactory factory = new sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.ObjectFactory();
        JAXBElement<AdditionalInformationType> jaxbElement = factory.createAdditionalInformation(additionalInformationType);

        try {
            Element element = JaxbUtils.marshalToElement(ObjectFactory.class, jaxbElement);
            extensionContentType1.setAny(element);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // Firma Digital
        UBLExtensionType ublExtensionType2 = new UBLExtensionType();
        ExtensionContentType extensionContentType2 = new ExtensionContentType();
        ublExtensionType2.setExtensionContent(extensionContentType2);
        ublExtensionsType.getUBLExtension().add(ublExtensionType2);

        return ublExtensionsType;
    }

    private static sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType buildAdditionalInformationType(TypeBean bean) {
        sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType additionalInformationType = new sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType();

        if (bean.getTotalGravado() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRAVADAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(bean.getMoneda(), bean.getTotalGravado()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (bean.getTotalInafecto() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_INAFECTAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(bean.getMoneda(), bean.getTotalInafecto()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (bean.getTotalExonerado() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_EXONERADAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(bean.getMoneda(), bean.getTotalExonerado()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (bean.getTotalGratuito() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRATUITAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(bean.getMoneda(), bean.getTotalGratuito()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }

        // Monto en letras
        AdditionalPropertyType leyendaMontoTexto = new AdditionalPropertyType();
        leyendaMontoTexto.setID(TypeUtils.buildIDType(TipoElementosAdicionalesComprobante.MONTO_EN_LETRAS.getCodigo()));
        leyendaMontoTexto.setValue(TypeUtils.buildValueType(MoneyConverters.SPANISH_BANKING_MONEY_VALUE.asWords(bean.getTotalPagar())));

        additionalInformationType.getAdditionalProperty().add(leyendaMontoTexto);

        return additionalInformationType;
    }

    private static SignatureType buildSignatureType(SupplierBean bean) {
        SignatureType signatureType = new SignatureType();

        String signID = "IDSign" + bean.getRazonSocial().toUpperCase().replaceAll("\\s", "");
        signatureType.setID(TypeUtils.buildIDType(signID));


        PartyType partyType = new PartyType();
        partyType.getPartyName().add(TypeUtils.buildPartyNameType(bean.getNombreComercial()));
        partyType.getPartyIdentification().add(TypeUtils.buildPartyIdentificationType(bean.getNumeroDocumento()));

        signatureType.setSignatoryParty(partyType);

        String URI = "#signature" + bean.getRazonSocial().toUpperCase().replaceAll("\\s", "");
        AttachmentType attachmentType = new AttachmentType();
        attachmentType.setExternalReference(TypeUtils.buildExternalReferenceType(URI));
        signatureType.setDigitalSignatureAttachment(attachmentType);

        return signatureType;
    }

    private static InvoiceLineType buildInvoiceLineType(int index, String moneda, TypeLineBean lineBean) {
        InvoiceLineType invoiceLineType = new InvoiceLineType();

        invoiceLineType.setID(TypeUtils.buildIDType(String.valueOf(index)));
        invoiceLineType.setInvoicedQuantity(TypeUtils.buildInvoicedQuantityType(lineBean.getUnidadMedida(), lineBean.getCantidad()));
        invoiceLineType.setLineExtensionAmount(TypeUtils.buildLineExtensionAmountType(moneda, lineBean.getSubtotal()));
        invoiceLineType.setItem(TypeUtils.buildItemType(lineBean.getDescripcion()));
        invoiceLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
        invoiceLineType.setPrice(TypeUtils.buildPriceType(moneda, lineBean.getValorUnitario()));

        if (lineBean.getTotalIgv() != null) {
            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIgv(), lineBean.getCodigoTipoIgv(), TipoTributo.IGV));
        }
        if (lineBean.getTotalIsc() != null) {
            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIsc(), lineBean.getCodigoTipoIsc(), TipoTributo.ISC));
        }

        return invoiceLineType;
    }

    private static CreditNoteLineType buildCreditNoteLineType(int index, String moneda, TypeLineBean datosVentaModel) {
        CreditNoteLineType creditNoteLineType = new CreditNoteLineType();

        creditNoteLineType.setID(TypeUtils.buildIDType(String.valueOf(index)));
        creditNoteLineType.setCreditedQuantity(TypeUtils.buildCreditedQuantityType((datosVentaModel.getUnidadMedida()), datosVentaModel.getCantidad()));
        creditNoteLineType.setLineExtensionAmount(TypeUtils.buildLineExtensionAmountType(moneda, datosVentaModel.getSubtotal()));
        creditNoteLineType.setItem(TypeUtils.buildItemType(datosVentaModel.getDescripcion()));
        creditNoteLineType.setPricingReference(buildPricingReferenceType(moneda, datosVentaModel));
        creditNoteLineType.setPrice(TypeUtils.buildPriceType(moneda, datosVentaModel.getValorUnitario()));

        if (datosVentaModel.getTotalIgv() != null) {
            creditNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, datosVentaModel.getTotalIgv(), datosVentaModel.getCodigoTipoIgv(), TipoTributo.IGV));
        }
        if (datosVentaModel.getTotalIsc() != null) {
            creditNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, datosVentaModel.getTotalIsc(), datosVentaModel.getCodigoTipoIsc(), TipoTributo.ISC));
        }

        return creditNoteLineType;
    }

    private static DebitNoteLineType buildDebitNoteLineType(int index, String moneda, TypeLineBean lineBean) {
        DebitNoteLineType debitNoteLineType = new DebitNoteLineType();

        debitNoteLineType.setID(TypeUtils.buildIDType(String.valueOf(index)));
        debitNoteLineType.setDebitedQuantity(TypeUtils.buildDebitedQuantityType((lineBean.getUnidadMedida()), lineBean.getCantidad()));
        debitNoteLineType.setLineExtensionAmount(TypeUtils.buildLineExtensionAmountType(moneda, lineBean.getSubtotal()));
        debitNoteLineType.setItem(TypeUtils.buildItemType(lineBean.getDescripcion()));
        debitNoteLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
        debitNoteLineType.setPrice(TypeUtils.buildPriceType(moneda, lineBean.getValorUnitario()));

        if (lineBean.getTotalIgv() != null) {
            debitNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIgv(), lineBean.getCodigoTipoIgv(), TipoTributo.IGV));
        }
        if (lineBean.getTotalIsc() != null) {
            debitNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIsc(), lineBean.getCodigoTipoIsc(), TipoTributo.ISC));
        }

        return debitNoteLineType;
    }

    private static PricingReferenceType buildPricingReferenceType(String moneda, TypeLineBean lineBean) {
        PricingReferenceType pricingReferenceType = new PricingReferenceType();

        TipoAfectacionIgv tipoAfectacionIgv = TipoAfectacionIgv.searchFromCodigo(lineBean.getCodigoTipoIgv());
        if (tipoAfectacionIgv.isOperacionNoOnerosa()) {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    TypeUtils.buildPriceType(moneda, BigDecimal.ZERO, TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
            );
            pricingReferenceType.getAlternativeConditionPrice().add(
                    TypeUtils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.VALOR_REF_UNIT_EN_OPER_NO_ORENOSAS.getCodigo())
            );
        } else {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    TypeUtils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
            );
        }

        return pricingReferenceType;
    }

    private static TaxTotalType buildTaxTotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
        TaxTotalType taxTotalType = new TaxTotalType();
        taxTotalType.setTaxAmount(TypeUtils.buildTaxAmountType(currency, value));
        taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(currency, value, tipoIGV, tipoTributo));
        return taxTotalType;
    }

    private static TaxSubtotalType buildTaxSubtotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(TypeUtils.buildTaxAmountType(currency, value));
        taxSubtotalType.setTaxCategory(buildTaxCategoryType(tipoIGV, tipoTributo));
        return taxSubtotalType;
    }

    private static TaxCategoryType buildTaxCategoryType(String tipoIGV, TipoTributo tipoTributo) {
        TaxCategoryType taxCategoryType = new TaxCategoryType();
        taxCategoryType.setTaxScheme(TypeUtils.buildTaxSchemeType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCodigo()));
        taxCategoryType.setTaxExemptionReasonCode(TypeUtils.buildTaxExemptionReasonCodeType(tipoIGV));
        return taxCategoryType;
    }


}
