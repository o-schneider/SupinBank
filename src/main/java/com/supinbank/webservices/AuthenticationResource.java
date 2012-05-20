package com.supinbank.webservices;

import com.supinbank.entities.Customer;
import com.supinbank.entities.User;
import com.supinbank.services.AuthenticationService;
import com.supinbank.services.PasswordService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/20/12
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/auth")
public class AuthenticationResource
{
    @Inject
    private AuthenticationService authService;
    @Inject
    private PasswordService passwordService;
    @Context
    private HttpServletRequest request;


    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticate(@FormParam("email") String email, @FormParam("password") String password) throws Exception
    {
        String hashedPass = passwordService.createHash(password);

        User user = authService.authenticate(email, hashedPass);

        if (user != null)
        {
            return Response.ok(user).build();
        } else
        {
            return Response.status(Response.Status.FORBIDDEN).entity("Wrong email or password").build();
        }
    }
}
