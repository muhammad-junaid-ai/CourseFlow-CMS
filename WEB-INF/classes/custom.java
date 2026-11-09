import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class custom extends HttpServlet {

  //Process the HTTP Post request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    response.setContentType("text/html");
    // get PrintWriter object
    PrintWriter out = response.getWriter();
  //get data from customform
    String courses=request.getParameter("courses"); 
    

 
    out.println("<html>");
    out.println("<head><title>CUSTOM SEARCH</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
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
      
      String query = "SELECT * FROM add_course WHERE (signup_id = ? AND name = ? AND courses = ?) ";
      PreparedStatement st = con.prepareStatement(query);
      st.setString(1, id);
      st.setString(2, name);
      st.setString(3, courses);
      ResultSet rs = st.executeQuery();

    if(rs.next())
    {	
      out.println("<div class='container'>");
      out.println("<h1 class='mt-3 text-center'>Searched Course</h1>");
      out.println("<table class='table table-bordered border-dark'>"); 
      out.println("<thead>");
      out.println("<tr class='table-dark'>");
      out.println("<th>Course Name</th>");
      out.println("<th>Teacher Name</th>");
      out.println("<th>Day</th>");
      out.println("<th>Time Slot</th>");
      out.println("</tr>");
      out.println("</thead>");
          
      do {
        out.println("<tr>");
        out.println("<td>" + rs.getString("courses") + "</td>");
        out.println("<td>" + rs.getString("teachers") + "</td>");
        out.println("<td>" + rs.getString("day") + "</td>");
        out.println("<td>" + rs.getString("time") + "</td>");
        out.println("</tr>");
    } while (rs.next());

      
      out.println("</tbody></table></div>");
    }
    else
    {
      out.println("<h1 class='mt-3 text-center'>You Are NOT Enrolled In That Course</h1>");
      out.println("<h1 class='mt-3 text-center'>First Add Courses</h1>");
    }
    st.close();
    con.close();
  }
  catch(Exception e)
  {
    out.println(e);
  }
}//else end
out.println("</body></html>");
}
}
