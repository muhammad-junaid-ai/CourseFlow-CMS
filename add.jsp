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

<html>
    <head>
        <title>ADD COURSE</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/style.css" />
        
        <script type="text/javascript" src="script/script.js" ></script>

    </head>
<body id="img3" style="color:whitesmoke">

    <%@ include file="/header.html" %>
     <h1 class="display-1 text-center">Add Courses</h1>
        <div class="container">
            <form  method="post" action="add" name="add_form" onsubmit="return validation_add()">
                <div class="row">
                    <div class="col-md-6" >  
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
                    </div>
                   
                    <div class="col-md-6">  
                     <label class="display-6 mb-3">Select Teacher</label>
                                <select name="teachers" class="form-select form-select-lg mb-3" id="selectTeacher">
                                    <option selected value="Open">Open menu</option>
                                    <option value="Hassan Khan">Hassan Khan</option>
                                    <option value="Umair Babar">Umair Babar</option>
                                    <option value="Esha Aftab">Esha Aftab</option>
                                    <option value="Khurram Shehzad">Khurram Shehzad</option>
                                </select>
                    </div>
                
                    <div class="col-md-6">  
                        <label class="display-6 mb-3">Select Time Slot </label>
                                    <select name="time" class="form-select form-select-lg mb-3" id="selectTime">
                                        <option selected value="Open">Open menu</option>
                                        <option value="09:00-10:25">09:00-10:25</option>
                                        <option value="10:25-11:50">10:25-11:50</option>
                                        <option value="12:45-02:10">12:45-02:10</option>
                                        <option value="02:10-03:35">02:10-03:35</option>
                                        <option value="03:35-05:00">03:35-05:00</option>
                                    </select>
                    </div>
                
                    <div class="col-md-6">  
                            <label class="display-6 mb-3">Select Day</label>
                                        <select name="day" class="form-select form-select-lg mb-3" id="selectDay">
                                            <option selected value="Open">Open menu</option>
                                            <option value="MONDAY">MONDAY</option>
                                            <option value="TUESDAY">TUESDAY</option>
                                            <option value="WEDNESDAY">WEDNESDAY</option>
                                            <option value="THURSDAY">THURSDAY</option>
                                        </select>
                    </div>       
                    
                    <button class="btn btn-primary" type="submit" value="Add">ADD COURSE</button>
                        
                </div>
               
            </form>
 
        </div>
    </body>
</html>