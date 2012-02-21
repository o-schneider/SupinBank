package com.supinbank.entities;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * BankAdvisor: oli
 * Date: 2/21/12
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAdvisorTest
{
    private static BankAdvisor advisor;

    @BeforeClass
    public static void init()
    {
        advisor = new BankAdvisor();
    }

    @Test
    public void hasEmail()
    {
        advisor.getEmail();
    }

    @Test
    public void hasPassword()
    {
        advisor.getPassword();
    }
}
