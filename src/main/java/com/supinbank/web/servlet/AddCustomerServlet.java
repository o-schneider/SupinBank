package com.supinbank.web.servlet;

import com.supinbank.entities.Customer;
import com.supinbank.web.utils.ValidationUtil;

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
 * Servlet that handles /admin/addCustomer.jsp page.
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
        Customer customer = new Customer();

        customer.setFirstName(request.getParameter("firstname"));
        customer.setLastName(request.getParameter("lastname"));
        customer.setEmail(request.getParameter("email"));
        customer.setAddress(request.getParameter("address"));
        customer.setCity(request.getParameter("city"));

        boolean validPhone;
        try
        {
            customer.setPhone(Integer.parseInt(request.getParameter("phone")));
            validPhone = ValidationUtil.validate(customer, "phone", request);
        } catch (NumberFormatException e)
        {
            validPhone = false;
            List<String> errors = new ArrayList<String>();
            errors.add("It should be a number");
            request.setAttribute("phoneError", errors);
        }

        boolean validZipCode;
        try
        {
            customer.setZipCode(Integer.parseInt(request.getParameter("zip")));
            validZipCode = ValidationUtil.validate(customer, "zipCode", request);
        } catch (NumberFormatException e)
        {
            validZipCode = false;
            List<String> errors = new ArrayList<String>();
            errors.add("It should be a number");
            request.setAttribute("zipCodeError", errors);
        }

        boolean validFirstname = ValidationUtil.validate(customer, "firstName", request);
        boolean validLastname = ValidationUtil.validate(customer, "lastName", request);
        boolean validEmail = ValidationUtil.validate(customer, "email", request);
        boolean validAddress = ValidationUtil.validate(customer, "address", request);
        boolean validCity = ValidationUtil.validate(customer, "city", request);

        boolean validInput = validAddress && validCity && validEmail && validFirstname && validLastname && validPhone && validZipCode;

        if (validInput)
        {
            request.setAttribute("[FLASH]customer", customer);
            response.sendRedirect(getServletContext().getContextPath() + "/admin/accounts/new");
        } else
        {
            request.setAttribute("customer", customer);
            doGet(request, response);
        }
    }
}
