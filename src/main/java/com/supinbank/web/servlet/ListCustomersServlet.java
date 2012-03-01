package com.supinbank.web.servlet;

import com.supinbank.entities.Customer;
import com.supinbank.services.GenericCrudService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/24/12
 * Time: 9:33 PM
 * Servlet that handles /admin/listCustomers.jsp page
 */
@WebServlet(name = "ListCustomersServlet", urlPatterns = {"/admin/customers"})
public class ListCustomersServlet extends HttpServlet
{
    @Inject
    private GenericCrudService genericCrudService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Customer> customers = genericCrudService.readAll(Customer.class);
        request.setAttribute("customers", customers);
        request.getRequestDispatcher(
                "/admin/listCustomers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
