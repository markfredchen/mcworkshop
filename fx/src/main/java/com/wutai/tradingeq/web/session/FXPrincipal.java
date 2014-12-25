// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.session;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.wutai.tradingeq.domain.User;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class FXPrincipal implements Serializable{
    
    private static final long serialVersionUID = -3886194398212892803L;
    
    User user;
    Set<String> roles = new HashSet<String>();
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    public void addRole(String role){
        this.roles.add(role);
    }
    
    public Set<String> getRoles(){
        return roles;
    }
    
    
}
