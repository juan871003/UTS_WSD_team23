<%@page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="team23.models.*"%>
<?xml-stylesheet type="text/xsl" href="pollsApp.xsl"?>
<% String filePath = application.getRealPath("WEB-INF/stored_polls.xml"); %>
<jsp:useBean id="pollApp" class="team23.models.PollApplication">
	<jsp:setProperty name="pollApp" property="filePath"
		value="<%=filePath%>"></jsp:setProperty>
</jsp:useBean>
<masterpage> 
<%
	DateFormat dateformatform = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
 	String name = request.getParameter("input_name");
 	String pollId = request.getParameter("input_poll_id");
 	String[] responses = request.getParameterValues("checkbox_response");
 	if (name != null && pollId != null) {
 		if (responses==null){
 			responses = new String[0];
 		}
 		ArrayList<Date> datesResponses = new ArrayList<Date>();
 		for (String theresponse : responses) {
 			Date date = dateformatform.parse(theresponse);
 			if(date!=null){
 				datesResponses.add(date);
 			}
 		}
 		//Poll thePoll = pollApp.getPoll(pollId);
		//Creator thePollCreator = pollApp.getCreators().getPollCreator(pollId);
		//if(thePollCreator!=null){
	//	Poll thePoll = thePollCreator.getPoll(pollId);
		//if(thePoll!=null){
		PollResponse newPollResponse = new PollResponse(name,datesResponses);
		pollApp.addResponse(pollId, newPollResponse);
		//thePoll.addResponse(newPollResponse);
		pollApp.save();
		//}
	}
 	response.sendRedirect("pollDetails.jsp?id="+pollId);
%>
</masterpage>