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


<masterpage title="Register"> 
	<menu> 
		<menuitemleft title="Home" link="index.jsp" active="true"></menuitemleft>
		<menuitemright title="Login" link="login.jsp"></menuitemright>
	</menu> 
	<content>
	
	<% if(request.getParameter("input_username")==null || request.getParameter("input_password1")==null){	//Checks if the page is being loaded for the first time %>	
		<registersection formaction="register.jsp" usernameinput="input_username" password1input="input_password1" password2input="input_password2"></registersection>
	<%} else if (!request.getParameter("input_password1").equals(request.getParameter("input_password2"))){   //Checks to see if both passwords match %>
		<registersection formaction="register.jsp" usernameinput="input_username" password1input="input_password1" password2input="input_password2" loginfailedmsg="Passwords do not match"></registersection>
	<%} else if (pollApp.checkUnique(request.getParameter("input_username"))){   //Checks that the username is not already taken %>    
		<registersection formaction="register.jsp" usernameinput="input_username" password1input="input_password1" password2input="input_password2" loginfailedmsg="Username already taken"></registersection>
	<%} else{  //Checks if everything is ok, creates a new Creator, adds it to the list, saves it to the xml, signs the creator in and redirects to the index page
			String username = request.getParameter("input_username");
			String password = request.getParameter("input_password1");
			Creator newCreator = null, me = null;
			if(username.trim().length()>0 && password.trim().length()>0){
				newCreator = new Creator(username, password);
				pollApp.addCreator(newCreator);
				me = pollApp.signCreator(username, password);
				pollApp.save();
			}
			
			if (me != null) {
				session.setAttribute("signed_creator_username", me.getUsername());	
				response.sendRedirect("index.jsp");
			}	
	}%>
	
	</content>
	
	
</masterpage>