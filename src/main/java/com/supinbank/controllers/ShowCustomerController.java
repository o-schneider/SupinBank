package com.supinbank.controllers;

import com.supinbank.entities.Customer;
import com.supinbank.services.GenericCrudService;
import com.supinbank.utils.MessageUtil;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/17/12
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class ShowCustomerController implements Serializable
{
    private String customerId;

    private Customer customer;
    @Inject
    private GenericCrudService genericCrudService;

    public void loadCustomer()
    {
        try
        {
            customer = genericCrudService.read(Customer.class, Integer.parseInt(customerId));
        } catch (NumberFormatException numberException)
        {
            MessageUtil.sendMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Couldn't find the customer you requested"));
        }
    }

    public String addAccount()
    {
        System.out.println("Customer : " + customer.getFirstName());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("customer", customer);
        return "/admin/accounts/new";
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
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
