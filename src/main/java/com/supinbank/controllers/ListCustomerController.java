package com.supinbank.controllers;

import com.supinbank.entities.Customer;
import com.supinbank.services.GenericCrudService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/16/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class ListCustomerController implements Serializable
{
    private List<Customer> customers;
    @Inject
    private GenericCrudService genericCrudService;

    @PostConstruct
    public void init()
    {
        customers = genericCrudService.readAll(Customer.class);
    }

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public void setCustomers(List<Customer> customers)
    {
        this.customers = customers;
    }
}
