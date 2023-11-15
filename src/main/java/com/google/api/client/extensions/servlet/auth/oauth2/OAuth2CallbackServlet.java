package com.google.api.client.extensions.servlet.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.PlusScopes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

@WebServlet(name = "OAuth2CallbackServlet",urlPatterns = "/oauth2callback")
public class OAuth2CallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath(req.getContextPath()+"/home");
        return url.build();
    }

    protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
        HttpSession session =  req.getSession(false);
        if (session != null){
            System.out.println("After authenticate : " + session.getId());
            return session.getId();
        }else {
            return "無資料";
        }


    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(OAuth2CallbackServlet.class.getResourceAsStream("/client_secret.json")));
        return new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Collections.singleton(PlusScopes.PLUS_ME)).setDataStoreFactory(
                Utils.getDataSourceFactory()).setAccessType("offline").build();
    }
}
