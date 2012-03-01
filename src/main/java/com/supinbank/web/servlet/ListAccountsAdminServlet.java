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

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/25/12
 * Time: 10:42 PM
 * Servlet that handles /admin/listAccounts.jsp page.
 */
@WebServlet(name = "ListAccountsAdminServlet", urlPatterns = "/admin/accounts")
public class ListAccountsAdminServlet extends HttpServlet
{
    @Inject
    private GenericCrudService genericCrudService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = genericCrudService.read(Customer.class, id);

        request.setAttribute("customer", customer);
        request.getRequestDispatcher(
                "/admin/listAccounts.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = genericCrudService.read(Customer.class, id);

        request.setAttribute("[FLASH]customer", customer);
        response.sendRedirect(getServletContext().getContextPath() + "/admin/accounts/new");
    }

    public GenericCrudService getGenericCrudService()
    {
        return genericCrudService;
    }

    public void setGenericCrudService(GenericCrudService genericCrudService)
    {
        this.genericCrudService = genericCrudService;
    }
}
