package com.supinbank.controllers;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.InterestPlan;
import com.supinbank.services.AccountService;
import com.supinbank.services.GenericCrudService;
import com.supinbank.services.MailService;
import com.supinbank.services.PasswordService;
import com.supinbank.utils.MessageUtil;
import com.supinbank.utils.NavigationUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/17/12
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class NewAccountController
{
    private Account account;
    private String interestPlanId;
    private Customer customer;

    @Inject
    private GenericCrudService genericCrudService;
    @Inject
    private MailService mailService;
    @Inject
    private PasswordService passwordService;
    @Inject
    private AccountService accountService;

    private List<InterestPlan> interestPlans;

    public NewAccountController()
    {
        account = new Account();
    }

    @PostConstruct
    public void init()
    {
        interestPlans = genericCrudService.readAll(InterestPlan.class);
    }

    public void checkCustomer(Customer customer)
    {
        if (customer == null)
        {
            MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred, please try again"));
        } else
        {
            this.customer = customer;
        }
    }

    public String validate()
    {
        String navigation = "";
        System.out.println("Customer : " + customer);
        if (genericCrudService.read(Customer.class, customer.getId()) == null)
        {
            Random rand = new Random(System.currentTimeMillis());
            String password = Long.toString(rand.nextInt() + rand.nextInt());

            try
            {
                customer.setPassword(passwordService.createHash(password));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            genericCrudService.create(customer);
            mailService.sendConfirmationMail(customer, password);
            navigation = "/admin/customers" + NavigationUtil.redirect;
        } else
        {
            navigation = "/admin/customers/show" + NavigationUtil.redirect + "&id=" + customer.getId();
        }

        InterestPlan plan = genericCrudService.read(InterestPlan.class, Integer.parseInt(interestPlanId));

        accountService.create(account, customer, plan);

        return navigation;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public List<InterestPlan> getInterestPlans()
    {
        return interestPlans;
    }

    public void setInterestPlans(List<InterestPlan> interestPlans)
    {
        this.interestPlans = interestPlans;
    }

    public String getInterestPlanId()
    {
        return interestPlanId;
    }

    public void setInterestPlanId(String interestPlanId)
    {
        this.interestPlanId = interestPlanId;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}
