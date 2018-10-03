package io.github.carlosthe19916.types;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.beans.catalogs.TipoTributo;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.config.ubl21.UBL21Defaults;
import io.github.carlosthe19916.beans.exceptions.Invoice20BeanValidacionException;
import io.github.carlosthe19916.beans.exceptions.Invoice21BeanValidacionException;
import io.github.carlosthe19916.beans.ubl.ubl21.Impuestos21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;
import io.github.carlosthe19916.utils.BeanUtils;
import io.github.carlosthe19916.utils.DateUtils;
import io.github.carlosthe19916.utils.UBL21Utils;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.CompanyIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.ProfileIDType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;

import javax.validation.ConstraintViolation;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanToType21 {

    private BeanToType21() {
        // Just util class
    }

    public static InvoiceType toInvoiceType(Invoice21Bean invoice) throws Invoice21BeanValidacionException {
        Set<ConstraintViolation<Invoice21Bean>> violations = BeanUtils.validate(invoice);
        if (!violations.isEmpty()) {
            throw new Invoice21BeanValidacionException("Invalid bean", violations);
        }

        // Type
        oasis.names.specification.ubl.schema.xsd.invoice_21.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_21.ObjectFactory();
        oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType invoiceType = factory.createInvoiceType();

        invoiceType.setUBLVersionID(UBL21Utils.buildUBLVersionID("2.0"));
        invoiceType.setCustomizationID(UBL21Utils.buildCustomizationIDType("1.0"));

        // Profile
        ProfileIDType profileIDType = UBL21Utils.toProfileIDType(invoice.getTipoOperacion());
        invoiceType.setProfileID(profileIDType);


        // Serie y numero
        String serieNumero = MessageFormat.format("{0}-{1}", invoice.getSerie(), invoice.getNumero());
        invoiceType.setID(UBL21Utils.buildIDType(serieNumero));

        // Fecha y hora de emision
        FechaBean fecha = invoice.getFecha();
        invoiceType.setIssueDate(DateUtils.toGregorianCalendar(fecha.getFechaEmision(), fecha.getTimeZone()));
        invoiceType.setIssueTime(DateUtils.toGregorianCalendarTime(fecha.getFechaEmision(), fecha.getTimeZone()));

        // Fecha vencimiento
        if (invoice.getFecha().getFechaVencimiento() != null) {
            XMLGregorianCalendar paymentDate = DateUtils.toGregorianCalendar(fecha.getFechaVencimiento(), fecha.getTimeZone());
            invoiceType.setDueDate(paymentDate);
        }

        // Tipo comprobante
        String codigoTipoComprobante = invoice.getCodigoTipoComprobante();
        invoiceType.setInvoiceTypeCode(UBL21Utils.buildInvoiceTypeCodeType(codigoTipoComprobante));

        // Moneda
        MonedaBean moneda = invoice.getMoneda();
        invoiceType.setDocumentCurrencyCode(UBL21Utils.buildDocumentCurrencyCodeType(moneda.getCodigo()));

        // Proveedor
        ProveedorBean proveedor = invoice.getProveedor();
        invoiceType.setAccountingSupplierParty(buildSupplierPartyType(proveedor));

        // Cliente
        ClienteBean cliente = invoice.getCliente();
        invoiceType.setAccountingCustomerParty(buildCustomerPartyType(cliente));

        // Totales pagar/descuentos/otros cargos
        Total21Bean total = invoice.getTotal();
        invoiceType.setLegalMonetaryTotal(buildMonetaryTotalType(total, invoice.getMoneda()));

        // Total impuestos IGV/ISC
        Impuestos21Bean impuestos = invoice.getImpuestos();
        invoiceType.getTaxTotal().addAll(buildTaxTotalType(impuestos, invoice.getTotalInformacionAdicional(), invoice.getMoneda()));

        return invoiceType;
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


    // Cliente

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


    // Total a pagar

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


    // Impuestos

    private static List<TaxTotalType> buildTaxTotalType(Impuestos21Bean impuestosBean, TotalInformacionAdicionalBean totalInformacionAdicional, MonedaBean moneda) {
        List<TaxTotalType> result = new ArrayList<>();

        if (impuestosBean.getIgv() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(impuestosBean.getIgv(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getIgv(), impuestosBean.getIgvAfecto(), moneda.getCodigo(), TipoTributo.IGV));

            result.add(taxTotalType);
        }
        if (impuestosBean.getIsc() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(impuestosBean.getIsc(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getIsc(), impuestosBean.getIscAfecto(), moneda.getCodigo(), TipoTributo.ISC));

            result.add(taxTotalType);
        }
        if (impuestosBean.getOtros() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(impuestosBean.getOtros(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getOtros(), impuestosBean.getOtrosAfecto(), moneda.getCodigo(), TipoTributo.OTROS));

            result.add(taxTotalType);
        }

        return result;
    }

    private static TaxSubtotalType buildTaxSubtotalType(BigDecimal taxAmount, BigDecimal taxableAmount, String currency, TipoTributo tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(taxAmount, currency));
        taxSubtotalType.setTaxCategory(UBL21Utils.buildTaxCategoryType(tipoTributo.getCategoria(), tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCodigo()));
        taxSubtotalType.setTaxableAmount(UBL21Utils.buildTaxableAmountType(taxableAmount, currency));
        return taxSubtotalType;
    }


//    private static ExtensionContentType buildExtensionContentType() {
//        return new ExtensionContentType();
//    }
//
//    private static UBLExtensionType buildUBLExtensionType() {
//        UBLExtensionType ublExtensionType = new UBLExtensionType();
//        ublExtensionType.setExtensionContent(buildExtensionContentType());
//        return ublExtensionType;
//    }
//
//    private static UBLExtensionsType buildUBLExtensionsType(String codigoMoneda, TotalInformacionAdicionalBean totalInformacionAdicionalBean) {
//        UBLExtensionsType ublExtensionsType = new UBLExtensionsType();
//
//        // Totales
//        UBLExtensionType ublExtensionType1 = new UBLExtensionType();
//        ExtensionContentType extensionContentType1 = new ExtensionContentType();
//        ublExtensionType1.setExtensionContent(extensionContentType1);
//        ublExtensionsType.getUBLExtension().add(ublExtensionType1);
//
//        AdditionalInformationType additionalInformationType = buildAdditionalInformationType(codigoMoneda, totalInformacionAdicionalBean);
//
//        sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.ObjectFactory factory = new sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.ObjectFactory();
//        JAXBElement<AdditionalInformationType> jaxbElement = factory.createAdditionalInformation(additionalInformationType);
//
//        try {
//            Element element = JaxbUtils.marshalToElement(ObjectFactory.class, jaxbElement);
//            extensionContentType1.setAny(element);
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Firma Digital
//        UBLExtensionType ublExtensionType2 = new UBLExtensionType();
//        ExtensionContentType extensionContentType2 = new ExtensionContentType();
//        ublExtensionType2.setExtensionContent(extensionContentType2);
//        ublExtensionsType.getUBLExtension().add(ublExtensionType2);
//
//        return ublExtensionsType;
//    }
//
//    private static sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType buildAdditionalInformationType(String codigoMoneda, TotalInformacionAdicionalBean totalInformacionAdicionalBean) {
//        sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType additionalInformationType = new sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType();
//
//        if (totalInformacionAdicionalBean.getGravado() != null) {
//            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
//            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRAVADAS.getCodigo()));
//            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getGravado()));
//
//            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
//        }
//        if (totalInformacionAdicionalBean.getInafecto() != null) {
//            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
//            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_INAFECTAS.getCodigo()));
//            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getInafecto()));
//
//            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
//        }
//        if (totalInformacionAdicionalBean.getExonerado() != null) {
//            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
//            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_EXONERADAS.getCodigo()));
//            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getExonerado()));
//
//            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
//        }
//        if (totalInformacionAdicionalBean.getGratuito() != null) {
//            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
//            additionalMonetaryTotalType.setID(UBLUtils.buildIDType(TipoConceptosTributarios.TOTAL_VALOR_VENTA_OPERACIONES_GRATUITAS.getCodigo()));
//            additionalMonetaryTotalType.setPayableAmount(UBLUtils.buildPayableAmountType(codigoMoneda, totalInformacionAdicionalBean.getGratuito()));
//
//            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
//        }
//
//        return additionalInformationType;
//    }
//
//    private static SignatureType buildSignatureType(ProveedorBean proveedorBean) {
//        SignatureType signatureType = new SignatureType();
//
//        String signID = "IDSign" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
//        signatureType.setID(UBLUtils.buildIDType(signID));
//
//
//        PartyType partyType = new PartyType();
//        partyType.getPartyName().add(UBLUtils.buildPartyNameType(proveedorBean.getNombreComercial()));
//        partyType.getPartyIdentification().add(UBLUtils.buildPartyIdentificationType(proveedorBean.getNumeroDocumento()));
//
//        signatureType.setSignatoryParty(partyType);
//
//        String URI = "#signature" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
//        AttachmentType attachmentType = new AttachmentType();
//        attachmentType.setExternalReference(UBLUtils.buildExternalReferenceType(URI));
//        signatureType.setDigitalSignatureAttachment(attachmentType);
//
//        return signatureType;
//    }
//
//    private static InvoiceLineType buildInvoiceLineType(int index, String moneda, DetalleBean lineBean) {
//        InvoiceLineType invoiceLineType = new InvoiceLineType();
//
//        invoiceLineType.setID(UBLUtils.buildIDType(String.valueOf(index)));
//        invoiceLineType.setInvoicedQuantity(UBLUtils.buildInvoicedQuantityType(lineBean.getUnidadMedida(), lineBean.getCantidad()));
//        invoiceLineType.setLineExtensionAmount(UBLUtils.buildLineExtensionAmountType(moneda, lineBean.getSubtotal()));
//        invoiceLineType.setItem(UBLUtils.buildItemType(lineBean.getDescripcion()));
//        invoiceLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
//        invoiceLineType.setPrice(UBLUtils.buildPriceType(moneda, lineBean.getValorUnitario()));
//
//        if (lineBean.getTotalIgv() != null) {
//            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIgv(), lineBean.getCodigoTipoIgv(), TipoTributo.IGV));
//        }
//        if (lineBean.getTotalIsc() != null) {
//            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIsc(), lineBean.getCodigoTipoIsc(), TipoTributo.ISC));
//        }
//
//        return invoiceLineType;
//    }
//
//    private static CreditNoteLineType buildCreditNoteLineType(int index, String moneda, DetalleBean datosVentaModel) {
//        CreditNoteLineType creditNoteLineType = new CreditNoteLineType();
//
//        creditNoteLineType.setID(UBLUtils.buildIDType(String.valueOf(index)));
//        creditNoteLineType.setCreditedQuantity(UBLUtils.buildCreditedQuantityType((datosVentaModel.getUnidadMedida()), datosVentaModel.getCantidad()));
//        creditNoteLineType.setLineExtensionAmount(UBLUtils.buildLineExtensionAmountType(moneda, datosVentaModel.getSubtotal()));
//        creditNoteLineType.setItem(UBLUtils.buildItemType(datosVentaModel.getDescripcion()));
//        creditNoteLineType.setPricingReference(buildPricingReferenceType(moneda, datosVentaModel));
//        creditNoteLineType.setPrice(UBLUtils.buildPriceType(moneda, datosVentaModel.getValorUnitario()));
//
//        if (datosVentaModel.getTotalIgv() != null) {
//            creditNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, datosVentaModel.getTotalIgv(), datosVentaModel.getCodigoTipoIgv(), TipoTributo.IGV));
//        }
//        if (datosVentaModel.getTotalIsc() != null) {
//            creditNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, datosVentaModel.getTotalIsc(), datosVentaModel.getCodigoTipoIsc(), TipoTributo.ISC));
//        }
//
//        return creditNoteLineType;
//    }
//
//    private static DebitNoteLineType buildDebitNoteLineType(int index, String moneda, DetalleBean lineBean) {
//        DebitNoteLineType debitNoteLineType = new DebitNoteLineType();
//
//        debitNoteLineType.setID(UBLUtils.buildIDType(String.valueOf(index)));
//        debitNoteLineType.setDebitedQuantity(UBLUtils.buildDebitedQuantityType((lineBean.getUnidadMedida()), lineBean.getCantidad()));
//        debitNoteLineType.setLineExtensionAmount(UBLUtils.buildLineExtensionAmountType(moneda, lineBean.getSubtotal()));
//        debitNoteLineType.setItem(UBLUtils.buildItemType(lineBean.getDescripcion()));
//        debitNoteLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
//        debitNoteLineType.setPrice(UBLUtils.buildPriceType(moneda, lineBean.getValorUnitario()));
//
//        if (lineBean.getTotalIgv() != null) {
//            debitNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIgv(), lineBean.getCodigoTipoIgv(), TipoTributo.IGV));
//        }
//        if (lineBean.getTotalIsc() != null) {
//            debitNoteLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIsc(), lineBean.getCodigoTipoIsc(), TipoTributo.ISC));
//        }
//
//        return debitNoteLineType;
//    }
//
//    private static PricingReferenceType buildPricingReferenceType(String moneda, DetalleBean lineBean) {
//        PricingReferenceType pricingReferenceType = new PricingReferenceType();
//
//        TipoAfectacionIgv tipoAfectacionIgv = TipoAfectacionIgv.searchFromCodigo(lineBean.getCodigoTipoIgv()).orElseThrow(() -> new InvalidCodeException("Codigo de tipo de IGV invalido"));
//        if (tipoAfectacionIgv.isOperacionNoOnerosa()) {
//            pricingReferenceType.getAlternativeConditionPrice().add(
//                    UBLUtils.buildPriceType(moneda, BigDecimal.ZERO, TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
//            );
//            pricingReferenceType.getAlternativeConditionPrice().add(
//                    UBLUtils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.VALOR_REF_UNIT_EN_OPER_NO_ORENOSAS.getCodigo())
//            );
//        } else {
//            pricingReferenceType.getAlternativeConditionPrice().add(
//                    UBLUtils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
//            );
//        }
//
//        return pricingReferenceType;
//    }
//
//    private static TaxTotalType buildTaxTotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
//        TaxTotalType taxTotalType = new TaxTotalType();
//        taxTotalType.setTaxAmount(UBLUtils.buildTaxAmountType(currency, value));
//        taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(currency, value, tipoIGV, tipoTributo));
//        return taxTotalType;
//    }
//
//    private static TaxSubtotalType buildTaxSubtotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
//        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
//        taxSubtotalType.setTaxAmount(UBLUtils.buildTaxAmountType(currency, value));
//        taxSubtotalType.setTaxCategory(buildTaxCategoryType(tipoIGV, tipoTributo));
//        return taxSubtotalType;
//    }
//
//    private static TaxCategoryType buildTaxCategoryType(String tipoIGV, TipoTributo tipoTributo) {
//        TaxCategoryType taxCategoryType = new TaxCategoryType();
//        taxCategoryType.setTaxScheme(UBLUtils.buildTaxSchemeType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCodigo()));
//        taxCategoryType.setTaxExemptionReasonCode(UBLUtils.buildTaxExemptionReasonCodeType(tipoIGV));
//        return taxCategoryType;
//    }


}
