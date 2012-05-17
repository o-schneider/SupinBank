package com.supinbank.services;


import javax.ejb.Stateless;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/22/12
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class PasswordService implements Serializable
{

    public String createHash(String password) throws Exception
    {
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e)
        {
            throw new Exception(e.getMessage());
        } catch (UnsupportedEncodingException e)
        {
            throw new Exception(e.getMessage());
        }

        byte raw[] = md.digest();
        String hash = byteToHex(raw);
        return hash;
    }

    private String byteToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
