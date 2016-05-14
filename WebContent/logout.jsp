<%@page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="pollsApp.xsl"?>
<%
	session.invalidate();
	response.sendRedirect("index.jsp");
%>