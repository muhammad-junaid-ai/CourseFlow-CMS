function validation_courses()
{  
  var courses = document.getElementById('selectCourses').value;
  if (courses=="Open" )
  {  
    alert("Select an Option From COURSE Field");  
    return false;  
  }
    return true;
  }  
    
  
  
  function validation_signup()
    {  
        var fname=document.signup_form.firstname.value;  
        var lname=document.signup_form.lastname.value;  
        var uname=document.signup_form.username.value;  
        var age=document.signup_form.age.value;
        var email=document.signup_form.email.value; 
        var password=document.signup_form.password.value;
        var confirm=document.signup_form.confirm_password.value;
        var degree=document.getElementById('selectDegree').value;
   
    if (fname=="")
    {  
      alert("First Name can't be blank");  
      return false;  
    }
    
    if (lname=="")
    {  
      alert("Last Name can't be blank");  
      return false;  
    }  
    if (uname.length<4)
    {  
      alert("User Name must be 4 characters long");  
      return false;  
    }
    if (age=="")
    {  
      alert("Age can't be blank");  
      return false;  
    }
    if (age<=0)
    {  
      alert("Age Should be greater than 0");  
      return false;  
    }
    if (email=="")
    {  
      alert("Email can't be blank");  
      return false;  
    }
    else 
    {
      if(email.indexOf("@") == 0)
      {
        alert("Format Not Correct\n'@' can't occur at start");
      return false;
      }
      if (email.indexOf("@") == -1)
      {
        alert("Format Not Correct\n Email Should contain  '@'");
      return false;
      }
      if (email.lastIndexOf("@") != email.indexOf("@")) 
      {
        alert("Format Not Correct\n'@' can use atmost one time in an email");
      return false;
      }

      if (email.indexOf(".") == -1) 
      {
        alert("Format Not Correct\n Email Should contain at least one '.'");
      return false;
      }

      if (email.lastIndexOf(".") < email.indexOf("@")) 
      {
        alert("Format Not Correct\nFormat \"abc@gmail.com\"");
      return false;
      }

      if (email.lastIndexOf(".") + 1 == email.length)
      {
        alert("Format Not Correct\nEmail should not end with'.'");
      return false;
      }

      if(email[email.indexOf("@") + 1] == '.')
      {
        alert("Format Not Correct\nThere should be at least one character between '@' and '.' ");
      return false;
      }
    }


    if (degree=="Open")
    {  
      alert("Select an Option From DEGREE Field");  
      return false;  
    }
    if(password.length<6)
    {  
      alert("Password must be at least 6 characters long.");  
      return false;  
    }
    if (password !=confirm )
    {
      alert("Password Did not Match");
      return false;
    }
    return true;
    }  



    //SIGNIN VALIDATION

function validation_signin()
{  
  var uname=document.signin_form.name.value;  
  var password=document.signin_form.password.value;
 
  if (uname.length<4)
  {  
    alert("User Name must be 4 characters long");  
    return false;  
  }
  if(password.length<6)
  {  
    alert("Password must be at least 6 characters long.");  
    return false;  
   }
      return true;
}


////////
function validation_teacher()

    { 

        var name=document.getElementById('selectName').value;
        var uname=document.signup_form.username.value;  
        var age=document.signup_form.age.value;
        var email=document.signup_form.email.value; 
        var password=document.signup_form.password.value;
        var confirm=document.signup_form.confirm_password.value;
        // var name=document.getElementById('selectTeacher').value;
       
   
        if (uname.length<4)
          {  
            alert("User Name must be 4 characters long");  
            return false;  
          }
          if (age=="")
          {  
            alert("Age can't be blank");  
            return false;  
          }
          if (age<=0)
          {  
            alert("Age Should be greater than 0");  
            return false;  
          }

          if (email=="")
            {  
              alert("Email can't be blank");  
              return false;  
            }
            else 
            {
              if(email.indexOf("@") == 0)
              {
                alert("Format Not Correct\n'@' can't occur at start");
              return false;
              }
              if (email.indexOf("@") == -1)
              {
                alert("Format Not Correct\n Email Should contain  '@'");
              return false;
              }
              if (email.lastIndexOf("@") != email.indexOf("@")) 
              {
                alert("Format Not Correct\n'@' can use atmost one time in an email");
              return false;
              }
        
              if (email.indexOf(".") == -1) 
              {
                alert("Format Not Correct\n Email Should contain at least one '.'");
              return false;
              }
        
              if (email.lastIndexOf(".") < email.indexOf("@")) 
              {
                alert("Format Not Correct\nFormat \"abc@gmail.com\"");
              return false;
              }
        
              if (email.lastIndexOf(".") + 1 == email.length)
              {
                alert("Format Not Correct\nEmail should not end with'.'");
              return false;
              }
        
              if(email[email.indexOf("@") + 1] == '.')
              {
                alert("Format Not Correct\nThere should be at least one character between '@' and '.' ");
              return false;
              }
            }
        
      if (name=="Open")
        {  
          alert("Select an Option From NAME Field");  
          return false;  
        }
        if(password.length<6)
        {  
          alert("Password must be at least 6 characters long.");  
          return false;  
        }
        if (password !=confirm )
        {
          alert("Password Did not Match");
          return false;
        }
        return true;
        }  



