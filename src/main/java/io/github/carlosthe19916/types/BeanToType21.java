package io.github.carlosthe19916.types;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.beans.catalogs.Catalogo16;
import io.github.carlosthe19916.beans.catalogs.Catalogo52;
import io.github.carlosthe19916.beans.catalogs.Catalogo5;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.exceptions.Invoice21BeanValidacionException;
import io.github.carlosthe19916.beans.ubl.ubl21.Impuestos21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Proveedor21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;
import io.github.carlosthe19916.utils.BeanUtils;
import io.github.carlosthe19916.utils.DateUtils;
import io.github.carlosthe19916.utils.UBL21Utils;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.CompanyIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.InvoiceTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.ProfileIDType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_21.ExtensionContentType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_21.UBLExtensionType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_21.UBLExtensionsType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;
import org.w3c.dom.Document;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
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

        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

        // Type
        oasis.names.specification.ubl.schema.xsd.invoice_21.ObjectFactory factory = new oasis.names.specification.ubl.schema.xsd.invoice_21.ObjectFactory();
        oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType invoiceType = factory.createInvoiceType();

        invoiceType.setUBLVersionID(UBL21Utils.buildUBLVersionID(defaults.getUblVersion()));
        invoiceType.setCustomizationID(UBL21Utils.buildCustomizationIDType(defaults.getCustomizationId()));

        // Profile
        ProfileIDType profileIDType = UBL21Utils.toProfileIDType(invoice.getTipoOperacion().getCode());
        invoiceType.setProfileID(profileIDType);

        // 10 Leyendas
        if (invoice.getTotal().getPagarLetras() != null) {
            invoiceType.addNote(UBL21Utils.buildNoteType(invoice.getTotal().getPagarLetras(), Catalogo52.MONTO_EXPRESADO_EN_LETRAS.getCode()));
        }
        if (invoice.getCodigoGeneradoPorSoftware() != null) {
            invoiceType.addNote(UBL21Utils.buildNoteType(invoice.getCodigoGeneradoPorSoftware(), Catalogo52.CODIGO_INTERNO_GENERADO_POR_EL_SOFTWARE_DE_FACTURACION.getCode()));
        }

        // 12 Guia de remision relacionada
        if (invoice.getGuiaRemisionRelacionada() != null) {
            GuiaRemisionRelacionadaBean guiaRemisionRelacionada = invoice.getGuiaRemisionRelacionada();
            DocumentReferenceType documentReferenceType = UBL21Utils.buildDocumentReferenceType1(guiaRemisionRelacionada.getGuiaRemision(), guiaRemisionRelacionada.getTipoGuiaRemision().getCode());
            invoiceType.addDespatchDocumentReference(documentReferenceType);
        }

        // 13 otro documento relacionado
        if (invoice.getOtroDocumentoRelacionado() != null) {
            OtroDocumentoRelacionadoBean otroDocumentoRelacionado = invoice.getOtroDocumentoRelacionado();
            DocumentReferenceType documentReferenceType = UBL21Utils.buildDocumentReferenceType2(otroDocumentoRelacionado.getDocumentoRelacionado(), otroDocumentoRelacionado.getTipoDocumentoRelacionado().getCode());
            invoiceType.addAdditionalDocumentReference(documentReferenceType);
        }

        // 20 direccion donde se entrega el bien
        // 21 descuentos globales

        // Serie y numero
        IDType idType = UBL21Utils.buildIDType(MessageFormat.format("{0}-{1}", invoice.getSerie(), invoice.getNumero()));
        invoiceType.setID(idType);

        // Fecha y hora de emision
        FechaBean fecha = invoice.getFecha();
        invoiceType.setIssueDate(DateUtils.toGregorianCalendar(fecha.getFechaEmision(), fecha.getTimeZone()));
        invoiceType.setIssueTime(DateUtils.toGregorianCalendarTime(fecha.getFechaEmision(), fecha.getTimeZone()));

        // Fecha vencimiento
        if (invoice.getFecha().getFechaVencimiento() != null) {
            invoiceType.setDueDate(DateUtils.toGregorianCalendar(fecha.getFechaVencimiento(), fecha.getTimeZone()));
        }

        // Tipo comprobante
        String codigoTipoComprobante = invoice.getTipoDocumento().getCode();
        InvoiceTypeCodeType invoiceTypeCodeType = UBL21Utils.buildInvoiceTypeCodeType(codigoTipoComprobante);
        invoiceType.setInvoiceTypeCode(invoiceTypeCodeType);

        // Moneda
        MonedaBean moneda = invoice.getMoneda();
        invoiceType.setDocumentCurrencyCode(UBL21Utils.buildDocumentCurrencyCodeType(moneda.getCodigo()));

        // Proveedor
        Proveedor21Bean proveedor = invoice.getProveedor();
        invoiceType.setAccountingSupplierParty(buildSupplierPartyType(proveedor));

        // Cliente
        ClienteBean cliente = invoice.getCliente();
        invoiceType.setAccountingCustomerParty(buildCustomerPartyType(cliente));

        // Totales pagar/descuentos/otros cargos
        Total21Bean total = invoice.getTotal();
        invoiceType.setLegalMonetaryTotal(buildMonetaryTotalType(total, invoice.getMoneda()));

        // Total bean IGV/ISC
        Impuestos21Bean impuestos = invoice.getImpuestos();
        invoiceType.getTaxTotal().addAll(buildTaxTotalType(impuestos, invoice.getTotalInformacionAdicional(), invoice.getMoneda()));

        // Detalle
        int i = 1;
        for (DetalleBean detalle : invoice.getDetalle()) {
            invoiceType.getInvoiceLine().add(buildInvoiceLineType(i, moneda.getCodigo(), detalle));
            i++;
        }

        invoiceType.setLineCountNumeric(BigDecimal.valueOf(invoice.getDetalle().size()));

        return invoiceType;
    }


    // Proveedor

    private static SupplierPartyType buildSupplierPartyType(Proveedor21Bean proveedor) {
        SupplierPartyType supplierPartyType = new SupplierPartyType();

        PartyType partyType = new PartyType();
        supplierPartyType.setParty(partyType);

        PartyTaxSchemeType partyTaxSchemeType = new PartyTaxSchemeType();
        partyType.addPartyTaxScheme(partyTaxSchemeType);

        TaxSchemeType taxSchemeType = new TaxSchemeType();
        taxSchemeType.setID("-");
        partyTaxSchemeType.setTaxScheme(taxSchemeType);

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

        TaxSchemeType taxSchemeType = new TaxSchemeType();
        taxSchemeType.setID("-");
        partyTaxSchemeType.setTaxScheme(taxSchemeType);

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

        if (total.getDescuentoGlobal() != null) {
            monetaryTotalType.setAllowanceTotalAmount(UBL21Utils.buildAllowanceTotalAmountType(total.getDescuentoGlobal(), moneda.getCodigo()));
        }
        if (total.getOtrosCargos() != null) {
            monetaryTotalType.setChargeTotalAmount(UBL21Utils.buildChargeTotalAmountType(total.getOtrosCargos(), moneda.getCodigo()));
        }
        if (total.getPagar() != null) {
            monetaryTotalType.setPayableAmount(UBL21Utils.buildPayableAmountType(total.getPagar(), moneda.getCodigo()));
        }

        if (total.getExtensionAmount() != null) {
            monetaryTotalType.setLineExtensionAmount(UBL21Utils.buildLineExtensionAmountType(total.getExtensionAmount(), moneda.getCodigo()));
        }
        if (total.getInclusiveAmount() != null) {
            monetaryTotalType.setTaxInclusiveAmount(UBL21Utils.buildTaxInclusiveAmountType(total.getInclusiveAmount(), moneda.getCodigo()));
        }
        if (total.getAnticipos() != null) {
            monetaryTotalType.setPrepaidAmount(UBL21Utils.buildPrepaidAmountType(total.getAnticipos(), moneda.getCodigo()));
        }

        return monetaryTotalType;
    }


    // Impuestos

    private static List<TaxTotalType> buildTaxTotalType(Impuestos21Bean impuestosBean, TotalInformacionAdicionalBean totalInformacionAdicional, MonedaBean moneda) {
        List<TaxTotalType> result = new ArrayList<>();

        if (impuestosBean.getIgv() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(impuestosBean.getIgv(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getIgv(), impuestosBean.getIgvAfecto(), moneda.getCodigo(), Catalogo5.IGV));

            result.add(taxTotalType);
        }
        if (impuestosBean.getIsc() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(impuestosBean.getIsc(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getIsc(), impuestosBean.getIscAfecto(), moneda.getCodigo(), Catalogo5.ISC));

            result.add(taxTotalType);
        }
        if (impuestosBean.getOtros() != null) {
            TaxTotalType taxTotalType = new TaxTotalType();
            taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(impuestosBean.getOtros(), moneda.getCodigo()));
            taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(impuestosBean.getOtros(), impuestosBean.getOtrosAfecto(), moneda.getCodigo(), Catalogo5.OTROS));

            result.add(taxTotalType);
        }

        return result;
    }

    private static TaxSubtotalType buildTaxSubtotalType(BigDecimal taxAmount, BigDecimal taxableAmount, String currency, Catalogo5 tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(taxAmount, currency));
        taxSubtotalType.setTaxCategory(UBL21Utils.buildTaxCategoryType(tipoTributo.getCategoria(), tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCode()));
        taxSubtotalType.setTaxableAmount(UBL21Utils.buildTaxableAmountType(taxableAmount, currency));
        return taxSubtotalType;
    }


    // Line

    private static InvoiceLineType buildInvoiceLineType(int index, String moneda, DetalleBean lineBean) {
        InvoiceLineType invoiceLineType = new InvoiceLineType();

        invoiceLineType.setID(UBL21Utils.buildIDType(String.valueOf(index)));
        invoiceLineType.setInvoicedQuantity(UBL21Utils.buildInvoicedQuantityType(lineBean.getUnidadMedida(), lineBean.getCantidad()));
        invoiceLineType.setLineExtensionAmount(UBL21Utils.buildLineExtensionAmountType(lineBean.getSubtotal(), moneda));
        invoiceLineType.setItem(UBL21Utils.buildItemType(lineBean.getDescripcion()));
        invoiceLineType.setPricingReference(buildPricingReferenceType(moneda, lineBean));
        invoiceLineType.setPrice(UBL21Utils.buildPriceType(moneda, lineBean.getValorUnitario()));

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
                    UBL21Utils.buildPriceType(moneda, BigDecimal.ZERO, Catalogo16.PRECIO_UNITARIO_INCLUYE_IGV.getCode())
            );
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL21Utils.buildPriceType(moneda, lineBean.getPrecioUnitario(), Catalogo16.VALOR_FERENCIAL_UNITARIO_EN_OPERACIONES_NO_ONEROSAS.getCode())
            );
        } else {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL21Utils.buildPriceType(moneda, lineBean.getPrecioUnitario(), Catalogo16.PRECIO_UNITARIO_INCLUYE_IGV.getCode())
            );
        }

        return pricingReferenceType;
    }

    private static TaxTotalType buildTaxTotalType(String currency, BigDecimal value, String tipoIGV, Catalogo5 tipoTributo) {
        TaxTotalType taxTotalType = new TaxTotalType();
        taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(value, currency));
        taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(currency, value, tipoIGV, tipoTributo));
        return taxTotalType;
    }

    private static TaxSubtotalType buildTaxSubtotalType(String currency, BigDecimal value, String tipoIGV, Catalogo5 tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(value, currency));
        taxSubtotalType.setTaxCategory(buildTaxCategoryType(tipoIGV, tipoTributo));
        return taxSubtotalType;
    }

    private static TaxCategoryType buildTaxCategoryType(String tipoIGV, Catalogo5 tipoTributo) {
        TaxCategoryType taxCategoryType = new TaxCategoryType();
        taxCategoryType.setTaxScheme(UBL21Utils.buildTaxSchemeType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCode()));
        taxCategoryType.setTaxExemptionReasonCode(UBL21Utils.buildTaxExemptionReasonCodeType(tipoIGV));


        IDType idType = new IDType();
        idType.setValue("S");
        idType.setSchemeID("UN/ECE 5305");
        idType.setSchemeName("Tax Category Identifier");
        idType.setSchemeAgencyName("United Nations Economic Commission for Europe");

        taxCategoryType.setID(idType);

        return taxCategoryType;
    }


}
