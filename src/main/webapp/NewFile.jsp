<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
<script>
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
		  });
	});
</script>
</head>
<body>
dhsdfnwqnfp
<div id="div11"><h2>sss</h2></div>
<button id="moreinf">ccc</button>

</body>
</html>