package com.supinbank.entities;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * Customer: oli
 * Date: 2/19/12
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerTest
{
    private static Customer customer;

    private static Validator validator;

    @BeforeClass
    public static void initUser()
    {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@heaven.com");
        customer.setAddress("Infinite Loop");
        customer.setZipCode(12345);
        customer.setCity("Nowhere");
        customer.setPhone(0102030405);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void hasFirstName()
    {
        customer.getFirstName();
    }

    @Test
    public void hasLastName()
    {
        customer.getLastName();
    }

    @Test
    public void hasEmail()
    {
        customer.getEmail();
    }

    @Test
    public void hasAddress()
    {
        customer.getAddress();
    }

    @Test
    public void hasZipCode()
    {
        customer.getZipCode();
    }

    @Test
    public void hasCity()
    {
        customer.getCity();
    }

    @Test
    public void hasPhone()
    {
        customer.getPhone();
    }

    @Test
    public void checkValidity()
    {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(0, violations.size());
    }

    @Test
    public void checkNullFirstName()
    {
        customer.setFirstName(null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setFirstName("John");
    }

    @Test
    public void checkEmptyFirstName()
    {
        customer.setFirstName("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setFirstName("John");
    }

    @Test
    public void checkEmptyLastName()
    {
        customer.setLastName("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setLastName("Doe");
    }

    @Test
    public void checkEmptyEmail()
    {
        customer.setEmail("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setEmail("john.doe@heaven.com");
    }

    @Test
    public void checkWrongEmailPattern()
    {
        customer.setEmail("john.doe@something");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setEmail("john.doe@heaven.com");
    }

    @Test
    public void checkNullZipCode()
    {
        customer.setZipCode(null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setZipCode(12345);
    }

    @Test
    public void checkEmptyAddress()
    {
        customer.setAddress("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setAddress("Infinite Loop");
    }

    @Test
    public void checkNullPhone()
    {
        customer.setPhone(null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setPhone(0102030405);
    }

    @Test
    public void checkEmptyCity()
    {
        customer.setCity("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.size() >= 1);
        customer.setCity("Nowhere");
    }
}
