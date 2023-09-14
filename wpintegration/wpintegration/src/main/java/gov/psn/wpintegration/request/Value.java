package gov.psn.wpintegration.request;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Value {
	public String messaging_product;
    public Metadata metadata;
    public ArrayList<Contact> contacts;
    public ArrayList<Message> messages;
}
