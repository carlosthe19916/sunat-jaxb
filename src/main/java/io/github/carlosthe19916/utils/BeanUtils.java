package io.github.carlosthe19916.utils;

import io.github.carlosthe19916.beans.FechaBean;
import io.github.carlosthe19916.beans.InvoiceBean;
import io.github.carlosthe19916.beans.ubl.UBLDefaults;
import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;
import io.github.carlosthe19916.beans.ubl.ubl20.UBL20Defaults;
import io.github.carlosthe19916.beans.ubl.ubl21.Impuestos21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.UBL21Defaults;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.util.Set;

public class BeanUtils {

    private BeanUtils() {
        // Just static methods
    }

    public static <T> Set<ConstraintViolation<T>> validate(T t) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator.validate(t);
    }

    public static Invoice20Bean applyDefaults(InvoiceBean invoice, UBL20Defaults... defaults) {
        Invoice20Bean result;
        if (invoice instanceof Invoice20Bean) {
            result = (Invoice20Bean) invoice;
        } else {
            result = new Invoice20Bean(invoice);
        }

        applyDefaults(result, defaults);

        for (UBL20Defaults ubl20Defaults : defaults) {

        }

        return result;
    }

    public static Invoice21Bean applyDefaults(InvoiceBean invoice, UBL21Defaults... defaults) {
        Invoice21Bean result;
        if (invoice instanceof Invoice21Bean) {
            result = (Invoice21Bean) invoice;
        } else {
            result = new Invoice21Bean(invoice);
        }

        applyDefaults(result, defaults);

        for (UBL21Defaults ubl21Defaults : defaults) {
            // totales
            if (ubl21Defaults.calculoAutomatico()) {
               
               
                if (result.getTotal() == null) {
                    result.setTotal(new Total21Bean());
                }

                Total21Bean total = result.getTotal();
                if (total.getExtensionAmount() == null && total.getInclusiveAmount() == null) {
                    BigDecimal totalAPAgar = total.getPagar();
                    BigDecimal otrosCarl = total.getOtrosCargos();
                    BigDecimal descuentosr = total.getDescuentoGlobal();
                    BigDecimal anticipo = total.getAnticipos() != null ? total.getAnticipos() : BigDecimal.ZERO;

                    BigDecimal extensionAmount = (totalAPAgar.subtract(otrosCarl).subtract(descuentosr)
                            .subtract(anticipo)).divide(ubl21Defaults.getIgv().add(BigDecimal.ONE));
                    BigDecimal inclusiveAmount = extensionAmount.multiply(ubl21Defaults.getIgv().add(BigDecimal.ONE));

                    total.setExtensionAmount(extensionAmount);
                    total.setInclusiveAmount(inclusiveAmount);
                }

                Impuestos21Bean a = result.getImpuestos();
                BigDecimal igv = a.getIgv();
                BigDecimal taxableAmount = igv.divide(ubl21Defaults.getIgv()); //: [Total valor de venta operaciones gravadas] + [Sumatoria ISC].
                a.setIgvAfecto(taxableAmount);
                    
            }
        }

        return result;
    }

    private static InvoiceBean applyDefaults(InvoiceBean invoice, UBLDefaults... defaults) {
        for (UBLDefaults ublDefaults : defaults) {
            // Time Zone
            if (invoice.getFecha() == null) {
                invoice.setFecha(new FechaBean());
            }

            if (ublDefaults.getTimeZone() != null) {
                if (invoice.getFecha().getTimeZone() == null) {
                    invoice.getFecha().setTimeZone(ublDefaults.getTimeZone());
                }
            }
        }

        return invoice;
    }

}
