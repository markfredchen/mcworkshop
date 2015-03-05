// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcworkshop.common.domain.Right;

/**
 * @author $Author$
 * 
 */
public class Rights {
    private static Rights      instance     = new Rights();

    private Map<Long, Right>   idRightMap   = new HashMap<Long, Right>();
    private Map<String, Right> nameRightMap = new HashMap<String, Right>();

    private Rights() {
    }

    public static Rights getInstance() {
        return instance;
    }

    public static void initRights(List<Map<String, Object>> rightList) {
        for (Map<String, Object> map : rightList) {
            Right right = new Right();
            Long rightID = (Long) map.get("rightID");
            right.setRightID(rightID);
            right.setName((String) map.get("name"));
            instance.idRightMap.put(right.getRightID(), right);
        }
        for (Map<String, Object> map : rightList) {
            Long parentRightID = (Long) map.get("parentRightID");
            Long rightID = (Long) map.get("rightID");
            if (instance.idRightMap.get(parentRightID) != null) {
                instance.idRightMap.get(parentRightID).addChildRights(
                        instance.idRightMap.get(rightID));
            }
        }
        for (Long rightID : instance.idRightMap.keySet()) {
            instance.nameRightMap.put(instance.idRightMap.get(rightID)
                    .getName(), instance.idRightMap.get(rightID));
        }
    }

    public Right getRightByID(Long rightID) {
        return instance.idRightMap.get(rightID);
    }

    public Right getRightByName(String rightName) {
        return instance.nameRightMap.get(rightName);
    }

    public static boolean hasRight(Right right, String rightName) {
        boolean hasRight = false;
        hasRight = right.getName().equals(rightName);
        if (!hasRight) {
            for (Right childRight : right.getChildRights()) {
                if (childRight.getName().equals(rightName)) {
                    hasRight = true;
                    break;
                }
            }
        }
        return hasRight;
    }

    public static boolean hasRight(List<Right> rights, String rightName) {
        boolean hasRight = false;
        for (Right right : rights) {
            if (hasRight(right, rightName)) {
                hasRight = true;
                break;
            }
        }
        return hasRight;
    }

    public List<Right> getAllRights() {
        return new ArrayList<Right>(idRightMap.values());
    }
    
	public List<Right> getAffiliateBlockRights() {
		return Arrays.asList(getRightByID(202L), getRightByID(203L),
				getRightByID(204L), getRightByID(205L), getRightByID(206L),
				getRightByID(207L), getRightByID(208L), getRightByID(209L),
				getRightByID(210L), getRightByID(211L), getRightByID(212L),
				getRightByID(213L), getRightByID(214L), getRightByID(215L),
				getRightByID(216L), getRightByID(217L), getRightByID(218L),
				getRightByID(219L), getRightByID(220L));
	}
}
