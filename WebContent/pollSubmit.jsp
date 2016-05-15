<%@page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
	<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.UUID"%>
<%@page import="java.util.ArrayList"%>
<%@page import="team23.models.*"%>
<?xml-stylesheet type="text/xsl" href="pollsApp.xsl"?>
<% String filePath = application.getRealPath("WEB-INF/stored_polls.xml"); %>
<jsp:useBean id="pollApp" class="team23.models.PollApplication">
	<jsp:setProperty name="pollApp" property="filePath" value="<%= filePath %>"></jsp:setProperty>
</jsp:useBean>
<% 
	StoredCreators allCreators = pollApp.getCreators();
	Creator me = (Creator)session.getAttribute("signed_creator");
	UUID pollID;
	String title = request.getParameter("title");
	String location = request.getParameter("location");
	String description = request.getParameter("description");
	ArrayList<Date> possibleTimes = null;
	Creator user = (Creator)session.getAttribute("signed_creator");
	Poll poll = new Poll(title, location, description, possibleTimes);
%>
<masterpage title="New Poll">
	<menu> 
		<menuitemleft title="Home" link="index.jsp" active="true"/>
		<% if (me==null) { %>
		<menuitemright title="Login" link="login.jsp"/>
		<% } else { %>
		<menuitemright title="LogOut" link="logout.jsp"/>
		<% } %> 
	</menu>
	<content>
		<buttonssection>
			<% if (me==null) { %>
				<buttonlink link="login.jsp" type="primary">Login</buttonlink>
				<buttonlink link="signup.jsp" type="success">Sign Up</buttonlink>
			<% } else { %>
				<buttonlink link="logout.jsp" type="danger">Logout</buttonlink>	
			<% } %>
				<buttonlink link="index.jsp" type="info">Exisiting Polls</buttonlink>
		</buttonssection>
		<cardssection>
		<% if(poll!=null) {
			user.addPoll(poll);
			%><getoutsection message="Poll Added!"/><%
		 }%>

		</cardssection>
	</content>
</masterpage>