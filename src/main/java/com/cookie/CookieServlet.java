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
        int count = 1;

        Cookie cookies[] = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {

                if (c.getName().equals("username")) {
                    name = c.getValue();
                }

                if (c.getName().equals("visitCount")) {
                    count = Integer.parseInt(c.getValue());
                    count++; // increment visit count
                }
            }
        }

        // Create cookies
        Cookie nameCookie = new Cookie("username", name);
        Cookie countCookie = new Cookie("visitCount", String.valueOf(count));

        // Set expiry time (60 seconds)
        nameCookie.setMaxAge(60);
        countCookie.setMaxAge(60);

        // Add cookies to response
        response.addCookie(nameCookie);
        response.addCookie(countCookie);

        // OUTPUT
        out.println("<html><body>");
        out.println("<h2>Welcome back " + name + "!</h2>");
        out.println("<h3>You have visited this page " + count + " times.</h3>");
        out.println("<p><b>Note:</b> Cookie will expire in 60 seconds.</p>");
        out.println("</body></html>");
    }
}