package gov.psn.wpintegration.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {

	private String name;
	private Language language;
	private List<Component> components = new ArrayList<Component>();

}
