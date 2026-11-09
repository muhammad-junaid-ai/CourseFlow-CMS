import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class dropCourse extends HttpServlet {

  //Process the HTTP POST request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     String courses=request.getParameter("courses"); 
     String signup_id=request.getParameter("signup_id"); 
      
     
    out.println("<html>");
    out.println("<head><title>drop course</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
    out.println("<body id='img2'>");
      HttpSession session=request.getSession(false);  

      String status = (session != null) ? (String) session.getAttribute("session_status") : null;
  
      if   ((session == null || status == null || !status.equals("teacher"))) 
    
    // if(session == null)
    {
      out.println("<h1 class='text-center mt-3'>YOU ARE NOT SIGNED IN OR YOUR SESSION HAS BEEN EXPIRED</h1>");
      out.println("<BR/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>SIGN IN</button></a>");    
    }else
  {
    RequestDispatcher rd = request.getRequestDispatcher("/teacher_header.html");
    rd.include(request,response);
try
{
     Connection con = DBConnection.getConnection();

     String name=(String)session.getAttribute("session_name");  
     String id=(String)session.getAttribute("session_id");  	              
     String teachers=(String)session.getAttribute("session_teachers"); 

     String query="Delete from add_course where (signup_id=? AND courses=? AND teachers=?)  ";
     PreparedStatement st = con.prepareStatement(query);
     st.setString(1, signup_id);
     st.setString(2, courses);
     st.setString(3, teachers);
     int rs = st.executeUpdate();
     if(rs==1)
     {
       out.println("<h1 class='text-center'>COURSE Dropped Successfully</h1>"); 
       out.println("<div class='text-center mt-3'><a href='teacher_dashboard.jsp' class='btn btn-primary'>Back To Dashboard</a></div>"); 
       String query1="UPDATE history SET status='DROPPED' where courses=? AND signup_id= ? AND teachers=?";
       PreparedStatement st1 = con.prepareStatement(query1);
       st1.setString(1, courses);
       st1.setString(2, signup_id);
       st1.setString(3, teachers);
       int rs2 = st1.executeUpdate();
       st1.close();
     }
      else
      {
        out.println("<h1 class='text-center'>Student is not Enrolled Yet in this course</h1>");
      }
      String query3="Delete from marks where (signup_id=? AND courses=? AND teachers=?)  ";
      PreparedStatement st3 = con.prepareStatement(query3);
      st3.setString(1, signup_id);
      st3.setString(2, courses);
      st3.setString(3, teachers);
      int rs3 = st3.executeUpdate();
      st3.close();

      st.close();
      con.close();
    }
    catch(Exception e)
    {
    out.println(e);
    }
  }//ELSE END
out.println("</body></html>");
}
}
