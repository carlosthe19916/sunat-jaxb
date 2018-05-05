package io.github.carlosthe19916.exceptions;

import io.github.carlosthe19916.beans.NoteBean;
import io.github.carlosthe19916.beans.NoteBean;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NoteBeanValidacionException extends Exception {

    private Set<ConstraintViolation<NoteBean>> violations;

    public NoteBeanValidacionException(String message, Set<ConstraintViolation<NoteBean>> violations) {
        super(message);
        this.violations = Collections.unmodifiableSet(new HashSet<>(violations));
    }

    public Set<ConstraintViolation<NoteBean>> getViolations() {
        return violations;
    }

}
