package com.google.api.client.extensions.servlet.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@WebServlet(name = "GooglePlusServlet",urlPatterns = "/plus")
public class GooglePlusServlet extends AbstractAuthorizationCodeServlet {
    @Override
    protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(GooglePlusServlet.class.getResourceAsStream("/client_secret.json")));
//        return new GoogleAuthorizationCodeFlow.Builder(
//                new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
//                clientSecrets,
//                Collections.singleton(PlusScopes.PLUS_ME)).setDataStoreFactory(Utils.getDataSourceFactory()).setAccessType("offline").build();
        return new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Arrays.asList(
                        "https://www.googleapis.com/auth/userinfo.email",
                        "https://www.googleapis.com/auth/userinfo.profile"
                )
        ).setDataStoreFactory(Utils.getDataSourceFactory())
                .setAccessType("offline")
                    .build();
    }

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath(req.getContextPath()+"/token");
        return url.build();
    }

    @Override
    protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
        HttpSession httpSession =  req.getSession(true);
        System.out.println("Before authenticate : " + httpSession.getId());
        return httpSession.getId();
    }
}
