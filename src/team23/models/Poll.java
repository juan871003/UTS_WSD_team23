package team23.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "poll")
public class Poll implements Serializable {

	@XmlAttribute(name = "id")
	private UUID pollID;
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "creationDate")
	private Date creationDate;
	@XmlElement(name = "meetingLocation")
	private String meetingLocation;
	@XmlElement(name = "description")
	private String description;
	@XmlElement(name = "status")
	private String status;
	@XmlElementWrapper(name = "possibleMeetingDates")
	@XmlElement(name = "meetingDate")
	private ArrayList<Date> possibleMeetingDates;
	@XmlElementWrapper(name = "all_responses")
	@XmlElement(name = "person_response")
	private ArrayList<PollResponse> pollResponses;

	/*
	 * empty constructor, for serializable and marshal/unmarshal
	 */
	public Poll() {
	}

	/*
	 * constructor with ID
	 * @param 	pollID	id of the poll, cannot be null 
	 */
	public Poll(UUID pollID, String title, Date creationDate, String meetingLocation, String description, String status,
			ArrayList<Date> possibleMeetingDates, ArrayList<PollResponse> pollResponses)
			throws IllegalArgumentException {
		if (pollID != null && pollID.toString().trim().length() > 0 
				&& possibleMeetingDates!=null && possibleMeetingDates.size()>0) {
			this.pollID = pollID;
			this.title = title;
			this.creationDate = creationDate;
			this.meetingLocation = meetingLocation;
			this.description = description;
			this.status = status;
			this.possibleMeetingDates = possibleMeetingDates;
			if(pollResponses!=null)
				this.pollResponses = pollResponses;
			else
				this.pollResponses = new ArrayList<PollResponse>();
		}
		else 
			throw new IllegalArgumentException("Poll, Constructor: pollID cannot be null or empty and at least one possibleMeeting date should be added");
	}

	/*
	 * comstructor without ID, id is generated inside constructor
	 */
	public Poll(String title, String meetingLocation, String description, ArrayList<Date> possibleMeetingDates) throws IllegalArgumentException {
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		if(possibleMeetingDates!=null&&possibleMeetingDates.size()>0){
		this.pollID = UUID.randomUUID();
		this.title = title;
		this.creationDate = date;
		this.description = description;
		this.meetingLocation = meetingLocation;
		this.status = "open";
		this.possibleMeetingDates = possibleMeetingDates;
		this.pollResponses = new ArrayList<PollResponse>();
		if(pollResponses!=null){
			this.pollResponses = pollResponses;
		}
		else{
			this.pollResponses = new ArrayList<PollResponse>();
		}
		}
		else{
			throw new IllegalArgumentException("at least one possibleMeetingDate must be added to the new poll");
		}
	}

	public UUID getPollID() {
		return pollID;
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

	/*
	 * overrides all the previous list of possible meeting dates for this poll
	 */
	public void setPossibleMeetingDates(ArrayList<Date> possibleMeetingDates) {
		this.possibleMeetingDates = possibleMeetingDates;
	}

	public ArrayList<PollResponse> getPollResponses() {
		return pollResponses;
	}

	/*
	 * overrides all the list of pollResponses
	 */
	public void setPollResponses(ArrayList<PollResponse> pollResponses) {
		this.pollResponses = pollResponses;
	}

	/*
	 * add a pollResoponse to the list of pollResponses
	 * @param	pollResponse	pollResponse to be added to the existing list of responses
	 */
	public void addResponse(PollResponse pollResponse) {
		this.pollResponses.add(pollResponse);
	}
}
