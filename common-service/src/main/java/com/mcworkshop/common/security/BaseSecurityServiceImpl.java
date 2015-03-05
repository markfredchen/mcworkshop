// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.mcworkshop.common.domain.Right;
import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.domain.User;
import com.mcworkshop.common.exception.DuplicateDomainResourceException;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.exception.ObjectNotFoundException;
import com.mcworkshop.common.exception.ValidationException;
import com.mcworkshop.common.model.OID;
import com.mcworkshop.common.persistence.SecurityMapper;

/**
 * @author $Author$
 * 
 */
public class BaseSecurityServiceImpl implements SecurityService {

    @Inject
    private SecurityMapper mapper;

    // @Override
    // @Transactional
    // public void createUser(User user, List<Long> roleIDs) throws
    // DuplicateDomainResourceException {
    // try {
    // user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
    // this.mapper.createContact(user.getContact());
    // this.mapper.createUser(user);
    // if(roleIDs != null && !roleIDs.isEmpty()){
    // for(Long roleID: roleIDs){
    // this.mapper.createUserRole(user.getUserID(), roleID);
    // }
    // }
    // } catch (Throwable e) {
    // PersistenceException pe = ExceptionUtil.findCause(e,
    // PersistenceException.class);
    // if (pe != null && pe.getMessage().contains("user_username")) {
    // throw new DuplicateDomainResourceException("username",
    // User.class, e);
    // } else if (pe != null
    // && pe.getMessage().contains("contacts_emailAddress")) {
    // throw new DuplicateDomainResourceException("emailAddress",
    // Contact.class, e);
    // } else {
    // throw ExceptionUtil.handleRuntimeException(e);
    // }
    // }
    // }

    // @Override
    // public User getUserByUserID(Long userID) {
    // return this.mapper.getUserByID(userID);
    // }

    // @Override
    // public Principle authenticate(String username, String password)
    // throws AuthenticationException {
    // User user = this.mapper.getUserByUsername(username);
    // List<Role> roles = new ArrayList<Role>();
    // if(user == null){
    // throw new
    // AuthenticationException(AuthenticationException.ERROR_MESSAGE_KEY_USER_NOT_FOUND);
    // }else if(!PasswordUtil.match(user.getPassword(), password)){
    // throw new
    // AuthenticationException(AuthenticationException.ERROR_MESSAGE_KEY_PASSWORD_NOT_MATCH);
    // }else{
    // List<Long> roleIDs = this.mapper.getUserRoleIDs(user.getUserID());
    // for(Long roleID: roleIDs){
    // roles.add(this.mapper.getRoleByID(roleID));
    // }
    // }
    // Principle principle = new Principle();
    // principle.setUser(user);
    // principle.setRoles(roles);
    // return principle;
    // }

    @Override
    public List<Map<String, Object>> getAllRights() {
        return this.mapper.getAllRights();
    }

    @Override
    @Transactional
    public void createRole(Role role) throws DuplicateDomainResourceException {
        try {
            this.mapper.createRole(role);
        } catch (Throwable e) {
            PersistenceException pe = ExceptionUtil.findCause(e,
                    PersistenceException.class);
            if (pe != null && pe.getMessage().contains("name_UNIQUE")) {
                throw new DuplicateDomainResourceException("name", Role.class,
                        pe);
            } else {
                throw ExceptionUtil.handleRuntimeException(e);
            }
        }
        for (Right right : role.getRights()) {
            this.mapper.createRoleRight(role.getRoleID(), right.getRightID());
        }
    }

    @Override
    public Role getRoleByID(Long roleID) {
        Role role = this.mapper.getRoleByID(roleID);
        List<Long> rightIDs = this.mapper.getRoleRights(roleID);
        for (Long rightID : rightIDs) {
            role.addRight(Rights.getInstance().getRightByID(rightID));
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = this.mapper.getAllRoles();
        for (Role role : roles) {
            List<Long> rightIDs = this.mapper.getRoleRights(role.getRoleID());
            for (Long rightID : rightIDs) {
                role.addRight(Rights.getInstance().getRightByID(rightID));
            }
        }
        return roles;
    }

    @Override
    public void deleteUser(Long userID) {
        this.mapper.deleteUser(userID);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        try {
            this.mapper.updateRole(role);
            this.mapper.deleteRoleRight(role.getRoleID());
            for (Right right : role.getRights()) {
                this.mapper.createRoleRight(role.getRoleID(),
                        right.getRightID());
            }
        } catch (Throwable e) {
            PersistenceException pe = ExceptionUtil.findCause(e,
                    PersistenceException.class);
            if (pe != null && pe.getMessage().contains("name_UNIQUE")) {
                throw new DuplicateDomainResourceException("name", Role.class,
                        e);
            } else {
                throw ExceptionUtil.handleRuntimeException(e);
            }
        }
    }

    @Override
    public void inviteUser(String emailAddress, Long invitorID) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
        Date expirationDate;
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH) + 2;
            int day = Calendar.getInstance().get(Calendar.DATE);
            expirationDate = sdf.parse(month + "-" + day + "-" + year);
        } catch (ParseException e) {
            throw new RuntimeException("fail to parse date");
        }
        OID invitationToken = OID.generate();
        this.mapper.createInvitationToken(invitationToken.toString(),
                emailAddress, false, expirationDate, invitorID);

    }

    @Override
    public boolean validateInvitedUser(OID invitationToken, String emailAddress) {
        return this.mapper.validateInvitationToken(invitationToken.toString(),
                emailAddress) != null;
    }

    @Override
    public void forgotPassword(String emailAddress)
            throws ObjectNotFoundException {
        User user = this.mapper.getUserByEmailAddress(emailAddress);
        Map<String, String> token = this.mapper
                .getTokenByEmailAddress(emailAddress);
        if (user != null) {
            OID resetPasswordToken;
            if (token == null) {
                resetPasswordToken = OID.generate();
            } else {
                resetPasswordToken = OID.fromString(token.get("token"));
            }
            this.mapper.createResetPasswordToken(resetPasswordToken.toString(),
                    emailAddress);
            // TODO: send Email
        } else {
            throw new ObjectNotFoundException();
        }
    }

    @Override
    public boolean validateResetPasswordToken(OID resetPasswordToken) {
        return this.mapper
                .getEmailAddressByToken(resetPasswordToken.toString()) != null;
    }

    @Override
    @Transactional
    public void resetPassword(OID resetPasswordToken, String newPassword)
            throws ValidationException {
        Map<String, String> resetPassowrdToken = this.mapper
                .getEmailAddressByToken(resetPasswordToken.toString());
        if (resetPassowrdToken == null) {
            throw new ValidationException();
        }
        String emailAddress = resetPassowrdToken.get("emailAddress");
        this.mapper.changePassword(newPassword, emailAddress);
        this.mapper.deleteResetPasswordToken(resetPassowrdToken.toString());
    }

}
