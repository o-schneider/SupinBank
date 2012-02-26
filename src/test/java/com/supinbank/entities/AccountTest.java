package com.supinbank.entities;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * BankAdvisor: oli
 * Date: 2/19/12
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountTest
{
    private static Account account;
    
    @BeforeClass
    public static void initTest()
    {
        account = new Account();
    }

    @Test
    public void hasName()
    {
        account.getName();
    }

    @Test
    public void hasInterestPlan()
    {
        account.getInterestPlan();
    }

    @Test
    public void hasAmount()
    {
        account.getAmount();
    }



}
