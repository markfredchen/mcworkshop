// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author $Author$
 * 
 */
public class BeanUtil {

    public static <T extends Object> T copy(T fromBean) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder out = new XMLEncoder(bos);
        out.writeObject(fromBean);
        out.close();
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        XMLDecoder in = new XMLDecoder(bis);
        T toBean = (T) in.readObject();
        in.close();
        return toBean;
    }
}
