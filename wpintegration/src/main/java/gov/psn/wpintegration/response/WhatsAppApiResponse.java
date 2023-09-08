package gov.psn.wpintegration.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class WhatsAppApiResponse {

	private String messagingProduct;
    private List<Contact> contacts = new ArrayList<Contact>();
    private List<Message> messages = new ArrayList<Message>();
	
}
