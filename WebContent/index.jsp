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
	DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
	Creator me = (Creator)session.getAttribute("signed_creator");
	String filter = request.getParameter("filter");
%>
<masterpage title="Home"> 
	<menu> 
		<menuitemleft title="Home" link="index.jsp" active="true"></menuitemleft>
		<% if (me==null) { %>
		<menuitemright title="Login" link="login.jsp"></menuitemright>
		<% } else { %>
		<menuitemright title="LogOut" link="logout.jsp"></menuitemright>
		<% } %> 
	</menu> 
	<content>
		<buttonssection>
			<% if (me==null) { %>
			<buttonlink link="login.jsp" type="primary">Login</buttonlink>
			<buttonlink link="signup.jsp" type="success">Sign Up</buttonlink>
			<% } else { %>
			<buttonlink link="logout.jsp" type="danger">Logout</buttonlink>	
			<buttonlink link="newPoll.jsp" type="info">Create new poll</buttonlink>
			
			<% 		if (filter==null || (filter!=null && !filter.equals("creator_only"))) {	%>	
			<buttonlink link="index.jsp?filter=creator_only" type="info">Show my polls only</buttonlink>
			<% 		}
					if (filter!=null && filter.equals("creator_only")) { %>
			<buttonlink link="index.jsp" type="info">Show all polls</buttonlink>
			<%		}
			   } %>
		</buttonssection>
		<cardssection>
		<% if (me != null && me.getPolls().size() > 0 ) { %>
			<card type="list" class="small-list-card" title="Polls created by me">
			<% for(Poll poll : me.getPolls()) { %>
				<carditem link="pollDetails.jsp?id=<%= poll.getPollID().toString() %>">
					<cardtoken label="Title: "><%= poll.getTitle() %></cardtoken>
					<cardtoken label=" On: "><%= dateformat.format(poll.getCreationDate()) %></cardtoken>
					<cardtoken label=" Status: "><%= poll.getStatus() %></cardtoken>
				</carditem>
			<% } %>
			</card>
		<% } %>
		<% if (filter==null || (filter!=null && !filter.equals("creator_only"))) { %>
			<% for(Creator creator : allCreators.getList()) { 
					if(me==null || (me!= null && !creator.getUsername().equals(me.getUsername()))) { %>
				<card type="list" class="small-list-card" title="Polls created by <%= creator.getUsername() %>">
					<% for(Poll poll : creator.getPolls()) { %>
					<carditem link="pollDetails.jsp?id=<%= poll.getPollID().toString() %>">
						<cardtoken label=" Title: "><%= poll.getTitle() %></cardtoken>
						<cardtoken label=" Created On: "><%= dateformat.format(poll.getCreationDate()) %></cardtoken>
					</carditem>
				<%     } %>
				</card>
				<% 	}
				} 
			} %>
		</cardssection> 
	</content>
</masterpage>