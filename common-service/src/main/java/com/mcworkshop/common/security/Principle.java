// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: Principle.java 54 2014-03-30 16:03:34Z mchen $
package com.mcworkshop.common.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mcworkshop.common.domain.Right;
import com.mcworkshop.common.domain.Role;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class Principle implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Role>        roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getRoleRights() {
        Set<String> roles = new HashSet<String>();
        for (Role role : this.roles) {
            for (Right right : role.getRights()) {
                roles.addAll(right.getGrantedRights());
            }
        }
        return new ArrayList<String>(roles);
    }

}
