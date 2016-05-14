<%@page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="team23.models.*"%>
<?xml-stylesheet type="text/xsl" href="pollsApp.xsl"?>
<% String filePath = application.getRealPath("WEB-INF/stored_polls.xml"); %>
<jsp:useBean id="pollApp" class="team23.models.PollApplication">
	<jsp:setProperty name="pollApp" property="filePath" value="<%= filePath %>"></jsp:setProperty>
</jsp:useBean>
<% 
	StoredCreators allCreators = pollApp.getCreators();
	DateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd");
	Creator me = (Creator)session.getAttribute("signed_creator");
	String pollId = request.getParameter("id");
	Creator pollCreator = null;
	Poll poll = null;
	if (pollId!= null) {
		pollCreator = allCreators.getPollCreator(pollId);
		if (pollCreator!=null) {
			poll = pollCreator.getPoll(pollId);
		}
	}
%>
<masterpage title="Poll Details">
	<menu> 
		<menuitemleft title="Home" link="index.jsp" active="true"></menuitemleft>
		<% if (me==null) { %>
		<menuitemright title="Login" link="login.jsp"></menuitemright>
		<% } else { %>
		<menuitemright title="LogOut" link="logout.jsp"></menuitemright>
		<% } %> 
	</menu>
	<content>
		<% if(poll==null){ %>
		<getoutsection message="Sorry, poll not found"></getoutsection>
		<!-- if a poll is closed and you are not the owner then you are not authorised -->
		<% } else if ((poll.getStatus().equals("close") && me==null) 
				|| (poll.getStatus().equals("close") 
						&& me!=null 
						&& !me.getUsername().equals(pollCreator.getUsername())) ) { %>
		<getoutsection message="You are not authorised to see this poll"></getoutsection>
		<% } else { %>
			<buttonssection>
			<% if (me==null) { %>
				<buttonlink link="login.jsp" type="primary">Login</buttonlink>
				<buttonlink link="signup.jsp" type="success">Sign Up</buttonlink>
			<% } else { %>
				<buttonlink link="logout.jsp" type="danger">Logout</buttonlink>	
			<% } %>
				<buttonlink link="index.jsp" type="info">All polls</buttonlink>
			</buttonssection>
			<cardssection>
				<card type="details" class="big-details-card" title="Poll Details">
					<cardrow label="Creator: "><%= pollCreator.getUsername() %></cardrow>
					<cardrow label="Title: "><%= poll.getTitle() %></cardrow>
					<cardrow label="Status: "><%= poll.getStatus() %></cardrow>
					<cardrow label="Date of creation: "><%= dateformat.format(poll.getCreationDate()) %></cardrow>
					<% if(poll.getDescription().trim().length() > 0) { %>
						<cardrow label="Description: "><%= poll.getMeetingLocation() %></cardrow>
					<% } %>
					<pollresponses>
					<% for(Date meetingDate : poll.getPossibleMeetingDates()){ %>
						<pollresponse></pollresponse>
					<% } %>
					</pollresponses>
				</card>
			</cardssection>
		<% } %>
	</content>
</masterpage>
