import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class update extends HttpServlet {

   //Process the HTTP POST request
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
     
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
 
     String courses=request.getParameter("courses");
     String newcourses=request.getParameter("newcourses");
     String newteachers=request.getParameter("newteachers");
     String newtime=request.getParameter("newtime");     
     String newday=request.getParameter("newday");
   
   
     out.println("<html>");
     out.println("<head><title>UPDATE COURSES</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
     out.println("<body id='img2'>");

    HttpSession session=request.getSession(false);  

    String status = (session != null) ? (String) session.getAttribute("session_status") : null;

    if   ((session == null || status == null || !status.equals("student"))) 
      // if(session == null)
     {
      out.println("<h1 class='text-center mt-3'>YOU ARE NOT SIGNED IN/Concerned Person OR YOUR SESSION HAS BEEN EXPIRED</h1>");
      out.println("<BR/><br/><a href='signin.html'><button class='btn btn-primary ms-3'>SIGN IN</button></a>");    
     }
     else
    {
      RequestDispatcher rd = request.getRequestDispatcher("/header.html");
      rd.include(request,response);
      
  try
  {
     Connection con = DBConnection.getConnection();
  
     String id=(String)session.getAttribute("session_id");  	  

     boolean check1 = true; 
     boolean check2 = true; 

     String query1 ="Select * from add_course  where (signup_id = ? AND courses=?)";
     PreparedStatement st1 = con.prepareStatement(query1);
     st1.setString(1, id);
     st1.setString(2, courses);
     ResultSet rs1 = st1.executeQuery();
     if(rs1.next())
     {

        String query2 ="Select * from add_course  where (signup_id = ? AND courses=?)";
        PreparedStatement st2 = con.prepareStatement(query2);
        st2.setString(1, id);
        st2.setString(2, newcourses);
        ResultSet rs2 = st2.executeQuery();
   
        if(rs2.next())
        {
           String course = rs2.getString("courses");
           out.println("<h1  class=' display-5 text-center mt-3'>Course : "+course+" is already Enrolled </h1>");
           check1 = false;
         }
   
        String query3 ="Select * from add_course  where (signup_id = ? AND time = ? AND day=? )";
        PreparedStatement st3 = con.prepareStatement(query3);
        st3.setString(1, id);
        st3.setString(2, newtime);
        st3.setString(3, newday);
        ResultSet rs3 = st3.executeQuery();
         
         if(rs3.next())
           {
           String d = rs3.getString("day");
           String t = rs3.getString("time");
           out.println("<h1  class=' display-5 text-center mt-3'>Slot :"+ t +","+d+" is already Reserved </h1>");   
           check2 = false;
            }

         if(check1 == true && check2 == true)
         {
           String query4="UPDATE add_course SET courses=? , teachers=? , time=? , day=? where courses=? AND signup_id=?";
           PreparedStatement st4 = con.prepareStatement(query4);
           st4.setString(1, newcourses);
           st4.setString(2, newteachers);
           st4.setString(3, newtime);
           st4.setString(4, newday);
           st4.setString(5, courses);
           st4.setString(6, id);
           int rs = st4.executeUpdate();  
          if(rs==1)
          {
             out.println("<h1 class='text-center'>Course Updated Successfully</h1>"); 
             out.println("<div class='text-center mt-3'><a href='dashboard.jsp' class='btn btn-primary'>Back To Dashboard</a></div>");
           }
          else 
          {
             out.println("<h1 class='text-center'>Course not updated Successfully</h1>"); 
             out.println("<div class='text-center mt-3'><a href='update.jsp' class='btn btn-primary'>Try Again</a></div>");
          }
          st4.close();
         }
        
         if(check1 == true && check2 == true)
         {
          String query5 ="Select * from history  where (signup_id = ? AND courses=?)";
          PreparedStatement st5 = con.prepareStatement(query5);
          st5.setString(1, id);
          st5.setString(2, newcourses);
          ResultSet rs5 = st5.executeQuery();
           
           if(rs5.next())
           {
            String query6="UPDATE history SET status='ENROLLED', teachers=? , time=? , day=? where courses=? AND signup_id=?";
            PreparedStatement st6 = con.prepareStatement(query6);
            st6.setString(1, newteachers);
            st6.setString(2, newtime);
            st6.setString(3, newday);
            st6.setString(4, newcourses);
            st6.setString(5, id);
            int rs6 = st6.executeUpdate();   
            st6.close();
           }
           else
           {
            String query7 = "INSERT INTO history(signup_id,courses,teachers,time,day,status)VALUES(?,?,?,?,?,'ENROLLED')";
            PreparedStatement st7 = con.prepareStatement(query7);
            st7.setString(1, id);
            st7.setString(2, newcourses);
            st7.setString(3, newteachers);
            st7.setString(4, newtime);
            st7.setString(5, newday);
            int rs7 = st7.executeUpdate();  
            st7.close();
           }
           st5.close();
           
           String query8="UPDATE history SET status='DROPPED-UPDATED' where courses=? AND signup_id=?";
           PreparedStatement st8 = con.prepareStatement(query8);
           st8.setString(1, courses);
           st8.setString(2, id);
           int rs8 = st8.executeUpdate();
           st8.close();
         }         
        st2.close();
        st3.close();
      }
      else
      {
       out.println("<h1 class='text-center'>Course Doesn't Exist</h1>");
      }
   
      st1.close();
      con.close();
    
    } catch(Exception e)
   {
      out.println(e);
   }

  }//ELSE END

  out.println("</body></html>");
}
}
