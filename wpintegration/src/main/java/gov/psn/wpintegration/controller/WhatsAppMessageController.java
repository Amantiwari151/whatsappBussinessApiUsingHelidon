package gov.psn.wpintegration.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.google.gson.JsonObject;

import gov.psn.wpintegration.common.ApiResponse;
import gov.psn.wpintegration.common.Error;
import gov.psn.wpintegration.exception.WhatsappException;
import gov.psn.wpintegration.request.Change;
import gov.psn.wpintegration.request.Entry;
import gov.psn.wpintegration.request.Message;
import gov.psn.wpintegration.request.Metadata;
import gov.psn.wpintegration.request.Value;
import gov.psn.wpintegration.request.WhatsAppMessageReplyRequest;
import gov.psn.wpintegration.request.WhatsAppMessageRequest;
import gov.psn.wpintegration.service.WhatsAppMessageService;
import gov.psn.wpintegration.utill.SharingMessageUsingWhatsApp;
import io.helidon.common.http.Http;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;

@Path("/call")
public class WhatsAppMessageController {

	@Inject
	private WhatsAppMessageService whatsAppMsgService;

	@Path("/getResp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendWhatsAppMessage(@RequestBody WhatsAppMessageRequest whatsAppMessageRequest) {

		try {
			String sendWhatsAppMessage = whatsAppMsgService.sendWhatsAppMessage(whatsAppMessageRequest);
			return Response.ok(ApiResponse.success(sendWhatsAppMessage)).build();
		} catch (WhatsappException e) {
			e.printStackTrace();
			return Response.ok(ApiResponse.error(Error.create("400", "some error occurred"))).build();
		}

	}

	@GET
	@Path("/webhooks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response handleWebhook(@QueryParam("hub.mode") String mode, @QueryParam("hub.challenge") String challenge,
			@QueryParam("hub.verify_token") String token) {

		final String myToken = "aman"; // Your token here

		if ("subscribe".equals(mode) && myToken.equals(token)) {
			return Response.ok(ApiResponse.success(challenge)).build();
		} else {
			return Response.ok(ApiResponse.error(Error.create("403", "forbidden"))).build();
		}
	}

	@POST
	@Path("/webhooks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response replyWhatsAppMessage(@RequestBody WhatsAppMessageReplyRequest whatsAppMessageReplyRequest) {

        try {
            if ("whatsapp".equals(whatsAppMessageReplyRequest.getObject())) {
                ArrayList<Entry> entries = whatsAppMessageReplyRequest.getEntry();
                if (entries != null && !entries.isEmpty()) {
                    Entry entry = entries.get(0);
                    ArrayList<Change> changes = entry.getChanges();
                    if (changes != null && !changes.isEmpty()) {
                        Change change = changes.get(0);
                        Value value = change.getValue();
                        if (value != null && value.getMessages() != null && !value.getMessages().isEmpty()) {
                            Message message = value.getMessages().get(0);
                            
                            String phoneNumberId = value.getMetadata().getPhone_number_id();
                            String from = message.getFrom();
                            String text = message.getText().getBody();

                            // Assuming you have the 'token' variable defined somewhere
                            String token = "Bearer EAAUqBCHMDVEBOwOZAszB5cTUR6viINLrFLE5U9wiZAPVOcGbyL50omJfqRq2RZCDNtcxEOZCuePrXlc61EtNh86kpLD1eYupsNNqq9baJ2llkEGfZCbXZB4LBxRg4DD6BHemHE6Xt6jFRm5V7o0f70DJwSCbTlt3uSc2KNBxO9yss7prmLZBmreE4k5uGE7SIRe";

                            // Construct your response JSON or perform other actions here
                            
                            String url = "https://graph.facebook.com/v17.0/"+ phoneNumberId +"/messages?access_token="+token;
                            
                            JsonObject json = new JsonObject();
                            json.addProperty("messaging_product", "whatsapp");
                            json.addProperty("to", from);
                            JsonObject textObject = new JsonObject();
                            textObject.addProperty("body", "hyy this is for testing");
                            
                            
                            json.add("text", textObject);
                            System.out.println(json);
                            
                            String jsonData = json.toString();
                            
                            
                            SharingMessageUsingWhatsApp.sendWhatsAppMessage(token, url, jsonData);

                            // Sending a success response
                            return Response.ok(ApiResponse.success("Received and Processed the Webhook")).build();
                        }
                    }
                }
            }

            // Return an error response if the JSON structure doesn't match expectations
            
            return Response.ok(ApiResponse.error(Error.create("404", "Invalid JSON structure."))).build();
        } catch (Exception ex) {
        	return Response.ok(ApiResponse.error(Error.create("400", "some error occurred"))).build();
        }
	}

}
