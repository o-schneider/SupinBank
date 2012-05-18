package com.supinbank.controllers;

import com.supinbank.entities.Account;
import com.supinbank.entities.Operation;
import com.supinbank.services.AccountService;
import com.supinbank.services.GenericCrudService;
import com.supinbank.services.OperationService;
import com.supinbank.utils.MessageUtil;
import com.supinbank.utils.NavigationUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/18/12
 * Time: 9:14 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class NewOperationController
{
    private Operation operation;
    private Account account;
    private String accountId;
    @Inject
    private AccountService accountService;
    @Inject
    private OperationService operationService;

    public NewOperationController()
    {
        operation = new Operation();
    }

    public void loadAccount()
    {
        try
        {
            account = accountService.completeRead(Integer.parseInt(accountId));
        } catch (NumberFormatException numberException)
        {
            MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Couldn't find the account you selected"));
        }
    }

    public String validate()
    {
        operationService.createOperation(account, operation);

        return "/admin/customers/show" + NavigationUtil.redirect + "&id=" + account.getAccountOwner().getId();
    }

    public Operation getOperation()
    {
        return operation;
    }

    public void setOperation(Operation operation)
    {
        this.operation = operation;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }
}
