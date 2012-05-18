package com.supinbank.filter;

import com.supinbank.entities.Customer;

import javax.servlet.annotation.WebFilter;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFilter(filterName = "CustomerAuthenticationFilter", urlPatterns = "/customer/*")
public class CustomerAuthenticationFilter extends AuthenticationFilter
{

    @Override
    protected boolean isUserAuthorized(Object user)
    {
        return (user instanceof Customer);
    }
}
