package com.supinbank.web.servlet;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.InterestPlan;
import com.supinbank.services.GenericCrudService;
import com.supinbank.services.MailService;
import com.supinbank.services.PasswordService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/25/12
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "AddAccountServlet", urlPatterns = {"/admin/accounts/new"})
public class AddAccountServlet extends HttpServlet
{
    @Inject
    private GenericCrudService genericCrudService;
    @Inject
    private PasswordService passwordService;
    @Inject
    private MailService mailService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Customer customer = (Customer) request.getAttribute("customer");

        List<InterestPlan> interestPlans = genericCrudService.readAll(InterestPlan.class);

        request.setAttribute("customer", customer);
        request.setAttribute("[FLASH]customer", customer);
        request.setAttribute("interestPlans", interestPlans);
        request.getRequestDispatcher(
                "/admin/addAccount.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Customer customer = (Customer) request.getAttribute("customer");

        Account account = new Account();

        Random rand = new Random(System.currentTimeMillis());
        String password = Long.toString(rand.nextLong()+rand.nextLong());

        try
        {
            customer.setPassword(passwordService.createHash(customer.getEmail()));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        genericCrudService.create(customer);

        mailService.sendConfirmationMail(customer, password);

        int interestPlanId = Integer.parseInt(request.getParameter("plan"));
        InterestPlan plan = genericCrudService.read(InterestPlan.class, interestPlanId);

        account.setName(request.getParameter("name"));
        account.setInterestPlan(plan);
        account.setAccountOwner(customer);

        genericCrudService.create(account);

        if (customer.getAccounts() == null)
        {
            customer.setAccounts(new ArrayList<Account>());
        }

        customer.getAccounts().add(account);

        genericCrudService.update(customer);

        response.sendRedirect(getServletContext().getContextPath() + "/admin/customers");
    }

    public GenericCrudService getGenericCrudService()
    {
        return genericCrudService;
    }

    public void setGenericCrudService(GenericCrudService genericCrudService)
    {
        this.genericCrudService = genericCrudService;
    }

    public PasswordService getPasswordService()
    {
        return passwordService;
    }

    public void setPasswordService(PasswordService passwordService)
    {
        this.passwordService = passwordService;
    }

    public MailService getMailService()
    {
        return mailService;
    }

    public void setMailService(MailService mailService)
    {
        this.mailService = mailService;
    }
}