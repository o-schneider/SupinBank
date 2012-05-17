package com.supinbank.web.filter;

import com.supinbank.controllers.UserController;
import com.supinbank.entities.BankAdvisor;
import com.supinbank.entities.User;
import com.supinbank.utils.MessageUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/29/12
 * Time: 6:19 PM
 * To change this template use File | Settings | File Templates.
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

        Object controller = httpRequest.getSession(true).getAttribute("userController");
        User user = null;

        if (controller != null && controller instanceof UserController)
        {
            UserController userController = (UserController) controller;
            user = userController.getUser();
        }

        if (user != null && isUserAuthorized(user))
            chain.doFilter(request, response);
        else
            httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/");
    }

    protected abstract boolean isUserAuthorized(Object user);

    @Override
    public void destroy()
    {
    }
}
