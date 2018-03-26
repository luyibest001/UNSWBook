<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
<script type="text/javascript">
function send() {
    var xmlHttp;
    xmlHttp= new XMLHttpRequest();
    xmlHttp.open("POST", "/unswbook/gg", true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var username = document.getElementById("username2").value;
    xmlHttp.send("username=" + username);
    xmlHttp.onreadystatechange = function() {
        if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            if(xmlHttp.responseText == "true") {
                document.getElementById("error").innerHTML = "error";
            } else {
                document.getElementById("error").innerHTML = "right";
            }
        }
    };
}
$(document).ready(function(){ 
	$("#NAME").blur(function(){
	$.post("/unswbook/gg",
			{username:$("#")})
	})
	
}); 
</script>


</head>
<body>
<form action="" method="post">

name: <input id="username1" type="text" name="uername" /> <span id="error"></span>

<input type="button" value="sig"  name="gender" onclick="send()"/>



name: <input id="username2" type="text" name="uername" /> <span id="error"></span>

<input type="button" value="sig" name="gender"  onclick="send()"/>
</form>

</body>
</html>