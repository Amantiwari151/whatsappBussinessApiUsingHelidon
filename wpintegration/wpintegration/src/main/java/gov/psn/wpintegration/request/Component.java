package gov.psn.wpintegration.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Component {

	private String type;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private String sub_type;
	private String index;

}
