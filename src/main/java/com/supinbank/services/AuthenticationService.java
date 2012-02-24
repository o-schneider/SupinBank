package com.supinbank.services;

import com.supinbank.entities.BankAdvisor;
import com.supinbank.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class AuthenticationService
{
    @PersistenceContext
    EntityManager em;

    public User authenticate(String email, String password)
    {
        User user = null;

        Query authenticationQuery = em.createNamedQuery("User.login");

        authenticationQuery.setParameter("email", email);
        authenticationQuery.setParameter("password", password);

        try
        {
            user = (User) authenticationQuery.getSingleResult();
        } catch (NoResultException ex)
        {

        }
        return user;
    }
}
