package com.supinbank.webservices;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.services.AccountService;
import com.supinbank.services.GenericCrudService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/18/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/accounts")
public class AccountsResource
{
    @Inject
    private GenericCrudService genericCrudService;
    @Inject
    private AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll(@QueryParam("id") String customerId)
    {
        Response response = Response.status(Response.Status.FORBIDDEN).entity("Unknown customer.").build();

        if (customerId != null && !customerId.isEmpty())
        {
            try
            {
                Customer customer = genericCrudService.read(Customer.class, Integer.parseInt(customerId));
                if (customer != null)
                {
                    List<Account> accounts = accountService.readUserAccounts(customer);
                    GenericEntity entity = new GenericEntity<List<Account>>(accounts)
                    {
                    };
                    response = Response.ok(entity).build();
                }
            } catch (Exception e)
            {

            }
        }
        return response;
    }
}
