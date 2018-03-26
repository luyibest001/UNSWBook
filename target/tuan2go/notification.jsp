<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="unswbook.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  setInterval(function(){
	    $.post("/unswbook/msgfunc",
	    {
	      username:$("#username").text(),
	      city:"Duckburg"
	    },
	    function(data,status){
	     	if (data=="0"){
	    		$("#count").text('');
	    	}else{
	 
	    		$("#count").text(data);}
	    });
	  },5000);
	});

</script>
</head>

<body>

<%String username1=(String)request.getAttribute("username");
String userface1=(String)request.getAttribute("userface");
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


<center>
<div style="margin-top: 40px;">
<h1 style=" font-size:17px;" >Message</h1>
<table class="ss" width=600px >
<c:forEach var="noti" items="${notifications}">
<tr><td style="color: white; background-color:#D0D3D4; font-size:13px;box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
${noti.content }
</td></tr>
<tr><td height=4px></td></tr>
</c:forEach>

</table>
</div>
</center>
</body>
</html>