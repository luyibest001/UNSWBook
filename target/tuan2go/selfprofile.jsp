<%@page import="com.mysql.jdbc.RowData"%>
<%@page import="unswbook.PostTableBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="unswbook.*, java.util.*, java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="unswbook.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
<script type="text/javascript">
function send(name,job,countnum,likestate,use) {
	

	if (name=="dolike"){
		fan="dounlike";
	}
	else{
		fan="dolike";
	}
    var xmlHttp;
    xmlHttp= new XMLHttpRequest();
    xmlHttp.open("POST", "/unswbook/center", true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send("action=" + name+"&&likepostid=" + job+"&&username="+use);
    xmlHttp.onreadystatechange = function() {
        if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            if(xmlHttp.responseText == "true") {
      
            	if (likestate=="true"){
            		var num=parseInt(countnum);
            	}else{
            		var num=parseInt(countnum)+1;
            	}
            
            	document.getElementById(name+job).style.color="black";
            	document.getElementById(fan+job).disabled="disabled";
            	document.getElementById("count"+name+job).innerHTML=num;
            } else {
           
            	if (likestate=="true"){
            		var num=parseInt(countnum)-1;
            	}else{
            		var num=parseInt(countnum);
            	}
         
            	document.getElementById(name+job).style.color="white";
            	document.getElementById(fan+job).disabled="";
            	document.getElementById("count"+name+job).innerHTML=num;

            }
        }
    };
}
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
		    $("#div11").load("moreselfinf.jsp");
		    $("#moreinf").hide();
		  });
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

<div class="block">
  <div class="block-nn">

<form action="center" method="post">
<center>
<textarea class="kuang"  name="inputtext" style="box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);"></textarea><br>

<input type="hidden" name="action" value = "posttext"/>
<ul><li>
<a href="javascript:;" class="a-upload">

<INPUT TYPE="file" NAME="inputpic" value="pic" />Select Image<BR>
</a></li><li>
<button class="btn"  name="postbutton" type="submit">Post</button>
</li>
</ul>
</center>
</form>

</div>
</div>
<CENTER>
<div style="margin-top: 50px;">
  <table class="ss">

<c:choose>
<c:when test="${posts.size()>4 }">
   	<c:forEach begin="0" end="4"  var="i">
   		<c:set var = "rowss" value ="${posts[i]}"/>
   		
  
   	<tr>
   	<td rowspan="6" width="70px">
   	<img src="${rowss.facepath }" alt="Cinque Terre" width="50">
   	
    </td>
   		<td colspan="2" width="520px" style="background-color:#F0F3F4; font-size:14px;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
   	${rowss.username}
   		</td>
   		</tr>
   		<tr>
   		<td colspan="2" style="background-color:#F0F3F4; font-size:8px;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
   	${rowss.time}
   		</td>
   		</tr>
   		<c:if test="${not empty rowss.content }">
   		<tr>
   		   		<td colspan="2" style="color: white; background-color:#D0D3D4; font-size:13px;box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
   	${rowss.content}
   		</td>
   	</tr>
   	</c:if>
   	<c:if test="${not empty rowss.picture }">
   	<tr><td colspan="2" style=" box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);" class="imagess">
   	<IMG  src="${rowss.picturepath}" >
   	</td>
   	</tr>
   	</c:if>	
   	   		<c:choose>
   	<c:when test="${rowss.islike=='true' }">
   	<tr><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dolike${rowss.id}" class="btnnn" onclick="send('dolike','${rowss.id}','${rowss.nlike }','${rowss.islike }','<%=username1 %>')" value="Like" style="color: black;"/><span id="countdolike${rowss.id }" class="dianzan">${rowss.nlike }</span>
    </form></td><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dounlike${rowss.id}" class="btnnn" onclick="send('dounlike','${rowss.id}','${rowss.nunlike }','${rowss.isunlike }','<%=username1 %>')" value="Unlike" disabled="disabled" style="color: white;"/><span id="countdounlike${rowss.id }" class="dianzan">${rowss.nunlike }</span>
    </form></td>
   	</tr>
   	</c:when>
   	<c:when test="${rowss.isunlike=='true' }">
   	   	<tr><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dolike${rowss.id}" class="btnnn" onclick="send('dolike','${rowss.id}','${rowss.nlike }','${rowss.islike }','<%=username1 %>')" value="Like" disabled="disabled" style="color: white;"/><span id="countdolike${rowss.id }" class="dianzan">${rowss.nlike }</span>
    </form></td><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dounlike${rowss.id}" class="btnnn" onclick="send('dounlike','${rowss.id}','${rowss.nunlike }','${rowss.isunlike }','<%=username1 %>')" value="Unlike" style="color: black;"/><span id="countdounlike${rowss.id }" class="dianzan">${rowss.nunlike }</span>
    </form></td>
   	</tr>
   	</c:when>
     <c:otherwise>
        	<tr><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dolike${rowss.id}" class="btnnn" onclick="send('dolike','${rowss.id}','${rowss.nlike }','${rowss.islike }','<%=username1 %>')" value="Like" style="color: white;"/><span id="countdolike${rowss.id }" class="dianzan">${rowss.nlike }</span>
    </form></td><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dounlike${rowss.id}" class="btnnn" onclick="send('dounlike','${rowss.id}','${rowss.nunlike }','${rowss.isunlike }','<%=username1 %>')" value="Unlike" style="color: white;"/><span id="countdounlike${rowss.id }" class="dianzan">${rowss.nunlike }</span>
    </form></td>
   	</tr>
   	     </c:otherwise>
