package io.github.carlosthe19916;

import com.helger.ubl20.UBL20Reader;
import com.helger.ubl21.UBL21Reader;
import com.helger.ubl21.UBL21Writer;
import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.beans.ubl.ubl20.GlobalUBL20Defaults;
import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;
import io.github.carlosthe19916.beans.ubl.ubl20.UBL20Defaults;
import io.github.carlosthe19916.beans.ubl.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.UBL21Defaults;
import io.github.carlosthe19916.exceptions.InvalidCodeException;
import io.github.carlosthe19916.exceptions.InvoiceBeanValidacionException;
import io.github.carlosthe19916.exceptions.NoteBeanValidacionException;
import io.github.carlosthe19916.sunat.TipoAfectacionIgv;
import io.github.carlosthe19916.sunat.TipoConceptosTributarios;
import io.github.carlosthe19916.sunat.TipoPrecioVentaUnitario;
import io.github.carlosthe19916.sunat.TipoTributo;
import io.github.carlosthe19916.utils.*;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.CompanyIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.ProfileIDType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionContentType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionsType;
import oasis.names.specification.ubl.schema.xsd.creditnote_2.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.debitnote_2.DebitNoteType;
import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import org.w3c.dom.Element;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalMonetaryTotalType;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.ObjectFactory;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.VoidedDocumentsLineType;
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

public class BeanToType21 {

    private BeanToType21() {
        // Just util class
    }

    private static String concatenarSerieNumero(String serie, int numero) {
        return MessageFormat.format("{0}-{1}", serie, numero);
    }

    public static InvoiceType toInvoice21Type(InvoiceBean invoice) throws InvoiceBeanValidacionException {
        UBL21Defaults globalUBL21Defaults = GlobalUBL21Defaults.getInstance();
        Invoice21Bean invoice21 = BeanUtils.applyDefaults(invoice, globalUBL21Defaults);

        Set<ConstraintViolation<InvoiceBean>> violations = BeanUtils.validate(invoice21);
        if (!violations.isEmpty()) {
            throw new InvoiceBeanValidacionException("Invalid bean", violations);
        }

        // Type
        oasis.names.specification.ubl.schema.xsd.invoice_21.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_21.ObjectFactory();
        oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType invoiceType = factory.createInvoiceType();

        invoiceType.setUBLVersionID(UBL21Utils.buildUBLVersionID("2.0"));
        invoiceType.setCustomizationID(UBL21Utils.buildCustomizationIDType("1.0"));

        // Profile
        ProfileIDType profileIDType = UBL21Utils.toProfileIDType(invoice21.getTipoOperacion());
        invoiceType.setProfileID(profileIDType);


        // Serie y numero
        String serieNumero = MessageFormat.format("{0}-{1}", invoice21.getSerie(), invoice21.globalUBL21Defaults());
        invoiceType.setID(UBL21Utils.buildIDType(serieNumero));

        // Fecha y hora de emision
        FechaBean fecha = invoice21.getFecha();
        invoiceType.setIssueDate(DateUtils.toGregorianCalendar(fecha.getFechaEmision(), fecha.getTimeZone()));
        invoiceType.setIssueTime(DateUtils.toGregorianCalendarTime(fecha.getFechaEmision(), fecha.getTimeZone()));

        // Fecha vencimiento
        if (invoice21.getFecha().getFechaVencimiento() != null) {
            XMLGregorianCalendar paymentDate = DateUtils.toGregorianCalendar(fecha.getFechaVencimiento(), fecha.getTimeZone());
            invoiceType.setDueDate(paymentDate);
        }

        // Tipo comprobante
        String codigoTipoComprobante = invoice21.getCodigoTipoComprobante();
        invoiceType.setInvoiceTypeCode(UBL21Utils.buildInvoiceTypeCodeType(codigoTipoComprobante));

        // Moneda
        MonedaBean moneda = invoice21.getMoneda();
        invoiceType.setDocumentCurrencyCode(UBL21Utils.buildDocumentCurrencyCodeType(moneda.getCodigo()));

        // Proveedor
        ProveedorBean proveedor = invoice21.getProveedor();
        invoiceType.setAccountingSupplierParty(buildSupplierPartyType(proveedor));

        // Cliente
        ClienteBean cliente = invoice21.getCliente();
        invoiceType.setAccountingCustomerParty(buildCustomerPartyType(cliente));

        // Totales pagar/descuentos/otros cargos
        Total21Bean total = invoice21.getTotal();
        invoiceType.setLegalMonetaryTotal(buildMonetaryTotalType(total, invoice21.getMoneda()));

        // Total impuestos IGV/ISC
        ImpuestosBean impuestos = invoice.getImpuestos();
        invoiceType.getTaxTotal().addAll(buildTaxTotalType(impuestos, invoice21.getMoneda()));

        return invoiceType;
    }

