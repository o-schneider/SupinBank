package com.supinbank.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Account.readUserAccounts", query = "SELECT a FROM Account a WHERE a.accountOwner = :accountOwner"),
        @NamedQuery(name = "Account.readAccountByBban", query = "SELECT a FROM Account a WHERE a.bban = :bban")
})
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @JoinColumn
    @ManyToOne
    private InterestPlan interestPlan;

    private BigDecimal amount;

    @OneToMany(mappedBy = "account")
    @XmlTransient
    private List<Operation> operations;

    @JoinColumn
    @ManyToOne
    @XmlTransient
    private Customer accountOwner;

    private String bban;

    @XmlTransient
    public Customer getAccountOwner()
    {
        return accountOwner;
    }

    public void setAccountOwner(Customer accountOwner)
    {
        this.accountOwner = accountOwner;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public InterestPlan getInterestPlan()
    {
        return interestPlan;
    }

    public void setInterestPlan(InterestPlan interestPlan)
    {
        this.interestPlan = interestPlan;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    @XmlTransient
    public List<Operation> getOperations()
    {
        return operations;
    }

    public void setOperations(List<Operation> operations)
    {
        this.operations = operations;
    }

    public String getBban()
    {
        return bban;
    }

    public void setBban(String bban)
    {
        this.bban = bban;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean equals = false;
        if (obj instanceof Account)
        {
            Account account = (Account) obj;
            if (account.getId() == this.id)
            {
                equals = true;
            }
        }

        return equals;
    }
}
