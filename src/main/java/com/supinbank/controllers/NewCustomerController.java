package com.supinbank.controllers;

import com.supinbank.entities.Customer;
import com.supinbank.utils.NavigationUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/17/12
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class NewCustomerController
{
    private Customer customer;

    public NewCustomerController()
    {
        this.customer = new Customer();
    }

    public String validate()
    {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("customer", customer);
        return "/admin/accounts/new";
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}
