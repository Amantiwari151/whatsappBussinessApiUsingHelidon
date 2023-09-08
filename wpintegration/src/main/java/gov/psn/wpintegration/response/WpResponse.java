package gov.psn.wpintegration.response;

import lombok.Data;

@Data
public class WpResponse {

	private boolean success; 


	public boolean isSuccess() {
		return success;
	}

}
