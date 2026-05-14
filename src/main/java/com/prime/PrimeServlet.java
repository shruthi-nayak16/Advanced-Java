package com.prime;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/primeServlet")
public class PrimeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String input = request.getParameter("num");

        out.println("<html><body>");

        try {
            int n = Integer.parseInt(input);

            if (n <= 1) {
                out.println("<h3>Number must be greater than 1</h3>");
            } else {
                boolean isPrime = true;

                for (int i = 2; i <= n / 2; i++) {
                    if (n % i == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    out.println("<h2>" + n + " is a Prime Number</h2>");
                } else {
                    out.println("<h2>" + n + " is NOT a Prime Number</h2>");
                }
            }

        } catch (NumberFormatException e) {
            // ERROR HANDLING (invalid input)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Error code 400
            out.println("<h3 style='color:red;'>Invalid Input! Please enter a valid number.</h3>");
        }

        out.println("</body></html>");
    }
}