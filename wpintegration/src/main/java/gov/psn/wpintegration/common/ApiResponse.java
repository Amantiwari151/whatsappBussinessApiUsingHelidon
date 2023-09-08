package gov.psn.wpintegration.common;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class ApiResponse<D> {

	private RequestStatus status;
	private D data;
	private Error error;
	
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	public static <D> ApiResponse<D> success(final D data){
		final ApiResponse<D> apiRespose= new ApiResponse<>();
		apiRespose.setStatus(RequestStatus.SUCCESS);
		apiRespose.setError(null);
		apiRespose.setData(data);
		return apiRespose;
	}
	public static <D> ApiResponse<D> error(final Error error){
		final ApiResponse<D> apiRespose= new ApiResponse<>();
		apiRespose.setStatus(RequestStatus.FAILURE);
		apiRespose.setError(error);
		apiRespose.setData(null);
		return apiRespose;
	}
	
//	public static Response buildErrorResponse(PensionException e) {
//		Error error1 = new Error();
//		error1.setCode("500");
//		error1.setDescription(e.getMessage());
//
//		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ApiResponse.error(error1)).build();
//	}
	
	public static Response buildErrorResponse(Exception e) {
		Error error1 = new Error();
		error1.setCode("500");
		error1.setDescription(e.getMessage());
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ApiResponse.error(error1)).build();
	//	return Response.ok(ApiResponse.error(error1)).build();
	}
	public static Response buildErrorResponse(String e) {
		Error error1 = new Error();
		error1.setCode("500");
		error1.setDescription(e);
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ApiResponse.error(error1)).build();
	//	return Response.ok(ApiResponse.error(error1)).build();
	}
}