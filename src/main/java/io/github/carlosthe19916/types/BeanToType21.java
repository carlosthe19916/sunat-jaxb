package io.github.carlosthe19916.types;

import io.github.carlosthe19916.beans.*;
import io.github.carlosthe19916.beans.catalogs.Catalogo52;
import io.github.carlosthe19916.beans.catalogs.TipoPrecioVentaUnitario;
import io.github.carlosthe19916.beans.catalogs.TipoTributo;
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
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.*;
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
            invoiceType.addNote(UBL21Utils.buildNoteType(invoice.getTotal().getPagarLetras(), Catalogo52.MONTO_EXPRESADO_EN_LETRAS.getCodigo()));
        }
        if (invoice.getCodigoGeneradoPorSoftware() != null) {
            invoiceType.addNote(UBL21Utils.buildNoteType(invoice.getCodigoGeneradoPorSoftware(), Catalogo52.CODIGO_INTERNO_GENERADO_POR_EL_SOFTWARE_DE_FACTURACION.getCodigo()));
        }

        // 12 Guia de remision relacionada
        // 13 otro documento relacionado
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
        String codigoTipoComprobante = invoice.getTipoDocumento().getCodigo();
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
        monetaryTotalType.setAllowanceTotalAmount(UBL21Utils.buildAllowanceTotalAmountType(total.getDescuentoGlobal(), moneda.getCodigo()));
        monetaryTotalType.setChargeTotalAmount(UBL21Utils.buildChargeTotalAmountType(total.getOtrosCargos(), moneda.getCodigo()));
        monetaryTotalType.setPayableAmount(UBL21Utils.buildPayableAmountType(total.getPagar(), moneda.getCodigo()));

        monetaryTotalType.setLineExtensionAmount(UBL21Utils.buildLineExtensionAmountType(total.getExtensionAmount(), moneda.getCodigo()));
        monetaryTotalType.setTaxInclusiveAmount(UBL21Utils.buildTaxInclusiveAmountType(total.getInclusiveAmount(), moneda.getCodigo()));

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
            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIgv(), lineBean.getTipoAfectacionIgv().getCodigo(), TipoTributo.IGV));
        }
        if (lineBean.getTotalIsc() != null) {
            invoiceLineType.getTaxTotal().add(buildTaxTotalType(moneda, lineBean.getTotalIsc(), lineBean.getCodigoTipoIsc(), TipoTributo.ISC));
        }

        return invoiceLineType;
    }

    private static PricingReferenceType buildPricingReferenceType(String moneda, DetalleBean lineBean) {
        PricingReferenceType pricingReferenceType = new PricingReferenceType();

        if (lineBean.getTipoAfectacionIgv().isOperacionNoOnerosa()) {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL21Utils.buildPriceType(moneda, BigDecimal.ZERO, TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
            );
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL21Utils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.VALOR_REF_UNIT_EN_OPER_NO_ORENOSAS.getCodigo())
            );
        } else {
            pricingReferenceType.getAlternativeConditionPrice().add(
                    UBL21Utils.buildPriceType(moneda, lineBean.getPrecioUnitario(), TipoPrecioVentaUnitario.PRECIO_UNITARIO.getCodigo())
            );
        }

        return pricingReferenceType;
    }

    private static TaxTotalType buildTaxTotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
        TaxTotalType taxTotalType = new TaxTotalType();
        taxTotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(value, currency));
        taxTotalType.getTaxSubtotal().add(buildTaxSubtotalType(currency, value, tipoIGV, tipoTributo));
        return taxTotalType;
    }

    private static TaxSubtotalType buildTaxSubtotalType(String currency, BigDecimal value, String tipoIGV, TipoTributo tipoTributo) {
        TaxSubtotalType taxSubtotalType = new TaxSubtotalType();
        taxSubtotalType.setTaxAmount(UBL21Utils.buildTaxAmountType(value, currency));
        taxSubtotalType.setTaxCategory(buildTaxCategoryType(tipoIGV, tipoTributo));
        return taxSubtotalType;
    }

    private static TaxCategoryType buildTaxCategoryType(String tipoIGV, TipoTributo tipoTributo) {
        TaxCategoryType taxCategoryType = new TaxCategoryType();
        taxCategoryType.setTaxScheme(UBL21Utils.buildTaxSchemeType(tipoTributo.getId(), tipoTributo.getAbreviatura(), tipoTributo.getCodigo()));
        taxCategoryType.setTaxExemptionReasonCode(UBL21Utils.buildTaxExemptionReasonCodeType(tipoIGV));
        return taxCategoryType;
    }


}
