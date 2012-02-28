package com.supinbank.messaging.xml;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/28/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Account
{
    private String ownerName;
    private String establishmentCode;
    private String branchCode;
    private String accountNumber;
    private String key;

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getEstablishmentCode()
    {
        return establishmentCode;
    }

    public void setEstablishmentCode(String establishmentCode)
    {
        this.establishmentCode = establishmentCode;
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
