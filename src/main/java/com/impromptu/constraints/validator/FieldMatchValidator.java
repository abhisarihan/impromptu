package com.impromptu.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.impromptu.constraints.FieldMatch;

/**
 * Validates that fields annotated with {@link FieldMatch} are equal.  Fields are considered equal if they are both null.
 * 
 * Taken from http://stackoverflow.com/a/2155576/630676
 *
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message; //TODO: i18n

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    	try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            context.buildConstraintViolationWithTemplate(message)
                .addNode(secondFieldName).addConstraintViolation();

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
    	} catch (Exception e) {
    	    return false;
    	}
    }
}