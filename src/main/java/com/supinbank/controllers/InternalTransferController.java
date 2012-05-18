package com.supinbank.controllers;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.Operation;
import com.supinbank.services.AccountService;
import com.supinbank.services.TransferService;
import com.supinbank.utils.MessageUtil;
import com.supinbank.utils.NavigationUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/18/12
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class InternalTransferController extends TransferController
{
    private String creditAccountId;

    public InternalTransferController()
    {
        super();
    }

    @PostConstruct
    public void init()
    {
        super.init();
    }

    public String validate()
    {
        String navigation = "";
        if (getDebitAccountId().equals(creditAccountId))
        {
            MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credit and Debit account", "The credit and debit accounts cannot be the same"));
        } else
        {
            Account debitAccount = getAccountService().completeRead(Integer.parseInt(getDebitAccountId()));
            Account creditAccount = getAccountService().completeRead(Integer.parseInt(creditAccountId));

            getTransferService().performInternalTransfer(debitAccount, creditAccount, getOperation().getAmount(), getOperation().getWording());
            navigation = "/customer/accounts" + NavigationUtil.redirect;
        }
        return navigation;
    }

    public String getCreditAccountId()
    {
        return creditAccountId;
    }

    public void setCreditAccountId(String creditAccountId)
    {
        this.creditAccountId = creditAccountId;
    }

}
