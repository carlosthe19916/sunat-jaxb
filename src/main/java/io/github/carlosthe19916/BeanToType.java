package io.github.carlosthe19916;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.exceptions.BeanException;
import io.github.carlosthe19916.exceptions.InvoiceBeanValidacionException;
import io.github.carlosthe19916.exceptions.NoteBeanValidacionException;
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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BeanToType {

    private BeanToType() {
        // Just util class
    }

    private static String concatenarSerieNumero(String serie, int numero) {
        return MessageFormat.format("{0}-{1}", serie, numero);
    }

    private static <T> Set<ConstraintViolation<T>> validate(T t) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator.validate(t);
    }

    public static InvoiceType toInvoiceType(InvoiceBean invoiceBean, TimeZone timeZone) throws InvoiceBeanValidacionException {
        Set<ConstraintViolation<InvoiceBean>> violations = validate(invoiceBean);
        if (violations.size() > 0) {
            throw new InvoiceBeanValidacionException("Invoice bean inválido", violations);
        }

        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
        InvoiceType invoiceType = factory.createInvoiceType();

        // General config
        invoiceType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        invoiceType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        String serieNumero = concatenarSerieNumero(invoiceBean.getSerie(), invoiceBean.getNumero());
        invoiceType.setID(TypeUtils.buildIDType(serieNumero));

        // Tipo comprobante boleta/factura
        String codigoTipoComprobante = invoiceBean.getCodigoTipoComprobante();
        invoiceType.setInvoiceTypeCode(TypeUtils.buildInvoiceTypeCodeType(codigoTipoComprobante));

        // Fechas
        FechaBean fechaBean = invoiceBean.getFecha();

        XMLGregorianCalendar issueDate = toGregorianCalendar(fechaBean.getFechaEmision(), timeZone);
        invoiceType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));
        if (fechaBean.getFechaVencimiento() != null) {
            XMLGregorianCalendar paymentDate = toGregorianCalendar(fechaBean.getFechaVencimiento(), timeZone);
            invoiceType.getPaymentMeans().add(TypeUtils.buildPaymentMeansType(paymentDate));
        }

        // Proveedor
        ProveedorBean proveedorBean = invoiceBean.getProveedor();
        invoiceType.setAccountingSupplierParty(buildSupplierPartyType(proveedorBean));

        // Cliente
        ClienteBean clienteBean = invoiceBean.getCliente();
        invoiceType.setAccountingCustomerParty(buildCustomerPartyType(clienteBean));

        // Moneda
        MonedaBean monedaBean = invoiceBean.getMoneda();
        invoiceType.setDocumentCurrencyCode(TypeUtils.buildDocumentCurrencyCodeType(monedaBean.getCodigo()));

        // Totales pagar/descuentos/otros cargos
        TotalBean totalBean = invoiceBean.getTotal();
        invoiceType.setLegalMonetaryTotal(buildMonetaryTotalType(monedaBean.getCodigo(), totalBean));

        // Total impuestos IGV/ISC
        ImpuestosBean impuestosBean = invoiceBean.getImpuestos();
        invoiceType.getTaxTotal().addAll(buildTaxTotalType(monedaBean.getCodigo(), impuestosBean));

        // Firma
        invoiceType.getSignature().add(buildSignatureType(invoiceBean.getProveedor()));

        // Extensiones
        TotalInformacionAdicionalBean totalInformacionAdicionalBean = invoiceBean.getTotalInformacionAdicional();
        invoiceType.setUBLExtensions(buildUBLExtensionsType(monedaBean.getCodigo(), totalInformacionAdicionalBean));

        // Observaciones
        invoiceType.getNote().add(TypeUtils.buildNoteType(invoiceBean.getObservaciones()));

        // Detalle
        int i = 1;
        for (DetalleBean lineBean : invoiceBean.getDetalle()) {
            invoiceType.getInvoiceLine().add(buildInvoiceLineType(i, monedaBean.getCodigo(), lineBean));
            i++;
        }

        return invoiceType;
    }

    public static CreditNoteType toCreditNoteType(NoteBean noteBean, TimeZone timeZone) throws NoteBeanValidacionException {
        Set<ConstraintViolation<NoteBean>> violations = validate(noteBean);
        if (violations.size() > 0) {
            throw new NoteBeanValidacionException("CreditNote bean inválido", violations);
        }

        oasis.names.specification.ubl.schema.xsd.creditnote_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.creditnote_2.ObjectFactory();
        CreditNoteType creditNoteType = factory.createCreditNoteType();

        // General config
        creditNoteType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        creditNoteType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        String serieNumero = concatenarSerieNumero(noteBean.getSerie(), noteBean.getNumero());
        creditNoteType.setID(TypeUtils.buildIDType(serieNumero));

        // Fechas
        FechaBean fechaBean = noteBean.getFecha();

        XMLGregorianCalendar issueDate = toGregorianCalendar(fechaBean.getFechaEmision(), timeZone);
        creditNoteType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));

        // Proveedor
        ProveedorBean proveedorBean = noteBean.getProveedor();
        creditNoteType.setAccountingSupplierParty(buildSupplierPartyType(proveedorBean));

        // Cliente
        ClienteBean clienteBean = noteBean.getCliente();
        creditNoteType.setAccountingCustomerParty(buildCustomerPartyType(clienteBean));

        // Moneda
        MonedaBean monedaBean = noteBean.getMoneda();
        creditNoteType.setDocumentCurrencyCode(TypeUtils.buildDocumentCurrencyCodeType(monedaBean.getCodigo()));

        // Totales pagar/descuentos/otros cargos
        TotalBean totalBean = noteBean.getTotal();
        creditNoteType.setLegalMonetaryTotal(buildMonetaryTotalType(monedaBean.getCodigo(), totalBean));

        // Total impuestos IGV/ISC
        ImpuestosBean impuestosBean = noteBean.getImpuestos();
        creditNoteType.getTaxTotal().addAll(buildTaxTotalType(monedaBean.getCodigo(), impuestosBean));

        // Firma
        creditNoteType.getSignature().add(buildSignatureType(noteBean.getProveedor()));

        // Extensions
        TotalInformacionAdicionalBean totalInformacionAdicionalBean = noteBean.getTotalInformacionAdicional();
        creditNoteType.setUBLExtensions(buildUBLExtensionsType(monedaBean.getCodigo(), totalInformacionAdicionalBean));

        // Observaciones
        creditNoteType.getNote().add(TypeUtils.buildNoteType(noteBean.getObservaciones()));

        // Invoice asociado
        InvoiceAfectadoBean invoiceAfectado = noteBean.getInvoiceAfectado();
        String referenceID = invoiceAfectado.getSerie() + "-" + invoiceAfectado.getNumero();
        String descripcion = noteBean.getObservaciones() != null ? noteBean.getObservaciones() : "Sin observación";
        creditNoteType.getDiscrepancyResponse().add(TypeUtils.buildResponseType(referenceID, noteBean.getCodigoMotivo(), descripcion));

        BillingReferenceType billingReferenceType = new BillingReferenceType();
        billingReferenceType.setInvoiceDocumentReference(TypeUtils.buildDocumentReferenceType(referenceID, invoiceAfectado.getCodigoTipoComprobante()));
        creditNoteType.getBillingReference().add(billingReferenceType);

        // Detalle
        int i = 1;
        for (DetalleBean lineBean : noteBean.getDetalle()) {
            creditNoteType.getCreditNoteLine().add(buildCreditNoteLineType(i, monedaBean.getCodigo(), lineBean));
            i++;
        }

        return creditNoteType;
    }

    public static DebitNoteType toDebitNoteType(NoteBean noteBean, TimeZone timeZone) throws NoteBeanValidacionException {
        Set<ConstraintViolation<NoteBean>> violations = validate(noteBean);
        if (violations.size() > 0) {
            throw new NoteBeanValidacionException("DebitNote bean inválido", violations);
        }

        oasis.names.specification.ubl.schema.xsd.debitnote_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.debitnote_2.ObjectFactory();
        DebitNoteType debitNoteType = factory.createDebitNoteType();

        // General config
        debitNoteType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        debitNoteType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        debitNoteType.setID(TypeUtils.buildIDType(concatenarSerieNumero(noteBean.getSerie(), noteBean.getNumero())));

        // Fechas
        FechaBean fechaBean = noteBean.getFecha();

        XMLGregorianCalendar issueDate = toGregorianCalendar(fechaBean.getFechaEmision(), timeZone);
        debitNoteType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));

        // Proveedor
        ProveedorBean proveedorBean = noteBean.getProveedor();
        debitNoteType.setAccountingSupplierParty(buildSupplierPartyType(proveedorBean));

        // Cliente
        ClienteBean clienteBean = noteBean.getCliente();
        debitNoteType.setAccountingCustomerParty(buildCustomerPartyType(clienteBean));

        // Moneda
        MonedaBean monedaBean = noteBean.getMoneda();
        debitNoteType.setDocumentCurrencyCode(TypeUtils.buildDocumentCurrencyCodeType(monedaBean.getCodigo()));

        // Totales pagar/descuentos/otros cargos
        TotalBean totalBean = noteBean.getTotal();
        debitNoteType.setRequestedMonetaryTotal(buildMonetaryTotalType(monedaBean.getCodigo(), totalBean));

        // Total impuestos IGV/ISC
        ImpuestosBean impuestosBean = noteBean.getImpuestos();
        debitNoteType.getTaxTotal().addAll(buildTaxTotalType(monedaBean.getCodigo(), impuestosBean));

        // Firma
        debitNoteType.getSignature().add(buildSignatureType(noteBean.getProveedor()));

        // Extensions
        TotalInformacionAdicionalBean totalInformacionAdicionalBean = noteBean.getTotalInformacionAdicional();
        debitNoteType.setUBLExtensions(buildUBLExtensionsType(monedaBean.getCodigo(), totalInformacionAdicionalBean));

        // Observaciones
        debitNoteType.getNote().add(TypeUtils.buildNoteType(noteBean.getObservaciones()));

        // Invoice asociado
        InvoiceAfectadoBean invoiceAfectado = noteBean.getInvoiceAfectado();
        String referenceID = invoiceAfectado.getSerie() + "-" + invoiceAfectado.getNumero();
        String descripcion = noteBean.getObservaciones() != null ? noteBean.getObservaciones() : "Sin observación";
        debitNoteType.getDiscrepancyResponse().add(TypeUtils.buildResponseType(referenceID, noteBean.getCodigoMotivo(), descripcion));


        BillingReferenceType billingReferenceType = new BillingReferenceType();
        billingReferenceType.setInvoiceDocumentReference(TypeUtils.buildDocumentReferenceType(referenceID, invoiceAfectado.getCodigoTipoComprobante()));
        debitNoteType.getBillingReference().add(billingReferenceType);

        // Detalle
        int i = 1;
        for (DetalleBean lineBean : noteBean.getDetalle()) {
            debitNoteType.getDebitNoteLine().add(buildDebitNoteLineType(i, monedaBean.getCodigo(), lineBean));
            i++;
        }

        return debitNoteType;
    }

    public static VoidedDocumentsType toVoidedDocumentsType(BajaBean bajaBean, TimeZone timeZone) {
        sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.ObjectFactory factory = new sunat.names.specification.ubl.peru.schema.xsd.voideddocuments_1.ObjectFactory();
        VoidedDocumentsType voidedDocumentsType = factory.createVoidedDocumentsType();

        // General config
        voidedDocumentsType.setUBLVersionID(TypeUtils.buildUBLVersionID("2.0"));
        voidedDocumentsType.setCustomizationID(TypeUtils.buildCustomizationIDType("1.0"));

        // documentId
        String serieNumero = concatenarSerieNumero(bajaBean.getSerie(), bajaBean.getNumero());
        voidedDocumentsType.setID(TypeUtils.buildIDType(serieNumero));

        // Fechas
        XMLGregorianCalendar issueDate = toGregorianCalendar(bajaBean.getFechaEmision(), timeZone);
        voidedDocumentsType.setIssueDate(TypeUtils.buildIssueDateType(issueDate));

        // Fecha de emisión del documento relacionado
        InvoiceAfectadoBean invoiceAfectadoBean = bajaBean.getInvoiceAfectado();

        XMLGregorianCalendar referenceDate = toGregorianCalendar(invoiceAfectadoBean.getFechaEmision(), timeZone);
        voidedDocumentsType.setReferenceDate(TypeUtils.buildReferenceDateType(referenceDate));

        // Proveedor
        voidedDocumentsType.setAccountingSupplierParty(buildSupplierPartyType(bajaBean.getSupplier()));

        // Detalle
        VoidedDocumentsLineType voidedDocumentsLineType = new VoidedDocumentsLineType();

        voidedDocumentsLineType.setLineID(TypeUtils.buildLineIDType("1"));
        voidedDocumentsLineType.setDocumentTypeCode(TypeUtils.buildDocumentTypeCodeType(bajaBean.getInvoiceAfectado().getCodigoTipoComprobante()));
        voidedDocumentsLineType.setDocumentSerialID(TypeUtils.buildIdentifierType(bajaBean.getInvoiceAfectado().getSerie()));
        voidedDocumentsLineType.setDocumentNumberID(TypeUtils.buildIdentifierType(String.valueOf(bajaBean.getInvoiceAfectado().getNumero())));
        voidedDocumentsLineType.setVoidReasonDescription(TypeUtils.buildTextType(bajaBean.getMotivoBaja()));

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

    private static SupplierPartyType buildSupplierPartyType(ProveedorBean proveedorBean) {
        SupplierPartyType supplierPartyType = new SupplierPartyType();

        // Numero DNI/RUC
        String numeroDocumento = proveedorBean.getNumeroDocumento();
        supplierPartyType.setCustomerAssignedAccountID(TypeUtils.buildCustomerAssignedAccountIDType(numeroDocumento));

        // Codigo de DNI/RUC
        String codigoTipoDocumento = proveedorBean.getCodigoTipoDocumento();
        supplierPartyType.getAdditionalAccountID().add(TypeUtils.buildAdditionalAccountIDType(codigoTipoDocumento));

        // Party
        PartyType partyType = new PartyType();

        // Party Name
        String nombreComercial = proveedorBean.getNombreComercial();
        partyType.getPartyName().add(TypeUtils.buildPartyNameType(nombreComercial));

        // Party Legal Entity
        String razonSocial = proveedorBean.getRazonSocial();
        partyType.getPartyLegalEntity().add(TypeUtils.buildPartyLegalEntityType(razonSocial));

        // Party Address
        AddressType addressType = new AddressType();
        addressType.setID(TypeUtils.buildIDType(proveedorBean.getCodigoPostal()));
        addressType.setStreetName(TypeUtils.buildStreetNameType(proveedorBean.getDireccion()));
        addressType.setDistrict(TypeUtils.buildDistrictType(proveedorBean.getDistrito()));
        addressType.setCityName(TypeUtils.buildCityNameType(proveedorBean.getProvincia()));
        addressType.setCountrySubentity(TypeUtils.buildCountrySubEntityType(proveedorBean.getRegion()));
        addressType.setCountry(TypeUtils.buildCountryType(proveedorBean.getCodigoPais()));
        partyType.setPostalAddress(addressType);

        supplierPartyType.setParty(partyType);
        return supplierPartyType;
    }

    private static CustomerPartyType buildCustomerPartyType(ClienteBean clienteBean) {
        CustomerPartyType customerPartyType = new CustomerPartyType();

        // Numero DNI/RUC
        String numeroDocumento = clienteBean.getNumeroDocumento();
        customerPartyType.setCustomerAssignedAccountID(TypeUtils.buildCustomerAssignedAccountIDType(numeroDocumento));

        // Código de DNI/RUC
        String codigoTipoDocumento = clienteBean.getCodigoTipoDocumento();
        customerPartyType.getAdditionalAccountID().add(TypeUtils.buildAdditionalAccountIDType(codigoTipoDocumento));

        // Party
        PartyType partyType = new PartyType();


        String nombre = clienteBean.getNombre();

        // Party Name
        partyType.getPartyName().add(TypeUtils.buildPartyNameType(nombre));
        // Party Legal Entity
        partyType.getPartyLegalEntity().add(TypeUtils.buildPartyLegalEntityType(nombre));

        // Party Address
        String direccion = clienteBean.getDireccion();
        AddressType addressType = new AddressType();
        addressType.setStreetName(TypeUtils.buildStreetNameType(direccion));

        customerPartyType.setParty(partyType);
        return customerPartyType;
    }

    private static MonetaryTotalType buildMonetaryTotalType(String codigoMoneda, TotalBean totalBean) {
        MonetaryTotalType monetaryTotalType = new MonetaryTotalType();
        monetaryTotalType.setAllowanceTotalAmount(TypeUtils.buildAllowanceTotalAmountType(codigoMoneda, totalBean.getDescuentoGlobal()));
        monetaryTotalType.setChargeTotalAmount(TypeUtils.buildChargeTotalAmountType(codigoMoneda, totalBean.getOtrosCargos()));
        monetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(codigoMoneda, totalBean.getPagar()));
        return monetaryTotalType;
    }

    private static List<TaxTotalType> buildTaxTotalType(String codigoMoneda, ImpuestosBean impuestosBean) {
        List<TaxTotalType> result = new ArrayList<>();

        if (impuestosBean.getIgv() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(TypeUtils.buildTaxAmountType(codigoMoneda, impuestosBean.getIgv()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(codigoMoneda, impuestosBean.getIgv(), TipoTributo.IGV));

            result.add(taxTotalType);
        }
        if (impuestosBean.getIsc() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(TypeUtils.buildTaxAmountType(codigoMoneda, impuestosBean.getIsc()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(codigoMoneda, impuestosBean.getIsc(), TipoTributo.ISC));

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

    private static UBLExtensionsType buildUBLExtensionsType(String codigoMoneda, TotalInformacionAdicionalBean totalInformacionAdicionalBean) {
        UBLExtensionsType ublExtensionsType = new UBLExtensionsType();

        // Totales
        UBLExtensionType ublExtensionType1 = new UBLExtensionType();
        ExtensionContentType extensionContentType1 = new ExtensionContentType();
        ublExtensionType1.setExtensionContent(extensionContentType1);
        ublExtensionsType.getUBLExtension().add(ublExtensionType1);

        AdditionalInformationType additionalInformationType = buildAdditionalInformationType(codigoMoneda, totalInformacionAdicionalBean);

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

    private static sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType buildAdditionalInformationType(String codigoMoneda, TotalInformacionAdicionalBean totalInformacionAdicionalBean) {
        sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType additionalInformationType = new sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType();

        if (totalInformacionAdicionalBean.getGravado() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRAVADAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getGravado()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getInafecto() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_INAFECTAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getInafecto()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getExonerado() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_EXONERADAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getExonerado()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getGratuito() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(TypeUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRATUITAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(TypeUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getGratuito()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }

        return additionalInformationType;
    }

    private static SignatureType buildSignatureType(ProveedorBean proveedorBean) {
        SignatureType signatureType = new SignatureType();

        String signID = "IDSign" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
        signatureType.setID(TypeUtils.buildIDType(signID));


        PartyType partyType = new PartyType();
        partyType.getPartyName().add(TypeUtils.buildPartyNameType(proveedorBean.getNombreComercial()));
        partyType.getPartyIdentification().add(TypeUtils.buildPartyIdentificationType(proveedorBean.getNumeroDocumento()));

        signatureType.setSignatoryParty(partyType);

        String URI = "#signature" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
        AttachmentType attachmentType = new AttachmentType();
        attachmentType.setExternalReference(TypeUtils.buildExternalReferenceType(URI));
        signatureType.setDigitalSignatureAttachment(attachmentType);

        return signatureType;
    }

    private static InvoiceLineType buildInvoiceLineType(int index, String moneda, DetalleBean lineBean) {
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

    private static CreditNoteLineType buildCreditNoteLineType(int index, String moneda, DetalleBean datosVentaModel) {
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

    private static DebitNoteLineType buildDebitNoteLineType(int index, String moneda, DetalleBean lineBean) {
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

    private static PricingReferenceType buildPricingReferenceType(String moneda, DetalleBean lineBean) {
        PricingReferenceType pricingReferenceType = new PricingReferenceType();

        TipoAfectacionIgv tipoAfectacionIgv = TipoAfectacionIgv.searchFromCodigo(lineBean.getCodigoTipoIgv()).orElseThrow(() -> new BeanException("Codigo de tipo de IGV invalido"));
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
