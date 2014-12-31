// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.exception;

/**
 * @author $Author$
 * 
 */
public class ExceptionUtil {

    private ExceptionUtil() {
    }

    private static int FIND_CAUSE_DEPTH = 10;

    public static <T extends Throwable> T findCause(Throwable e,
            Class<T> targetCauseClazz) {
        Throwable cause = e;
        int depth = 0;
        while (cause != null && depth < FIND_CAUSE_DEPTH) {
            if (targetCauseClazz.equals(cause.getClass())
                    || targetCauseClazz.isAssignableFrom(cause.getClass())) {
                return targetCauseClazz.cast(cause);
            } else {
                cause = cause.getCause();
                depth++;
            }
        }
        return null;
    }

    public static RuntimeException handleRuntimeException(Throwable e) {
        return new RuntimeException(e);
    }

    public static RuntimeException handleRuntimeException(String message,
            Throwable e) {
        return new RuntimeException(message, e);
    }
}
