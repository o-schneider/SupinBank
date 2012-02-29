package com.supinbank.messaging;

import com.supinbank.messaging.xml.BankTransfer;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/29/12
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageSender
{
    private final String CONNECTION_FACTORY = "queue/externalTransfer";
    private final String DESTINATION = "jms/externalTransfer";

    public void sendTransferMessage(BankTransfer transfer)
    {
        try
        {
            Context ctx = new InitialContext();

            ConnectionFactory connectionFactory =
                    (ConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
            Destination destination =
                    (Destination) ctx.lookup(DESTINATION);
            Connection cnx = connectionFactory.createConnection();
            Session session =
                    cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();

            StringWriter writer = new StringWriter();

            JAXBContext context = JAXBContext.newInstance(BankTransfer.class);
            Marshaller m = context.createMarshaller();
            m.marshal(transfer, writer);

            message.setText(writer.toString());
            producer.send(message);
            cnx.close();
        } catch (JAXBException e)
        {
            e.printStackTrace();
        } catch (JMSException e)
        {
            e.printStackTrace();
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
    }
}
