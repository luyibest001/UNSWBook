<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: nandong
  Date: 2017/9/26
  Time: 下午6:50
  To change this template use File | Settings | File Templates.
--%>
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
    <%if (request.getAttribute("added") != null){ %>
    <script>
        alert("<%=(String)request.getAttribute("added") %>");
    </script>
    <%} %>
    <title>search</title>
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

    </div>
</div>




<input type="hidden" name="name" value="#">
<input type="hidden" name="username" value=<%=request.getAttribute("username")%>>
<input type="hidden" name="dob" value="#">
<input type="hidden" name="gender" value="#">
<form method="post" action="register">
    <body style="background-color: #ddddd">
    <div class="form-style-8">

    <table>
        <tr>
            <td style="font-size:12pt;">
                Name:
            </td>
            <td>
                <input type="text" name ="name">
            </td>
        </tr>
        <br><br> <div style="font-size:12pt;">
        Age of your friend:
        <select name="register_dob_year" value="year">
            <%
                Calendar now = Calendar.getInstance();   // Gets the current date and time
                int year = now.get(Calendar.YEAR);
                out.println("<option>"+"year"+"</option>");
                for(int i=0;i<=100;i++){
                    out.println("<option>"+i+"</option>");
                }
            %>
        </select>
</div>
        <br><br>
        <tr >
            <input type="radio" name="gender" value="m" > Male
            <input type="radio" name="gender" value="f" > Female
        </tr>


    </table>
    <input type="hidden" name="username" value=<%=username1%>>
    <input type="hidden" name="action" value="search">
    <input type="submit" value="search">

</form>

</div>

</body>
</html>

