package team23.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Poll implements Serializable {
	@XmlAttribute(name = "id")
	private UUID pollID;
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "creationDate")
	private Date creationDate;
	@XmlElement(name = "creator")
	private Creator creator;
	@XmlElement(name = "meetingLocation")
	private String meetingLocation;
	@XmlElement(name = "description")
	private String description;
	@XmlElement(name = "status")
	private String status;
	@XmlElementWrapper(name="possibleMeetingDates")
	@XmlElement(name = "meetingDate")
	private ArrayList<Date> possibleMeetingDates;
	@XmlElementWrapper(name="responses")
	@XmlElement(name = "response")
	private ArrayList<PollResponse> pollResponses;
	
	public Poll() { }

	public Poll(UUID pollID, String title, Date creationDate, Creator creator, String meetingLocation,
			String description, String status, ArrayList<Date> possibleMeetingDates,
			ArrayList<PollResponse> pollResponses) {
		this.pollID = pollID;
		this.title = title;
		this.creationDate = creationDate;
		this.creator = creator;
		this.meetingLocation = meetingLocation;
		this.description = description;
		this.status = status;
		this.possibleMeetingDates = possibleMeetingDates;
		this.pollResponses = pollResponses;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getMeetingLocation() {
		return meetingLocation;
	}

	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Date> getPossibleMeetingDates() {
		return possibleMeetingDates;
	}

	public void setPossibleMeetingDates(ArrayList<Date> possibleMeetingDates) {
		this.possibleMeetingDates = possibleMeetingDates;
	}

	public ArrayList<PollResponse> getPollResponses() {
		return pollResponses;
	}

	public void setPollResponses(ArrayList<PollResponse> pollResponses) {
		this.pollResponses = pollResponses;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}
}
