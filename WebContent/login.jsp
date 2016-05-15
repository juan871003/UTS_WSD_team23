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
<% Creator me = (Creator)session.getAttribute("signed_creator"); %>
<masterpage title="Login">
	<menu>
		<menuitemleft title="Home" link="index.jsp"></menuitemleft>
		<% if (me!=null) { %>
		<menuitemright title="LogOut" link="logout.jsp"></menuitemright>
		<% } %>
	</menu>
	<% if (me!=null) { %>
	<content>
		<getoutsection message="Hello <%= me.getUsername() %>! we can't log you in while your are logged in, please log out first before attempting to log in ;)"></getoutsection>
	</content>
	<% } else if (request.getParameter("input_username")==null || request.getParameter("input_password")==null) { %>
	<loginsection formaction="login.jsp" usernameinput="input_username" passwordinput="input_password"></loginsection>
	<% } else { 
			String username = request.getParameter("input_username");
			String password = request.getParameter("input_password");
			me = pollApp.getCreators().getCreator(username, password);
			
			if (me != null) {
				session.setAttribute("signed_creator", me);	
				response.sendRedirect("index.jsp");
			}
			else { %>
				<loginsection formaction="login.jsp" usernameinput="input_username" passwordinput="input_password" loginfailedmsg="Incorrect username or password"></loginsection>
			<% }
		} %>
</masterpage>