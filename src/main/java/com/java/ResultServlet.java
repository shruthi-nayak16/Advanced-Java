/* 10.b Build an Application to get Rollno, Studentname, Sub1, Sub2, Sub3, Sub4 and Sub5 through JSP
called index.jsp with client sided validation and submit to the servlet called ResultServlet and process all
the fields with server sided validation and display all the data along with result ( Pass if all subjects
greater than 40%) and Average marks through result.jsp with a link to move to the client side*/
package com.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String roll = request.getParameter("rollno");
        String name = request.getParameter("name");
        String[] marksStr = request.getParameterValues("marks");

        int marks[] = new int[5];
        int total = 0;
        boolean pass = true;

        // Server-side validation
        if (roll == null || name == null || marksStr == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            for (int i = 0; i < 5; i++) {
                marks[i] = Integer.parseInt(marksStr[i]);

                if (marks[i] < 0 || marks[i] > 100) {
                    pass = false;
                }

                if (marks[i] < 40) {
                    pass = false;
                }

                total += marks[i];
            }
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
            return;
        }

        double avg = total / 5.0;
        String result = pass ? "PASS" : "FAIL";

        // Send data to JSP
        request.setAttribute("roll", roll);
        request.setAttribute("name", name);
        request.setAttribute("marks", marks);
        request.setAttribute("avg", avg);
        request.setAttribute("result", result);

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}