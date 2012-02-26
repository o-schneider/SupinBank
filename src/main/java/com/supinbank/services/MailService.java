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
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class MailService
{
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String USERNAME = "supinbankmailing@gmail.com ";
    private static final String PASSWORD = "soupedelegume67";

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
            new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
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

            content.append("Dear "+customer.getFirstName()+" "+customer.getLastName()+" \n\n");
            content.append("Your account to SupinBank has been created!\n");
            content.append("===========================================\n");
            content.append(" Username : "+customer.getEmail()+"\n");
            content.append(" Password : "+clearPassword+"\n");
            content.append("===========================================\n");
            content.append("How to log in to it? Just visit www.supinbank.com! \n");
            content.append("See you soon!\n\n");
            content.append("SupinBank customer service");

            System.out.println(content.toString());

            msg.setSubject("Your new SupinBank account");
            msg.setSentDate(new Date());
            msg.setText(content.toString());
            Transport.send(msg);
        } catch (MessagingException mex)
        {
            return new AsyncResult<Boolean>(Boolean.FALSE);
        }
        return new AsyncResult<Boolean>(Boolean.TRUE);
    }
}
