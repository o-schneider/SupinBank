package com.supinbank.controllers;

import com.supinbank.entities.Account;
import com.supinbank.entities.Operation;
import com.supinbank.utils.MessageUtil;
import com.supinbank.utils.NavigationUtil;
import com.supinbank.web.utils.ValidationUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/18/12
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class ExternalTransferController extends TransferController
{
    private final String supinBankBankCode = "76267";
    private final String supinBankBranchCode = "00000";

    private String debitAccountId;

    private String bankCode;
    private String branchCode;
    private String accountNumber;
    private String key;

    public ExternalTransferController()
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

        String bban = bankCode + branchCode + accountNumber + key;


        Account debitAccount = getAccountService().completeRead(Integer.parseInt(debitAccountId));

        if (bban.length() == 23)
        {
            if (bankCode.equals(this.bankCode) && branchCode.equals(this.branchCode))
            {
                Account creditAccount = getAccountService().readAccountByBban(bban);
                if (creditAccount == null)
                {
                    MessageUtil.sendMessage(":bankCode", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "This account doesn't exist"));
                } else if (creditAccount.getId() == debitAccount.getId())
                {
                    MessageUtil.sendMessage(":bankCode", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credit and debit account cannot be the same"));
                } else
                {
                    getTransferService().performInternalTransfer(debitAccount, creditAccount, getOperation().getAmount(), getOperation().getWording());
                    navigation = "/customer/accounts" + NavigationUtil.redirect;
                }
            } else
            {
                MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "External transfer not supported"));
            }
        }
        return navigation;
    }

    public String getDebitAccountId()
    {
        return debitAccountId;
    }

    public void setDebitAccountId(String debitAccountId)
    {
        this.debitAccountId = debitAccountId;
    }

    public String getBankCode()
    {
        return bankCode;
    }

    public void setBankCode(String bankCode)
    {
        this.bankCode = bankCode;
    }

    public String getBranchCode()
    {
        return branchCode;
    }

    public void setBranchCode(String branchCode)
    {
        this.branchCode = branchCode;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
