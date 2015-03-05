// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.carousel;

import java.io.Serializable;

/**
 * @author $Author$
 * 
 */
public class CarouselImage implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            imageURL;
    private String            title;
    private String            description;

    public CarouselImage(String imageURL, String title, String description) {
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;

    }

    public CarouselImage(String imageUrl) {
        this(imageUrl, null, null);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
