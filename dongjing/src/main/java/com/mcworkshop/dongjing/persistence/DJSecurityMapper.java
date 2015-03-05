// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;

import com.mcworkshop.dongjing.domain.Contact;
import com.mcworkshop.dongjing.domain.User;

/**
 * @author $Author$
 * 
 */
public interface DJSecurityMapper {

	@Select("SELECT * FROM users WHERE username = #{username}")
	@Results(value = { @Result(column = "contactID", property = "contact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJSecurityMapper.getContactByID")) })
	User authenticate(@Param("username") String username);

	@Select("SELECT * FROM users WHERE userID = #{userID}")
	@Results(value = { @Result(column = "contactID", property = "contact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJSecurityMapper.getContactByID")) })
	User getUserByID(@Param("userID") Long userID);

	@Select("SELECT * FROM contacts WHERE contactID = #{contactID}")
	Contact getContactByID(@Param("contactID") Long contactID);

	@Insert("INSERT INTO contacts(fullName, phone, cellPhone, emailAddress, title, department) "
			+ "VALUES(#{fullName}, #{phone}, #{cellPhone}, #{emailAddress}, #{title}, #{department})")
	@Options(useGeneratedKeys = true, keyProperty = "contactID")
	void createContact(Contact contact) throws PersistenceException;

	@Insert("INSERT INTO users(contactID, userStatusID, username, password, createdDate) "
			+ "VALUES(#{contact.contactID}, #{userStatusID}, #{username}, #{password}, now())")
	@Options(useGeneratedKeys = true, keyProperty = "userID")
	void createUser(User user) throws PersistenceException;

	@Select("SELECT u.userID, u.username, c.fullName FROM users u INNER JOIN contacts c ON c.contactID = u.contactID")
	List<Map<String, Object>> getAllUsers();

	@Update("UPDATE contacts " + "SET fullName = #{fullName}, "
			+ "phone = #{phone}, " + "cellPhone = #{cellPhone}, "
			+ "emailAddress = #{emailAddress}, " + "title = #{title}, "
			+ "department = #{department}" + "WHERE contactID = #{contactID}")
	void updateContact(Contact contact);

	@Update("UPDATE users SET password = #{password} WHERE userID = #{userID}")
	void resetPassword(@Param("password") String password,
			@Param("userID") Long userID);

}
