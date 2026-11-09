import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class updateMarks extends HttpServlet {
  
  //Process the HTTP Post request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.setContentType("text/html");
	// get PrintWriter object
	PrintWriter out = response.getWriter();

  //get data from addform
  String courses=request.getParameter("courses");
  String signup_id=request.getParameter("signup_id");
  String oquiz=request.getParameter("oquiz");
  String oassignment=request.getParameter("oassignment");
  String oproject=request.getParameter("oproject");
  String omid=request.getParameter("omid");
  String ofinal=request.getParameter("ofinal");
  

  
    
  out.println("<html>");
  out.println("<head><title>UPDATE MARKS</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
  out.println("<body id='img2'>");
   

    // clear cache prevent backbuuttton
    // response.setHeader("Cache-Control", "no-cache, no-store,must-revalidate");

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

     boolean check1=true;
     boolean check2=true;
  
    String query1="Select * from marks where signup_id=? AND  courses=? ";
    PreparedStatement st1 = con.prepareStatement(query1);
    st1.setString(1, signup_id);
    st1.setString(2, courses);
    ResultSet rs1 = st1.executeQuery();

    if(!(rs1.next()))
    { 
     out.println("<h1  class='text-center mt-3'>Roll number: "+signup_id+" has not got marks yet.<br>First Add marks then you can update.</h1>");
     check1=false;
    }

    
    String query2="Select * from add_course where courses=? AND signup_id=? AND teachers =?";
    PreparedStatement st2 = con.prepareStatement(query2);
    st2.setString(1, courses);
    st2.setString(2, signup_id);
    st2.setString(3, teachers);
    ResultSet rs2 = st2.executeQuery();

    if(!(rs2.next()))
    { 
     out.println("<h1  class='text-center mt-3'>Roll number: "+signup_id+" has not enrolled in "+courses+" yet</h1>");
     check2=false;
    }


  if(check1==true && check2==true )
    {
      String query = "UPDATE marks SET oquiz=?, oassignment=? , oproject=?, omid=?, ofinal=? where courses=? AND signup_id=? AND  teachers =?";
      PreparedStatement st = con.prepareStatement(query);
      st.setString(1, oquiz);
      st.setString(2, oassignment);
      st.setString(3, oproject);
      st.setString(4, omid);
      st.setString(5, ofinal);
      st.setString(6, courses);
      st.setString(7, signup_id);
      st.setString(8, teachers);
      int rs = st.executeUpdate();    
      if(rs==1)
      {    
      out.println("<h1  class='text-center mt-3'>Roll number: "+signup_id+" marks updated succesfully  </h1>");
      out.println("<div class='text-center mt-3'><a href='teacher_dashboard.jsp' class='btn btn-primary'>Back To Dashboard</a></div>");
      }  
      else{
        out.println("<h1  class='text-center mt-3'>Roll number: "+signup_id+" marks could not be updated </h1>");
        out.println("<div class='text-center mt-3'><a href='updateMarks.jsp' class='btn btn-primary'>Try Again</a></div>");
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
}//else closed
out.println("</body></html>");
}
}
