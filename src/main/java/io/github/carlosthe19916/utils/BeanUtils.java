package io.github.carlosthe19916.utils;

import io.github.carlosthe19916.beans.FechaBean;
import io.github.carlosthe19916.beans.InvoiceBean;
import io.github.carlosthe19916.beans.ubl.UBLDefaults;
import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;
import io.github.carlosthe19916.beans.ubl.ubl20.UBL20Defaults;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.UBL21Defaults;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanUtils {

    private BeanUtils() {
        //  Just static methods
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
