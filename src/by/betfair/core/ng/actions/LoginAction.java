package by.betfair.core.ng.actions;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import by.betfair.core.ng.managers.IActionManager;
import by.betfair.core.ng.managers.NGActionManager;
import by.betfair.core.ng.util.JsonConverter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class LoginAction{
	
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	
    private static final int PORT = 443;
    private static final String URL = "https://identitysso.betfair.com/api/certlogin";
    private static final String KEY_MANAGER_PATH = "c:\\Users\\RemiX\\workspace\\betfair-core-ng\\cert\\client-2048.p12";
    private static final String KEY_MANAGER_PASSWORD = "Password1";
    
    private String sessionToken = null;
    
    public void execute() throws Exception {
 
        DefaultHttpClient httpClient = new DefaultHttpClient();
 
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            KeyManager[] keyManagers = getKeyManagers("pkcs12", new FileInputStream(new File(KEY_MANAGER_PATH)), KEY_MANAGER_PASSWORD);
            ctx.init(keyManagers, null, new SecureRandom());
            SSLSocketFactory factory = new SSLSocketFactory(ctx, new StrictHostnameVerifier());
 
            ClientConnectionManager manager = httpClient.getConnectionManager();
            manager.getSchemeRegistry().register(new Scheme("https", PORT, factory));
            HttpPost httpPost = new HttpPost(URL);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", IActionManager.USERNAME));
            nvps.add(new BasicNameValuePair("password", IActionManager.PASSWORD));
 
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            httpPost.setHeader("X-Application",IActionManager.APPLICATION_KEY);
            logger.debug("executing request" + httpPost.getRequestLine());
 
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
 
            logger.debug(response.getStatusLine());
            if (entity != null) {
                JsonObject jsonObject = JsonConverter.parseToJsonObject(EntityUtils.toString(entity));
                sessionToken = jsonObject.get("sessionToken").getAsString();
                logger.debug("sessionToken" + sessionToken);
            }
 
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
    
    public String getResult(){
    	return sessionToken;
    }
    
    private static KeyManager[] getKeyManagers(String keyStoreType, InputStream keyStoreFile, String keyStorePassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(keyStoreFile, keyStorePassword.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, keyStorePassword.toCharArray());
        return kmf.getKeyManagers();
    }

}
