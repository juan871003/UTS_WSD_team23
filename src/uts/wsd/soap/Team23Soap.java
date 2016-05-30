package uts.wsd.soap;

import javax.jws.WebService;
import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import team23.models.*;

@WebService
public class Team23Soap {
	@Resource
	private WebServiceContext context;

	private PollApplication getPollAplication() {
		ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

		// The web server can handle requests from different clients in
		// parallel.
		// These are called "threads".
		//
		// We do NOT want other threads to manipulate the application object at
		// the same
		// time that we are manipulating it, otherwise bad things could happen.
		//
		// The "synchronized" keyword is used to lock the application object
		// while
		// we're manpulating it.
		synchronized (application) {
			PollApplication pollApp = (PollApplication) application.getAttribute("pollApp");
			if (pollApp == null) {
				pollApp = new PollApplication();
				pollApp.setFilePath(application.getRealPath("WEB-INF/stored_polls.xml"));
				application.setAttribute("pollApp", pollApp);
			}
			return pollApp;
		}
	}

	/*
	 * gets all the polls filtered by the parameters.
	 * 
	 * @username credentials - username - only needed if trying to view owned
	 * 'close' polls
	 * 
	 * @password credentials - password - only needed if trying to view owned
	 * 'close' polls
	 * 
	 * @creatorusername to filter the list by poll creator
	 * 
	 * @status to filter the list by status (if filtering by 'close' status then
	 * credentials are needed
	 * 
	 * @minResponses to filter the list by minimum number of responses.
	 * 
	 * @return ArrayList<Poll> list of polls filtered by the given criteria
	 */
	@WebMethod
	public ArrayList<Poll> getPolls(String username, String password, String creatorUsername, String status,
			int minResponses) {
		PollApplication pollApp = getPollAplication();
		Creator signedCreator = pollApp.signCreator(username, password);
		Creator requestedCreator = pollApp.getCreator(creatorUsername);
		return pollApp.getPolls(signedCreator, requestedCreator, status, minResponses);
	}

	/*
	 * creates a new poll, only if the given data is valid.
	 * 
	 * @param creatorUsername username of the creator of the poll
	 * 
	 * @param creatorPassword password of the creator of the poll
	 * 
	 * @param title title of the poll, cannot be empty
	 * 
	 * @param meetingLocation location of meeting, cannot by empty
	 * 
	 * @param description poll description
	 * 
	 * @param dates ArrayList<Date> dates for poll users to choose from, cannot
	 * by empty
	 * 
	 * @return String id of the newly created poll, null if poll was not
	 * created.
	 */
	@WebMethod
	public String createPoll(String creatorUsername, String creatorPassword, String title, String meetingLocation,
			String description, ArrayList<Date> dates) {
		PollApplication pollApp = getPollAplication();
		Creator signedCreator = pollApp.signCreator(creatorUsername, creatorPassword);
		return pollApp.createPoll(signedCreator, title, meetingLocation, description, dates);
	}

	/*
	 * changes statos of poll to 'close' only if right credentials are given
	 * 
	 * @param username poll owner username
	 * 
	 * @param password poll owner password
	 * 
	 * @param pollId id of poll to be updated
	 */
	@WebMethod
	public void closePoll(String username, String password, String pollId) {
		if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0
				&& pollId != null && pollId.trim().length() > 0) {
			PollApplication pollApp = getPollAplication();
			Creator signedCreator = pollApp.signCreator(username, password);
			pollApp.setPollStatus(signedCreator, pollId, "close");
		}
	}
}
