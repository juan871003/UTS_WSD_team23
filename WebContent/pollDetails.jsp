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
	DateFormat dateformatDate = new SimpleDateFormat("yyyy.MM.dd");
	DateFormat dateformatDateTime = new SimpleDateFormat("EEE, d MMM yyyy 'at' hh:mm aaa");
	DateFormat dateformatform = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
	String myUsername = (String)session.getAttribute("signed_creator_username");
	Creator me = null;
	if(myUsername!=null && myUsername.length()>0){
		me = pollApp.getCreator(myUsername);	
	}
	String pollId = request.getParameter("id");
	Creator pollCreator = null;
	Poll poll = null;
	if(pollId!=null){
		poll = pollApp.getPoll(pollId);
		pollCreator = pollApp.getPollCreator(pollId);
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
		<% } else if (poll.getStatus().equals("close") && !pollApp.isSamePerson(me,pollCreator)) { %>
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
					<% if(pollApp.isSamePerson(me, pollCreator)) { %>
					<cardrowselectstatus label="Status" poll_id="<%= pollId %>">
						<selectstatusitem <%= poll.getStatus().equals("open")?"selected='true'":"" %> value="open">Open</selectstatusitem>
						<selectstatusitem <%= poll.getStatus().equals("close")?"selected='true'":"" %> value="close">Close</selectstatusitem>
					</cardrowselectstatus>
					<% } else { %>
					<cardrow label="Status: "><%= poll.getStatus() %></cardrow>
					<% } %>
					<cardrow label="Date of creation: "><%= dateformatDate.format(poll.getCreationDate()) %></cardrow>
					<% if(poll.getMeetingLocation().trim().length() > 0) { %>
						<cardrow label="Location: "><%= poll.getMeetingLocation() %></cardrow>
					<% } %>
					<% if(poll.getDescription().trim().length() > 0) { %>
						<cardrow label="Description: "><%= poll.getDescription() %></cardrow>
					<% } %>
					<pollresponses pollid="<%= pollId %>">
					<% 	for(Date meetingDate : poll.getPossibleMeetingDates()){ %>
						<possibledate><%= dateformatDateTime.format(meetingDate)%></possibledate>
					<%	}
						for(PollResponse pResponse : poll.getPollResponses()) {
					%>
						<response personName="<%= pResponse.getPersonName() %>">
					<%
							for(Date meetingDate : poll.getPossibleMeetingDates()){
								if(pResponse.isDateInResponses(meetingDate)){
					%>
							<pollRdate going="yes"></pollRdate>
					<%			} else { %>
							<pollRdate going="no"></pollRdate>
					<%			
								}
							}
					%>
						</response>
					<%  }
						//if user is not signed or user is not the owner of this poll then user can add new responses
						//if user is the owner user should not be able to add new responses to her own poll, just see the responses.
						if(me==null || !pollApp.isSamePerson(me, pollCreator)) {	%>
						<addresponse inputvalue="<%= me==null ? "" : me.getUsername() %>">
					<% 		for(Date meetingDate : poll.getPossibleMeetingDates()){ %>
							<addresponsedate date="<%= dateformatform.format(meetingDate) %>"></addresponsedate>
					<%		} %>
						</addresponse>
					<%  } %>
					</pollresponses>
				</card>
			</cardssection>
		<% } %>
	</content>
</masterpage>
