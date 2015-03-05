// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.validation;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;

/**
 * @author $Author$
 * 
 */
public class FileUploadValidator extends Behavior implements
        IValidator<FileUpload> {

    private static final long serialVersionUID = 1L;

    @Override
    public void validate(IValidatable<FileUpload> validatable) {
    }

}
