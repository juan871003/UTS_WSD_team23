<%@page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="team23.models.*"%>
<?xml-stylesheet type="text/xsl" href="pollsApp.xsl"?>
<% String filePath = application.getRealPath("WEB-INF/stored_polls.xml"); %>
<jsp:useBean id="pollApp" class="team23.models.PollApplication">
	<jsp:setProperty name="pollApp" property="filePath" value="<%= filePath %>"></jsp:setProperty>
</jsp:useBean>
<% 
	StoredCreators allCreators = pollApp.getCreators();
	DateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd");
%>
<masterpage title="Login"> 
	<menu> 
		<menuitemleft title="Home" link="index.jsp" active="true"></menuitemleft>
		<menuitemright title="Login" link="login.jsp"></menuitemright> 
	</menu> 
	<content>
		<buttonssection>
			<buttonlink link="login.jsp" type="primary">Login</buttonlink>
			<buttonlink link="signup.jsp" type="success">Sign Up</buttonlink>
		</buttonssection>
		<cardssection>
			<card type="list" class="small-list-card" title="Polls created by me">
				<carditem link="pollDetails.jsp?id=1">
					<cardtoken label="Title: ">this is a mock poll, it is not real</cardtoken>
					<cardtoken label=" On ">2016.05.01</cardtoken>
					<cardtoken label=" State ">open</cardtoken>
				</carditem>
			</card>
			<% for(Creator creator : allCreators.getList()) { %>
			<card type="list" class="small-list-card" title="Polls created by <%= creator.getUsername() %>">
				<% for(Poll poll : creator.getPolls()) { %>
				<carditem link="pollDetails.jsp?id=<%= poll.getPollID().toString() %>">
					<cardtoken label=" Title: "><%= poll.getTitle() %></cardtoken>
					<cardtoken label=" Created On: "><%= dateformat.format(poll.getCreationDate()) %></cardtoken>
				</carditem>
				<% } %>
			</card>
			<% } %>
		</cardssection> 
	</content>
</masterpage>