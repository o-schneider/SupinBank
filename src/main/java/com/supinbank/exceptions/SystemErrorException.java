package com.supinbank.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/22/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SystemErrorException extends Exception
{
    public SystemErrorException()
    {

    }

    public SystemErrorException(String msg) {
        super(msg);
    }
}
