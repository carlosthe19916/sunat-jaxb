package io.github.carlosthe19916.sunatjaxb.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

}
