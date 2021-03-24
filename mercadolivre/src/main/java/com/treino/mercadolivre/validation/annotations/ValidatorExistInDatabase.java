package com.treino.mercadolivre.validation.annotations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidatorExistInDatabase implements ConstraintValidator<ExistInDatabase, Integer> {
    @PersistenceContext
    private EntityManager em;
    private String atributo;
    private Class<?> klass;

    @Override
    public void initialize(ExistInDatabase constraintAnnotation) {
        this.atributo = constraintAnnotation.fieldName();
        this.klass = constraintAnnotation.klass();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("SELECT 1 FROM " + klass.getName() + " where " + atributo + "=:value");
        query.setParameter("value", value);

        List<?> lista = query.getResultList();
        return lista.size() >= 1 || value == null;
    }
}
