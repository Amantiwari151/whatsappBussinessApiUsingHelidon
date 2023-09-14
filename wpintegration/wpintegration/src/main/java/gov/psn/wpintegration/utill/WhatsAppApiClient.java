package gov.psn.wpintegration.utill;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import gov.psn.wpintegration.request.WhatsAppMessageRequest;
import gov.psn.wpintegration.response.WhatsAppApiResponse;
import gov.psn.wpintegration.response.WpResponse;

@RegisterRestClient
@Path("/v17.0/122094889856010964")
public interface WhatsAppApiClient {

	@POST
	@Path("/messages") // Replace with the actual API endpoint path
	@Produces(MediaType.APPLICATION_JSON)
	String sendMessage( String authToken, WhatsAppMessageRequest messageRequest);

}
