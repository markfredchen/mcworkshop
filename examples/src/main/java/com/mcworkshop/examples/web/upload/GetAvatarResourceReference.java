// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.examples.web.upload;

import java.io.IOException;

import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;

/**
 * @author $Author$
 * 
 */
public class GetAvatarResourceReference extends ResourceReference {

    private static final long serialVersionUID = 1L;
    private final FileManager fileManager;

    public GetAvatarResourceReference(String baseFolder) {
        super(GetAvatarResourceReference.class, "file-manager");

        this.fileManager = new FileManager(baseFolder);
    }

    @Override
    public IResource getResource() {
        return new FileManageResource(fileManager);
    }

    private static class FileManageResource extends AbstractResource {
        private final FileManager fileManager;

        private FileManageResource(FileManager fileManager) {
            this.fileManager = fileManager;
        }

        private static final String FILENAME = "filename";

        @Override
        protected ResourceResponse newResourceResponse(Attributes attributes) {
            ServletWebRequest request = (ServletWebRequest) attributes
                    .getRequest();
            IRequestParameters queryParameters = request.getQueryParameters();
            boolean delete = queryParameters.getParameterValue("delete")
                    .toBoolean(false);
            final StringValue fileName = queryParameters
                    .getParameterValue(FILENAME);

            final ResourceResponse resourceResponse = new ResourceResponse();

            if (delete) {
                fileManager.delete(fileName.toString());
                resourceResponse.setWriteCallback(new WriteCallback() {
                    @Override
                    public void writeData(Attributes attributes)
                            throws IOException {
                    }
                });
            } else {
                resourceResponse.setWriteCallback(new WriteCallback() {
                    @Override
                    public void writeData(Attributes attributes)
                            throws IOException {
                        byte[] bytes = fileManager.get(fileName.toString());
                        attributes.getResponse().write(bytes);
                    }
                });
            }

            return resourceResponse;
        }
    }
}
