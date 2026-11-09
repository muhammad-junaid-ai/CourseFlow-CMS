import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class signin extends HttpServlet {

      //Process the HTTP Post request
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      String u_name=request.getParameter("name");
      String password=request.getParameter("password");
      
     
    out.println("<html>");
    out.println("<head><title>SIGN IN </title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
    out.println("<body id='img2'>");
  try
  {
    Connection con = DBConnection.getConnection();
    
     String query="Select * from data where username=? && password=? ";
     PreparedStatement st=con.prepareStatement(query);
     st.setString(1, u_name);
     st.setString(2, password);
     ResultSet rs = st.executeQuery();
     if(rs.next())
     {
          String name = rs.getString("username");
          String firstname = rs.getString("firstname");
    	  	String id = rs.getString("id");
          String status = rs.getString("status");
          
     HttpSession session=request.getSession();
     
     session.setAttribute("session_name",name);    
     session.setAttribute("session_id",id);    
     session.setAttribute("session_teachers",firstname);
     session.setAttribute("session_status",status);
          
    
     
     if (status.equals("teacher"))
{
     response.sendRedirect("teacher_dashboard.jsp");//if username and password correct then redirect page to dashboard    
}
else{
  response.sendRedirect("dashboard.jsp");
}
    }
     else
     {
       out.println("<div style='color:red; '><h1 class='text-center p-4'>Username Or Password Doesnot Exist </h1></div>");
    	 out.println("<a href='signin.html'><button  style='padding-left:25px '  class='btn btn-primary ms-3'>Try Again</button></a>");
       out.println("<br/><br/><a href='forgot.html'><button style='padding-left:25px ' class='btn btn-primary ms-3'>Forgot Username/Password</button></a>"); 
      }
       st.close();
       con.close();
  }
  catch(Exception e)
  {
    out.println(e);
  }
  out.println("</body></html>");
}
}
