package team23.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/*
 * a PollResponse is a model class that contains the 
 * name of the person who responded and a list of
 * dates relating the options chosen by the user with
 * the given name.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PollResponse implements Serializable {
	@XmlAttribute(name = "personName")
	private String personName;
	@XmlElement(name = "response")
	private ArrayList<Date> responses = new ArrayList<Date>();

	/*
	 * empty constructor to be use by the serializer and marshaller
	 */
	public PollResponse() {
	}

	/*
	 * constructor throws axception if personName is empty, also responses may
	 * be empty but should not be null.
	 * 
	 * @param personName name of the person who responded
	 * 
	 * @param responses arraylist of dates relating the poll options that the
	 * person chose as available
	 */
	public PollResponse(String personName, ArrayList<Date> responses) throws IllegalArgumentException {
		if (personName != null && personName.trim().length() > 0 && responses != null) {
			this.personName = personName;
			this.responses = responses;
		} else {
			throw new IllegalArgumentException("PollResponse, constructor: personName cannot be empty");
		}
	}

	public String getPersonName() {
		return personName;
	}

	/*
	 * @param personName name of person who responded, should not be empty
	 */
	public void setPersonName(String personName) {
		if (personName != null && personName.trim().length() > 0)
			this.personName = personName;
	}

	public ArrayList<Date> getResponses() {
		return responses;
	}

	/*
	 * overrides list of responses
	 * 
	 * @param responses arraylist of dates, if null then request is ignored
	 */
	public void setResponses(ArrayList<Date> responses) {
		if (responses != null)
			this.responses = responses;
	}

	/*
	 * checks if a given date is in the responses, checks if the user responded
	 * as 'yes, I am available' on the given date. if param is null returns
	 * false.
	 * 
	 * @param dateToSearch the given date to check
	 * 
	 * @return boolean true if the response has a date equal to the given date,
	 * false otherwise
	 */
	public boolean isDateInResponses(Date dateToSearch) {
		if (dateToSearch != null) {
			for (Date date : responses) {
				if (date.equals(dateToSearch)) {
					return true;
				}
			}
		}
		return false;
	}
}
