package com.treino.mercadolivre.validation.annotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidatorUniqueValue implements ConstraintValidator<UniqueValue, String> {

    private final EntityManager em;

    private String atributo;
    private Class<?> klass;

    public ValidatorUniqueValue(EntityManager em) {
        this.em = em;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.klass = constraintAnnotation.klass();
        this.atributo = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("SELECT 1 FROM " + klass.getName() + " where lower(" + atributo + ") =:value ");
        query.setParameter("value", value.toLowerCase());

        List<?> lista = query.getResultList();
        return lista.size() < 1;
    }
}
