// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mcworkshop.common.security.Rights;

/**
 * @author $Author$
 * 
 */
public class Role extends DomainObject {

    private static final long serialVersionUID = 4497070430299866354L;

    private Long              roleID;
    private String            name;
    private List<Right>       rights           = new ArrayList<Right>();

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    public void addRight(Right right) {
        this.rights.add(right);
    }

    public void setAdmin(boolean isAdmin) {
        if (isAdmin) {
            rights.clear();
            rights.add(Rights.getInstance().getRightByName("SYSTEM"));
        } else {
            rights.clear();
        }
    }

    public boolean isAdmin() {
        for (Right right : rights) {
            if (right.getName().equals("SYSTEM")) {
                return true;
            }
        }
        return false;
    }

    public Set<String> getGrantedRights() {
        Set<String> grantedRights = new HashSet<String>();
        for (Right right : rights) {
            grantedRights.addAll(right.getGrantedRights());
        }
        return grantedRights;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Role)) {
            return false;
        }
        Role r = (Role) obj;
        return roleID == r.getRoleID();
    }
}
