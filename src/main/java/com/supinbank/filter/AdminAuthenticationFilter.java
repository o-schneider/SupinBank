package com.supinbank.filter;

import com.supinbank.entities.BankAdvisor;

import javax.servlet.annotation.WebFilter;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFilter(filterName = "AdminAuthenticationFilter", urlPatterns = "/admin/*")
public class AdminAuthenticationFilter extends AuthenticationFilter
{
    @Override
    protected boolean isUserAuthorized(Object user)
    {
        return (user instanceof BankAdvisor);
    }
}
