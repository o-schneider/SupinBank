package com.supinbank.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Customer: oli
 * Date: 2/19/12
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Customer extends User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50)
    private String address;

    @NotNull
    private Integer zipCode;

    @NotNull
    @Size(min = 1, max = 50)
    private String city;

    @NotNull
    private Integer phone;

    @OneToMany(mappedBy = "accountOwner")
    private List<Account> accounts;

    public List<Account> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(List<Account> accounts)
    {
        this.accounts = accounts;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Integer getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(Integer zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Integer getPhone()
    {
        return phone;
    }

    public void setPhone(Integer phone)
    {
        this.phone = phone;
    }

}
