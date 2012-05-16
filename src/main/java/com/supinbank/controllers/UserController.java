package com.supinbank.controllers;

import com.supinbank.entities.BankAdvisor;
import com.supinbank.entities.Customer;
import com.supinbank.entities.User;
import com.supinbank.services.AuthenticationService;
import com.supinbank.services.PasswordService;
import com.supinbank.utils.MessageUtil;
import com.supinbank.utils.NavigationUtil;
import org.primefaces.component.message.Message;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.attribute.standard.Severity;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/16/12
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class UserController
{
    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private PasswordService passwordService;

    private String email;
    private String password;

    private User user;

    public String login()
    {
        String navigation = "";

        if (email == null || email.isEmpty())
            MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication error", "Please enter your email"));
        else if (password == null || password.isEmpty())
            MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication error", "Please enter your email"));
        else
        {
            try
            {
                String hashedPassword = passwordService.createHash(password);
                User user = authenticationService.authenticate(email, hashedPassword);
                if (user == null)
                {
                    MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication error", "Wrong username or password. Please try again"));
                } else
                {

                    this.user = user;
                    if (this.user instanceof BankAdvisor)
                    {
                        navigation = "/admin/customers";
                    } else if (user instanceof Customer)
                    {
                        navigation = "/customer/accounts";
                    }
                    navigation += NavigationUtil.redirect;
                }
            } catch (Exception e)
            {
                MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred. Please contact the support"));
            }
        }
        return navigation;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
