package io.github.carlosthe19916.types;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.beans.catalogs.Catalogo16;
import io.github.carlosthe19916.beans.catalogs.Catologo14;
import io.github.carlosthe19916.beans.catalogs.Catalogo5;
import io.github.carlosthe19916.beans.exceptions.Invoice20BeanValidacionException;
import io.github.carlosthe19916.beans.ubl.ubl20.Impuestos20Bean;
import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;
import io.github.carlosthe19916.beans.ubl.ubl20.Total20Bean;
import io.github.carlosthe19916.utils.BeanUtils;
import io.github.carlosthe19916.utils.DateUtils;
import io.github.carlosthe19916.utils.JaxbUtils;
import io.github.carlosthe19916.utils.UBL20Utils;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionContentType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionsType;
import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import org.w3c.dom.Element;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalInformationType;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.AdditionalMonetaryTotalType;
import sunat.names.specification.ubl.peru.schema.xsd.sunataggregatecomponents_1.ObjectFactory;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanToType20 {

    private BeanToType20() {
        // Just util class
    }

    public static InvoiceType toInvoiceType(Invoice20Bean invoice) throws Invoice20BeanValidacionException {
        Set<ConstraintViolation<Invoice20Bean>> violations = BeanUtils.validate(invoice);
        if (!violations.isEmpty()) {
            throw new Invoice20BeanValidacionException("Invalid moneda", violations);
        }

        // Type
        oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory();
        oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType invoiceType = factory.createInvoiceType();

        invoiceType.setUBLVersionID(UBL20Utils.buildUBLVersionID("2.0"));
        invoiceType.setCustomizationID(UBL20Utils.buildCustomizationIDType("2.0"));

        // Observaciones
        invoiceType.getNote().add(UBL20Utils.buildNoteType(invoice.getObservaciones()));


        // Serie y numero
        String serieNumero = MessageFormat.format("{0}-{1}", invoice.getSerie(), invoice.getNumero());
        invoiceType.setID(UBL20Utils.buildIDType(serieNumero));

        // Fecha y hora de emision
        FechaBean fecha = invoice.getFecha();
        invoiceType.setIssueDate(DateUtils.toGregorianCalendar(fecha.getFechaEmision(), fecha.getTimeZone()));
        invoiceType.setIssueTime(DateUtils.toGregorianCalendarTime(fecha.getFechaEmision(), fecha.getTimeZone()));

        // Fecha vencimiento
        if (fecha.getFechaVencimiento() != null) {
            XMLGregorianCalendar paymentDate = DateUtils.toGregorianCalendar(fecha.getFechaVencimiento(), fecha.getTimeZone());
            invoiceType.getPaymentMeans().add(UBL20Utils.buildPaymentMeansType(paymentDate));
        }

        // Tipo comprobante
        String codigoTipoComprobante = invoice.getTipoDocumento().getCode();
        invoiceType.setInvoiceTypeCode(UBL20Utils.buildInvoiceTypeCodeType(codigoTipoComprobante));

        // builder
        MonedaBean moneda = invoice.getMoneda();
        invoiceType.setDocumentCurrencyCode(UBL20Utils.buildDocumentCurrencyCodeType(moneda.getCodigo()));

        // Proveedor
        AbstractProveedorBean proveedor = invoice.getProveedor();
        invoiceType.setAccountingSupplierParty(buildSupplierPartyType(proveedor));

        // Cliente
        ClienteBean cliente = invoice.getCliente();
        invoiceType.setAccountingCustomerParty(buildCustomerPartyType(cliente));

        // Totales pagar/descuentos/otros cargos
        Total20Bean total = invoice.getTotal();
        invoiceType.setLegalMonetaryTotal(buildMonetaryTotalType(total, invoice.getMoneda()));

        // Total moneda IGV/ISC
        Impuestos20Bean impuestos = invoice.getImpuestos();
        invoiceType.getTaxTotal().addAll(buildTaxTotalType(impuestos, invoice.getMoneda()));

        // Detalle
        int i = 1;
        for (DetalleBean lineBean : invoice.getDetalle()) {
            invoiceType.getInvoiceLine().add(buildInvoiceLineType(i, moneda.getCodigo(), lineBean));
            i++;
        }

        // Extensiones
        TotalInformacionAdicionalBean totalInformacionAdicional = invoice.getTotalInformacionAdicional();
        invoiceType.setUBLExtensions(buildUBLExtensionsType(totalInformacionAdicional, moneda.getCodigo()));

        // Firma
        invoiceType.getSignature().add(buildSignatureType(invoice.getProveedor()));

        return invoiceType;
    }


    // Proveedor

    private static SupplierPartyType buildSupplierPartyType(AbstractProveedorBean proveedor) {
        SupplierPartyType supplierPartyType = new SupplierPartyType();

        PartyType partyType = new PartyType();
        supplierPartyType.setParty(partyType);

        AddressType addressType = new AddressType();
        partyType.setPostalAddress(addressType);

        // Documento identidad
        String numeroDocumento = proveedor.getNumeroDocumento();
        supplierPartyType.setCustomerAssignedAccountID(UBL20Utils.buildCustomerAssignedAccountIDType(numeroDocumento));

        String codigoTipoDocumento = proveedor.getCodigoTipoDocumento();
        supplierPartyType.getAdditionalAccountID().add(UBL20Utils.buildAdditionalAccountIDType(codigoTipoDocumento));

        // Nombre comercial
        String nombreComercial = proveedor.getNombreComercial();
        partyType.getPartyName().add(UBL20Utils.buildPartyNameType(nombreComercial));

        // Razon social
        String razonSocial = proveedor.getRazonSocial();
        partyType.getPartyLegalEntity().add(UBL20Utils.buildPartyLegalEntityType(razonSocial));

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

        // Documento identidad
        String numeroDocumento = cliente.getNumeroDocumento();
        customerPartyType.setCustomerAssignedAccountID(UBL20Utils.buildCustomerAssignedAccountIDType(numeroDocumento));

        String codigoTipoDocumento = cliente.getCodigoTipoDocumento();
        customerPartyType.getAdditionalAccountID().add(UBL20Utils.buildAdditionalAccountIDType(codigoTipoDocumento));

        // Razon social
        String nombre = cliente.getNombre();
        partyType.getPartyLegalEntity().add(UBL20Utils.buildPartyLegalEntityType(nombre));

        return customerPartyType;
    }


    // Total a pagar

    private static MonetaryTotalType buildMonetaryTotalType(Total20Bean total, MonedaBean moneda) {
        MonetaryTotalType monetaryTotalType = new MonetaryTotalType();
        monetaryTotalType.setAllowanceTotalAmount(UBL20Utils.buildAllowanceTotalAmountType(total.getDescuentoGlobal(), moneda.getCodigo()));
        monetaryTotalType.setChargeTotalAmount(UBL20Utils.buildChargeTotalAmountType(total.getOtrosCargos(), moneda.getCodigo()));
        monetaryTotalType.setPayableAmount(UBL20Utils.buildPayableAmountType(total.getPagar(), moneda.getCodigo()));
        return monetaryTotalType;
    }


    // Impuestos

    private static List<TaxTotalType> buildTaxTotalType(Impuestos20Bean impuestosBean, MonedaBean moneda) {
        List<TaxTotalType> result = new ArrayList<>();

        if (impuestosBean.getIgv() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL20Utils.buildTaxAmountType(impuestosBean.getIgv(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getIgv(), moneda.getCodigo(), Catalogo5.IGV));

            result.add(taxTotalType);
        }
        if (impuestosBean.getIsc() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL20Utils.buildTaxAmountType(impuestosBean.getIsc(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getIsc(), moneda.getCodigo(), Catalogo5.ISC));

            result.add(taxTotalType);
        }
        if (impuestosBean.getOtros() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL20Utils.buildTaxAmountType(impuestosBean.getOtros(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getOtros(), moneda.getCodigo(), Catalogo5.OTROS));

            result.add(taxTotalType);
        }

        return result;
    }

    private static TaxSubtotalType buildTaxSubtotalType(BigDecimal value, String currency, Catalogo5 tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBL20Utils.buildTaxAmountType(value, currency));
        taxSubtotalType.setTaxCategory(UBL20Utils.buildTaxCategoryType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCode()));
        return taxSubtotalType;
    }

    private static ExtensionContentType buildExtensionContentType() {
        return new ExtensionContentType();
    }


    // Extensiones

    private static UBLExtensionsType buildUBLExtensionsType(TotalInformacionAdicionalBean totalInformacionAdicional, String codigoMoneda) {
        UBLExtensionsType ublExtensionsType = new UBLExtensionsType();

        // Totales
        UBLExtensionType ublExtensionType1 = new UBLExtensionType();
        ExtensionContentType extensionContentType1 = new ExtensionContentType();
        ublExtensionType1.setExtensionContent(extensionContentType1);
        ublExtensionsType.getUBLExtension().add(ublExtensionType1);

        AdditionalInformationType additionalInformationType = buildAdditionalInformationType(codigoMoneda, totalInformacionAdicional);

        ObjectFactory factory = new ObjectFactory();
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

    private static AdditionalInformationType buildAdditionalInformationType(String codigoMoneda, TotalInformacionAdicionalBean totalInformacionAdicionalBean) {
        AdditionalInformationType additionalInformationType = new AdditionalInformationType();

        if (totalInformacionAdicionalBean.getGravado() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(UBL20Utils.buildIDType(Catologo14.TOTAL_VALOR_VENTA_OPERACIONES_GRAVADAS.getCode()));
            additionalMonetaryTotalType.setPayableAmount(UBL20Utils.buildPayableAmountType(totalInformacionAdicionalBean.getGravado(), codigoMoneda));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getInafecto() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(UBL20Utils.buildIDType(Catologo14.TOTAL_VALOR_VENTA_OPERACIONES_INAFECTAS.getCode()));
            additionalMonetaryTotalType.setPayableAmount(UBL20Utils.buildPayableAmountType(totalInformacionAdicionalBean.getInafecto(), codigoMoneda));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getExonerado() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(UBL20Utils.buildIDType(Catologo14.TOTAL_VALOR_VENTA_OPERACIONES_EXONERADAS.getCode()));
            additionalMonetaryTotalType.setPayableAmount(UBL20Utils.buildPayableAmountType(totalInformacionAdicionalBean.getExonerado(), codigoMoneda));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }
        if (totalInformacionAdicionalBean.getGratuito() != null) {
            AdditionalMonetaryTotalType additionalMonetaryTotalType = new AdditionalMonetaryTotalType();
            additionalMonetaryTotalType.setID(UBL20Utils.buildIDType(Catologo14.TOTAL_VALOR_VENTA_OPERACIONES_GRATUITAS.getCode()));
            additionalMonetaryTotalType.setPayableAmount(UBL20Utils.buildPayableAmountType(totalInformacionAdicionalBean.getGratuito(), codigoMoneda));

            additionalInformationType.getAdditionalMonetaryTotal().add(additionalMonetaryTotalType);
        }

        return additionalInformationType;
    }

    // Firma

    private static SignatureType buildSignatureType(AbstractProveedorBean proveedorBean) {
        SignatureType signatureType = new SignatureType();

        String signID = "IDSign" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
        signatureType.setID(UBL20Utils.buildIDType(signID));


        PartyType partyType = new PartyType();
        partyType.getPartyName().add(UBL20Utils.buildPartyNameType(proveedorBean.getNombreComercial()));
        partyType.getPartyIdentification().add(UBL20Utils.buildPartyIdentificationType(proveedorBean.getNumeroDocumento()));

        signatureType.setSignatoryParty(partyType);

        String URI = "#signature" + proveedorBean.getNombreComercial().replaceAll("\\s", "");
        AttachmentType attachmentType = new AttachmentType();
        attachmentType.setExternalReference(UBL20Utils.buildExternalReferenceType(URI));
        signatureType.setDigitalSignatureAttachment(attachmentType);

        return signatureType;
    }


    // Line

    private static InvoiceLineType buildInvoiceLineType(int index, String moneda, DetalleBean lineBean) {
        InvoiceLineType invoiceLineType = new InvoiceLineType();

        invoiceLineType.setID(UBL20Utils.buildIDType(String.valueOf(index)));
        invoiceLineType.setInvoicedQuantity(UBL20Utils.buildInvoicedQuantityType(lineBean.getUnidadMedida(), lineBean.getCantidad()));
        invoiceLineType.setLineExtensionAmount(UBL20Utils.buildLineExtensionAmountType(moneda, lineBean.getSubtotal()));
        invoiceLineType.setItem(UBL20Utils.buildItemType(lineBean.getDescripcion()));
        invoiceLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
        invoiceLineType.setPrice(UBL20Utils.buildPriceType(moneda, lineBean.getValorUnitario()));

        if (lineBean.getTotalIgv() != null) {
            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIgv(), lineBean.getTipoAfectacionIgv().getCode(), Catalogo5.IGV));
        }
        if (lineBean.getTotalIsc() != null) {
            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIsc(), lineBean.getCodigoTipoIsc(), Catalogo5.ISC));
        }

        return invoiceLineType;
    }

    private static PricingReferenceType buildPricingReferenceType(String moneda, DetalleBean lineBean) {
        PricingReferenceType pricingReferenceType = new PricingReferenceType();

        if (lineBean.getTipoAfectacionIgv().isOperacionNoOnerosa()) {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL20Utils.buildPriceType(moneda, BigDecimal.ZERO, Catalogo16.PRECIO_UNITARIO_INCLUYE_IGV.getCode())
            );
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL20Utils.buildPriceType(moneda, lineBean.getPrecioUnitario(), Catalogo16.VALOR_FERENCIAL_UNITARIO_EN_OPERACIONES_NO_ONEROSAS.getCode())
            );
        } else {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL20Utils.buildPriceType(moneda, lineBean.getPrecioUnitario(), Catalogo16.PRECIO_UNITARIO_INCLUYE_IGV.getCode())
            );
        }

        return pricingReferenceType;
    }

    private static TaxTotalType buildTaxTotalType(String currency, BigDecimal value, String tipoIGV, Catalogo5 tipoTributo) {
        TaxTotalType taxTotalType = new TaxTotalType();
        taxTotalType.setTaxAmount(UBL20Utils.buildTaxAmountType(value, currency));
        taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(currency, value, tipoIGV, tipoTributo));
        return taxTotalType;
    }

    private static TaxSubtotalType buildTaxSubtotalType(String currency, BigDecimal value, String tipoIGV, Catalogo5 tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBL20Utils.buildTaxAmountType(value, currency));
        taxSubtotalType.setTaxCategory(buildTaxCategoryType(tipoIGV, tipoTributo));
        return taxSubtotalType;
    }

    private static TaxCategoryType buildTaxCategoryType(String tipoIGV, Catalogo5 tipoTributo) {
        TaxCategoryType taxCategoryType = new TaxCategoryType();
        taxCategoryType.setTaxScheme(UBL20Utils.buildTaxSchemeType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCode()));
        taxCategoryType.setTaxExemptionReasonCode(UBL20Utils.buildTaxExemptionReasonCodeType(tipoIGV));
        return taxCategoryType;
    }


}
