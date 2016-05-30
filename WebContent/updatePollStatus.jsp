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
	String status = request.getParameter("select_status");
	String pollId = request.getParameter("input_poll_id");
	String myUsername = (String)session.getAttribute("signed_creator_username");
	Creator me = null;
	if(myUsername!=null && myUsername.length()>0){
		me = pollApp.getCreator(myUsername);	
	}
	if(me!=null && status!=null && (status.equals("open") || status.equals("close")) && pollId!=null) {
		pollApp.setPollStatus(me, pollId, status);
		pollApp.save();
	}
	response.sendRedirect("pollDetails.jsp?id="+pollId);
%>
</masterpage>