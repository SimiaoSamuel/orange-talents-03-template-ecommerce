package com.treino.mercadolivre.validation.annotations;

import com.treino.mercadolivre.pagamento.PagamentoGateway;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorEnum.class)
public @interface ValidEnum {
    String message() default "Enum inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    PagamentoGateway[] anyOf();
}
