package com.supinbank.entities;

/**
 * Created by IntelliJ IDEA.
 * Customer: oli
 * Date: 2/19/12
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerTest {
    private Customer customer;

    @BeforeClass
    public void initUser()
    {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@heaven.com");
        customer.setAddress("Infinite Loop");
        customer.setZipCode(12345);
        customer.setCity("Nowhere");
        customer.setPhone(0102030405);
    }

    @test
    public void hasFirstName()
    {
        customer.getFirstName();
    }

    @Test
    public void hasLastName()
    {
        customer.getLastName();
    }

    @Test
    public void hasEmail()
    {
        customer.getEmail();
    }

    @test
    public void hasAddress()
    {
        customer.getAddress();
    }

    @Test
    public void hasZipCode()
    {
        customer.getZipCode();
    }

    @Test
    public void hasCity()
    {
        customer.getCity();
    }

    @test
    public void hasPhone()
    {
        customer.getPhone();
    }
}
