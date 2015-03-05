// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.domain;

import java.io.Serializable;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class Product implements Serializable{

    private static final long serialVersionUID = 1526668415259126464L;

    private Long productID;
    private OID productOID;
    private String name;
    private String shortDescription;
    private String imageURL;
    private String fullDescription;
    public Long getProductID() {
        return productID;
    }
    public void setProductID(Long productID) {
        this.productID = productID;
    }
    public OID getProductOID() {
        return productOID;
    }
    public void setProductOID(OID productOID) {
        this.productOID = productOID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getFullDescription() {
        return fullDescription;
    }
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
}
