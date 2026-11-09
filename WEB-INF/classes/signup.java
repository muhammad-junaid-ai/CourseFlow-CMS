import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class signup extends HttpServlet {
        
        //Process the HTTP POST request
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
          response.setContentType("text/html");
        // response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstname=request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String age=request.getParameter("age");
        String password=request.getParameter("password");
        String degree=request.getParameter("degree");
        String status=request.getParameter("status");

      
    out.println("<html>");
    out.println("<head><title>SIGN UP</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
    out.println("<body id='img2'>");
    try
    {
        Connection con = DBConnection.getConnection();

        String query1 ="Select * from data where username=? ";
        PreparedStatement st1 = con.prepareStatement(query1);
        st1.setString(1, username);
        ResultSet rs1 = st1.executeQuery();

        boolean check1 = true; 
        boolean check2 = true;
       if(rs1.next())
       {
	    	    String Uname = rs1.getString("username");
     	      out.println("<h1 class='text-center mt-3'>User Name: "+Uname+" is already taken </h1><br/>");
            check1 = false;
      }

        String query2 ="Select * from data where email=? ";
        PreparedStatement st2 = con.prepareStatement(query2);
        st2.setString(1, email);
        ResultSet rs2 = st2.executeQuery();
    
        if(rs2.next())
        {
         String Email = rs2.getString("email"); 
         out.println("<h1  class='text-center mt-3'>Email: "+Email+" is already taken </h1>");
         check2 = false;
        }
     
	    if(check1 == true && check2 == true)
      {    
       String query = "INSERT INTO data(firstname,lastname,username,age,email,degree,password,status)VALUES(?,?,?,?,?,?,?,?) ";
       PreparedStatement st = con.prepareStatement(query);
       st.setString(1, firstname);
       st.setString(2, lastname);
       st.setString(3, username);
       st.setString(4, age);
       st.setString(5, email);
       st.setString(6, degree);
       st.setString(7, password);
       st.setString(8, status);
       int rs = st.executeUpdate();
       if(rs==1)
       {
          response.sendRedirect("signin.html");//if username and password correct then redirect page to dashboard
        }
	     else
       {	
        out.println("<h1>Error! Account is not Created Try Again.</h1>");
        out.println("<br/><br/><a href='signup.html'><button class='btn btn-primary ms-3'>Create Account</button></a>");
       }
       st.close();
      }
     
     st1.close();
     st2.close();
     con.close();
    }
    catch(Exception e)
    {
      out.println(e);
    }
    out.println("</body></html>");
}
}
