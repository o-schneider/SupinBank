package com.supinbank.web.servlet;

import com.supinbank.entities.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/25/12
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "AddCustomerServlet", urlPatterns = {"/admin/customers/new"})
public class AddCustomerServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher(
                "/admin/addCustomer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        boolean valid = true;

        Customer customer = new Customer();

        customer.setFirstName(request.getParameter("firstname"));
        customer.setLastName(request.getParameter("lastname"));
        customer.setEmail(request.getParameter("email"));
        customer.setAddress(request.getParameter("address"));
        customer.setCity(request.getParameter("city"));

        try
        {
            customer.setPhone(Integer.parseInt(request.getParameter("phone")));
        } catch (NumberFormatException e)
        {
            valid = false;
            request.setAttribute("phoneError", "It should be a number");
        }

        try
        {
            customer.setZipCode(Integer.parseInt(request.getParameter("zip")));
        } catch (NumberFormatException e)
        {
            valid = false;
            request.setAttribute("zipCodeError", "It should be a number");
        }


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        boolean validFirstname = validate(customer, "firstName", validator, request);
        boolean validLastname = validate(customer, "lastName", validator, request);
        boolean validEmail = validate(customer, "email", validator, request);
        boolean validAddress = validate(customer, "address", validator, request);
        boolean validCity = validate(customer, "city", validator, request);
        boolean validZipCode = validate(customer, "zipCode", validator, request);
        boolean validPhone = validate(customer, "phone", validator, request);

        valid = valid && validAddress && validCity && validEmail && validFirstname && validLastname && validPhone && validZipCode;

        if (valid)
        {
            request.setAttribute("[FLASH]customer", customer);
            response.sendRedirect(getServletContext().getContextPath() + "/admin/accounts/new");
        } else
        {
            request.setAttribute("customer", customer);
            doGet(request, response);
        }
    }

    private boolean validate(Customer customer, String property, Validator validator, HttpServletRequest request)
    {
        boolean valid = true;

        Set<ConstraintViolation<Customer>> violations = validator.validateProperty(customer, property);

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
