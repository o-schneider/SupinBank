package com.supinbank.services;

import com.supinbank.entities.Account;
import com.supinbank.entities.Operation;
import com.supinbank.messaging.MessageSender;
import com.supinbank.messaging.xml.BankTransfer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/28/12
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class TransferService
{
    @Inject
    private OperationService operationService;

    public void performInternalTransfer(Account debitAccount, Account creditAccount, BigDecimal amount, String wording)
    {
        operationService.createOperation(creditAccount, amount, wording);
        operationService.createOperation(debitAccount, amount.negate(), wording);
    }

    public void performExternalTransfer(Account debAccount, String creditAccountBban, BigDecimal amount, String wording)
    {
        BankTransfer bankTransfer = new BankTransfer();
        com.supinbank.messaging.xml.Account sender = new com.supinbank.messaging.xml.Account();
        com.supinbank.messaging.xml.Account recipient = new com.supinbank.messaging.xml.Account();

        sender.setEstablishmentCode(debAccount.getBban().substring(0, 5));
        sender.setBranchCode(debAccount.getBban().substring(5, 10));
        sender.setAccountNumber(debAccount.getBban().substring(10, 21));
        sender.setKey(debAccount.getBban().substring(21, 23));
        sender.setOwnerName(debAccount.getAccountOwner().getFirstName() + " " + debAccount.getAccountOwner().getLastName());

        recipient.setEstablishmentCode(creditAccountBban.substring(0, 5));
        recipient.setBranchCode(creditAccountBban.substring(5, 10));
        recipient.setAccountNumber(creditAccountBban.substring(10, 21));
        recipient.setKey(creditAccountBban.substring(21, 23));
        recipient.setOwnerName("");

        bankTransfer.setAmount(amount);
        bankTransfer.setTransferDate(new Date());
        bankTransfer.setWording(wording);
        bankTransfer.setRecipient(recipient);
        bankTransfer.setSender(sender);

        MessageSender msgSender = new MessageSender();
        msgSender.sendTransferMessage(bankTransfer);

        operationService.createOperation(debAccount, amount.negate(), wording);
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
