// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.model;

import java.io.Serializable;
import java.util.List;

import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.domain.User;

/**
 * @author $Author$
 * 
 */
public class Principal implements Serializable {

    private static final long serialVersionUID = 1L;

    private User              user;
    private List<Role>        roles;

    public Principal(User user, List<Role> roles) {
        this.user = user;
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
