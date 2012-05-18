package com.supinbank.controllers;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.Operation;
import com.supinbank.services.AccountService;
import com.supinbank.utils.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/18/12
 * Time: 12:09 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ListOperationController
{

    private String accountId;
    private List<Operation> operations;
    private List<Account> accounts;

    @Inject
    private AccountService accountService;
    @Inject
    private UserController userController;

    @PostConstruct
    public void init()
    {
        accounts = accountService.readUserAccounts((Customer) userController.getUser());
    }

    public void loadFromParameters()
    {
        if (accountId != null && !FacesContext.getCurrentInstance().isPostback())
        {
            loadOperations();
        }
    }

    public void loadOperations()
    {
        if (accountId.isEmpty())
        {
            MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No account selected", "Please select an account"));
        } else
        {
            int id = -1;
            try
            {
                id = Integer.parseInt(accountId);
            } catch (Exception e)
            {

            }
            Account account = accountService.completeRead(id);

            if (accounts.contains(account))
            {
                this.operations = account.getOperations();
            } else
            {
                MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't find the account", "Could not find the account you selected"));
            }
        }
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public List<Operation> getOperations()
    {
        return operations;
    }

    public void setOperations(List<Operation> operations)
    {
        this.operations = operations;
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
