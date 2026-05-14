package com.cookie;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");

        int visitCount = 1;
        boolean found = false;

        Cookie[] cookies = request.getCookies();

        // Check existing cookies
        if (cookies != null) {
            for (Cookie c : cookies) {

                if (c.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(c.getValue());
                    visitCount++;

                    c.setValue(String.valueOf(visitCount));

                    // Expiry time = 15 seconds
                    c.setMaxAge(15);

                    response.addCookie(c);
                    found = true;
                }
            }
        }

        // If no visit cookie found
        if (!found) {
            Cookie visitCookie = new Cookie("visitCount", "1");
            visitCookie.setMaxAge(15);
            response.addCookie(visitCookie);
        }

        // Create username cookie
        Cookie nameCookie = new Cookie("username", name);
        nameCookie.setMaxAge(15);
        response.addCookie(nameCookie);

        // Output
        out.println("<html><body>");
        out.println("<h2>Welcome back " + name + "!</h2>");
        out.println("<h3>You have visited this page " + visitCount + " times</h3>");

        // Display cookie list
        out.println("<h3>List of Cookies and their Values:</h3>");

        Cookie[] allCookies = request.getCookies();

        if (allCookies != null) {
            for (Cookie c : allCookies) {
                out.println("Cookie Name: " + c.getName() +
                            " | Value: " + c.getValue() + "<br>");
            }
        }

        // Expiry demonstration
        out.println("<br><b>Note:</b> Cookies will expire in 15 seconds.");
        out.println("<br>After expiry, visit count resets to 1.");

        out.println("</body></html>");
    }
}