package gov.psn.wpintegration.utill;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.json.Json;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.google.gson.JsonObject;

import gov.psn.wpintegration.request.WhatsAppMessageRequest;

public class SharingMessageUsingWhatsApp {
	
	public static String sendWhatsAppMessage( String token, String url ,String data)  {
		System.out.println("correct url - " + url);
		System.out.println("token - " + token);
		String responseBody = "";
		try {
			SSLContextBuilder sscb = new SSLContextBuilder();
	        sscb.loadTrustMaterial(null, new org.apache.http.conn.ssl.TrustSelfSignedStrategy());
	        SSLContext sslContext = sscb.build();

	        SSLConnectionSocketFactory sslSocketFactory =
	                new SSLConnectionSocketFactory(sslContext, new org.apache.http.conn.ssl.DefaultHostnameVerifier());
	        
	        System.out.println(sslContext);
	        System.out.println(sslSocketFactory);
	        
	        HttpClient httpClient = HttpClients
	                .custom()
	                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
	                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
	                .build();
	        System.out.println(httpClient);
	       
	        Client client = ClientBuilder.newClient();

		    Invocation.Builder requestBuilder = client
		            .target(url)
		            .request("application/json");

		        // Set headers using the requestBuilder
		        requestBuilder.header("Authorization", token);
		        
//		        String jsonData = "{\"messaging_product\":\"whatsapp\",\"to\":\"918107772560\",\"type\":\"template\",\"template\":{\"name\":\"hello_world\",\"language\":{\"code\":\"en_US\"}}}";
		        Entity<String> entity = Entity.entity(data, MediaType.APPLICATION_JSON);
		        
		        Response response = requestBuilder.method("POST", entity);
		        
		        int statusCode = response.getStatus();
		        System.out.println("Status Code: " + statusCode);
		        
		        responseBody = response.readEntity(String.class);
		        System.out.println(responseBody);
		        
		        if (statusCode == 200) {
		            System.out.println("API call succeeded: " + responseBody);
		        } else {
		        	System.out.println("API call failed: " + responseBody);
		        }
		        
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return responseBody;
		
	}
	
}
