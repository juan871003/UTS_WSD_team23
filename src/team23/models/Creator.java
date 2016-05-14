package team23.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="creator")
public class Creator implements Serializable {
	@XmlAttribute(name="username")
	private String username;
	@XmlAttribute(name="password")
	private String password;
	@XmlElementWrapper(name="createdpolls")
	@XmlElement(name="poll")
	private ArrayList<Poll> polls = new ArrayList<Poll>();
	public Creator() {}
	
	public Creator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Poll> getPolls() {
		return polls;
	}

	public void setPolls(ArrayList<Poll> polls) {
		this.polls = polls;
	}
	
	public void addPoll(Poll poll){
		polls.add(poll);
	}
	
	public Poll getPoll(String pollId){
		for(Poll poll : polls){
			if(poll.getPollID().toString().equals(pollId)){
				return poll;
			}
		}
		return null;
	}
	
}
