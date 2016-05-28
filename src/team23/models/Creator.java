package team23.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.tools.ws.wsdl.document.jaxws.Exception;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "creator")
public class Creator implements Serializable {
	@XmlAttribute(name = "username")
	private String username;
	@XmlAttribute(name = "password")
	private String password;
	@XmlElementWrapper(name = "createdpolls")
	@XmlElement(name = "poll")
	private ArrayList<Poll> polls = new ArrayList<Poll>();

	/*
	 * Empty constructor, mainly used for the serialiser and the marshaller
	 */
	public Creator() {
	}

	/*
	 * constructor
	 * 
	 * @param username unique creators username, cannot be empty, check
	 * uniqueness before calling
	 * 
	 * @param password creator's password, cannot be empty
	 */
	public Creator(String username, String password) throws IllegalArgumentException {
		if (username != null && username.length() > 0 && password != null && password.length() > 0) {
			this.username = username;
			this.password = password;
		} else {
			throw new IllegalArgumentException("Creator constructor: username or password cannot be empty");
		}
	}

	/*
	 * @return creator's username
	 */
	public String getUsername() {
		return username;
	}

	/*
	 * @param username unique username, cannot be empty, check uniqueness before
	 * setting
	 */
	public void setUsername(String username) throws IllegalArgumentException {
		if (username != null && username.length() > 0)
			this.username = username;
		else
			throw new IllegalArgumentException("Creator, setUsername: username cannot be empty");
	}

	/*
	 * @return creator's password
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * @password creator's password, cannot be empty
	 */
	public void setPassword(String password) {
		if (password != null && password.length() > 0)
			this.password = password;
	}

	/*
	 * @return polls list of polls created by creator
	 */
	public ArrayList<Poll> getPolls() {
		return polls;
	}

	/*
	 * @param polls list of polls created by creator, overrides current list of
	 * polls.
	 */
	public void setPolls(ArrayList<Poll> polls) {
		this.polls = polls;
	}

	/*
	 * @param poll poll to be added to list of polls, if null then poll is not
	 * added
	 */
	public void addPoll(Poll poll) {
		if (poll != null)
			polls.add(poll);
	}

	/*
	 * @param pollId id of the poll to be retrieved
	 * 
	 * @return Poll with given id, if not found returns null
	 */
	public Poll getPoll(String pollId) {
		if (pollId != null && pollId.length() > 0) {
			for (Poll poll : polls) {
				if (poll.getPollID().toString().equals(pollId)) {
					return poll;
				}
			}
		}
		return null;
	}

}
