package com.supinbank.controllers;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.Operation;
import com.supinbank.services.AccountService;
import com.supinbank.services.TransferService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/18/12
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransferController
{
    private String debitAccountId;
    private Operation operation;

    private List<Account> accounts;

    @Inject
    private AccountService accountService;
    @Inject
    private UserController userController;
    @Inject
    private TransferService transferService;

    public TransferController()
    {
        this.operation = new Operation();
    }

    protected void init()
    {
        accounts = accountService.readUserAccounts((Customer) userController.getUser());
    }

    public Operation getOperation()
    {
        return operation;
    }

    public void setOperation(Operation operation)
    {
        this.operation = operation;
    }

    public List<Account> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(List<Account> accounts)
    {
        this.accounts = accounts;
    }

    public AccountService getAccountService()
    {
        return accountService;
    }

    public void setAccountService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    public UserController getUserController()
    {
        return userController;
    }

    public void setUserController(UserController userController)
    {
        this.userController = userController;
    }

    public TransferService getTransferService()
    {
        return transferService;
    }

    public void setTransferService(TransferService transferService)
    {
        this.transferService = transferService;
    }

    public String getDebitAccountId()
    {
        return debitAccountId;
    }

    public void setDebitAccountId(String debitAccountId)
    {
        this.debitAccountId = debitAccountId;
    }
}
