package com.cookie;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cookieServlet")
public class CookieServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");

        // Get existing cookies
        Cookie cookies[] = request.getCookies();

        int visitCount = 0;
        boolean found = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(c.getValue());
                    visitCount++;
                    c.setValue(String.valueOf(visitCount));

                    // Set expiry time (10 seconds for demo)
                    c.setMaxAge(10);

                    response.addCookie(c);
                    found = true;
                }
            }
        }

        if (!found) {
            visitCount = 1;
            Cookie visitCookie = new Cookie("visitCount", String.valueOf(visitCount));

            // Expiry time (10 seconds)
            visitCookie.setMaxAge(10);

            response.addCookie(visitCookie);
        }

        // Create cookie for username
        Cookie nameCookie = new Cookie("username", name);
        nameCookie.setMaxAge(10); // expires in 10 seconds
        response.addCookie(nameCookie);

        // Display output
        out.println("<html><body>");
        out.println("<h2>Welcome back " + name + "!</h2>");
        out.println("<h3>You have visited this page " + visitCount + " times.</h3>");

        // Display all cookies
        out.println("<h3>List of Cookies:</h3>");

        Cookie allCookies[] = request.getCookies();

        if (allCookies != null) {
            for (Cookie c : allCookies) {
                out.println("Name: " + c.getName() + " | Value: " + c.getValue() + "<br>");
            }
        }

        // Expiry info
        out.println("<br><p><b>Note:</b> Cookies will expire in 10 seconds.</p>");

        out.println("</body></html>");
    }
}