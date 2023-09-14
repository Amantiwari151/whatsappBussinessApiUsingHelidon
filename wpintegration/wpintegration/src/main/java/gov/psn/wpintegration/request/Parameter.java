package gov.psn.wpintegration.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {
	
	private String type;
    private String text;
    private String payload;
	
}
