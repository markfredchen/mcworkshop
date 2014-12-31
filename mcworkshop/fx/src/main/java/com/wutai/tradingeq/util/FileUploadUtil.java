// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.util;

import java.io.File;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;

/**
 * @author $Author$
 *
 */
public class FileUploadUtil {

    public static void uploadFile(final FileUpload upload, final Folder folder, final String newFileName) {
        folder.mkdirs();
        if (upload != null) {
            // Create a new file
            File newFile = new File(folder, newFileName);
            // Check new file, delete if it allready existed
            if (newFile.exists()) {
                // Try to delete the file
                if (!Files.remove(newFile)) {
                    throw new RuntimeException("Unable to remove file" + newFile);
                }
            }
            try {
                // Save to new file
                newFile.createNewFile();
                upload.writeTo(newFile);
                // fileName = upload.getClientFileName();
            } catch (Exception e) {
                throw new RuntimeException("Unable to write file" + newFile);
            }
        }
    }
}
