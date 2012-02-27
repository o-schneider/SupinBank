package com.supinbank.web.servlet;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/26/12
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "ListOperationsServlet", urlPatterns = "/customer/operations")
public class ListOperationsServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = 0;
        try
        {
            id = Integer.parseInt(request.getParameter("accountId"));
        } catch (Exception e)
        {

        }

        Customer customer = (Customer) request.getSession().getAttribute("user");

        for (Account account : customer.getAccounts())
        {
            if (account.getId() == id)
            {
                request.setAttribute("selectedAccount", account);
            }
        }

        request.getRequestDispatcher(
                "/customer/listOperations.jsp").forward(request, response);
    }
}
