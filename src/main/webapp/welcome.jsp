<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>
<body>

<%
String name = request.getParameter("uname");
String t = request.getParameter("time");

// Default session time (avoid null warning)
int time = 60;

if (t != null && !t.isEmpty()) {
    try {
        time = Integer.parseInt(t);
    } catch (Exception e) {
        time = 60;
    }
}

// Store name in session
session.setAttribute("user", name);

// Set session expiry time
session.setMaxInactiveInterval(time);
%>

<h2>Hello <%= name %>!</h2>

<p>Session has started for <%= time %> seconds.</p>

<p>Click the link below to check session:</p>

<a href="second.jsp">Check Session Status</a>

<br><br>
<p> Wait for the given time and click again to see session expiry.</p>

</body>
</html>