package gov.psn.wpintegration.common;

public class Error {
	private String code;
	private String description;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Error create(final String code,final String description) {
		Error error =new Error();
		error.setCode(code);
		error.setDescription(description);
		return error;
	}
}