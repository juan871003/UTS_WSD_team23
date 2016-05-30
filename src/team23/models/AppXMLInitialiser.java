package team23.models;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
/*
 * Initialises the application with data.
 * It is manually ran when running the app for the first time
 * or when wanting to reset the data to its initial state
 */
public class AppXMLInitialiser {
	/*
	 * path and name of the file where the XML is stored, 
	 * this XML is used to store the data: Creators, polls, and responses
	 */
	public static final String storedPollsfilePath = "WebContent/WEB-INF/stored_polls.xml";

	public static void main(String[] args) throws Exception {
		//creators
		Creator creator1 = new Creator("Michael", "asdf");
		Creator creator2 = new Creator("Pieter", "asdf");
		
		//pollresponse1
		ArrayList<Date> possibleMeetingDates1 = new ArrayList<Date>();
		possibleMeetingDates1.add(new GregorianCalendar(2016,5,15,13,30,0).getTime());
		possibleMeetingDates1.add(new GregorianCalendar(2016,5,16,11,0,0).getTime());
		possibleMeetingDates1.add(new GregorianCalendar(2016,5,17,12,30,0).getTime());
		ArrayList<PollResponse> allResponses1 = new ArrayList<PollResponse>();
		ArrayList<Date> sangResponses = new ArrayList<Date>();
		sangResponses.add(new GregorianCalendar(2016,5,15,13,30,0).getTime());
		sangResponses.add(new GregorianCalendar(2016,5,16,11,0,0).getTime());
		ArrayList<Date> juanResponses = new ArrayList<Date>();
		juanResponses.add(new GregorianCalendar(2016,5,16,11,0,0).getTime());
		allResponses1.add(new PollResponse("Sang",sangResponses));
		allResponses1.add(new PollResponse("Juan",juanResponses));
		
		//pollResponse2
		ArrayList<Date> possibleMeetingDates2 = new ArrayList<Date>();
		possibleMeetingDates2.add(new GregorianCalendar(2016,6,13,9,0,0).getTime());
		possibleMeetingDates2.add(new GregorianCalendar(2016,6,13,10,0,0).getTime());
		possibleMeetingDates2.add(new GregorianCalendar(2016,6,15,21,0,0).getTime());
		ArrayList<PollResponse> allResponses2 = new ArrayList<PollResponse>();
		ArrayList<Date> jhonResponse = new ArrayList<Date>();
		jhonResponse.add(new GregorianCalendar(2016, 6, 13, 10, 0, 0).getTime());
		jhonResponse.add(new GregorianCalendar(2016, 6, 13, 9, 0, 0).getTime());
		ArrayList<Date> sofiResponse = new ArrayList<Date>();
		sofiResponse.add(new GregorianCalendar(2016, 6, 13, 10, 0, 0).getTime());
		sofiResponse.add(new GregorianCalendar(2016, 6, 15, 21, 0, 0).getTime());
		ArrayList<Date> eddyResponse = new ArrayList<Date>();
		eddyResponse.add(new GregorianCalendar(2016, 6, 15, 21, 0, 0).getTime());
		allResponses2.add(new PollResponse("Jhon", jhonResponse));
		allResponses2.add(new PollResponse("Sofi", sofiResponse));
		allResponses2.add(new PollResponse("Eddy", eddyResponse));
		
		//pollresponse3
		ArrayList<Date> possibleMeetingDates3 = new ArrayList<Date>();
		possibleMeetingDates3.add(new GregorianCalendar(2016,5,1,7,30,0).getTime());
		possibleMeetingDates3.add(new GregorianCalendar(2016,5,1,12,0,0).getTime());
		possibleMeetingDates3.add(new GregorianCalendar(2016,5,4,14,30,0).getTime());
		ArrayList<PollResponse> allResponses3 = new ArrayList<PollResponse>();
		ArrayList<Date> pedroResponse = new ArrayList<Date>();
		pedroResponse.add(new GregorianCalendar(2016, 5, 1, 12, 0, 0).getTime());
		pedroResponse.add(new GregorianCalendar(2016, 5, 4, 14, 30, 0).getTime());
		ArrayList<Date> marcoResponse = new ArrayList<Date>();
		marcoResponse.add(new GregorianCalendar(2016, 5, 1, 12, 0, 0).getTime());
		marcoResponse.add(new GregorianCalendar(2016, 5, 4, 14, 30, 0).getTime());
		marcoResponse.add(new GregorianCalendar(2016, 5, 1, 12, 0, 0).getTime());
		ArrayList<Date> maryResponse = new ArrayList<Date>();
		maryResponse.add(new GregorianCalendar(2016, 5, 1, 12, 0, 0).getTime());
		allResponses3.add(new PollResponse("Pedro", pedroResponse));
		allResponses3.add(new PollResponse("Marco", marcoResponse));
		allResponses3.add(new PollResponse("Mary", maryResponse));
		
		//poll1 - pollresponse1
		Poll poll1 = new Poll(
				UUID.randomUUID(), 
				"poll 1", 
				new GregorianCalendar(2016,4,15).getTime(),
				"location 1", 
				"description 1", 
				"open", 
				possibleMeetingDates1, 
				allResponses1);
		creator1.addPoll(poll1);
		
		//poll2 - pollresponse2
		Poll poll2 = new Poll(
				UUID.randomUUID(), 
				"poll 2", 
				new GregorianCalendar(2016,5,1).getTime(),
				"location 2", 
				"description 2", 
				"open", 
				possibleMeetingDates2, 
				allResponses2);
		creator2.addPoll(poll2);
		
		//poll3 - pollresponse3
		Poll poll3 = new Poll(
				UUID.randomUUID(), 
				"poll 3", 
				new GregorianCalendar(2016,4,28).getTime(),
				"location 3", 
				"description 3", 
				"open", 
				possibleMeetingDates3, 
				allResponses3);
		creator2.addPoll(poll3);
		
		StoredCreators creators = new StoredCreators();
		
		creators.addCreator(creator1);
		creators.addCreator(creator2);
		
		//marchal data to XML, then added to file.
		// Boilerplate code to convert objects to XML...
		JAXBContext jc = JAXBContext.newInstance(StoredCreators.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(creators, System.out);
		
		FileOutputStream fout = new FileOutputStream(storedPollsfilePath);
		m.marshal(creators, fout);
	}

}
