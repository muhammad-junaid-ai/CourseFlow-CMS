import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class forgot extends HttpServlet {

  //Process the HTTP Get request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
     String email=request.getParameter("email");
   

     out.println("<html>");
     out.println("<head><title>FORGOT </title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
     out.println("<body id='img2'>");
  try
  {
      Connection con = DBConnection.getConnection();

     String query="Select * from data where email=?";
     PreparedStatement st=con.prepareStatement(query);
     st.setString(1, email);
     ResultSet rs = st.executeQuery();
   
     if(rs.next())
     {
        out.println("<div style='background-color:red; height:100px;'><h1 class='text-center p-4'>User Credentials</h1></div>");
        String name = rs.getString("username");
        String pwd = rs.getString("password");

        out.println("<h1>Username :" +name+"</h1>");
	    	out.println("<br/><br/><h1>Password :" +pwd+"</h1>");
        out.println("<a href='signin.html'><button class='btn btn-primary ms-3'>Back To Login Page</button></a>");
	  }
     
     else
     {
       out.println("<div style='color:red'><h1 class='text-center p-4'>Email Does Not Exist </h1></div>");
    	 out.println("<a href='forgot.html'><button class='btn btn-primary ms-3'>Back To Forgot Username/Password</button></a>");
       out.println("<br/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>Back to LogIn Page</button></a>");    
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
