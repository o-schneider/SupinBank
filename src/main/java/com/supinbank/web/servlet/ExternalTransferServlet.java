package com.supinbank.web.servlet;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/28/12
 * Time: 10:53 AM
 * Servlet that handles /customer/externalTransfer.jsp page.
 */

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
import java.util.List;

@WebServlet(name = "ExternalTransferServlet", urlPatterns = {"/customer/transfer/external"})
public class ExternalTransferServlet extends HttpServlet
{
    private final String bankCode = "76267";
    private final String branchCode = "00000";

    @Inject
    private AccountService accountService;
    @Inject
    private GenericCrudService genericCrudService;
    @Inject
    private TransferService transferService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher(
                "/customer/externalTransfer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String bankCode = request.getParameter("bankCode");
        String branchCode = request.getParameter("branchCode");
        String accountNumber = request.getParameter("accountNumber");
        String key = request.getParameter("key");

        String amount = request.getParameter("amount");
        String wording = request.getParameter("wording");

        String bban = bankCode + branchCode + accountNumber + key;

        Operation testOperation = new Operation();

        int debitAccountId = Integer.parseInt(request.getParameter("debitAccount"));
        Account debitAccount = genericCrudService.read(Account.class, debitAccountId);

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
        boolean error = false;
        if (bban.length() == 23 && validAmount && validWording)
        {
            if (bankCode.equals(this.bankCode) && branchCode.equals(this.branchCode))
            {
                Account creditAccount = accountService.readAccountByBban(bban);
                if (creditAccount == null)
                {
                    request.setAttribute("creditAccountError", "This account doesn't exist");
                    error = true;
                } else if (creditAccount.getId() == debitAccount.getId())
                {
                    request.setAttribute("creditAccountError", "Debit account and credit account cannot be the same.");
                    error = true;
                } else
                {
                    transferService.performInternalTransfer(debitAccount, creditAccount, amountNb, wording);
                    response.sendRedirect(getServletContext().getContextPath() + "/customer/accounts");
                }
            } else
            {
                transferService.performExternalTransfer(debitAccount, bban, amountNb, wording);
                response.sendRedirect(getServletContext().getContextPath() + "/customer/accounts");
            }
        } else
        {
            error = true;
        }

        if (error)
        {
            request.setAttribute("bankCode", bankCode);
            request.setAttribute("branchCode", branchCode);
            request.setAttribute("accountNumber", accountNumber);
            request.setAttribute("key", key);

            request.setAttribute("amount", amount);
            request.setAttribute("wording", wording);
            request.setAttribute("creditAccountError", "Missing information in bban");
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

    public AccountService getAccountService()
    {
        return accountService;
    }

    public void setAccountService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    public TransferService getTransferService()
    {
        return transferService;
    }

    public void setTransferService(TransferService transferService)
    {
        this.transferService = transferService;
    }
}
