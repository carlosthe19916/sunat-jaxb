package io.github.carlosthe19916.sunatjaxb.ubl21.exceptions;

import io.github.carlosthe19916.sunatjaxb.ubl21.beans.DebitNote21Bean;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DebitNote21BeanValidacionException extends RuntimeException {

    private Set<ConstraintViolation<DebitNote21Bean>> violations;

    public DebitNote21BeanValidacionException(String message, Set<ConstraintViolation<DebitNote21Bean>> violations) {
        super(message);
        this.violations = Collections.unmodifiableSet(new HashSet<>(violations));
    }

    public Set<ConstraintViolation<DebitNote21Bean>> getViolations() {
        return violations;
    }

}
