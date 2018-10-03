package io.github.carlosthe19916.beans.exceptions;

import io.github.carlosthe19916.beans.ubl.ubl20.Invoice20Bean;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Invoice20BeanValidacionException extends RuntimeException {

    private Set<ConstraintViolation<Invoice20Bean>> violations;

    public Invoice20BeanValidacionException(String message, Set<ConstraintViolation<Invoice20Bean>> violations) {
        super(message);
        this.violations = Collections.unmodifiableSet(new HashSet<>(violations));
    }

    public Set<ConstraintViolation<Invoice20Bean>> getViolations() {
        return violations;
    }

}
