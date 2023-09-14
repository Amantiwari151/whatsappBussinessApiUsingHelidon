package gov.psn.wpintegration.controller;

import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class Utill {

	  //private static final Logger logger = LoggerFactory.getLogger(UtilsAPI.class);
    private final static String USER_AGENT = "*/*";


    public  static String connectAuth(String url,String param,String auth)
    {
        String responseString = null;
        try {

            SSLContextBuilder sscb = new SSLContextBuilder();
            sscb.loadTrustMaterial(null, new org.apache.http.conn.ssl.TrustSelfSignedStrategy());
            SSLContext sslContext = sscb.build();

            SSLConnectionSocketFactory sslSocketFactory =
                    new SSLConnectionSocketFactory(sslContext, new org.apache.http.conn.ssl.DefaultHostnameVerifier());

            HttpClient httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            post.setHeader("Authorization", auth);
            post.setHeader("Connection", "keep-alive");
            final StringEntity entity = new StringEntity(param);
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //logger.error("Error:"+e.getMessage());
        }

        return responseString;
    }

    public  static String connect(String url,String param)
    {
        String responseString = null;
        try {

            HttpClient httpClient =  HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            final StringEntity entity = new StringEntity(param);
            post.setEntity(entity);
            
            HttpResponse response = httpClient.execute(post);
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //logger.error("Error:"+e.getMessage());
        }

        return responseString;
    }


    public  static String connect(String url, List<NameValuePair> urlParameters)
    {
        String responseString = null;
        try {

            SSLContextBuilder sscb = new SSLContextBuilder();
            sscb.loadTrustMaterial(null, new org.apache.http.conn.ssl.TrustSelfSignedStrategy());
            SSLContext sslContext = sscb.build();

            SSLConnectionSocketFactory sslSocketFactory =
                    new SSLConnectionSocketFactory(sslContext, new org.apache.http.conn.ssl.DefaultHostnameVerifier());

            HttpClient httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/x-www-form-urlencoded");

            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = httpClient.execute(post);
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
          
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return responseString;
    }
	
}
