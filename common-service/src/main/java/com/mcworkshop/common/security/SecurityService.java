// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.security;

import java.util.List;
import java.util.Map;

import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.exception.DuplicateDomainResourceException;
import com.mcworkshop.common.exception.ObjectNotFoundException;
import com.mcworkshop.common.exception.ValidationException;
import com.mcworkshop.common.model.OID;

/**
 * @author $Author$
 * 
 */
public interface SecurityService {

    List<Map<String, Object>> getAllRights();

    void createRole(Role role) throws DuplicateDomainResourceException;

    Role getRoleByID(Long roleID);

    List<Role> getAllRoles();

    void deleteUser(Long userID);

    void updateRole(Role role);

    void inviteUser(String emailAddress, Long invitorID);

    boolean validateInvitedUser(OID invitationToken, String emailAddress);

    void forgotPassword(String emailAddress) throws ObjectNotFoundException;

    boolean validateResetPasswordToken(OID resetPasswordToken);

    void resetPassword(OID resetPasswordToken, String newPassword)
            throws ValidationException;

}
