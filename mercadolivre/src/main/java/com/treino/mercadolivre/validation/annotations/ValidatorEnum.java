package com.treino.mercadolivre.validation.annotations;

import com.treino.mercadolivre.pagamento.PagamentoGateway;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValidatorEnum implements ConstraintValidator<ValidEnum, PagamentoGateway> {
    private PagamentoGateway[] pagamentoGateways;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
       pagamentoGateways = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(PagamentoGateway pagamento, ConstraintValidatorContext constraintValidatorContext) {
        return pagamento == null || Arrays.asList(pagamentoGateways).contains(pagamento);
    }
}
