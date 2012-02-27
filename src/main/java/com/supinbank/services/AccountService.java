package com.supinbank.services;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    @PersistenceContext
    EntityManager em;

    public List<Account> readUserAccounts(Customer accountOwner)
    {
        Query query = em.createNamedQuery("Account.readUserAccounts");
        query.setParameter("accountOwner", accountOwner);
        List<Account> accounts = query.getResultList();
        return accounts;
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
