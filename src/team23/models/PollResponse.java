package team23.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class PollResponse implements Serializable {
	@XmlAttribute(name="personName")
	private String personName;
	@XmlElement(name="response")
	private ArrayList<Date> responses = new ArrayList<Date>();
	
	public PollResponse() {	}

	public PollResponse(String personName, ArrayList<Date> responses) {
		super();
		this.personName = personName;
		this.responses = responses;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public ArrayList<Date> getResponses() {
		return responses;
	}

	public void setResponses(ArrayList<Date> responses) {
		this.responses = responses;
	}
	
	public boolean isDateInResponses(Date dateToSearch){
		for(Date date : responses){
			if(date.equals(dateToSearch)){
				return true;
			}
		}
		return false;
	}
}
