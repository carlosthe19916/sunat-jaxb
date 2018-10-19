package io.github.carlosthe19916.sunatjaxb.ubl21.exceptions;

import io.github.carlosthe19916.sunatjaxb.ubl21.beans.Invoice21Bean;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Invoice21BeanValidacionException extends RuntimeException {

    private Set<ConstraintViolation<Invoice21Bean>> violations;

    public Invoice21BeanValidacionException(String message, Set<ConstraintViolation<Invoice21Bean>> violations) {
        super(message);
        this.violations = Collections.unmodifiableSet(new HashSet<>(violations));
    }

    public Set<ConstraintViolation<Invoice21Bean>> getViolations() {
        return violations;
    }

}
