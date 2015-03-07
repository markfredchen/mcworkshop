// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service;

import com.google.inject.Inject;
import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.domain.UserStatus;
import com.mcworkshop.common.exception.AuthenticationException;
import com.mcworkshop.common.exception.DuplicateDomainResourceException;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.exception.UndeletableException;
import com.mcworkshop.common.persistence.SecurityMapper;
import com.mcworkshop.common.security.BaseSecurityServiceImpl;
import com.mcworkshop.common.security.Rights;
import com.mcworkshop.common.util.PasswordUtil;
import com.mcworkshop.dongjing.domain.Contact;
import com.mcworkshop.dongjing.domain.User;
import com.mcworkshop.dongjing.persistence.DJSecurityMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author $Author$
 * 
 */
public class SecurityServiceImpl extends BaseSecurityServiceImpl implements
		SecurityService {

	@Inject
	private DJSecurityMapper mapper;

	@Inject
	private SecurityMapper baseMapper;

	@Override
	@Transactional
	public void createUser(User user) {
		try {
			user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
			user.setUserStatus(UserStatus.ACTIVE);
			this.mapper.createContact(user.getContact());
			this.mapper.createUser(user);
			if (user.getRole() != null) {
				this.baseMapper.createUserRole(user.getUserID(), user.getRole()
						.getRoleID());
			}
		} catch (Throwable e) {
			PersistenceException pe = ExceptionUtil.findCause(e,
					PersistenceException.class);
			if (pe != null && pe.getMessage().contains("user_username")) {
				throw new DuplicateDomainResourceException("username",
						User.class, e);
			} else if (pe != null
					&& pe.getMessage().contains("contacts_emailAddress")) {
				throw new DuplicateDomainResourceException("emailAddress",
						Contact.class, e);
			} else {
				throw ExceptionUtil.handleRuntimeException(e);
			}
		}
	}

	@Override
	public User authenticate(String username, String password)
			throws AuthenticationException {
		User user = this.mapper.authenticate(username);
		List<Role> roles = new ArrayList<Role>();
		if (user == null) {
			throw new AuthenticationException(
					AuthenticationException.ERROR_MESSAGE_KEY_USER_NOT_FOUND);
		} else if (!PasswordUtil.match(user.getPassword(), password)) {
			throw new AuthenticationException(
					AuthenticationException.ERROR_MESSAGE_KEY_PASSWORD_NOT_MATCH);
		} else {
			List<Long> roleIDs = this.baseMapper.getUserRoleIDs(user
					.getUserID());
			for (Long roleID : roleIDs) {
				Role role = this.baseMapper.getRoleByID(roleID);
				roles.add(role);
				List<Long> rightIDs = this.baseMapper.getRoleRights(roleID);
				for (Long rightID : rightIDs) {
					role.addRight(Rights.getInstance().getRightByID(rightID));
				}
			}
		}
		user.setRole(roles.get(0));
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> usersMap = this.mapper.getAllUsers();
		for (Map<String, Object> userMap : usersMap) {
			User user = new User();
			user.setUserID((Long) userMap.get("userID"));
			user.setUsername((String) userMap.get("username"));
			user.getContact().setFullName((String) userMap.get("fullName"));
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUserByID(Long userID) {
		User user = this.mapper.getUserByID(userID);
		Long roleID = this.baseMapper.getUserRoleIDs(user.getUserID()).get(0);
		user.setRole(this.baseMapper.getRoleByID(roleID));
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		this.baseMapper.deleteUserRole(user.getUserID());
		this.baseMapper.deleteUser(user.getUserID());
		this.baseMapper.deleteContact(user.getContact().getContactID());
	}

	@Override
	@Transactional
	public void updateUser(User originalUser, User updatedUser) {
		try {
			if (!originalUser.getContact().equals(updatedUser.getContact())) {
				this.mapper.updateContact(updatedUser.getContact());
			}
			this.baseMapper.deleteUserRole(updatedUser.getUserID());
			this.baseMapper.createUserRole(updatedUser.getUserID(), updatedUser
					.getRole().getRoleID());
		} catch (Throwable e) {
			PersistenceException pe = ExceptionUtil.findCause(e,
					PersistenceException.class);
			if (pe != null && pe.getMessage().contains("contacts_emailAddress")) {
				throw new DuplicateDomainResourceException("emailAddress",
						Contact.class, e);
			} else {
				throw ExceptionUtil.handleRuntimeException(e);
			}
		}
	}

	@Override
	@Transactional
	public void deleteRole(Role role) {
		try {
			this.baseMapper.deleteRoleRight(role.getRoleID());
			this.baseMapper.deleteRole(role.getRoleID());
		} catch (Throwable e) {
			PersistenceException pe = ExceptionUtil.findCause(e,
					PersistenceException.class);
			if (pe != null && pe.getMessage().contains("fk_user_has_role_role")) {
				throw new UndeletableException(Role.class, e);
			} else {
				throw ExceptionUtil.handleRuntimeException(e);
			}

		}
	}

	@Override
	public void changePassword(String oldPwd, String newPassword, User user)
			throws AuthenticationException {
		if (!PasswordUtil.match(user.getPassword(), oldPwd)) {
			throw new AuthenticationException();
		}
		mapper.resetPassword(PasswordUtil.encryptPassword(newPassword),
				user.getUserID());
	}

}