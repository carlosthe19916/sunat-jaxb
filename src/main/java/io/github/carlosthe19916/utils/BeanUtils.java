package io.github.carlosthe19916.utils;

import io.github.carlosthe19916.beans.config.ubl20.GlobalUBL20Defaults;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.mappers.Invoice20FillOut;
import io.github.carlosthe19916.beans.mappers.Invoice21FillOut;
import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21Bean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

public class BeanUtils {

    private static final List<Invoice20FillOut> invoice20FillOut;
    private static final List<Invoice21FillOut> invoice21FillOut;

    static {
        invoice20FillOut = new ArrayList<>();
        for (Invoice20FillOut fillOut : ServiceLoader.load(Invoice20FillOut.class)) {
            invoice20FillOut.add(fillOut);
        }
        invoice20FillOut.sort((t1, t2) -> t2.order() - t1.order());

        invoice21FillOut = new ArrayList<>();
        for (Invoice21FillOut fillOut : ServiceLoader.load(Invoice21FillOut.class)) {
            invoice21FillOut.add(fillOut);
        }
        invoice21FillOut.sort((t1, t2) -> t2.order() - t1.order());
    }

    private BeanUtils() {
        // Just static methods
    }

    public static <T> Set<ConstraintViolation<T>> validate(T t) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator.validate(t);
    }

    public static Invoice20Bean fillOut(Invoice20Bean invoice, Invoice20FillOut... fillOuts) {
        GlobalUBL20Defaults defaults = GlobalUBL20Defaults.getInstance();

        for (Invoice20FillOut fillOut : fillOuts) {
            invoice = fillOut.fillIn(invoice);
        }

        if (defaults.getAplicarCalculosInternosAutomaticos()) {
            for (Invoice20FillOut fillOut : invoice20FillOut) {
                invoice = fillOut.fillIn(invoice);
            }
        }

        return invoice;
    }

    public static Invoice21Bean fillOut(Invoice21Bean invoice, Invoice21FillOut... fillOuts) {
        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

        for (Invoice21FillOut fillOut : fillOuts) {
            invoice = fillOut.fillIn(invoice);
        }

        if (defaults.getAplicarCalculosInternosAutomaticos()) {
            for (Invoice21FillOut fillOut : invoice21FillOut) {
                invoice = fillOut.fillIn(invoice);
            }
        }

        return invoice;
    }


}
