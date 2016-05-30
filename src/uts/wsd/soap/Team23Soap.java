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

	@WebMethod
	public ArrayList<Poll> getPolls(String username, String password, String creatorUsername, String status,
			int minResponses) {
		PollApplication pollApp = getPollAplication();
		Creator signedCreator = pollApp.signCreator(username, password);
		Creator requestedCreator = pollApp.getCreator(creatorUsername);
		return pollApp.getPolls(signedCreator, requestedCreator, status, minResponses);
	}

	@WebMethod
	public String createPoll(String creatorUsername, String creatorPassword, String title, String meetingLocation,
			String description, ArrayList<Date> dates) {
		PollApplication pollApp = getPollAplication();
		Creator signedCreator = pollApp.signCreator(creatorUsername, creatorPassword);
		return pollApp.createPoll(signedCreator, title, meetingLocation, description, dates);
	}

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
