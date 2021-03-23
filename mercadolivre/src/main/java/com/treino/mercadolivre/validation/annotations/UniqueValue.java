package com.treino.mercadolivre.validation.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidatorUniqueValue.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "Esse campo precisa ser único! você digitou um valor que já existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> klass();

    String fieldName();
}
