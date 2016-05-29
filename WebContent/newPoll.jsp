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
	String username = (String)session.getAttribute("signed_creator_username");
	Creator me = pollApp.getCreator(username);
	Poll tempPoll;
	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	DateFormat presentationFormat = new SimpleDateFormat("EEE, d MMM yyyy 'at' hh:mm aaa");
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
			<%if(request.getParameter("action") != null) {%>
			<buttonlink link="newPoll.jsp" type="info">New Poll</buttonlink>
			<%}%>
		</buttonssection>
	
	<!-- if the form is submitted using the CREATE button, create a new poll with the form 
		detail and add them to the currently logged in user. Reset the temporary poll value, 
		used to store a list of dates. -->
<%if(request.getParameter("action") != null && request.getParameter("action").equals("CREATE")){
	ArrayList<Date> possibleTimes;
	String title = request.getParameter("title");
	String location = request.getParameter("location");
	String description = request.getParameter("description");
	Date date;
	if(request.getParameter("date") != null){
		date = dateformat.parse(request.getParameter("date"));
	}
	else{
		date = null;
	}
	if(session.getAttribute("current_poll") != null || date != null){
		if(session.getAttribute("current_poll") != null){
			possibleTimes = ((Poll)session.getAttribute("current_poll")).getPossibleMeetingDates();
			if(date != null){
				possibleTimes.add(dateformat.parse(request.getParameter("date")));
			}
		} else {
			possibleTimes = new ArrayList<Date>();
			possibleTimes.add(date);
		}
		session.setAttribute("current_poll", null);
		me.addPoll(new Poll(title, location, description, possibleTimes));
		pollApp.marshall(filePath);%> 
		
	<cardsection>
		<getoutsection message="Poll Added!"/>
	</cardsection>
	
	<%} else {%>
	
	<cardsection>
		<getoutsection message="Error: poll not added, must have at least one meeting date!"/>
	</cardsection>
	<!-- Otherwise if the form was submitted with the ADD ANOTHER TIME button, use the value
		from the date/time input and store it in temporary poll, then store that poll in the
		session so that it is persistent. -->
<% }
}
else if(request.getParameter("action") != null && request.getParameter("action").equals("ADD MEETING TIME")) {
	ArrayList<Date> possibleDates;
		if(((Poll)session.getAttribute("current_poll"))!=null){
			tempPoll = (Poll)session.getAttribute("current_poll");
		} else {
			tempPoll = new Poll();
		}
		Date date = dateformat.parse(request.getParameter("date"));
		if(tempPoll.getPossibleMeetingDates() == null){
			possibleDates = new ArrayList<Date>();
		} else {
			possibleDates = tempPoll.getPossibleMeetingDates();
		}
		possibleDates.add(date);
		tempPoll.setPossibleMeetingDates(possibleDates);
		session.setAttribute("current_poll",tempPoll);
	
	%>
	<cardssection>
	<!-- if there is no poll value stored in the session, there is also no dates that need to
	brought over from the last page, therefore just load a regular form -->
	<card type="details" class="big-details-card" title="New Poll">
	
	<% 	
	if(((Poll)session.getAttribute("current_poll"))!=null){
		for(Date meetingDate : ((Poll)session.getAttribute("current_poll")).getPossibleMeetingDates()){ %>
			<cardrow label="Time: ">
				<%= presentationFormat.format(meetingDate)%>
			</cardrow>
		<%}
	}%>
			<newpollinput formaction="newPoll.jsp" titleinput="title" locationinput="location" 
			descriptioninput="description" dateinput="date"/>
		</card>
	</cardssection>
	<!-- If there is a poll stored in the session, then there are dates that need to be brought
		over form the previous form/forms. Load the poll and display the dates/times that
		have already been added. -->
	<% } else { %>
	<cardssection>
	<card type="details" class="big-details-card" title="New Poll">
	<% if(((Poll)session.getAttribute("current_poll"))!=null){
		for(Date meetingDate : ((Poll)session.getAttribute("current_poll")).getPossibleMeetingDates()){ %>
			<cardrow label="Time: ">
				<%= presentationFormat.format(meetingDate)%>
			</cardrow>
<%		}
	}%>
		
			<newpollinput formaction="newPoll.jsp" titleinput="title" locationinput="location" 
			descriptioninput="description" dateinput="date"/>
		</card>
	</cardssection>
<% } %>
	</content>
</masterpage>