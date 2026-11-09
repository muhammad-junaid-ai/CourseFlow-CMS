import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class saveMarks extends HttpServlet {
  
  //Process the HTTP Post request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.setContentType("text/html");
	// get PrintWriter object
	PrintWriter out = response.getWriter();

  //get data from addform
  String courses=request.getParameter("courses");
  String signup_id=request.getParameter("signup_id");//rooll
  String oquiz=request.getParameter("oquiz");
  String oassignment=request.getParameter("oassignment");
  String oproject=request.getParameter("oproject");
  String omid=request.getParameter("omid");
  String ofinal=request.getParameter("ofinal");
  

  
   
  out.println("<html>");
  out.println("<head><title>ADD MARKS</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
  out.println("<body id='img2'>");
   

    // clear cache prevent backbuuttton
    // response.setHeader("Cache-Control", "no-cache, no-store,must-revalidate");

    HttpSession session=request.getSession(false);  

    String status = (session != null) ? (String) session.getAttribute("session_status") : null;

    if   ((session == null || status == null || !status.equals("teacher")))  
    // if(session == null)
    {
      out.println("<h1 class='text-center mt-3'>YOU ARE NOT SIGNED IN/concerned person OR YOUR SESSION HAS BEEN EXPIRED</h1>");
      out.println("<BR/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>SIGN IN</button></a>");    
    }else
{
       RequestDispatcher rd = request.getRequestDispatcher("/teacher_header.html");
       rd.include(request,response);
   
  try
  {
    Connection con = DBConnection.getConnection();

    String name=(String)session.getAttribute("session_name");  //username
    String id=(String)session.getAttribute("session_id");//teacher id
    String teachers=(String)session.getAttribute("session_teachers");  	//teacchers name 

     boolean check1=true;
    //  boolean check2=true;
     boolean check3=true;

    String query1 ="Select * from add_course where courses=? AND teachers=? AND signup_id=?";
    PreparedStatement st1 = con.prepareStatement(query1);
    st1.setString(1, courses);
    st1.setString(2, teachers);
    st1.setString(3, signup_id);
    ResultSet rs1 = st1.executeQuery();
  
   if(!(rs1.next()))
   {  
    out.println("<h1 class='text-center mt-3'>None of the students has enrolled in "+courses+" Yet</h1><br/>");
    check1=false;
      }

 

    String query3 ="Select * from marks where signup_id=? AND courses=? AND teachers = ? ";
    PreparedStatement st3 = con.prepareStatement(query3);
    st3.setString(1, signup_id);
    st3.setString(2, courses);
    st3.setString(3, teachers);
    ResultSet rs3 = st3.executeQuery();

    if(rs3.next())
    { 
     out.println("<h1  class='text-center mt-3'>Roll number: "+signup_id+" has already got marks <br/> You can Update his marks.</h1>");
     out.println("<a style='padding-left:40px' href='updateMarks.jsp'><button  class='btn btn-primary'>Update Marks</button></a>");
     check3=false;
    }
 
    if(check1==true  && check3==true)
    {
      String query = "INSERT INTO marks(signup_id,oquiz,oassignment,oproject,omid,ofinal,courses,teachers)VALUES(?,?,?,?,?,?,?,?)";
      PreparedStatement st = con.prepareStatement(query);
      st.setString(1, signup_id);
      st.setString(2, oquiz);
      st.setString(3, oassignment);
      st.setString(4, oproject);
      st.setString(5, omid);
      st.setString(6, ofinal);
      st.setString(7, courses);
      st.setString(8, teachers);
      int rs = st.executeUpdate();  
      if(rs==1)
      {    
      out.println("<h1  class='text-center mt-3'>Roll number: "+signup_id+" marks added succesfully  </h1>");
      out.println("<div class='text-center mt-3'><a href='teacher_dashboard.jsp' class='btn btn-primary'>Back To Dashboard</a></div>");
      }  
      st.close();
    }   
     st1.close();
     st3.close();
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
