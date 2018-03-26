<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: luyibest001
  Date: 24/09/2017
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link type="text/css" rel="stylesheet" href="login_register.css">
</head>
<body style="background-color: #dddddd">

<div class="ue-bar">


    <div class="ue-bar-warp">
        <div class="ue-bar-logo">
           <img src="unsw.png" width="100" alt="postwall" title="postwall">
            
    </div>

        <div class="ue-bar-nav">

        </div>
    </div>
</div>

<div class="form-style-8">
    <h2>Join UNSWBook today</h2>
    <%
        if(request.getAttribute("register_error_info")!=null){
            out.println("<p style='color:red'>"+request.getAttribute("register_error_info")+"</p>");
        }
    %>
    <form action="center" method="post">
        <input type="text" name="register_full_name" placeholder="Full Name">
        <input type="text" name="register_username" placeholder="Username" />
        <input type="password" name="register_password" placeholder="Password" />
        <input type="email" name="register_email" placeholder="Your UNSW Zmail">
        <input type="radio" name="register_gender" value="male"> Male
        <input type="radio" name="register_gender" value="female"> Female
        <br><br>
        Date of Birth
        <select name="register_dob_year">
            <%
                Calendar now = Calendar.getInstance();   // Gets the current date and time
                int year = now.get(Calendar.YEAR);
                for(int i=1900;i<=year;i++){
                    out.println("<option>"+i+"</option>");
                }
            %>
        </select>
        <select name="register_dob_month">
            <%
                for(int i=1;i<=12;i++){
                    out.println("<option>"+i+"</option>");
                }
            %>
        </select>
        <select name="register_dob_day">
            <%
                for(int i=1;i<=31;i++){
                    out.println("<option>"+i+"</option>");
                }
            %>
        </select>
        <br><br>
        <label for="register_photo">Upload profile photo</label>
        <input type="file" id="register_photo" name="register_profile_photo">
        <br><br>
        <input type="hidden" name="action" value="register">
        <input type="submit" value="Sign up">
        <br><br>
        <a href="/unswbook/welcome.jsp" class="commonHyperLink">have an account?</a>
    </form>
</div>
</body>
</html>
