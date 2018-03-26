<%@page import="unswbook.SearchDatabase"%>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: luyibest001
  Date: 25/09/2017
  Time: 7:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile</title>
    <link type="text/css" rel="stylesheet" href="login_register.css">
    <link type="text/css" rel="stylesheet" href="unswbook.css">
</head>
<body>
<%String username1=(String)request.getParameter("username");
String userface1=(String)request.getAttribute("userface");
request.setAttribute("userface", userface1);
request.setAttribute("username", username1);
%>
<div class="ue-bar">

    <div class="ue-bar-warp">
        <div class="ue-bar-logo">
           <img src="unsw.png" width="100" alt="postwall" title="postwall">
            
    </div>

        <div class="ue-bar-nav">

            <ul>
                <li><a href="/unswbook/center?username=<%=username1 %>&&action=moreinfo">
                    <em>Homepage</em>
                </a></li>
                <li><a href="/unswbook/search.jsp?username=<%=username1 %>">
                    <em>Search</em>
                </a></li>
                <li><a href="/unswbook/center?username=<%=username1 %>&&action=notification">
                    <em>Message</em>
                    <span class="li a unread" id="count"></span>
                </a></li>
                <li><a href="/unswbook/login_register?username=<%=username1 %>&&action=profile">
                    <em>Profile</em>
                    <span class="li a unread" id="count"></span>
                </a></li>
                <li><a href="/unswbook/welcome.jsp" >
                    <em id="username"><%=username1 %></em>
                    <span class="li a unread" id="count"></span>
                </a></li>
   
            </ul>
        </div>
        <div  class="divcss5">
              <a href="/unswbook/center?action=selfinfo&&username=<%=username1 %>">
                    <img src="<%=userface1 %>" alt="Cinque Terre" width="50">
              </a>
        </div>
    </div>
</div>
<div class="form-style-8">
    <h2>Edit profile</h2>
    <%
        if(request.getAttribute("edit_error_info")!=null){
            out.println("<p style='color:red'>"+request.getAttribute("edit_error_info")+"</p>");
        }
    %>
    <form action="/unswbook/login_register" method="post">

        <%
        	
            out.println("Full name: <input type='text' name='edit_full_name'>");
        %>

        Password: <input type="password" name="edit_password"/>
        Email: <input type="email" name="edit_email">
        <input type="radio" name="edit_gender" value="male"> Male
        <input type="radio" name="edit_gender" value="female"> Female
        <br><br>
        Date of Birth
        <select name="edit_dob_year">
            <%
                Calendar now = Calendar.getInstance();   // Gets the current date and time
                int year = now.get(Calendar.YEAR);
                for(int i=1900;i<=year;i++){
                    out.println("<option>"+i+"</option>");
                }
            %>
        </select>
        <select name="edit_dob_month">
            <%
                for(int i=1;i<=12;i++){
                    out.println("<option>"+i+"</option>");
                }
            %>
        </select>
        <select name="edit_dob_day">
            <%
                for(int i=1;i<=31;i++){
                    out.println("<option>"+i+"</option>");
                }
            %>
        </select>
        <br><br>
        <label for="edit_photo">Upload profile photo</label>
        <input type="file" id="edit_photo" name="edit_profile_photo">
        <br><br>
        <input type="hidden" name="action" value="edit_profile">
        <input type="submit" value="Save change">
    </form>
</div>
</body>
</html>
