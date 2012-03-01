package com.supinbank.web.servlet;

import com.supinbank.entities.BankAdvisor;
import com.supinbank.entities.Customer;
import com.supinbank.entities.User;
import com.supinbank.services.AuthenticationService;
import com.supinbank.services.PasswordService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LoggingMXBean;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/21/12
 * Time: 10:43 PM
 * Servlet that handles /index.jsp page.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/home"})
public class LoginServlet extends HttpServlet
{
    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private PasswordService passwordService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try
        {
            String hashedPassword = passwordService.createHash(password);
            User user = authenticationService.authenticate(email, hashedPassword);
            if (user == null)
            {
                request.setAttribute("generalError", "Wrong username or password. Please try again");
                doGet(request, response);
            } else
            {
                request.getSession().setAttribute("user", user);
                if (user instanceof BankAdvisor)
                {
                    request.getSession().setAttribute("admin", true);
                    response.sendRedirect(getServletContext().getContextPath() + "/admin/customers");
                } else if (user instanceof Customer)
                {
                    request.getSession().setAttribute("admin", false);
                    response.sendRedirect(getServletContext().getContextPath() + "/customer/accounts");
                }
            }
        } catch (Exception e)
        {
            request.setAttribute("generalError", "An error occurred. Please contact the support");
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher(
                "/index.jsp").forward(request, response);
    }

    public AuthenticationService getAuthenticationService()
    {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }

    public PasswordService getPasswordService()
    {
        return passwordService;
    }

    public void setPasswordService(PasswordService passwordService)
    {
        this.passwordService = passwordService;
    }
}
