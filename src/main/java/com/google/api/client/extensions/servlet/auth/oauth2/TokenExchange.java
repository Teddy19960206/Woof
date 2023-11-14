package com.google.api.client.extensions.servlet.auth.oauth2;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;


@WebServlet("/token")
@MultipartConfig
public class TokenExchange extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request ,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        exchangeCodeForToken(request,response);
    }

    public void exchangeCodeForToken(HttpServletRequest request , HttpServletResponse response) {

        try (InputStream is = GooglePlusServlet.class.getResourceAsStream("/client_secret.json")){


            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(is);
            JsonNode web = rootNode.path("web");
            String clientId = web.path("client_id").asText();
            String clientSecret = web.path("client_secret").asText();
            JsonNode redirectUris = web.path("redirect_uris");

            String authorizationCode = request.getParameter("code");
            if (authorizationCode != null && !authorizationCode.isEmpty()) {

                URL url = new URL("https://oauth2.googleapis.com/token");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setDoOutput(true);

//                redirect_uri 需要與該servlet相同
                String params = "code=" + URLEncoder.encode(authorizationCode, "UTF-8") +
                        "&client_id=" + URLEncoder.encode(clientId, "UTF-8") +
                        "&client_secret=" + URLEncoder.encode(clientSecret, "UTF-8") +
                        "&redirect_uri=http://localhost:8081/woof/token" +
                        "&grant_type=authorization_code";

                OutputStream os = conn.getOutputStream();
                os.write(params.getBytes());
                os.flush();
                os.close();


//              接收response資料
                int responseCode = conn.getResponseCode();

                if (responseCode != HttpURLConnection.HTTP_OK) {
                    InputStream errorStream = conn.getErrorStream();
                    // 讀取錯誤流中的資料...
                }


                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer responseBuffer = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                in.close();

                System.out.println(responseBuffer);

                JSONObject jsonObject = new JSONObject(responseBuffer.toString());
                String idToken = jsonObject.getString("id_token");


                // 解析 JWT 以獲取 Key ID
                DecodedJWT jwt = JWT.decode(idToken);
                String keyId = jwt.getKeyId();

                // 獲取公鑰
                RSAPublicKey publicKey = getPublicKey(keyId);

                // 創建用於驗證 JWT 的驗證器
                JWTVerifier verifier = JWT.require(Algorithm.RSA256(publicKey, null))
                        .withIssuer("https://accounts.google.com")
                        .withAudience(clientId)
                        .build();

                System.out.println(verifier);

                // 驗證 JWT
                DecodedJWT verifiedJwt = verifier.verify(idToken);

                System.out.println(verifiedJwt);

                String userId = verifiedJwt.getSubject(); // 獲取用戶的唯一識別碼
                String userEmail = verifiedJwt.getClaim("email").asString(); // 獲取用戶的郵箱地址
                String userName = verifiedJwt.getClaim("name").asString();

                request.setAttribute("id" , userId);
                request.setAttribute("name" , userName);
                request.setAttribute("email" , userEmail);

                request.getRequestDispatcher("/login?action=google").forward(request,response);

            } else {
                // 處理錯誤情況
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RSAPublicKey getPublicKey(String keyId) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://www.googleapis.com/oauth2/v3/certs");
        String response = EntityUtils.toString(httpClient.execute(request).getEntity());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode keys = mapper.readTree(response).get("keys");

        for (JsonNode key : keys) {
            if (key.get("kid").asText().equals(keyId)) {
                String n = key.get("n").asText();
                String e = key.get("e").asText();

                BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
                BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(e));

                return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
            }
        }

        throw new Exception("Public key not found");
    }
}