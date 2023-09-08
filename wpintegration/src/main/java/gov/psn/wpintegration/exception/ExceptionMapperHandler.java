package gov.psn.wpintegration.exception;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import gov.psn.wpintegration.common.ApiResponse;


@Provider
public class ExceptionMapperHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		
		if(exception instanceof WhatsappException)
		{
			return ApiResponse.buildErrorResponse(exception);
		}
		
		return null;
	}

}