import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class add extends HttpServlet {
  
  //Process the HTTP Post request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.setContentType("text/html");
	// get PrintWriter object
	PrintWriter out = response.getWriter();

  //get data from addform
  String courses=request.getParameter("courses");
  String teachers=request.getParameter("teachers");
  String time=request.getParameter("time");
  String day=request.getParameter("day");

  
    out.println("<html>");
    out.println("<head><title>ADD COURSE</title><link rel='stylesheet' href='css/bootstrap.min.css'/><link rel='stylesheet' href='css/style.css'/></head>");
    out.println("<body id='img2'>");

    // clear cache prevent backbuuttton
    // response.setHeader("Cache-Control", "no-cache, no-store,must-revalidate");
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

        
    boolean check1 = true; 
    boolean check2 = true; 

    String query1 ="Select * from add_course  where (signup_id = ? AND name = ? AND day=? AND time=?)";
    PreparedStatement st1 = con.prepareStatement(query1);
    st1.setString(1, id);
    st1.setString(2, name);
    st1.setString(3, day);
    st1.setString(4, time);
    ResultSet rs1 = st1.executeQuery();
    if(rs1.next())
    {
           String d = rs1.getString("day");
           String t = rs1.getString("time");
           out.println("<h1  class=' display-5 text-center mt-3'>Slot :"+ t +","+d+" is already Reserved </h1>");
       
    check1 = false;
     
    }

    String query2 ="Select * from add_course  where (signup_id = ? AND name = ? AND courses=? )";
    PreparedStatement st2 = con.prepareStatement(query2);
    st2.setString(1, id);
    st2.setString(2, name);
    st2.setString(3, courses);
    ResultSet rs2 = st2.executeQuery();
     if(rs2.next())
     {
      String course = rs2.getString("courses");
      out.println("<h1  class=' display-5 text-center mt-3'>Course : "+course+" is already Enrolled </h1>");
       
      check2 = false;
     }
     
        if(check1 == true&& check2 == true)
      {
	
        String query = "INSERT INTO add_course(signup_id,name,courses,teachers,time,day)VALUES(?,?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, id);
        st.setString(2, name);
        st.setString(3, courses);
        st.setString(4, teachers);
        st.setString(5, time);
        st.setString(6, day);
        int rs = st.executeUpdate();
        // JOptionPane.showMessageDialog(null,"Student Details Inserted Successfully");
    
         if(rs==1)
         {	
          out.println("<h1 class='display-3 text-center'>COURSE Added Successfully</h1>");
          out.println("<div class='text-center mt-3'><a href='dashboard.jsp' class='btn btn-primary'>Back To Dashboard</a></div>");
         }
         else
         {	
          out.println("<h1 class='display-3 text-center'>Course Could not be added Successfully</h1>");
          out.println("<div class='text-center mt-3'><a href='add.jsp' class='btn btn-primary'>Try Again</a></div>");
         }
        st.close();
      } 

        if(check1 == true && check2 == true)
       {
    
          String query3 ="Select * from history  where (signup_id = ?  AND courses=?)";
          PreparedStatement st3 = con.prepareStatement(query3);
          st3.setString(1, id);
          st3.setString(2, courses);
          ResultSet rs3 = st3.executeQuery();
          if(rs3.next())
          {
            String query4="UPDATE history SET status='ENROLLED',teachers=? , time=? , day=? where courses=? AND signup_id=?";
            PreparedStatement st4 = con.prepareStatement(query4);
            st4.setString(1, teachers);
            st4.setString(2, time);
            st4.setString(3, day);
            st4.setString(4, courses);
            st4.setString(5, id);
            int rs4 = st4.executeUpdate();
            st4.close();
          }else
          {
            String query = "INSERT INTO history(signup_id,courses,teachers,time,day,status)VALUES(?,?,?,?,?,'ENROLLED')";
            PreparedStatement stHistory = con.prepareStatement(query);
            stHistory.setString(1, id);
            stHistory.setString(2, courses);
            stHistory.setString(3, teachers);
            stHistory.setString(4, time);
            stHistory.setString(5, day);
            int rs = stHistory.executeUpdate();  
            stHistory.close();
          }
          st3.close();
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
