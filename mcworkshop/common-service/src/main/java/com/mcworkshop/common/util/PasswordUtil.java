// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author $Author$
 * 
 */
public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean match(String encryptedPwd, String pwd) {
        return BCrypt.checkpw(pwd, encryptedPwd);
    }
    
    public static void main(String[] args) {
		System.out.println(PasswordUtil.encryptPassword("admin"));
	}
}
