<%@ page import="unswbook.*" %><%--
  Created by IntelliJ IDEA.
  User: luyibest001
  Date: 26/09/2017
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activated</title>
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
<div class="display_block">
<%
    if(request.getAttribute("activate_error")==null){
        out.println("<h3>your account has been activated successfully!\n You can now go back and log in to your account.</h3>");
    }else{
        out.print("<h3>");
        out.println(request.getAttribute("activate_error"));
        out.print("</h3>");
    }
%>
</div>

</body>
</html>
