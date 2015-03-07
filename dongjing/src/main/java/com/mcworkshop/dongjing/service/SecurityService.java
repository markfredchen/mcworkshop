// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service;

import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.exception.AuthenticationException;
import com.mcworkshop.dongjing.domain.User;

import java.util.List;

/**
 * @author $Author$
 * 
 */
public interface SecurityService extends
		com.mcworkshop.common.security.SecurityService {

	void createUser(User user);

	User authenticate(String username, String password)
			throws AuthenticationException;

	List<User> getAllUsers();

	User getUserByID(Long userID);

	void deleteUser(User user);

	void updateUser(User originalUser, User updatedUser);

	void deleteRole(Role role);

	void changePassword(String oldPwd, String newPassword, User user)
			throws AuthenticationException;
}
