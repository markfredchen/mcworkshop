// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.exception;

/**
 * @author $Author$
 * 
 */
public class AuthenticationException extends Exception {

    private static final long  serialVersionUID                     = 1L;

    public static final String ERROR_MESSAGE_KEY_PASSWORD_NOT_MATCH = "authenticate.fail.password.not.match";

    public static final String ERROR_MESSAGE_KEY_USER_NOT_FOUND     = "authenticate.fail.user.not.exists";

    public AuthenticationException() {}
    
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable e) {
        super(e);
    }

    public AuthenticationException(String message, Throwable e) {
        super(message, e);
    }
}
