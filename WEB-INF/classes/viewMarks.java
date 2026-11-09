import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class viewMarks extends HttpServlet {

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
   out.println("<head><title>VIEW MARKS</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
    out.println("<body id='img2'>");
    
    HttpSession session=request.getSession(false);  

    String status = (session != null) ? (String) session.getAttribute("session_status") : null;

    if   ((session == null || status == null || !status.equals("student"))) 
    // if(session == null)
    {
      out.println("<h1 class='text-center mt-3'>YOU ARE NOT SIGNED IN OR YOUR SESSION HAS BEEN EXPIRED</h1>");
      out.println("<BR/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>SIGN IN</button></a>");    
    }
   else
  {
      RequestDispatcher rd = request.getRequestDispatcher("/header.html");
      rd.include(request,response);
      
    try
    {   
      Connection con = DBConnection.getConnection();
	    
      String name=(String)session.getAttribute("session_name");  
	    String id=(String)session.getAttribute("session_id"); 
      String teachers=(String)session.getAttribute("session_teachers");  	
    
      String query = "SELECT * FROM marks WHERE signup_id = ? ";      
      PreparedStatement st = con.prepareStatement(query);
      st.setString(1, id);
      ResultSet rs = st.executeQuery();

      if(rs.next())
      {	
          out.println("<div class='container'>");
          out.println("<h1 class='mt-3 text-center'> Result </h1>");
          out.println("<table class='table table-bordered border-dark'>"); 
          out.println("<thead>");
          out.println("<tr class='table-dark'>");
          out.println("<th>Course</th>");
          out.println("<th>Teacher</th>");
          out.println("<th>Quiz</th>");
          out.println("<th>Assignment</th>");
          out.println("<th>Project</th>");
          out.println("<th>Mid</th>");
          out.println("<th>Final</th>");
          out.println("</tr>");
          out.println("</thead>");
                    
          out.println("<tbody>");

          do {
              out.println("<tr>");
              out.println("<td>" + rs.getString("courses") + "</td>");
              out.println("<td>" + rs.getString("teachers") + "</td>");
              out.println("<td>" + rs.getString("oquiz") + "</td>");
              out.println("<td>" + rs.getString("oassignment") + "</td>");
              out.println("<td>" + rs.getString("oproject") + "</td>");
              out.println("<td>" + rs.getString("omid") + "</td>");
              out.println("<td>" + rs.getString("ofinal") + "</td>");
              out.println("</tr>");
          } while (rs.next());
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
    }         
       else
       {
        out.println("<h1 style='color:red; 'class='mt-3 text-center display-5'>Note</h1>");
        out.println("<h1 class='mt-3 text-center'>Your Exams are not marked yet.Or You have not enrolled in any course yet</h1>");
      
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
