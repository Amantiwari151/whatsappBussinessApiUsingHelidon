package gov.psn.wpintegration.controller;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import gov.psn.wpintegration.common.ApiResponse;
import gov.psn.wpintegration.common.Error;
import gov.psn.wpintegration.exception.WhatsappException;
import gov.psn.wpintegration.request.WhatsAppMessageRequest;
import gov.psn.wpintegration.service.WhatsAppMessageService;

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

	

}
