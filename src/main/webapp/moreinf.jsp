<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="unswbook.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
<script type="text/javascript">

</script>
<title>Insert title here</title>
</head>
<body>
<% String username1=(String)request.getAttribute("username"); %>
<center>
<table class="ss">
    <c:forEach begin="5" end="${posts.size()-1 }"  var="i">
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
   	<IMG  src="${rowss.picturepath}">
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
   	<tr><td >
   	</td>
   	 </tr>
   	    	<tr><td height=20px>
   	</td>
   	 </tr>
   	    	<tr><td>
   	</td>
   	 </tr>
     </c:forEach>
   	 </table>
   	 </center>
</body>
</html>