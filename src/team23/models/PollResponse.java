package team23.models;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class PollResponse implements Serializable {
	@XmlAttribute(name="personName")
	private String personName;
	@XmlAttribute(name="response")
	private Date response;
	
	public PollResponse() {	}

	public PollResponse(String personName, Date response) {	
		this.personName = personName;
		this.response = response;
	}

	public String getPersonName() {
		return personName;
	}

	public Date getResponse() {
		return response;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setResponse(Date response) {
		this.response = response;
	}
	
	
	
}
