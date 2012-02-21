package com.supinbank.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal balance;

    @JoinColumn
    @OneToMany
    private List<Operation> operations;

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

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public List<Operation> getOperations()
    {
        return operations;
    }

    public void setOperations(List<Operation> operations)
    {
        this.operations = operations;
    }
}
