package com.supinbank.services;

import com.supinbank.entities.Customer;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/25/12
 * Time: 9:33 PM
 * Service to send mails to new customers.
 */
@Stateless
public class MailService
{
    private final String SMTP_HOST_NAME = "smtp.gmail.com";
    private final String SMTP_PORT = "465";
    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private final String USERNAME = "supinbankmailing@gmail.com ";
    private final String PASSWORD = "soupedelegume67";

    @Asynchronous
    public Future<Boolean> sendConfirmationMail(Customer customer, String clearPassword)
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.from", "hello@supinbank.com");


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator()
                {

                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(MimeMessage.RecipientType.TO,
                    customer.getEmail());

            StringBuilder content = new StringBuilder();

            content.append("Dear " + customer.getFirstName() + " " + customer.getLastName() + " \n\n");
            content.append("Your account to SupinBank has been created!\n");
            content.append("===========================================\n");
            content.append(" Username : " + customer.getEmail() + "\n");
            content.append(" Password : " + clearPassword + "\n");
            content.append("===========================================\n");
            content.append("How to log in to it? Just visit www.supinbank.com! \n");
            content.append("See you soon!\n\n");
            content.append("SupinBank customer service");

            System.out.println(content.toString());

            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setSubject("Your new SupinBank account", "UTF-8");
            msg.setSentDate(new Date());
            msg.setText(content.toString(), "UTF-8");
            Transport.send(msg);
        } catch (MessagingException mex)
        {
            return new AsyncResult<Boolean>(Boolean.FALSE);
        }
        return new AsyncResult<Boolean>(Boolean.TRUE);
    }
}
