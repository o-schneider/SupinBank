package com.supinbank.services;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.InterestPlan;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/27/12
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class AccountService
{
    private static final String bankCode = "76267";
    private static final String branchCode = "00000";

    @PersistenceContext
    EntityManager em;

    public Account completeRead(Integer accountId)
    {
        Account account = em.find(Account.class, accountId);
        if (account != null)
            account.getOperations().size();
        return account;
    }

    public List<Account> readUserAccounts(Customer accountOwner)
    {
        Query query = em.createNamedQuery("Account.readUserAccounts");
        query.setParameter("accountOwner", accountOwner);
        List<Account> accounts = query.getResultList();
        return accounts;
    }

    public Account readAccountByBban(String bban)
    {
        Query query = em.createNamedQuery("Account.readAccountByBban");
        query.setParameter("bban", bban);
        Account account;
        try
        {
            account = (Account) query.getSingleResult();
            account.getOperations().size();
        } catch (NoResultException e)
        {
            account = null;
        }

        return account;
    }

    public void create(Account account, Customer customer, InterestPlan plan)
    {
        account.setInterestPlan(plan);
        account.setAccountOwner(customer);
        account.setAmount(BigDecimal.ZERO);
        account.setAccountOwner(customer);

        em.persist(account);

        if (customer.getAccounts() == null)
        {
            customer.setAccounts(new ArrayList<Account>());
        }

        customer.getAccounts().add(account);

        em.merge(customer);


        String accountNumber = String.format("%011d", account.getId());
        String bbanWithoutKey = bankCode + branchCode + accountNumber;
        BigInteger keyConcatNum = new BigInteger(bbanWithoutKey);
        BigInteger key = (new BigInteger("97").subtract(keyConcatNum.multiply(new BigInteger("100")).mod(new BigInteger("97"))));
        String keyString = String.format("%02d", key);
        String bban = bbanWithoutKey + key;

        account.setBban(bban);
        em.merge(account);
    }

    public EntityManager getEm()
    {
        return em;
    }

    public void setEm(EntityManager em)
    {
        this.em = em;
    }
}
