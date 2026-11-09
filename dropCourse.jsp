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
<html>
    <head>
        <title>DROP COURSE</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link href="css/style.css" rel="stylesheet" >
        <script type="text/javascript" src="script/script.js" ></script>
    </head>
    <body id="img3" style="color: whitesmoke;"> 

        <%@ include file="/teacher_header.html" %>
        <h1 class="display-1 text-center">DROP Course</h1>
        <div class="container">
            <form method="post" action="dropCourse" name="drop_form" onsubmit="return validation_drop()">
                 
                <label class="display-6 mb-3" >Select Course</label>
                    <select name="courses" class="form-select form-select-lg mb-3" id="selectCourses">
                        <option selected value="Open">Open menu</option>
                        <option value="DBA">Data Base Administration</option>
                        <option value="OOP">Object Oriented Programming</option>
                        <option value="DSA">Data Structure Algorithms</option>
                        <option value="COAL">Computer Organization & Assembly Language</option>
                        <option value="ITC">Introduction To Computing</option>
                        <option value="PF">Programming Fundamentals</option>
                    </select>
                
                <div class="mb-3">
                    <label class="display-5">Enter Roll Number</label>
                    <input type="nb" name="signup_id" class="form-control" placeholder="Enter Roll Number">
                </div>
                    
                <button class="btn btn-primary" type="submit" value="Delete">DROP COURSE</button>
            </form>
        </div>
        
    </body>
</html>