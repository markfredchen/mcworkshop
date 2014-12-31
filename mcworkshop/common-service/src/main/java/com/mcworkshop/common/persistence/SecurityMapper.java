// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.persistence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;

import com.mcworkshop.common.domain.Contact;
import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.domain.User;
import com.mcworkshop.common.persistence.search.SearchProvider;
import com.mcworkshop.common.search.SearchCriteria;

/**
 * @author $Author$
 * 
 */
public interface SecurityMapper {

    @Insert("INSERT INTO users(contactID, userStatusID, username, password, createdDate) "
            + "VALUES(#{contact.contactID}, #{userStatusID}, #{username}, #{password}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "userID")
    void createUser(User user) throws PersistenceException;

    @Insert("INSERT INTO contacts(fullName, phone, cellPhone, emailAddress) "
            + "VALUES(#{fullName}, #{phone}, #{cellPhone}, #{emailAddress})")
    @Options(useGeneratedKeys = true, keyProperty = "contactID")
    void createContact(Contact contact) throws PersistenceException;

    @Insert("INSERT INTO roles(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "roleID")
    void createRole(Role role) throws PersistenceException;

    @Insert("INSERT INTO role_right VALUES(#{roleID}, #{rightID})")
    void createRoleRight(@Param("roleID") Long roleID,
            @Param("rightID") Long rightID);

    @Select("SELECT * FROM rights")
    List<Map<String, Object>> getAllRights();

    @Select("SELECT * FROM roles WHERE roleID = #{roleID}")
    Role getRoleByID(@Param("roleID") Long roleID);

    @Select("SELECT rightID FROM role_right WHERE roleID = #{roleID}")
    List<Long> getRoleRights(@Param("roleID") Long roleID);

    @Insert("INSERT INTO user_role VALUES(#{userID}, #{roleID})")
    void createUserRole(@Param("userID") Long userID,
            @Param("roleID") Long roleID);

    @Select("SELECT roleID FROM user_role WHERE userID = #{userID}")
    List<Long> getUserRoleIDs(@Param("userID") Long userID);

    @Select("SELECT * FROM roles ORDER BY roleID ASC")
    List<Role> getAllRoles();

    @SelectProvider(type = SearchProvider.class, method = "searchUser")
    @Results(value = { @Result(column = "contactID", property = "contact", one = @One(select = "com.mcworkshop.common.persistence.SecurityMapper.getContactByID")) })
    List<Map<String, Object>> searchUsers(
            @Param("searchCriteria") SearchCriteria criteria);

    @Delete("DELETE FROM users WHERE userID = #{userID}")
    void deleteUser(@Param("userID") Long userID);

    @Delete("DELETE FROM user_role WHERE userID = #{userID}")
    void deleteUserRole(@Param("userID") Long userID);

    @Delete("DELETE FROM contacts WHERE contactID = #{contactID}")
    void deleteContact(@Param("contactID") Long contactID);

    @Select("SELECT u.userID, u.username, c.fullName FROM users u INNER JOIN contacts c ON u.contactID = c.contactID")
    List<Map<String, Object>> getAllUsers();

    @Delete("DELETE FROM role_right WHERE roleID = #{roleID}")
    void deleteRoleRight(@Param("roleID") Long roleID);

    @Delete("DELETE FROM roles WHERE roleID = #{roleID}")
    void deleteRole(@Param("roleID") Long roleID);

    @Update("UPDATE roles SET name = #{name} WHERE roleID = #{roleID}")
    void updateRole(Role role);

    @Select("SELECT userID, username, emailAddress FROM users WHERE emailAddress = #{emailAddress}")
    User getUserByEmailAddress(@Param("emailAddress") String emailAddress);

    @Insert("INSERT INTO reset_password_token VALUES(#{token}, #{emailAddress})")
    void createResetPasswordToken(@Param("token") String token,
            @Param("emailAddress") String emailAddress);

    @Select("SELECT * FROM reset_password_token WHERE token = #{token}")
    Map<String, String> getEmailAddressByToken(@Param("token") String token);

    @Select("SELECT * FROM reset_password_token WHERE emailAddress = #{emailAddress}")
    Map<String, String> getTokenByEmailAddress(
            @Param("emailAddress") String emailAddress);

    @Delete("DELETE FROM reset_password_token WHERE token = #{token}")
    void deleteResetPasswordToken(@Param("token") String token);

    @Update("UPDATE users SET password = #{newPassword} WHERE userID = #{emailAddress}")
    void changePassword(@Param("newPassword") String newPassword,
            @Param("emailAddress") String emailAddress);

    @Insert("INSERT INTO invitation_token VALUES(#{token}, #{emailAddress}, #{isRegistered}, #{expirationDate})")
    void createInvitationToken(@Param("token") String token,
            @Param("emailAddress") String emailAddress,
            @Param("isRegistered") boolean isRegistered,
            @Param("expirationDate") Date expirationDate,
            @Param("invitedBy") Long userID);

    @Select("SELECT * FROM invitation_token WHERE token = #{token} AND emailAddress = #{emailAddress} AND isRegistered = 'false')")
    Map<String, String> validateInvitationToken(@Param("token") String token,
            @Param("emailAddress") String emailAddress);

    @Update("UPDATE invitation_token SET isRegistered = 'true' WHERE token = #{token}")
    void setInvitationTokenRegistered(@Param("token") String token);
}
