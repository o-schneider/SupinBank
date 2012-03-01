package com.supinbank.web.filter;

import com.supinbank.entities.BankAdvisor;
import com.supinbank.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/29/12
 * Time: 6:19 PM
 * Filter to protect sensible data from unauthorized access.
 */
public abstract class AuthenticationFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Object user = httpRequest.getSession().getAttribute("user");

        if (user == null || !isUserAuthorized(user))
        {
            httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/");
        } else
        {
            chain.doFilter(request, response);
        }
    }

    protected abstract boolean isUserAuthorized(Object user);

    @Override
    public void destroy()
    {
    }
}