    private static void toInvoiceType(InvoiceBean invoice) throws InvoiceBeanValidacionException {
        Set<ConstraintViolation<InvoiceBean>> violations = validate(invoice);
        if (!violations.isEmpty()) {
            throw new InvoiceBeanValidacionException("Invoice bean inválido", violations);
        }

        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
        InvoiceType invoiceType = factory.createInvoiceType();


        // Firma
        invoiceType.getSignature().add(buildSignatureType(invoice.getProveedor()));

        // Extensiones
        TotalInformacionAdicionalBean totalInformacionAdicionalBean = invoice.getTotalInformacionAdicional();
        invoiceType.setUBLExtensions(buildUBLExtensionsType(monedaBean.getCodigo(), totalInformacionAdicionalBean));

        // Observaciones
        invoiceType.getNote().add(UBLUtils.buildNoteType(invoice.getObservaciones()));

        // Detalle
        int i = 1;
        for (DetalleBean lineBean : invoice.getDetalle()) {
            invoiceType.getInvoiceLine().add(buildInvoiceLineType(i, monedaBean.getCodigo(), lineBean));
            i++;
        }


    }

    public static CreditNoteType toCreditNoteType(NoteBean noteBean, TimeZone timeZone) throws NoteBeanValidacionException {
        Set<ConstraintViolation<NoteBean>> violations = validate(noteBean);
        if (!violations.isEmpty()) {
            throw new NoteBeanValidacionException("CreditNote bean inválido", violations);
        }

        oasis.names.specification.ubl.schema.xsd.creditnote_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.creditnote_2.ObjectFactory();
        CreditNoteType creditNoteType = factory.createCreditNoteType();

        // General config
        creditNoteType.setUBLVersionID(UBLUtils.buildUBLVersionID("2.0"));
        creditNoteType.setCustomizationID(UBLUtils.buildCustomizationIDType("1.0"));

        // documentId
        String serieNumero = concatenarSerieNumero(noteBean.getSerie(), noteBean.getNumero());
        creditNoteType.setID(UBLUtils.buildIDType(serieNumero));

        // Fechas
        FechaBean fechaBean = noteBean.getFecha();

        XMLGregorianCalendar issueDate = toGregorianCalendar(fechaBean.getFechaEmision(), timeZone);
        creditNoteType.setIssueDate(UBLUtils.buildIssueDateType(issueDate));

        // Proveedor
        ProveedorBean proveedorBean = noteBean.getProveedor();
        creditNoteType.setAccountingSupplierParty(buildSupplierPartyType(proveedorBean));

        // Cliente
        ClienteBean clienteBean = noteBean.getCliente();
        creditNoteType.setAccountingCustomerParty(buildCustomerPartyType(clienteBean));

        // Moneda
        MonedaBean monedaBean = noteBean.getMoneda();
        creditNoteType.setDocumentCurrencyCode(UBLUtils.buildDocumentCurrencyCodeType(monedaBean.getCodigo()));

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
        creditNoteType.getNote().add(UBLUtils.buildNoteType(noteBean.getObservaciones()));

        // Invoice asociado
        InvoiceAfectadoBean invoiceAfectado = noteBean.getInvoiceAfectado();
        String referenceID = invoiceAfectado.getSerie() + "-" + invoiceAfectado.getNumero();
        String descripcion = noteBean.getObservaciones() != null ? noteBean.getObservaciones() : "Sin observación";
        creditNoteType.getDiscrepancyResponse().add(UBLUtils.buildResponseType(referenceID, noteBean.getCodigoMotivo(), descripcion));

        BillingReferenceType billingReferenceType = new BillingReferenceType();
        billingReferenceType.setInvoiceDocumentReference(UBLUtils.buildDocumentReferenceType(referenceID, invoiceAfectado.getCodigoTipoComprobante()));
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
        if (!violations.isEmpty()) {
            throw new NoteBeanValidacionException("DebitNote bean inválido", violations);
        }

        oasis.names.specification.ubl.schema.xsd.debitnote_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.debitnote_2.ObjectFactory();
        DebitNoteType debitNoteType = factory.createDebitNoteType();

        // General config
        debitNoteType.setUBLVersionID(UBLUtils.buildUBLVersionID("2.0"));
        debitNoteType.setCustomizationID(UBLUtils.buildCustomizationIDType("1.0"));

        // documentId
        debitNoteType.setID(UBLUtils.buildIDType(concatenarSerieNumero(noteBean.getSerie(), noteBean.getNumero())));

        // Fechas
        FechaBean fechaBean = noteBean.getFecha();

        XMLGregorianCalendar issueDate = toGregorianCalendar(fechaBean.getFechaEmision(), timeZone);
        debitNoteType.setIssueDate(UBLUtils.buildIssueDateType(issueDate));

        // Proveedor
        ProveedorBean proveedorBean = noteBean.getProveedor();
        debitNoteType.setAccountingSupplierParty(buildSupplierPartyType(proveedorBean));

        // Cliente
        ClienteBean clienteBean = noteBean.getCliente();
        debitNoteType.setAccountingCustomerParty(buildCustomerPartyType(clienteBean));

        // Moneda
        MonedaBean monedaBean = noteBean.getMoneda();
        debitNoteType.setDocumentCurrencyCode(UBLUtils.buildDocumentCurrencyCodeType(monedaBean.getCodigo()));

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
        debitNoteType.getNote().add(UBLUtils.buildNoteType(noteBean.getObservaciones()));

        // Invoice asociado
        InvoiceAfectadoBean invoiceAfectado = noteBean.getInvoiceAfectado();
        String referenceID = invoiceAfectado.getSerie() + "-" + invoiceAfectado.getNumero();
        String descripcion = noteBean.getObservaciones() != null ? noteBean.getObservaciones() : "Sin observación";
        debitNoteType.getDiscrepancyResponse().add(UBLUtils.buildResponseType(referenceID, noteBean.getCodigoMotivo(), descripcion));


        BillingReferenceType billingReferenceType = new BillingReferenceType();
        billingReferenceType.setInvoiceDocumentReference(UBLUtils.buildDocumentReferenceType(referenceID, invoiceAfectado.getCodigoTipoComprobante()));
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
        voidedDocumentsType.setUBLVersionID(UBLUtils.buildUBLVersionID("2.0"));
        voidedDocumentsType.setCustomizationID(UBLUtils.buildCustomizationIDType("1.0"));

        // documentId
        String serieNumero = concatenarSerieNumero(bajaBean.getSerie(), bajaBean.getNumero());
        voidedDocumentsType.setID(UBLUtils.buildIDType(serieNumero));

        // Fechas
        XMLGregorianCalendar issueDate = toGregorianCalendar(bajaBean.getFechaEmision(), timeZone);
        voidedDocumentsType.setIssueDate(UBLUtils.buildIssueDateType(issueDate));

        // Fecha de emisión del documento relacionado
        InvoiceAfectadoBean invoiceAfectadoBean = bajaBean.getInvoiceAfectado();

        XMLGregorianCalendar referenceDate = toGregorianCalendar(invoiceAfectadoBean.getFechaEmision(), timeZone);
        voidedDocumentsType.setReferenceDate(UBLUtils.buildReferenceDateType(referenceDate));

        // Proveedor
        voidedDocumentsType.setAccountingSupplierParty(buildSupplierPartyType(bajaBean.getSupplier()));

        // Detalle
        VoidedDocumentsLineType voidedDocumentsLineType = new VoidedDocumentsLineType();

        voidedDocumentsLineType.setLineID(UBLUtils.buildLineIDType("1"));
        voidedDocumentsLineType.setDocumentTypeCode(UBLUtils.buildDocumentTypeCodeType(bajaBean.getInvoiceAfectado().getCodigoTipoComprobante()));
        voidedDocumentsLineType.setDocumentSerialID(UBLUtils.buildIdentifierType(bajaBean.getInvoiceAfectado().getSerie()));
        voidedDocumentsLineType.setDocumentNumberID(UBLUtils.buildIdentifierType(String.valueOf(bajaBean.getInvoiceAfectado().getNumero())));
        voidedDocumentsLineType.setVoidReasonDescription(UBLUtils.buildTextType(bajaBean.getMotivoBaja()));

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


    // Proveedor

    private static SupplierPartyType buildSupplierPartyType(ProveedorBean proveedor) {
        SupplierPartyType supplierPartyType = new SupplierPartyType();

        PartyType partyType = new PartyType();
        supplierPartyType.setParty(partyType);

        PartyTaxSchemeType partyTaxSchemeType = new PartyTaxSchemeType();
        partyType.addPartyTaxScheme(partyTaxSchemeType);

        AddressType addressType = new AddressType();
        partyTaxSchemeType.setRegistrationAddress(addressType);

        // Documento identidad
        String numeroDocumento = proveedor.getNumeroDocumento();
        String codigoTipoDocumento = proveedor.getCodigoTipoDocumento();

        CompanyIDType companyIDType = UBL21Utils.buildCompanyIDType(numeroDocumento);
        companyIDType.setSchemeID(codigoTipoDocumento);

        partyTaxSchemeType.setCompanyID(companyIDType);

        // Nombre comercial
        String nombreComercial = proveedor.getNombreComercial();
        partyType.getPartyName().add(UBL21Utils.buildPartyNameType(nombreComercial));

        // Razon social
        String razonSocial = proveedor.getRazonSocial();
        partyTaxSchemeType.setRegistrationName(razonSocial);

        // Domicilio fiscal del emisor
        String codigoPostal = proveedor.getCodigoPostal();
        addressType.setAddressTypeCode(codigoPostal);

        return supplierPartyType;
    }

    private static CustomerPartyType buildCustomerPartyType(ClienteBean cliente) {
        CustomerPartyType customerPartyType = new CustomerPartyType();

        PartyType partyType = new PartyType();
        customerPartyType.setParty(partyType);

        PartyTaxSchemeType partyTaxSchemeType = new PartyTaxSchemeType();
        partyType.addPartyTaxScheme(partyTaxSchemeType);

        // Documento identidad
        String numeroDocumento = cliente.getNumeroDocumento();
        String codigoTipoDocumento = cliente.getCodigoTipoDocumento();

        CompanyIDType companyIDType = UBL21Utils.buildCompanyIDType(numeroDocumento);
        companyIDType.setSchemeID(codigoTipoDocumento);

        partyTaxSchemeType.setCompanyID(companyIDType);

        // Razon social
        String nombre = cliente.getNombre();
        partyTaxSchemeType.setRegistrationName(nombre);

        return customerPartyType;
    }

    private static MonetaryTotalType buildMonetaryTotalType(Total21Bean total, MonedaBean moneda) {
        MonetaryTotalType monetaryTotalType = new MonetaryTotalType();
        monetaryTotalType.setAllowanceTotalAmount(UBL21Utils.buildAllowanceTotalAmountType(total.getDescuentoGlobal(), moneda.getCodigo()));
        monetaryTotalType.setChargeTotalAmount(UBL21Utils.buildChargeTotalAmountType(total.getOtrosCargos(), moneda.getCodigo()));
        monetaryTotalType.setPayableAmount(UBL21Utils.buildPayableAmountType(total.getPagar(), moneda.getCodigo()));

        monetaryTotalType.setLineExtensionAmount(UBL21Utils.buildLineExtensionAmountType(total.getExtensionAmount(), moneda.getCodigo()));
        monetaryTotalType.setTaxInclusiveAmount(UBL21Utils.buildTaxInclusiveAmountType(total.getInclusiveAmount(), moneda.getCodigo()));
        monetaryTotalType.setPrepaidAmount(UBL21Utils.buildPrepaidAmountType(total.getAnticipos(), moneda.getCodigo()));

        return monetaryTotalType;
    }

    private static List<TaxTotalType> buildTaxTotalType(ImpuestosBean impuestosBean, MonedaBean moneda) {
        List<TaxTotalType> result = new ArrayList<>();

        if (impuestosBean.getIgv() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(impuestosBean.getIgv(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getIgv(), moneda.getCodigo(), TipoTributo.IGV));

            result.add(taxTotalType);
        }
        if (impuestosBean.getIsc() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(codigoMoneda, impuestosBean.getIsc()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(codigoMoneda, impuestosBean.getIsc(), TipoTributo.ISC));

            result.add(taxTotalType);
        }

        return result;
    }

    private static TaxSubtotalType buildTaxSubtotalType(BigDecimal value, String currency, TipoTributo tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(value, currency));
        taxSubtotalType.setTaxCategory(UBL21Utils.buildTaxCategoryType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCodigo()));
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
            throw new RuntimeException(e);
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
            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRAVADAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getGravado()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getInafecto() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_INAFECTAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getInafecto()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getExonerado() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_EXONERADAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getExonerado()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getGratuito() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRATUITAS.getCodigo()));
            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getGratuito()));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }

        return additionalInformationType;
    }

    private static SignatureType buildSignatureType(ProveedorBean proveedorBean) {
        SignatureType signatureType = new SignatureType();

        String signID = "IDSign" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
        signatureType.setID(UBLUtils.buildIDType(signID));


        PartyType partyType = new PartyType();
        partyType.getPartyName().add(UBLUtils.buildPartyNameType(proveedorBean.getNombreComercial()));
        partyType.getPartyIdentification().add(UBLUtils.buildPartyIdentificationType(proveedorBean.getNumeroDocumento()));

        signatureType.setSignatoryParty(partyType);

        String URI = "#signature" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
        AttachmentType attachmentType = new AttachmentType();
        attachmentType.setExternalReference(UBLUtils.buildExternalReferenceType(URI));
        signatureType.setDigitalSignatureAttachment(attachmentType);

        return signatureType;
    }

    private static InvoiceLineType buildInvoiceLineType(int index, String moneda, DetalleBean lineBean) {
        InvoiceLineType invoiceLineType = new InvoiceLineType();

        invoiceLineType.setID(UBLUtils.buildIDType(String.valueOf(index)));
        invoiceLineType.setInvoicedQuantity(UBLUtils.buildInvoicedQuantityType(lineBean.getUnidadMedida(), lineBean.getCantidad()));
        invoiceLineType.setLineExtensionAmount(UBLUtils.buildLineExtensionAmountType(moneda, lineBean.getSubtotal()));
        invoiceLineType.setItem(UBLUtils.buildItemType(lineBean.getDescripcion()));
        invoiceLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
        invoiceLineType.setPrice(UBLUtils.buildPriceType(moneda, lineBean.getValorUnitario()));

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

        creditNoteLineType.setID(UBLUtils.buildIDType(String.valueOf(index)));
        creditNoteLineType.setCreditedQuantity(UBLUtils.buildCreditedQuantityType((datosVentaModel.getUnidadMedida()), datosVentaModel.getCantidad()));
        creditNoteLineType.setLineExtensionAmount(UBLUtils.buildLineExtensionAmountType(moneda, datosVentaModel.getSubtotal()));
        creditNoteLineType.setItem(UBLUtils.buildItemType(datosVentaModel.getDescripcion()));
        creditNoteLineType.setPricingReference(buildPricingReferenceType(moneda, datosVentaModel));
        creditNoteLineType.setPrice(UBLUtils.buildPriceType(moneda, datosVentaModel.getValorUnitario()));

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

        debitNoteLineType.setID(UBLUtils.buildIDType(String.valueOf(index)));
        debitNoteLineType.setDebitedQuantity(UBLUtils.buildDebitedQuantityType((lineBean.getUnidadMedida()), lineBean.getCantidad()));
        debitNoteLineType.setLineExtensionAmount(UBLUtils.buildLineExtensionAmountType(moneda, lineBean.getSubtotal()));
        debitNoteLineType.setItem(UBLUtils.buildItemType(lineBean.getDescripcion()));
        debitNoteLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
        debitNoteLineType.setPrice(UBLUtils.buildPriceType(moneda, lineBean.getValorUnitario()));

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

        TipoAfectacionIgv tipoAfectacionIgv = TipoAfectacionIgv.searchFromCodigo(lineBean.getCodigoTipoIgv()).orElseThrow(() -> new InvalidCodeException("Codigo de tipo de IGV invalido"));
        if (tipoAfectacionIgv.isOperacionNoOnerosa()) {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBLUtils.buildPriceType(moneda, BigDecimal.ZERO, TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
            );
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBLUtils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.VALOR_REF_UNIT_EN_OPER_NO_ORENOSAS.getCodigo())
            );
        } else {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBLUtils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
            );
        }

        return pricingReferenceType;
    }

    private static TaxTotalType buildTaxTotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
        TaxTotalType taxTotalType = new TaxTotalType();
        taxTotalType.setTaxAmount(UBLUtils.buildTaxAmountType(currency, value));
        taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(currency, value, tipoIGV, tipoTributo));
        return taxTotalType;
    }

    private static TaxSubtotalType buildTaxSubtotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBLUtils.buildTaxAmountType(currency, value));
        taxSubtotalType.setTaxCategory(buildTaxCategoryType(tipoIGV, tipoTributo));
        return taxSubtotalType;
    }

    private static TaxCategoryType buildTaxCategoryType(String tipoIGV, TipoTributo tipoTributo) {
        TaxCategoryType taxCategoryType = new TaxCategoryType();
        taxCategoryType.setTaxScheme(UBLUtils.buildTaxSchemeType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCodigo()));
        taxCategoryType.setTaxExemptionReasonCode(UBLUtils.buildTaxExemptionReasonCodeType(tipoIGV));
        return taxCategoryType;
    }


}
