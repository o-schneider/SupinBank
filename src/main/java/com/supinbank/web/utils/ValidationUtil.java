package com.supinbank.web.utils;

import com.supinbank.entities.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/26/12
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidationUtil
{
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> boolean validate(T objectToValidate, String property, HttpServletRequest request)
    {
        boolean valid = true;

        Set<ConstraintViolation<T>> violations = validator.validateProperty(objectToValidate, property);

        if (!violations.isEmpty())
        {
            valid = false;
            List<String> errors = new ArrayList<String>();
            for (ConstraintViolation violation : violations)
            {
                errors.add(violation.getMessage());
            }
            request.setAttribute(property + "Error", errors);
        }
        return valid;
    }
}
