import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class signout extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    try
    {
      HttpSession session=request.getSession(false);  
      if(session == null)
      {
          out.println("<html>");
          out.println("<head><title>SIGN OUT</title><link rel='stylesheet' href='css/bootstrap.min.css'/></head>");
          out.println("<body style='background-color: #e3f2fd;'>");
          out.println("<h1 class='text-center mt-3'>YOU ARE NOT SIGNED IN OR YOUR SESSION HAS BEEN EXPIRED</h1>");
          out.println("<BR/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>SIGN IN</button></a>");   
          out.println("</body></html>"); 
      }
      else
      {
           session.invalidate();
           response.sendRedirect("home.html");}
      }
      catch(Exception e)
      {
      out.println(e);
      }
  }
}
