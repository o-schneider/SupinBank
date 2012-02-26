package com.supinbank.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Operation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @Size(min = 1, max = 255)
    private String wording;

    @NotNull
    private BigDecimal amount;

    @JoinColumn
    @ManyToOne
    private Account account;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getWording()
    {
        return wording;
    }

    public void setWording(String wording)
    {
        this.wording = wording;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }
}
