package team23.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PollApplication {

	//public static final String storedPollsfilePath = "WebContent/WEB-INF/stored_polls.xml";
	private String filePath;
	private StoredCreators creators;

	public PollApplication() {
		// TODO Auto-generated constructor stub
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		// Create the unmarshaller
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(StoredCreators.class);
			Unmarshaller u = jc.createUnmarshaller();
			// Now unmarshal the object from the file
			FileInputStream fin = new FileInputStream(filePath);
			creators = (StoredCreators) u.unmarshal(fin); // This loads the "polls" object
			fin.close();
			this.filePath = filePath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void marshall(String filePath) throws JAXBException, FileNotFoundException{
		JAXBContext jc = JAXBContext.newInstance(StoredCreators.class);
		
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(creators, new FileOutputStream(filePath));
	}
	
	public StoredCreators getCreators() {
		return creators;
	}
	
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
	
	public void save() {
		setCreators(this.creators);
	}
	
	public void addResponse(String pollId, PollResponse pollResponse) {
		creators.getPollCreator(pollId).getPoll(pollId).addResponse(pollResponse);
	}
	
	public Creator getCreator(String username){
		return creators.getCreator(username);
	}
	
	public Creator signCreator(String username, String password){
		return creators.getCreator(username, password);
	}
	
	public ArrayList<Creator> getCreatorsList(){
		return creators.getList();
	}
	
	public Poll getPoll(String pollId){
		return creators.getPollCreator(pollId).getPoll(pollId);
	}
	
	public Creator getPollCreator(String pollId){
		return creators.getPollCreator(pollId);
	}
	
	public boolean isSamePerson(Creator c1, Creator c2){
		return (c1!=null && c2!=null) && c1.getUsername().equals(c2.getUsername());
	}
	
	public void setPollStatus(String pollId, String status) {
		getPoll(pollId).setStatus(status);
	}
}
