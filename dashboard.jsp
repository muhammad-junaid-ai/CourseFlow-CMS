<%@ page language="java" %>
<%  

    String status = (String) session.getAttribute("session_status");
    String id = (String) session.getAttribute("session_id");   

    if   ((status == null || !status.equals("student")) || id==null) 
    {
    response.sendRedirect("home.html");
        return;
    }
    
%>


<!-- <%@ page language="java" %>
<%
    
    if (session.getAttribute("session_id")==null) {
        response.sendRedirect("home.html");
        return;
    }
%> -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DASHBOARD</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" >
        <link href="css/style.css" rel="stylesheet" >
    </head>
  <body id="img2">
   
    <div style="background-color:black; height: 50px; padding-right: 10px; ">
        <a href="signout">
            <button class="btn btn-danger float-end mt-2">SIGN OUT</button>
        </a>
        <h1 style="color: white; padding-left: 70px;" class="text-center">COURSE MANAGEMENT SYSTEM</h1>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-6 mt-5">     
            <h3>ADD COURSES</h3><br/>
            <p>Enroll In to Available Courses By clicking on Add Button</p>
            <a href="add.jsp">
            <button class="btn btn-primary ">ADD COURSES</button>
            </a>
        </div>
        
        <div class="col-6 mt-5">
            <h3>VIEW COURSES</h3></br>
            <p> View Enrolled Courses By clicking on View Button</p>
            <a href="view">
            <button class="btn btn-primary ">VIEW COURSES</button>
            </a>
        </div>
        
        <div class="col-6 mt-5">
            <h3>UPDATE COURSES</h3></br>
            <p> Update Already Enrolled Courses By clicking on Update Button</p>
            <a href="update.jsp">
            <button class="btn btn-primary ">UPDATE COURSES</button>
            </a>
        </div>

        <div class="col-6 mt-5">
            <h3>DROP COURSES</h3></br>
            <p> Drop Already Enrolled Courses By clicking on Delete Button</p>
            <a href="drop.jsp">
            <button class="btn btn-primary ">DROP COURSES</button>
            </a>
        </div>
        
        <div class="col-6 mt-5">
            <h3>CUSTOM SEARCH</h3></br>
            <p> SEARCH ANY COURSE By COURSE NAME</p>
            <a href="custom.jsp">
            <button class="btn btn-primary ">CUSTOM SEARCH</button>
            </a>
        </div>

        <div class="col-6 mt-5">
            <h3>HISTORY</h3></br>
        <p>Check history of all courses taken by clicking on History button</p>
            <a href="history">
            <button class="btn btn-primary ">HISTORY OF COURSES</button>
            </a>
        </div>
    
        <div class="col-6 mt-5">
            <h3>View Marks</h3></br>
            <p> View Your Result</p>
            <a href="viewMarks">
            <button class="btn btn-primary ">VIEW MARKS</button>
            </a>
        </div>

      </div>
    </div>
  </body>
</html>