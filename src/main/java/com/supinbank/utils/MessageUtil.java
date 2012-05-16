package com.supinbank.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 5/16/12
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageUtil
{
    public static void sendMessage(String source, FacesMessage message)
    {
        FacesContext.getCurrentInstance().addMessage(source, message);
    }
}
