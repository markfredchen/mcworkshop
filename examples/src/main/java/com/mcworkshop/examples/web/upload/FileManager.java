// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.examples.web.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.upload.FileItem;

/**
 * @author $Author$
 *
 */
public class FileManager
{
    private final Folder baseFolder;

    public FileManager(final String baseFolder)
    {
        this.baseFolder = new Folder(baseFolder);
    }

    public int save(FileItem fileItem) throws IOException
    {
        File file = new File(baseFolder, fileItem.getName());
        FileOutputStream fileOS = new FileOutputStream(file, false);
        return IOUtils.copy(fileItem.getInputStream(), fileOS);
    }

    public byte[] get(String fileName) throws IOException {
        File file = new File(baseFolder, fileName);
        return IOUtils.toByteArray(new FileInputStream(file));
    }

    public boolean delete(String fileName)
    {
        File file = new File(baseFolder, fileName);
        return Files.remove(file);
    }
}
