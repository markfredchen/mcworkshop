// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.examples.web.upload;

import java.util.List;

import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.upload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author $Author$
 *
 */
public class UploadAvatarResourceReference extends ResourceReference
{

    private static final long serialVersionUID = 1L;
    private final FileManager fileManager;

    public UploadAvatarResourceReference(String baseFolder)
    {
        super(UploadAvatarResourceReference.class, "file-upload");

        this.fileManager = new FileManager(baseFolder);
    }

    @Override
    public IResource getResource()
    {
        return new AbstractFileUploadResource(fileManager)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected String generateJsonResponse(ResourceResponse resourceResponse, ServletWebRequest webRequest, List<FileItem> files) {
                JSONArray json = new JSONArray();

                for (FileItem fileItem : files)
                {
                    JSONObject fileJson = new JSONObject();

                    try {
                        fileJson.put("name", fileItem.getName());
                        fileJson.put("url", getViewUrl(fileItem));
                        fileJson.put("thumbnail_url", getViewUrl(fileItem));
                        fileJson.put("size", fileItem.getSize());
                        fileJson.put("delete_type", "POST");
                        fileJson.put("delete_url", getDeleteUrl(fileItem));
                    } catch (JSONException e) {
                        try {
                            fileJson.put("error", e.getMessage());
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }

                    json.put(fileJson);
                }

                return json.toString();
            }

            @Override
            protected String generateHtmlResponse(ResourceResponse resourceResponse, ServletWebRequest webRequest, List<FileItem> files)
            {
                String jsonResponse = generateJsonResponse(resourceResponse, webRequest, files);
                return jsonResponse;
            }
        };
    }

    private CharSequence getViewUrl(FileItem fileItem) {
        PageParameters params = new PageParameters();
        params.set("filename", fileItem.getName());
        CharSequence url = RequestCycle.get().urlFor(new GetAvatarResourceReference("/Users/mchen1/MCWorkshop/client/examples/avator"), params);
        return url;
    }


    private CharSequence getDeleteUrl(FileItem fileItem) {
        PageParameters params = new PageParameters();
        params.set("filename", fileItem.getName());
        params.set("delete", true);
        CharSequence url = RequestCycle.get().urlFor(new GetAvatarResourceReference("/Users/mchen1/MCWorkshop/client/examples/avator"), params);
        return url;
    }

}
