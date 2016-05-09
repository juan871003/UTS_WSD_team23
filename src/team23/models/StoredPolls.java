package team23.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "polls")
public class StoredPolls implements Serializable {
	@XmlElement(name = "poll")
	private ArrayList<Poll> list = new ArrayList<Poll>();

	public StoredPolls() { }

	public StoredPolls(ArrayList<Poll> list) {
		super();
		this.list = list;
	}

	public ArrayList<Poll> getList() {
		return list;
	}

	public void setList(ArrayList<Poll> list) {
		this.list = list;
	}
	
	public void addPoll(Poll poll) {
		list.add(poll);
	}
	
	public void removePoll(Poll poll) {
		list.remove(poll);
	}
	
}
