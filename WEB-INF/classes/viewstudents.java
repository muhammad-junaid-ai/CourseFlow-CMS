import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class viewstudents extends HttpServlet {

  //Process the HTTP Get request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head><title>VIEW STUDENTS</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
    out.println("<body id='img2'>");
    
    String courses=request.getParameter("courses");
   
    HttpSession session=request.getSession(false);  

    String status = (session != null) ? (String) session.getAttribute("session_status") : null;

    if   ((session == null || status == null || !status.equals("teacher")))  
    
    // if(session == null)
    {
      out.println("<h1 class='text-center mt-3'>YOU ARE NOT SIGNED IN OR YOUR SESSION HAS BEEN EXPIRED</h1>");
      out.println("<BR/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>SIGN IN</button></a>");    
    }
   else
  {
      RequestDispatcher rd = request.getRequestDispatcher("/teacher_header.html");
      rd.include(request,response);
      
    try
    {   
      Connection con = DBConnection.getConnection();
	    
      String name=(String)session.getAttribute("session_name");  
	    String id=(String)session.getAttribute("session_id"); 
      String teachers=(String)session.getAttribute("session_teachers");  	
    
      String query = "SELECT * FROM add_course WHERE courses = ? && teachers = ? ";      
      PreparedStatement st = con.prepareStatement(query);
      st.setString(1, courses);
      st.setString(2, teachers);
      ResultSet rs = st.executeQuery();

      if(rs.next())
      {	
          out.println("<div class='container'>");
          out.println("<h1 class='mt-3 text-center'> Enrolled Students </h1>");
          out.println("<table class='table table-bordered border-dark'>"); 
          out.println("<thead>");
          out.println("<tr class='table-dark'>");
          out.println("<th>Roll #</th>");
          out.println("<th>Student Name</th>");
          out.println("<th>Day</th>");
          out.println("<th>Time Slot</th>");
           out.println("</tr>");
          out.println("</thead>");
                    
          out.println("<tbody>");

          do {
              out.println("<tr>");
              out.println("<td>" + rs.getString("signup_id") + "</td>");
              out.println("<td>" + rs.getString("name") + "</td>");
              out.println("<td>" + rs.getString("day") + "</td>");
              out.println("<td>" + rs.getString("time") + "</td>");
              out.println("</tr>");
          } while (rs.next());
  
          out.println("</tbody>");
          out.println("</table>");
          out.println("</div>");
        }         
       else
       {
        out.println("<h1 style='color:red; 'class='mt-3 text-center display-5'>Note</h1>");
        out.println("<h1 class='mt-3 text-center'>None of the students has enrolled in "+courses+" Yet</h1>");
      
      }
     st.close();
     con.close();
    }
    catch(Exception e)
    {
      out.println(e);
    }
  } //ELSE END
    out.println("</body></html>");
}
}
