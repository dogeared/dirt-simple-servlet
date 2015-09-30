package com.stormpath.sdk.examples.servlet;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.idsite.IdSiteUrlBuilder;
import com.stormpath.sdk.servlet.application.ApplicationResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IDSiteLogoutController extends BaseIDSiteController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Application app = ApplicationResolver.INSTANCE.getApplication(request);

        IdSiteUrlBuilder idSiteBuilder = app.newIdSiteUrlBuilder();
        idSiteBuilder.setCallbackUri(getBaseURL(request) + "/id_site_logout_finish");
        idSiteBuilder.forLogout();

        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Location", idSiteBuilder.build());
    }

}
