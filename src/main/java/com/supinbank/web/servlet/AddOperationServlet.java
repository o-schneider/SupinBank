package com.supinbank.web.servlet;

import com.supinbank.entities.Account;
import com.supinbank.entities.Customer;
import com.supinbank.entities.Operation;
import com.supinbank.services.GenericCrudService;
import com.supinbank.services.OperationService;
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
 * Servlet that handles /admin/addOperation.jsp page.
 */

@WebServlet(name = "AddOperationServlet", urlPatterns = "/admin/operations/new")
public class AddOperationServlet extends HttpServlet
{
    @Inject
    private GenericCrudService genericCrudService;
    @Inject
    private OperationService operationService;

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
        Account account;
        try
        {
            account = genericCrudService.read(Account.class, Integer.parseInt(request.getParameter("id")));
        } catch (Exception e)
        {
            request.setAttribute("generalError", "An error occurred. Please try again. If the problem persists, contact the support");
            doGet(request, response);
            return;
        }

        String wording = request.getParameter("wording");

        Operation testOperation = new Operation();
        testOperation.setWording(wording);

        boolean validAmount;
        BigDecimal amountNb = BigDecimal.ZERO;
        try
        {
            amountNb = new BigDecimal(request.getParameter("amount"));
            testOperation.setAmount(amountNb);
            validAmount = ValidationUtil.validate(testOperation, "amount", request);
        } catch (NumberFormatException e)
        {
            validAmount = false;
            List<String> errors = new ArrayList<String>();
            errors.add("It should be a number");
            request.setAttribute("amountError", errors);
        }

        boolean validWording = ValidationUtil.validate(testOperation, "wording", request);

        if (validAmount && validWording)
        {
            operationService.createOperation(account, amountNb, wording);

            response.sendRedirect(request.getServletContext().getContextPath() + "/admin/accounts?id=" + account.getAccountOwner().getId());
        } else
        {
            request.setAttribute("account", account);
            request.setAttribute("operation", testOperation);
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

    public OperationService getOperationService()
    {
        return operationService;
    }

    public void setOperationService(OperationService operationService)
    {
        this.operationService = operationService;
    }
}
