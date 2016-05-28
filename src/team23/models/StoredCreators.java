package team23.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * JavaBean relating the root element to marshal,
 * it just has a list of creators, these creators in turn have a list of polls having a list of responses
 * so this is the root elemten that contains oll the other data.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "creators")
public class StoredCreators implements Serializable {
	@XmlElement(name = "creator")
	private ArrayList<Creator> list = new ArrayList<Creator>();

	/*
	 * empty constructor for serializer and marshaler purposes
	 */
	public StoredCreators() {
	}

	/*
	 * constructor
	 */
	public StoredCreators(ArrayList<Creator> list) {
		this.list = list;
	}

	/*
	 * @return list arraylist of creators
	 */
	public ArrayList<Creator> getList() {
		return list;
	}

	/*
	 * overrides previous list of creators
	 */
	public void setList(ArrayList<Creator> list) {
		this.list = list;
	}

	/*
	 * adds a creator to the current list of creators if the list is null or the
	 * param creator is null then request is ignored
	 * 
	 * @param creato creator to be added
	 */
	public void addCreator(Creator creator) {
		if (list != null && creator != null)
			list.add(creator);
	}

	/*
	 * removes a creator from the list of creators, if given creator is null or
	 * not found then request is ignored
	 * 
	 * @param creator Creator to be removed
	 */
	public void removeCreator(Creator creator) {
		if (list != null && creator != null)
			list.remove(creator);
	}

	/*
	 * gets a creator by his/her username/password, return null if not found
	 * 
	 * @param username creator's username, if null returns null
	 * 
	 * @param password creator's password in plain text, if null returns null
	 * 
	 * @return creator whose username and password are equal to given username
	 * and password, null if not found.
	 */
	public Creator getCreator(String username, String password) {
		if (username != null && username.length() > 0 && password != null && password.length() > 0) {
			for (Creator creator : list) {
				if (creator.getUsername().equals(username) && creator.getPassword().equals(password)) {
					return creator;
				}
			}
		}
		return null;
	}

	/*
	 * gets creator by his/her username
	 * 
	 * @param username creator's username, if null returns null
	 * 
	 * @return creator whose username is equal to given username, null if not
	 * found.
	 */
	public Creator getCreator(String username) {
		if (username != null && username.length() > 0) {
			for (Creator creator : list) {
				if (creator.getUsername().equals(username)) {
					return creator;
				}
			}
		}
		return null;
	}

	/*
	 * gets creator whi created the poll woth the given id, null if not found
	 * 
	 * @param pollId id of the poll created by returned creator
	 * 
	 * @return creator who created the poll with the given id, null if not found
	 */
	public Creator getPollCreator(String pollId) {
		if (pollId != null && pollId.length() > 0) {
			for (Creator creator : list) {
				for (Poll poll : creator.getPolls()) {
					if (poll.getPollID().toString().equals(pollId)) {
						return creator;
					}
				}
			}
		}
		return null;
	}
}
