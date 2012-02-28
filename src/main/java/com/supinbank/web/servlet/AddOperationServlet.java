package com.supinbank.web.servlet;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.Operation;
import com.supinbank.services.GenericCrudService;
import com.supinbank.web.utils.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/26/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */

@WebServlet(name = "AddOperationServlet", urlPatterns = "/admin/operations/new")
public class AddOperationServlet extends HttpServlet
{
    @Inject
    private GenericCrudService genericCrudService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = genericCrudService.read(Account.class, id);

        request.setAttribute("account", account);
        request.getRequestDispatcher(
                "/admin/addOperation.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = genericCrudService.read(Account.class, id);

        Operation operation = new Operation();
        operation.setWording(request.getParameter("wording"));

        boolean validAmount = true;
        try
        {
            operation.setAmount(new BigDecimal(request.getParameter("amount")));
            validAmount = ValidationUtil.validate(operation, "amount", request);
        } catch (NumberFormatException e)
        {
            validAmount = false;
            List<String> errors = new ArrayList<String>();
            errors.add("It should be a number");
            request.setAttribute("amountError", errors);
        }

        boolean validWording = ValidationUtil.validate(operation, "wording", request);

        if (validAmount && validWording)
        {
            operation.setAccount(account);
            operation.setDate(new Date());
            BigDecimal currentAmount = account.getAmount();
            currentAmount = currentAmount.add(operation.getAmount());
            account.setAmount(currentAmount);
            account.getOperations().add(operation);

            genericCrudService.create(operation);
            genericCrudService.update(account);

            response.sendRedirect(request.getServletContext().getContextPath() + "/admin/accounts?id=" + account.getAccountOwner().getId());
        } else
        {
            request.setAttribute("account", account);
            request.setAttribute("operation", operation);
            doGet(request, response);
        }
    }


    public GenericCrudService getGenericCrudService()
    {
        return genericCrudService;
    }

    public void setGenericCrudService(GenericCrudService genericCrudService)
    {
        this.genericCrudService = genericCrudService;
    }
}
