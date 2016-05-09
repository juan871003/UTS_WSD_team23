package team23.models;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class AppXMLInitialiser {
	public static final String storedPollsfilePath = "WebContent/WEB-INF/stored_polls.xml";

	public static void main(String[] args) throws Exception {
		Creator creator1 = new Creator("Michael", "asdf");
		Creator creator2 = new Creator("Pieter", "asdf");
		
		ArrayList<Date> possibleMeetingDates1 = new ArrayList<Date>();
		possibleMeetingDates1.add(new GregorianCalendar(2016,5,15).getTime());
		possibleMeetingDates1.add(new GregorianCalendar(2016,5,16).getTime());
		possibleMeetingDates1.add(new GregorianCalendar(2016,5,17).getTime());
		ArrayList<PollResponse> responses1 = new ArrayList<PollResponse>();
		responses1.add(new PollResponse("Sang", new GregorianCalendar(2016,5,15).getTime()));
		responses1.add(new PollResponse("Sang", new GregorianCalendar(2016,5,16).getTime()));
		responses1.add(new PollResponse("Juan", new GregorianCalendar(2016,5,16).getTime()));
		
		ArrayList<Date> possibleMeetingDates2 = new ArrayList<Date>();
		possibleMeetingDates2.add(new GregorianCalendar(2016,6,13).getTime());
		possibleMeetingDates2.add(new GregorianCalendar(2016,6,14).getTime());
		possibleMeetingDates2.add(new GregorianCalendar(2016,6,15).getTime());
		ArrayList<PollResponse> responses2 = new ArrayList<PollResponse>();
		responses2.add(new PollResponse("Jhon", new GregorianCalendar(2016,6,13).getTime()));
		responses2.add(new PollResponse("Sofi", new GregorianCalendar(2016,6,13).getTime()));
		responses2.add(new PollResponse("Eddy", new GregorianCalendar(2016,6,15).getTime()));
		
		ArrayList<Date> possibleMeetingDates3 = new ArrayList<Date>();
		possibleMeetingDates3.add(new GregorianCalendar(2016,5,1).getTime());
		possibleMeetingDates3.add(new GregorianCalendar(2016,5,12).getTime());
		possibleMeetingDates3.add(new GregorianCalendar(2016,5,4).getTime());
		ArrayList<PollResponse> responses3 = new ArrayList<PollResponse>();
		responses3.add(new PollResponse("Pedro", new GregorianCalendar(2016,5,1).getTime()));
		responses3.add(new PollResponse("Alister", new GregorianCalendar(2016,5,1).getTime()));
		responses3.add(new PollResponse("Mary", new GregorianCalendar(2016,5,1).getTime()));
		
		Poll poll2 = new Poll(
				UUID.randomUUID(), 
				"poll 2", 
				new GregorianCalendar(2016,5,1).getTime(), 
				creator2, 
				"location 2", 
				"description 2", 
				"open", 
				possibleMeetingDates2, 
				responses2);
		
		Poll poll1 = new Poll(
				UUID.randomUUID(), 
				"poll 1", 
				new GregorianCalendar(2016,4,15).getTime(), 
				creator1, 
				"location 1", 
				"description 1", 
				"open", 
				possibleMeetingDates1, 
				responses1);
		
		Poll poll3 = new Poll(
				UUID.randomUUID(), 
				"poll 3", 
				new GregorianCalendar(2016,4,28).getTime(), 
				creator2, 
				"location 3", 
				"description 3", 
				"open", 
				possibleMeetingDates3, 
				responses3);
		
		StoredPolls polls = new StoredPolls();
		
		polls.addPoll(poll1);
		polls.addPoll(poll2);
		polls.addPoll(poll3);
		
		// Boilerplate code to convert objects to XML...
		JAXBContext jc = JAXBContext.newInstance(StoredPolls.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(polls, System.out);
		
		FileOutputStream fout = new FileOutputStream(storedPollsfilePath);
		m.marshal(polls, fout);
	}

}
