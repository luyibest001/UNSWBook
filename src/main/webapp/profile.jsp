<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="unswbook.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Profile</title>
    <link type="text/css" rel="stylesheet" href="login_register.css">
    <link type="text/css" rel="stylesheet" href="unswbook.css">
</head>
<body style="background-color: #dddddd">
<%String username1=(String)request.getAttribute("username");
String userface1=(String)request.getAttribute("userface");
request.setAttribute("userface", userface1);
request.setAttribute("username", username1);
%>
<%=username1 %>
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
<div class="display_block">
    <% 
    	User user = (User)request.getAttribute("currentUser");
    	out.println("<h2>Username: "+user.getUsername()+"</h2><br><br>"); 
		out.println("<h2>Full name: "+user.getFullname()+"</h2><br><br>");   
		out.println("<h2>Email: "+user.getEmail()+"</h2><br><br>"); 
		out.println("<h2>Geder: "+user.getGender()+"</h2><br><br>"); 
		out.println("<h2>DoB: "+user.getDob()+"</h2>"); 
	%>
	<br><br>
    
    <a href="/unswbook/edit_profile.jsp?username=<%=username1 %>" class="button_sign_up">Edit Profile</a>
</div>
</body>
</html>