package uts.wsd.soap.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import sun.util.resources.cldr.CalendarData;
import team23.models.Creator;
import team23.models.PollApplication;

public class Team23SoapClient {
	/*
	 * main console application to test SOAP service
	 */
	public static void main(String[] args) throws ServiceException, RemoteException {
		Team23SoapServiceLocator locator = new Team23SoapServiceLocator();
		Team23Soap team23Soap = locator.getTeam23SoapPort();

		// return all polls (open polls)
		System.out.println("All Polls:");
		Poll[] result1 = team23Soap.getPolls(null, null, null, null, 0);
		if (result1 != null) {
			for (Poll poll : result1) {
				System.out.println("title: " + poll.getTitle() + " status: " + poll.getStatus() + " # of responses: "
						+ poll.getAll_responses().length);
			}
		} else {
			System.out.println("returned null");
		}

		// return open polls created by user
		System.out.println("polls created by Michael:");
		Poll[] result2 = team23Soap.getPolls(null, null, "Michael", null, 0);
		if (result2 != null) {
			for (Poll poll : result2) {
				System.out.println("title: " + poll.getTitle() + " status: " + poll.getStatus() + " # of responses: "
						+ poll.getAll_responses().length);
			}
		} else {
			System.out.println("returned null");
		}

		// return open polls with 3 or more responses
		System.out.println("polls with trhee or more responses:");
		Poll[] result3 = team23Soap.getPolls(null, null, null, null, 3);
		if (result3 != null) {
			for (Poll poll : result3) {
				System.out.println("title: " + poll.getTitle() + " status: " + poll.getStatus() + " # of responses: "
						+ poll.getAll_responses().length);
			}
		} else {
			System.out.println("returned null");
		}

		// return closed polls created by user, with 3 or more responses
		System.out.println("closed polls with trhee or more responses created by Pieter:");
		Poll[] result4 = team23Soap.getPolls("Pieter", "asdf", "Pieter", "close", 3);
		if (result4 != null) {
			for (Poll poll : result4) {
				System.out.println("title: " + poll.getTitle() + " status: " + poll.getStatus() + " # of responses: "
						+ poll.getAll_responses().length);
			}
		} else {
			System.out.println("returned null");
		}

		// return all open and close polls created by pieter
		System.out.println("All polls created by Pieter:");
		Poll[] result5 = team23Soap.getPolls("Pieter", "asdf", "Pieter", null, 0);
		if (result5 != null) {
			for (Poll poll : result5) {
				System.out.println("title: " + poll.getTitle() + " status: " + poll.getStatus() + " # of responses: "
						+ poll.getAll_responses().length);
			}
		} else {
			System.out.println("returned null");
		}

		// create new poll
		System.out.println("Creating new poll for Michael - pollSoap 1");
		Calendar dates[] = { new GregorianCalendar(2016, 9, 8) };
		String returnedId = team23Soap.createPoll("Michael", "asdf", "pollSoap 1", "Building 11", "created via Soap",
				dates);
		System.out.println("new poll ID: " + returnedId);

		// close poll
		System.out.println("Closing the newly created poll: pollSoap 1");
		team23Soap.closePoll("Michael", "asdf", returnedId);
		System.out.println("Poll closed, pollID: " + returnedId);
	}

}
