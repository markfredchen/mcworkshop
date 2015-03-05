// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wutai.tradingeq.domain.OID;
import com.wutai.tradingeq.domain.Product;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public interface ProductMapper {

    static final String INSERT = "INSERT INTO product(productOID, name, shortDescription, imageURL, fullDescription) VALUES(#{productOID}, #{name}, #{shortDescription}, #{imageURL}, #{fullDescription})";
    static final String UPDATE = "UPDATE product SET name=#{name}, shortDescription=#{shortDescription}, fullDescription=#{fullDescription} WHERE productOID = #{productOID}";
            
    static final String SELECT = "SELECT * FROM product WHERE productID = #{productID}";
    static final String SELECT_BY_OID = "SELECT * FROM product WHERE productOID = #{productOID}";
    static final String SELECT_ALL = "SELECT * FROM product";
    
    @Insert(INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void createProduct(Product product);
    
    @Select(SELECT)
    public Product getProduct(Long productID);
    
    @Select(SELECT_BY_OID)
    public Product getProductByOID(OID productOID);
    
    @Select(SELECT_ALL)
    public List<Product> getProducts();
    
    @Update(UPDATE)
    public void updateProduct(Product product);
}
