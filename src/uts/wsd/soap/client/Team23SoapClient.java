package uts.wsd.soap.client;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import team23.models.Creator;
import team23.models.PollApplication;

public class Team23SoapClient {

	public static void main(String[] args) throws ServiceException, RemoteException {
		Team23SoapServiceLocator locator = new Team23SoapServiceLocator();
		Team23Soap team23Soap = locator.getTeam23SoapPort();
		
		System.out.println("All Polls:");
		Poll[] result1 = team23Soap.getPolls(null, null, null, null, 0);
		if (result1 != null) {
			for (Poll poll : result1) {
				System.out.println("title: "+ poll.getTitle() + " status: "+poll.getStatus() + " # of responses: "+poll.getAll_responses().length);
			}
		}else{
			System.out.println("returned null");
		}
		
		System.out.println("polls created by Michael:");
		Poll[] result2 = team23Soap.getPolls(null, null, "Michael", null, 0);
		if (result2 != null) {
			for (Poll poll : result2) {
				System.out.println("title: "+ poll.getTitle() + " status: "+poll.getStatus() + " # of responses: "+poll.getAll_responses().length);
			}
		}else{
			System.out.println("returned null");
		}
		
		System.out.println("polls with trhee or more responses:");
		Poll[] result3 = team23Soap.getPolls(null, null, null, null, 3);
		if (result3 != null) {
			for (Poll poll : result3) {
				System.out.println("title: "+ poll.getTitle() + " status: "+poll.getStatus() + " # of responses: "+poll.getAll_responses().length);
			}
		}else{
			System.out.println("returned null");
		}
		
		System.out.println("closed polls with trhee or more responses created by Pieter:");
		Poll[] result4 = team23Soap.getPolls("Pieter", "asdf", "Pieter", "close", 3);
		if (result4 != null) {
			for (Poll poll : result4) {
				System.out.println("title: "+ poll.getTitle() + " status: "+poll.getStatus() + " # of responses: "+poll.getAll_responses().length);
			}
		}else{
			System.out.println("returned null");
		}
		
		System.out.println("All polls created by Pieter:");
		Poll[] result5 = team23Soap.getPolls("Pieter", "asdf", "Pieter", null, 0);
		if (result5 != null) {
			for (Poll poll : result5) {
				System.out.println("title: "+ poll.getTitle() + " status: "+poll.getStatus() + " # of responses: "+poll.getAll_responses().length);
			}
		}else{
			System.out.println("returned null");
		}
	}

}
