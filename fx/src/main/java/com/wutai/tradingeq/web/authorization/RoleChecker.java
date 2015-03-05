// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.authorization;

import java.util.Set;

import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import com.wutai.tradingeq.web.FXWebSession;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class RoleChecker implements IRoleCheckingStrategy{

    @Override
    public boolean hasAnyRole(Roles roles) {
        if(FXWebSession.get().getPrincipal() == null){
            return false;
        }else{
            Set<String> userRoles = FXWebSession.get().getPrincipal().getRoles();
            boolean hasRole = false;
            for(String userRole: userRoles){
               if(roles.contains(userRole)){
                   hasRole = true;
                   break;
               }
            }
            return hasRole;
        }
    }

}
