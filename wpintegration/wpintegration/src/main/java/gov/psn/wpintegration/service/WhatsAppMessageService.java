package gov.psn.wpintegration.service;

import gov.psn.wpintegration.exception.WhatsappException;
import gov.psn.wpintegration.request.WhatsAppMessageRequest;

public interface WhatsAppMessageService {
	
	public String sendWhatsAppMessage(WhatsAppMessageRequest whatsAppMessageRequest) throws WhatsappException;
	
}
