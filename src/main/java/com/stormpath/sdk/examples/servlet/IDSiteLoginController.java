package com.stormpath.sdk.examples.servlet;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.idsite.IdSiteUrlBuilder;
import com.stormpath.sdk.servlet.application.ApplicationResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IDSiteLoginController extends BaseIDSiteController {
    private static final String ID_SITE_CALLBACK = "/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Application app = ApplicationResolver.INSTANCE.getApplication(request);

        IdSiteUrlBuilder idSiteBuilder = app.newIdSiteUrlBuilder()
            .setCallbackUri(getBaseURL(request) + ID_SITE_CALLBACK);

        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Location", idSiteBuilder.build());
    }
}