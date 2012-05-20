package com.supinbank.controllers;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.services.AccountService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/18/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class ListAccountsController
{
    @Inject
    private UserController userController;
    @Inject
    private AccountService accountService;

    private List<Account> accounts;

    @PostConstruct
    public void init()
    {
        accounts = accountService.readUserAccounts((Customer) userController.getUser());
    }

    public List<Account> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(List<Account> accounts)
    {
        this.accounts = accounts;
    }
}
