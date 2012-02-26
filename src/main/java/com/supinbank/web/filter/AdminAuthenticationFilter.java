package com.supinbank.web.filter;

import com.supinbank.entities.BankAdvisor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFilter(filterName = "AdminAuthenticationFilter", urlPatterns = "/admin/*")
public class AdminAuthenticationFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Object user = httpRequest.getSession().getAttribute("user");

        if (user == null || !(user instanceof BankAdvisor))
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