// //SIGNIN VALIDATION

// function validation_signin()
// {  
//   var uname=document.signin_form.name.value;  
//   var password=document.signin_form.password.value;
 
//   if (uname.length<4)
//   {  
//     alert("User Name must be 4 characters long");  
//     return false;  
//   }
//   if(password.length<6)
//   {  
//     alert("Password must be at least 6 characters long.");  
//     return false;  
//    }
//       return true;
// }









////////




function validation_marks()
{ 
  var signup_id=document.marks_form.signup_id.value;   
  var oquiz=document.marks_form.oquiz.value;  
  var oassignment=document.marks_form.oassignment.value;
  var oproject=document.marks_form.oproject.value;
  var omid=document.marks_form.omid.value;
  var omid=document.marks_form.omid.value;

  if (signup_id=="")
  {  
    alert("Enter Roll Number");  
    return false;  
  }
  if (oquiz<0 && oquiz>5)
  {  
    alert("Enter Quiz Marks Between 0 and 5");  
    return false;  
  }
  if (oassignment<0 && oassignment>10)
  {  
    alert("Enter Assignment Marks Between 0 and 10");  
    return false;  
  }
  if (oproject<0 && oproject>10)
  {  
    alert("Enter Project Marks Between 0 and 10");  
    return false;  
  }
  if (omid<0 && omid>35)
  {  
    alert("Enter Mid Marks Between 0 and 35");  
    return false;  
  }
  if (ofinal<0 && ofinal>40)
  {  
    alert("Enter Final Marks Between 0 and 40");  
    return false;  
  }

  return true;
}

//forgot form
function validation_forgot()
{  
  var email=document.forgot_form.email.value;  
    
if (email=="")
{  
  alert("Email can't be blank");  
  return false;  
}
return true;
}

//Add form


function validation_add()
  {  
    var courses = document.getElementById('selectCourses').value;
    var teachers=document.getElementById('selectTeacher').value; 
    var time=document.getElementById('selectTime').value;
    var day=document.getElementById('selectDay').value;
   if (courses=="Open" )
   {  
     alert("Select an Option From COURSE Field");  
     return false;  
   }
   if (teachers=="Open")
   {  
     alert("Select an Option From Teacher Field");  
     return false;  
   }  
   if (time=="Open")
   {  
    alert("Select an Option From TIME Field");  
    return false;  
   }
   if (day=="Open")
   {  
    alert("Select an Option From DAY Field");  
    return false;  
   }
   return true;  
   }  


   //custom search form

   function validation_custom()
   {  

       var courses = document.getElementById('selectCourses').value;
        
         if (courses=="Open" )
         {  
           alert("Select an Option From COURSE Field");  
           return false;  
         }
         
     return true;

   }

   //drop form

   
   function validation_drop()
   {  

         var courses = document.getElementById('selectCourses').value;
        
         if (courses=="Open" )
         {  
           alert("Select an Option From COURSE Field");  
           return false;  
         }
         
     return true;

   }  

// update form
   function validation_update()
   {  

         var update = document.getElementById('selectUpdate').value;
         var courses = document.getElementById('selectCourses').value;
         var teachers = document.getElementById('selectTeacher').value;
         var day = document.getElementById('selectDay').value;
         var time = document.getElementById('selectTime').value;
         if (update=="Open" )
         {  
           alert("Select an Option From Update COURSE Field");  
           return false;  
         }
        
         if (courses=="Open" )
         {  
           alert("Select an Option From COURSE Field");  
           return false;  
         }
         if (teachers=="Open" )
         {  
           alert("Select an Option From TEACHERS Field");  
           return false;  
         }
         if (day=="Open" )
         {  
           alert("Select an Option From DAY Field");  
           return false;  
         }
         if (time=="Open" )
         {  
           alert("Select an Option From TIME Field");  
           return false;  
         }
         
     return true;

   }  