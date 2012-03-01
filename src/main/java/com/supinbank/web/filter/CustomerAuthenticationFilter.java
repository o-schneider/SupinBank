package com.supinbank.web.filter;

import com.supinbank.entities.Customer;
import com.supinbank.services.AuthenticationService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 10:40 PM
 * Filter to protect customer data.
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
