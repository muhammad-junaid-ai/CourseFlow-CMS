<%@ page language="java" %>
<%  

    String status = (String) session.getAttribute("session_status");
    String id = (String) session.getAttribute("session_id");   

    if   ((status == null || !status.equals("teacher")) || id==null) 
    {
    response.sendRedirect("home.html");
        return;
    }
    
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>UPDATE MARKS</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" >
        <link href="css/style.css" rel="stylesheet" >
        <script type="text/javascript" src="script/script.js" ></script>
    </head> 
 
    <body id="img1" style="color: whitesmoke;">  
        
        <%@ include file="/teacher_header.html" %>
     
      <div class="container">
        <div class="row">
          <div class="col-md-6 offset-md-3">
            <form name="marks_form" method="post" action="updateMarks"  class="mt-2 p-4 bg-dark shadow" onsubmit="return validation_marks()" >
            <h4 class="mb-5 text-center">Update Marks</h4>
         
              <div> 
                <label class="display-6 mb-3" >Select Course</label> 
                <select name="courses" class="form-select form-select-lg mb-3" >
                    <option selected value="Open">Open menu</option>
                    <option value="DBA">Data Base Administration</option>
                    <option value="OOP">Object Oriented Programming</option>
                    <option value="DSA">Data Structure Algorithms</option>
                    <option value="COAL">Computer Organization & Assembly Language</option>
                    <option value="ITC">Introduction To Computing</option>
                    <option value="PF">Programming Fundamentals</option>
                </select>
              </div>
              
              <div class="mb-3 ">
                <label>Enter Roll Number</label>
                <input type="nb" name="signup_id" class="form-control" placeholder="Enter Roll Number">
              </div>
              
              <div class="mb-3" >
                <label>Quiz Obtained Marks</label>
                <input type="text" name="oquiz" class="form-control" placeholder="Enter Marks between 0 and 5">
              </div>

              <div class="mb-3" >
                <label>Assignment Obtained Marks</label>
                <input type="text" name="oassignment" class="form-control" placeholder="Enter Marks between 0 and 10">
              </div>

              <div class="mb-3" >
                <label>Project Obtained Marks</label>
                <input type="text" name="oproject" class="form-control" placeholder="Enter Marks between 0 and 10">
              </div>

              <div class="mb-3" >
                <label>Mid Obtained Marks</label>
                <input type="text" name="omid" class="form-control" placeholder="Enter Marks between 0 and 35">
              </div>

              <div class="mb-3" >
                <label>Final Obtained Marks</label>
                <input type="text" name="ofinal" class="form-control" placeholder="Enter Marks between 0 and 40">
              </div>

              <a href="updateMarks">
                <button class="btn btn-primary" type="submit" value="Add">Submit</button>
              </a>
            </form>
          </div>
        </div>
      </div>

    </body>
</html>