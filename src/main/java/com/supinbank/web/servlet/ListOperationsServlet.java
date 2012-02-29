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
import java.util.ArrayList;
import java.util.List;

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
    @Inject
    private AccountService accountService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = 0;
        try
        {
            id = Integer.parseInt(request.getParameter("accountId"));
        } catch (Exception e)
        {
            request.setAttribute("noAccountSelected", true);
        }

        Customer customer = (Customer) request.getSession().getAttribute("user");
        List<Account> accounts = accountService.readUserAccounts(customer);

        for (Account account : accounts)
        {
            if (account.getId() == id)
            {
                request.setAttribute("selectedAccount", account);
            }
        }

        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher(
                "/customer/listOperations.jsp").forward(request, response);
    }

    public AccountService getAccountService()
    {
        return accountService;
    }

    public void setAccountService(AccountService accountService)
    {
        this.accountService = accountService;
    }
}
