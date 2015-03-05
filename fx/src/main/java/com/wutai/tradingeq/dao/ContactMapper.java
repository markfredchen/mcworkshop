package com.wutai.tradingeq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wutai.tradingeq.domain.Contact;

public interface ContactMapper {

	public static final String INSERT = "INSERT INTO contact(name, email, phone, comments) VALUES(#{name},#{email},#{phone},#{comments})";
	@Insert(INSERT)
	void createContact(Contact contact);
	
	@Select("SELECT * FROM contact")
	List<Contact> getContacts();
	
}
