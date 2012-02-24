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
 * To change this template use File | Settings | File Templates.
 */
@WebFilter(filterName = "CustomerAuthenticationFilter", urlPatterns = "/customer/*")
public class CustomerAuthenticationFilter implements Filter
{
    private AuthenticationService authenticationService;

    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Object user = httpRequest.getSession().getAttribute("user");

        if (user == null || !(user instanceof Customer))
        {
            httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/");
        } else
        {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
