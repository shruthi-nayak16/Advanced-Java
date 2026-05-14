<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Session Status</title>
</head>
<body>

<h2>Session Status</h2>

<%
String name = (String) session.getAttribute("user");

if (name == null) {
%>
    <h3>Sorry, session has expired!</h3>
<%
} else {
%>
    <h3>Hello <%= name %>! Session is still active.</h3>
<%
}
%>

<br><br>
<a href="index.html">Go Back</a>

</body>
</html>