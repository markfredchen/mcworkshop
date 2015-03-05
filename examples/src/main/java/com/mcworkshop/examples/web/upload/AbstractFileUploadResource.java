// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.examples.web.upload;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.wicket.protocol.http.servlet.MultipartServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileUploadException;

/**
 * @author $Author$
 * 
 */
public abstract class AbstractFileUploadResource extends AbstractResource {
    
    private static final long serialVersionUID = 1L;
    
    private final FileManager fileManager;

    public AbstractFileUploadResource(FileManager fileManager)
    {
        this.fileManager = fileManager;
    }
    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        final ResourceResponse resourceResponse = new ResourceResponse();

        final ServletWebRequest webRequest = (ServletWebRequest) attributes
                .getRequest();

        try {
            MultipartServletWebRequest multiPartRequest = webRequest
                    .newMultipartWebRequest(getMaxSize(), "ignored");

            Map<String, List<FileItem>> files = multiPartRequest.getFiles();
            List<FileItem> fileItems = files.get("0");

            saveFiles(fileItems);

            prepareResponse(resourceResponse, webRequest, fileItems);
        } catch (Exception fux) {
            fux.printStackTrace();
            throw new AbortWithHttpErrorCodeException(
                    500,
                    fux.getMessage());
        }

        return resourceResponse;
    }

    /**
     * Sets the response's content type and body
     * 
     * @param resourceResponse
     * @param webRequest
     * @param fileItems
     * @throws FileUploadException
     * @throws IOException
     */
    protected void prepareResponse(ResourceResponse resourceResponse,
            ServletWebRequest webRequest, List<FileItem> fileItems)
            throws FileUploadException, IOException {

        final String responseContent;
        String accept = webRequest.getHeader("Accept");
        if (wantsHtml(accept)) {
            // Internet Explorer
            resourceResponse.setContentType("text/html");

            responseContent = generateHtmlResponse(resourceResponse,
                    webRequest, fileItems);
        } else {
            // a real browser
            resourceResponse.setContentType("application/json");

            responseContent = generateJsonResponse(resourceResponse,
                    webRequest, fileItems);
        }

        resourceResponse.setWriteCallback(new WriteCallback() {
            @Override
            public void writeData(Attributes attributes) throws IOException {
                attributes.getResponse().write(responseContent);
            }
        });
    }

    /**
     * Delegates to FileManager to store the uploaded files
     * 
     * @param fileItems
     * @throws IOException
     */
    protected void saveFiles(List<FileItem> fileItems) throws IOException {
        for (FileItem fileItem : fileItems) {
            fileManager.save(fileItem);
        }
    }

    /**
     * Decides what should be the response's content type depending on the
     * 'Accept' request header. HTML5 browsers work with "application/json",
     * older ones use IFrame to make the upload and the response should be HTML.
     * Read http://blueimp.github.com/jQuery-File-Upload/ docs for more info.
     * 
     * @param acceptHeader
     * @return
     */
    protected boolean wantsHtml(String acceptHeader) {
        return !Strings.isEmpty(acceptHeader)
                && acceptHeader.contains("text/html");
    }

    /**
     * Defines what is the maximum size of the uploaded files. TODO: integrate
     * this in FileUploadBehavior to set the max size at the client side too
     * 
     * @return
     */
    protected Bytes getMaxSize() {
        return Bytes.megabytes(100);
    }

    /**
     * Should generate the response's body in JSON format
     * 
     * @param resourceResponse
     * @param webRequest
     * @param files
     * @return
     */
    protected abstract String generateJsonResponse(
            ResourceResponse resourceResponse, ServletWebRequest webRequest,
            List<FileItem> files);

    /**
     * Should generate the response's body in HTML format
     * 
     * @param resourceResponse
     * @param webRequest
     * @param files
     * @return
     */
    protected abstract String generateHtmlResponse(
            ResourceResponse resourceResponse, ServletWebRequest webRequest,
            List<FileItem> files);

}
