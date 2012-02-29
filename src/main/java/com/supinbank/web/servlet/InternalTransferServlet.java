package com.supinbank.web.servlet;

import com.supinbank.entities.Account;
import com.supinbank.entities.Operation;
import com.supinbank.services.AccountService;
import com.supinbank.services.GenericCrudService;
import com.supinbank.services.TransferService;
import com.supinbank.web.utils.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/27/12
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "InternalTransferServlet", urlPatterns = {"/customer/transfer/internal"})
public class InternalTransferServlet extends HttpServlet
{
    @Inject
    private GenericCrudService genericCrudService;
    @Inject
    private TransferService transferService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher(
                "/customer/internalTransfer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String amount = request.getParameter("amount");
        String wording = request.getParameter("wording");

        int debitAccountId = Integer.parseInt(request.getParameter("debitAccount"));
        int creditAccountId = Integer.parseInt(request.getParameter("creditAccount"));

        Account debitAccount = genericCrudService.read(Account.class, debitAccountId);
        Account creditAccount = genericCrudService.read(Account.class, creditAccountId);

        Operation testOperation = new Operation();

        boolean validAccounts = true;

        if (debitAccountId == creditAccountId)
        {
            validAccounts = false;
            request.setAttribute("accountError", "Debit account and credit account cannot be the same.");
        }

        boolean validAmount;
        BigDecimal amountNb = new BigDecimal(0);
        try
        {
            testOperation.setAmount(new BigDecimal(amount));
            validAmount = ValidationUtil.validate(testOperation, "amount", request);
            amountNb = testOperation.getAmount();
        } catch (NumberFormatException e)
        {
            validAmount = false;
            List<String> errors = new ArrayList<String>();
            errors.add("It should be a number");
            request.setAttribute("amountError", errors);
        }

        testOperation.setWording(request.getParameter("wording"));
        boolean validWording = ValidationUtil.validate(testOperation, "wording", request);

        if (validAmount && validWording && validAccounts)
        {
            transferService.performInternalTransfer(debitAccount, creditAccount, amountNb, wording);
            response.sendRedirect(getServletContext().getContextPath() + "/customer/accounts");
        } else
        {
            request.setAttribute("debitAccount", debitAccount);
            request.setAttribute("creditAccount", creditAccount);
            request.setAttribute("amount", amount);
            request.setAttribute("wording", wording);
            doGet(request, response);
        }

    }
}
