// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.sysenum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author $Author$
 * 
 */
public class SystemEnumeration {

    private static SystemEnumeration                     instance     = new SystemEnumeration();

    private Map<Enum<? extends SysEnum>, Long>           enumToID     = new HashMap<Enum<? extends SysEnum>, Long>();
    private Map<Long, Enum<? extends SysEnum>>           idToEnum     = new HashMap<Long, Enum<? extends SysEnum>>();
    private Map<Class<?>, List<Enum<? extends SysEnum>>> typeEnumList = new HashMap<Class<?>, List<Enum<? extends SysEnum>>>();

    private SystemEnumeration() {
    }

    public static SystemEnumeration getInstance() {
        return instance;
    }

    public static void initSystemEnumeration(
            List<Map<String, Object>> sysEnumList) throws Throwable {
        for (Map<String, Object> map : sysEnumList) {
            String type = (String) map.get("type");
            String value = (String) map.get("value");
            Class clz = Class.forName(type);
            Enum<? extends SysEnum> enumValue = Enum.valueOf(clz, value);
            Long id = (Long) map.get("sysEnumID");
            ((SysEnum) enumValue).setSequence((Integer) map.get("sequence"));
            ((SysEnum) enumValue).setMessageKey(type + "." + value);
            instance.enumToID.put(enumValue, id);
            instance.idToEnum.put(id, enumValue);
            if (instance.typeEnumList.get(clz) == null) {
                instance.typeEnumList.put(clz,
                        new ArrayList<Enum<? extends SysEnum>>());
            }
            instance.typeEnumList.get(clz).add(enumValue);
        }
    }

    public <T extends Enum<T>> Long getIdByKey(Enum<T> key) {
        if (key == null) {
            return null;
        }
        return this.enumToID.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T getKeyById(Class<T> clazz, Long id) {
        return (T) this.idToEnum.get(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> List<T> getKeysByType(Class<T> clazz) {
        return (List<T>) this.typeEnumList.get(clazz);
    }
}
