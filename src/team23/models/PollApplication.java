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

public class PollApplication {

	//public static final String storedPollsfilePath = "WebContent/WEB-INF/stored_polls.xml";
	private String filePath;
	private StoredPolls polls;

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
			jc = JAXBContext.newInstance(StoredPolls.class);
			Unmarshaller u = jc.createUnmarshaller();
			// Now unmarshal the object from the file
			FileInputStream fin = new FileInputStream(filePath);
			polls = (StoredPolls) u.unmarshal(fin); // This loads the "polls" object
			fin.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public StoredPolls getPolls() {
		return polls;
	}
	
	public void setPolls(StoredPolls polls) {
		this.polls = polls;
		try {
			FileOutputStream fout = new FileOutputStream(filePath);
			JAXBContext jc = JAXBContext.newInstance(StoredPolls.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(polls, fout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void save() {
		setPolls(polls);
	}

}
