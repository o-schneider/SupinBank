package com.supinbank.messaging.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/28/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class BankTransfer
{
    private String wording;
    private Account sender;
    private Account recipient;
    private BigDecimal amount;
    private Date transferDate;

    public String getWording()
    {
        return wording;
    }

    public void setWording(String wording)
    {
        this.wording = wording;
    }

    public Account getSender()
    {
        return sender;
    }

    public void setSender(Account sender)
    {
        this.sender = sender;
    }

    public Account getRecipient()
    {
        return recipient;
    }

    public void setRecipient(Account recipient)
    {
        this.recipient = recipient;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Date getTransferDate()
    {
        return transferDate;
    }

    public void setTransferDate(Date transferDate)
    {
        this.transferDate = transferDate;
    }
}
