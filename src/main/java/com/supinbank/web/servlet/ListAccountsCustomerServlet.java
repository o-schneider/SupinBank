package com.supinbank.web.servlet;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.services.AccountService;
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
 * Date: 2/26/12
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "ListAccountsCustomerServlet", urlPatterns = "/customer/accounts")
public class ListAccountsCustomerServlet extends HttpServlet
{
    @Inject
    private AccountService accountService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Customer customer = (Customer) request.getSession().getAttribute("user");
        List<Account> accounts = accountService.readUserAccounts(customer);

        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher(
                "/customer/listAccounts.jsp").forward(request, response);
    }
}
