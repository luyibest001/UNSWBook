<%--
  Created by IntelliJ IDEA.
  User: nandong
  Date: 2017/9/26
  Time: 下午8:29
  To change this template use File | Settings | File Templates.
--%>
<%@page import="unswbook.profile"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="unswbook.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  setInterval(function(){
	    $.post("/unswbook/msgfunc",
	    {
	      username:$("#username").text(),
	      iiii:"Duckburg"
	    },
	    function(data,status){
	    	if (data=="0"){
	    		$("#count").text('');
	    	}else{
	 
	    		$("#count").text(data);}
	    });
	  },5000);
	  
	  $("#moreinf").click(function(){
		    $("#div11").load("moreinf.jsp");
		    $("#moreinf").hide();
		  });
	});

</script>
    <title>result</title>
    <link rel="stylesheet" type="text/css" href="login_register.css" />
</head>

<body>
<%String username1=(String)request.getParameter("username");
if (username1==null||username1.equals("null")){
	username1=(String)request.getAttribute("username");
}
%>
<div class="ue-bar">


    <div class="ue-bar-warp">
        <div class="ue-bar-logo">
           <img src="unsw.png" width="100" alt="postwall" title="postwall">
            
    </div>

        <div class="ue-bar-nav">

            <ul>
                <li><a href="/unswbook/center?username=${username}&&action=moreinfo">
                    <em>Homepage</em>
                </a></li>
                <li><a href="/unswbook/search.jsp?username=${username}">
                    <em>Search</em>
                </a></li>
                <li><a href="/unswbook/center?username=${username}&&action=notification">
                    <em>Message</em>
                    <span class="li a unread" id="count"></span>
                </a></li>
                <li><a href="/unswbook/welcome.jsp" >
                    <em id="username">${username }</em>
                    <span class="li a unread" id="count"></span>
                </a></li>
   
            </ul>
        </div>

    </div>
</div>

<body style="background-color: #ddddd">
<div class="form-style-8">




    <%

        List<profile> profiles = (List<profile>)request.getAttribute("result");
        String user=(String)request.getParameter("username");
    %>
    <table width="100%" border="1" cellspacing="0">
        <tr>
            <td>Name</td>
            <td>Birth of date</td>
            <td>Gender</td>
            <td>Add friend</td>
        </tr>

        <%for (profile p : profiles) { %>


        <tr>
            <td>
                <%=p.getName()%>
            </td>
            <td>
                <%=p.getDob()%>
            </td>
            <td>
                <%=p.getGender()%>
            </td>
            <td>
                <input type="button" value="add friend" onClick="window.location.href='register?action=sendreq&username=<%=user%>&friname=<%=p.getUsername()%>'">
            </td>
        </tr>



        <%}%>
    </table>
    <%if (profiles.size()==0){ %>
    <br>
    no result!
    </br>
    <%} %>
    <form method="post" action="register">
        <input type="hidden" name="username" value=<%=request.getAttribute("username")%>><input type="hidden" name="action" value="gohome">
        <input type="submit" value="gohome">

    </form>

</div>



</body>
</html>
