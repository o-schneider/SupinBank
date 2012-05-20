package com.supinbank.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/22/12
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
})

public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9" +
            "])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "email wrongly formatted")
    @Size(min = 1, max = 50)
    private String email;

    @NotNull
    @Size(min = 6)
    @XmlTransient
    private String password;

    public boolean isAdmin()
    {
        return this instanceof BankAdvisor;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @XmlTransient
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}