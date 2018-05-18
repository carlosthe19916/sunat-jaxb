package io.github.carlosthe19916.exceptions;

import io.github.carlosthe19916.beans.InvoiceBean;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class InvoiceBeanValidacionException extends RuntimeException {

    private Set<ConstraintViolation<InvoiceBean>> violations;

    public InvoiceBeanValidacionException(String message, Set<ConstraintViolation<InvoiceBean>> violations) {
        super(message);
        this.violations = Collections.unmodifiableSet(new HashSet<>(violations));
    }

    public Set<ConstraintViolation<InvoiceBean>> getViolations() {
        return violations;
    }

}
