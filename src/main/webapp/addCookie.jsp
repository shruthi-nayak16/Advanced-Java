<%@ page language="java" %>
<%@ page import="javax.servlet.http.Cookie" %>

<html>
<body>

<%
    String name = request.getParameter("cname");
    String domain = request.getParameter("domain");
    int age = 0;

    try {
        age = Integer.parseInt(request.getParameter("age"));

        Cookie cookie = new Cookie(name, domain);
        cookie.setMaxAge(age);

        response.addCookie(cookie);
%>

        <h2>Cookie Added Successfully!</h2>
        <p><b>Name:</b> <%= name %></p>
        <p><b>Domain:</b> <%= domain %></p>
        <p><b>Max Age:</b> <%= age %> seconds</p>

        <br>
        <a href="viewCookies.jsp">Go to Active Cookie List</a>

<%
    } catch (Exception e) {
%>
        <h3 style="color:red;">Invalid Input! Please enter valid age.</h3>
<%
    }
%>

</body>
</html>