package com.supinbank.entities;

/**
 * Created by IntelliJ IDEA.
 * Customer: oli
 * Date: 2/19/12
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Customer
{
    @Id
    @GeneratedValue(GenerationType.INCREMENTAL)
    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private int zipCode;

    private String city;

    private int phone;

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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
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

    public int getPhone()
    {
        return phone;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }
}
