package team23.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "creators")
public class StoredCreators implements Serializable {
	@XmlElement(name = "creator")
	private ArrayList<Creator> list = new ArrayList<Creator>();
	
	public StoredCreators() { }

	public StoredCreators(ArrayList<Creator> list) {
		super();
		this.list = list;
	}

	public ArrayList<Creator> getList() {
		return list;
	}

	public void setList(ArrayList<Creator> list) {
		this.list = list;
	}
	
	public void addCreator(Creator creator) {
		list.add(creator);
	}
	
	public void removeCreator(Creator creator){
		list.remove(creator);
	}
	
	public Creator getCreator(String username, String password) {
		for (Creator creator : list) {
			if (creator.getUsername().equals(username) && creator.getPassword().equals(password)) {
				return creator;
			}
		}
		return null;
	}
	
	public Creator getPollCreator(String pollId){
		for (Creator creator : list) {
			for (Poll poll : creator.getPolls()){
				if(poll.getPollID().toString().equals(pollId)){
					return creator;
				}
			}
		}
		return null;
	}
}
