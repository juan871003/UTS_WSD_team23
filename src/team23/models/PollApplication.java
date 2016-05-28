package team23.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/*
 * gateway to use the application: 
 * class containing the main business logic to interact 
 * with the models (Creator, Poll, PollResponse)
 * front-end and services talk to this JavaBean to interact with 
 * the models. 
 */
public class PollApplication {
	// path and filename where the XML is saved
	private String filePath;
	// object to unmarshal/marshal from/to XML file
	private StoredCreators creators;

	public PollApplication() {
	}

	public String getFilePath() {
		return filePath;
	}

	/*
	 * sets the XML file path/name.xml and also unmarshals the XML into the
	 * StoredCreator object
	 * 
	 * @param filePath path/filename.xml where the object is serialized as XML
	 */
	public void setFilePath(String filePath) {
		// Create the unmarshaller
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(StoredCreators.class);
			Unmarshaller u = jc.createUnmarshaller();
			// Now unmarshal the object from the file
			FileInputStream fin = new FileInputStream(filePath);
			creators = (StoredCreators) u.unmarshal(fin); // This loads the
															// "polls" object
			fin.close();
			this.filePath = filePath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @return creators an object of type StoredCreators this object contains
	 * all the creators, their polls, and their responses
	 */
	public StoredCreators getCreators() {
		return creators;
	}

	/*
	 * marshals the data and saves it into the xml file the filePath variable
	 * must be previouly set before calling this method.
	 * 
	 * @param creators the StoredCreators object to be saved as XML
	 */
	public void setCreators(StoredCreators creators) {
		this.creators = creators;
		try {
			FileOutputStream fout = new FileOutputStream(filePath);
			JAXBContext jc = JAXBContext.newInstance(StoredCreators.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(creators, fout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * saves the current data into an XML file only if setFilePath had
	 * previously been called and therefore the variables are initialixed,
	 * namely: creators -> StordeCreators object filepath -> path/name.xml where
	 * the XML file is stored.
	 */
	public void save() {
		if (this.creators != null && filePath.length() > 0)
			setCreators(this.creators);
	}

	/*
	 * adds a response to the poll with the given pollId, if poll is not found
	 * request of adding response is simply ignored (no exception is thrown).
	 * 
	 * @param pollId id of poll where poll-response should be added
	 * 
	 * @param pollResponse PollResponse to be added to poll
	 */
	public void addResponse(String pollId, PollResponse pollResponse) {
		if (pollId != null && pollId.length() > 0 && pollResponse != null) {
			Creator theCreator = creators.getPollCreator(pollId);
			if (theCreator != null) {
				Poll thePoll = theCreator.getPoll(pollId);
				if (thePoll != null) {
					thePoll.addResponse(pollResponse);
				}
			}
		}
	}

	/*
	 * gets creator by username, if not found returns null
	 * 
	 * @param username username of creator to be retreived
	 * 
	 * @return Creator creator with given username, null if not found
	 */
	public Creator getCreator(String username) {
		if (username != null && username.length() > 0)
			return creators.getCreator(username);
		return null;
	}

	/*
	 * gets a creator only if credentials are correct, returns null otherwise
	 * 
	 * @param username creator's username
	 * 
	 * @param password plain text creator's password
	 * 
	 * @return Creator returns creator only if credentials are correct, returns
	 * null otherwise
	 */
	public Creator signCreator(String username, String password) {
		if (username != null && username.length() > 0 && password != null && password.length() > 0)
			return creators.getCreator(username, password);
		return null;
	}

	/*
	 * @return ArrayList of Creator, list of all creators in the system
	 */
	public ArrayList<Creator> getCreatorsList() {
		if (creators != null)
			return creators.getList();
		return null;
	}

	/*
	 * tries to find a poll with the given ID, if not found returns null\
	 * 
	 * @param pollId id of the poll to be retrieved
	 * 
	 * @return Poll poll with given ID, null if not found
	 */
	public Poll getPoll(String pollId) {
		if (pollId != null && pollId.length() > 0) {
			Creator theCreator = creators.getPollCreator(pollId);
			if (theCreator != null) {
				return theCreator.getPoll(pollId);
			}
		}
		return null;
	}

	/*
	 * tries to find creator containing a poll with given id, return null if not
	 * found
	 * 
	 * @param pollId id of poll created by creator
	 * 
	 * @return Creator the person that created the poll
	 */
	public Creator getPollCreator(String pollId) {
		if (pollId != null && pollId.length() > 0)
			return creators.getPollCreator(pollId);
		return null;
	}

	/*
	 * based on the username (unique), checks if two creators are not null and
	 * equal
	 * 
	 * @return boolean true if given creators are not null and have same
	 * username , false otherwise.
	 */
	public boolean isSamePerson(Creator c1, Creator c2) {
		return (c1 != null && c2 != null) && c1.getUsername().equals(c2.getUsername());
	}

	/*
	 * changes the status of the poll with the given pollId only if the poll was
	 * found and the status is a valid option
	 * 
	 * @param pollId id of the poll to change status
	 * 
	 * @param status 'open' or 'close' new status for the poll
	 */
	public void setPollStatus(String pollId, String status) {
		if (pollId != null && pollId.length() > 0 && (status.equals("open") || status.equals("close"))) {
			Poll thePoll = getPoll(pollId);
			if (thePoll != null)
				getPoll(pollId).setStatus(status);
		}
	}
}
