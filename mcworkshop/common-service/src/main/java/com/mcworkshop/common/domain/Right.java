// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class Right extends DomainObject {

    private static final long serialVersionUID = 1L;

    private Long              rightID;
    private String            name;
    private List<Right>       childRights      = new ArrayList<Right>();

    public Long getRightID() {
        return rightID;
    }

    public void setRightID(Long rightID) {
        this.rightID = rightID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Right> getChildRights() {
        return childRights;
    }

    public void setChildRights(List<Right> childRights) {
        this.childRights = childRights;
    }

    public void addChildRights(Right childRight) {
        this.childRights.add(childRight);
    }

    public List<String> getGrantedRights() {
        List<String> grantedRights = new ArrayList<String>();
        grantedRights.add(name);
        for (Right right : childRights) {
            grantedRights.add(right.getName());
        }

        return grantedRights;
    }
}
