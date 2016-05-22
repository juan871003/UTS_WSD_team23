package team23.models.rest;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import team23.models.Creator;
import team23.models.Poll;
import team23.models.PollApplication;
import team23.models.StoredCreators;

@Path("/pollApp")
public class PollService {
	@Context
	private ServletContext application;
	
	private PollApplication getPollApp() {
		synchronized(application) {
			PollApplication pollApp = (PollApplication)application.getAttribute("pollApp");
			if(pollApp == null) {
				pollApp = new PollApplication();
				pollApp.setFilePath(application.getRealPath("WEB-INF/stored_polls.xml"));
				application.setAttribute("pollApp", pollApp);
			}
			return pollApp;
		}
	}
	
	private boolean inList(ArrayList<Poll> list, Poll poll){
		boolean isIn = false;
		for(Poll tempPoll : list){
			if(poll == tempPoll){
				isIn = true;
			}
		}
		return isIn;
	}
	
	private boolean matchUser(Poll poll, String username){
		PollApplication pollApp = getPollApp();
		boolean match = false;
		for(Poll userPoll : pollApp.getCreator(username).getPolls()){
			if(userPoll.equals(poll)){
			match = true;	
			}
		}
		return match;
	}
	
	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(){
		return "Hello World";
	}
	
	@Path("/polls")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Poll> getPollCreator(
			@QueryParam("username") String username,
			@QueryParam("status") String status,
			@QueryParam("minResponses") String minResponses){
		ArrayList<Poll> polls = new ArrayList<Poll>();
		ArrayList<Poll> allPolls = new ArrayList<Poll>();
		PollApplication pollApp = getPollApp();
		for(Creator creator : pollApp.getCreatorsList()){
			for(Poll poll : creator.getPolls()){
				allPolls.add(poll);
			}
		}
		if(username != null && status != null && minResponses != null){
			for(Poll poll: allPolls){
				if(poll.getStatus().equals(status)){
					if((poll.getPollResponses().size() >= Integer.parseInt(minResponses)) && !inList(polls, poll)){
						if(matchUser(poll, username)){
							polls.add(poll);
						}
					}
				}
			}
		}
		else if(status != null){
			if(username != null){
				for(Poll poll: allPolls){
					if(matchUser(poll, username)){
						if(poll.getStatus().equals(status) && !inList(polls, poll)){
							polls.add(poll);
						}
					}
				}
			}
			else if(minResponses != null){
				for(Poll poll: allPolls){
					if(poll.getStatus().equals(status)){
						if((poll.getPollResponses().size() >= Integer.parseInt(minResponses)) && !inList(polls, poll)){
							polls.add(poll);
						}
					}
				}
			}
			else { for(Poll poll : allPolls){
					if(poll.getStatus().equals(status)){
						if(!inList(polls, poll)){						
							polls.add(poll);
						}
					}
			}
			}	
		}
		else if(username != null){
			if(minResponses != null){
				for(Poll poll: allPolls){
					if(matchUser(poll, username)){
						if((poll.getPollResponses().size() >= Integer.parseInt(minResponses)) && !inList(polls, poll)){
							polls.add(poll);
						}
					}
				}
			}
			else { for(Poll poll : allPolls){
				if(matchUser(poll, username) && !inList(polls, poll)){
					polls.add(poll);
				}
			}
			}
		}
		else if(minResponses != null){
			for(Poll poll : allPolls){
				if((poll.getPollResponses().size() >= Integer.parseInt(minResponses)) && !inList(polls, poll)) {
					polls.add(poll);
				}
			}
		}
		if(username == null && status == null && minResponses == null) {
			for(Creator tempCreator : pollApp.getCreatorsList()){
				for(Poll poll : tempCreator.getPolls()){
					if(poll.getStatus().equals("open")){
						polls.add(poll);
					}
				}
			}
		}
		return polls;
	}
}
