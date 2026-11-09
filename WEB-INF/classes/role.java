import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class role extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedRole = request.getParameter("role");

        if ("S".equalsIgnoreCase(selectedRole)) {
            response.sendRedirect("signup.html");
        } else {
            response.sendRedirect("teacher_signup.html");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("role.html");
    }
}