</c:choose>
   	<tr><td style="color: white;background-color:#D0D3D4; font-size:9px; box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">${rowss.likes }
   	
   	</td>
   	<td style="background-color:#D0D3D4; font-size:9px; box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);"></td>
   	 </tr>
   	    	<tr><td height=20px>
   	</td>
   	 </tr>
   	    	<tr><td>
   	</td>
   	 </tr>
     </c:forEach>
     </c:when>
     <c:otherwise>
     <c:forEach var="rowss" items="${posts}">
     	   	<tr>
   	<td rowspan="6" width="70px">
   	<img src="${rowss.facepath }" alt="Cinque Terre" width="50">
   	
    </td>
   		<td colspan="2" width="520px" style="background-color:#F0F3F4; font-size:14px;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
   	${rowss.username}
   		</td>
   		</tr>
   		<tr>
   		<td colspan="2" style="background-color:#F0F3F4; font-size:8px;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
   	${rowss.time}
   		</td>
   		</tr>
   		<c:if test="${not empty rowss.content }">
   		<tr>
   		   		<td colspan="2" style="color: white; background-color:#D0D3D4; font-size:13px;box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
   	${rowss.content}
   		</td>
   	</tr>
   	</c:if>
   	<c:if test="${not empty rowss.picture }">
   	<tr><td colspan="2" style=" box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);" class="imagess">
   	<IMG  src="${rowss.picturepath}" >
   	</td>
   	</tr>
   	</c:if>	
   	   		<c:choose>
   	<c:when test="${rowss.islike=='true' }">
   	<tr><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dolike${rowss.id}" class="btnnn" onclick="send('dolike','${rowss.id}','${rowss.nlike }','${rowss.islike }','<%=username1 %>')" value="Like" style="color: black;"/><span id="countdolike${rowss.id }" class="dianzan">${rowss.nlike }</span>
    </form></td><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dounlike${rowss.id}" class="btnnn" onclick="send('dounlike','${rowss.id}','${rowss.nunlike }','${rowss.isunlike }','<%=username1 %>')" value="Unlike" disabled="disabled" style="color: white;"/><span id="countdounlike${rowss.id }" class="dianzan">${rowss.nunlike }</span>
    </form></td>
   	</tr>
   	</c:when>
   	<c:when test="${rowss.isunlike=='true' }">
   	   	<tr><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dolike${rowss.id}" class="btnnn" onclick="send('dolike','${rowss.id}','${rowss.nlike }','${rowss.islike }','<%=username1 %>')" value="Like" disabled="disabled" style="color: white;"/><span id="countdolike${rowss.id }" class="dianzan">${rowss.nlike }</span>
    </form></td><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dounlike${rowss.id}" class="btnnn" onclick="send('dounlike','${rowss.id}','${rowss.nunlike }','${rowss.isunlike }','<%=username1 %>')" value="Unlike" style="color: black;"/><span id="countdounlike${rowss.id }" class="dianzan">${rowss.nunlike }</span>
    </form></td>
   	</tr>
   	</c:when>
     <c:otherwise>
        	<tr><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dolike${rowss.id}" class="btnnn" onclick="send('dolike','${rowss.id}','${rowss.nlike }','${rowss.islike }','<%=username1 %>')" value="Like" style="color: white;"/><span id="countdolike${rowss.id }" class="dianzan">${rowss.nlike }</span>
    </form></td><td style="background-color:#D0D3D4;  box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">
    <form action="" method="post">
    <input type="hidden" name="username" value = "<%=username1 %>"/>
    <input type="button" id="dounlike${rowss.id}" class="btnnn" onclick="send('dounlike','${rowss.id}','${rowss.nunlike }','${rowss.isunlike }','<%=username1 %>')" value="Unlike" style="color: white;"/><span id="countdounlike${rowss.id }" class="dianzan">${rowss.nunlike }</span>
    </form></td>
   	</tr>
   	     </c:otherwise>
</c:choose>
   	<tr><td style="color: white;background-color:#D0D3D4; font-size:9px; box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);">${rowss.likes }
   	
   	</td>
   	<td style="background-color:#D0D3D4; font-size:9px; box-shadow: 0 2px 18px 0 rgba(0,0,0,0.2), 0 3px 20px 0 rgba(0,0,0,0.19);"></td>
   	 </tr>
   	    	<tr><td height=20px>
   	</td>
   	 </tr>
   	    	<tr><td>
   	</td>
   	 </tr>
     </c:forEach>
     </c:otherwise>
</c:choose>
  </table>
  </div>
  </CENTER>

<div id="div11"></div>
<c:choose>
<c:when test="${posts.size()>4 }">
<div class="row">
<center>
<form action="moreselfinf.jsp" method="post">
<input type="hidden" name="username" value = "<%=username1 %>"/>
<button class="btncc" id="moreinf">More...</button>
</form>
</center>
</div>
</c:when>
</c:choose>

</body>
</html>