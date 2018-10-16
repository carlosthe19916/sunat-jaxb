package io.github.carlosthe19916.sunatjaxb.ubl21.exceptions;

import io.github.carlosthe19916.sunatjaxb.beans.beans21.CreditNote21Bean;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CreditNote21BeanValidacionException extends RuntimeException {

    private Set<ConstraintViolation<CreditNote21Bean>> violations;

    public CreditNote21BeanValidacionException(String message, Set<ConstraintViolation<CreditNote21Bean>> violations) {
        super(message);
        this.violations = Collections.unmodifiableSet(new HashSet<>(violations));
    }

    public Set<ConstraintViolation<CreditNote21Bean>> getViolations() {
        return violations;
    }

}
