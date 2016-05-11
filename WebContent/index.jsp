<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="pollsApp.xsl"?>
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
					<cardtoken label="Title: ">this is a poll title 1</cardtoken>
					<cardtoken label=" On ">2016.05.01</cardtoken>
					<cardtoken label=" State ">open</cardtoken>
				</carditem>
			</card>
			<card type="list" class="small-list-card" title="Polls created by 'user1'">
				<carditem link="pollDetails.jsp?id=2">
					<cardtoken label="Title: ">this is a poll title 1</cardtoken>
					<cardtoken label=" On ">2016.05.01</cardtoken>
				</carditem> 
			</card>
		</cardssection> 
	</content>
</masterpage>