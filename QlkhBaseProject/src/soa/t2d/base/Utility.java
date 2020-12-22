package soa.t2d.base;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Utility {


    public static String encode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	MessageDigest md = MessageDigest.getInstance("MD5");
    	md.update(str.getBytes("UTF-8"));
    	byte[] digest = md.digest();
    	return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
