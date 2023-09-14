package gov.psn.wpintegration.request;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	public String from;
	public String id;
	public String timestamp;
	public Text text;
	public String type;

}
