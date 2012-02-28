package com.supinbank.services;

import com.supinbank.entities.Account;
import com.supinbank.entities.Operation;
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
    private GenericCrudService genericCrudService;

    public void performInternalTransfer(Account debitAccount, Account creditAccount, BigDecimal amount, String wording)
    {
        createTransferOperation(creditAccount, amount, wording);
        createTransferOperation(debitAccount, amount.negate(), wording);
    }

    public void performExternalTransfer(Account debAccount, String creditAccountBban, BigDecimal amount, String wording) throws NamingException, JMSException, JAXBException
    {
        Context ctx = new InitialContext();

        ConnectionFactory connectionFactory =
                (ConnectionFactory) ctx.lookup("queue/externalTransfer");
        Destination destination =
                (Destination) ctx.lookup("jms/externalTransfer");
        Connection cnx = connectionFactory.createConnection();
        Session session =
                cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage();

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

        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(BankTransfer.class);
        Marshaller m = context.createMarshaller();
        m.marshal(bankTransfer, writer);

        message.setText(writer.toString());
        producer.send(message);
        cnx.close();

        createTransferOperation(debAccount, amount.negate(), wording);
    }

    public void createTransferOperation(Account account, BigDecimal amount, String wording)
    {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setDate(new Date());
        operation.setAmount(amount);
        operation.setWording(wording);

        BigDecimal currentAmount = account.getAmount();
        BigDecimal newAmount = currentAmount.add(amount);
        account.setAmount(newAmount);
        account.getOperations().add(operation);

        genericCrudService.create(operation);
        genericCrudService.update(account);
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
