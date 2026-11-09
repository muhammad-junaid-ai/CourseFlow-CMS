import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class drop extends HttpServlet {

  //Process the HTTP POST request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     String courses=request.getParameter("courses"); 
      
     
    out.println("<html>");
    out.println("<head><title>DROP COURSE</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
    out.println("<body id='img2'>");
    
      HttpSession session=request.getSession(false);  

    String status = (session != null) ? (String) session.getAttribute("session_status") : null;

    if   ((session == null || status == null || !status.equals("student")))   
    
    // if(session == null)
    {
      out.println("<h1 class='text-center mt-3'>YOU ARE NOT SIGNED IN OR YOUR SESSION HAS BEEN EXPIRED</h1>");
      out.println("<BR/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>SIGN IN</button></a>");    
    }else
  {
    RequestDispatcher rd = request.getRequestDispatcher("/header.html");
    rd.include(request,response);
try
{
     Connection con = DBConnection.getConnection();

     String name=(String)session.getAttribute("session_name");  
     String id=(String)session.getAttribute("session_id"); 
  	              
      
     String query="Delete from add_course where (signup_id=? AND courses=?)  ";
     PreparedStatement st = con.prepareStatement(query);
     st.setString(1, id);
     st.setString(2, courses);
     int rs = st.executeUpdate();
     if(rs==1)
     {
       out.println("<h1 class='text-center'>COURSE Deleted Successfully</h1>"); 
       out.println("<div class='text-center mt-3'><a href='dashboard.jsp' class='btn btn-primary'>Back To Dashboard</a></div>"); 
       String query1="UPDATE history SET status='DROPPED' where courses=? AND signup_id= ?";
       PreparedStatement st1 = con.prepareStatement(query1);
       st1.setString(1, courses);
       st1.setString(2, id);
       int rs2 = st1.executeUpdate();
       st1.close();
     }
      else
      {
        out.println("<h1 class='text-center'>COURSE Doesn't Exist</h1>");
      }
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
