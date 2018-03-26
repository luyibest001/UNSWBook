<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: miaogd
  Date: 24/9/17
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="Admin.css">
<head>
    <title>AdminSearchActivity</title>
</head>
<body>
<%
    ArrayList<ArrayList<String>> ARUserlist = (ArrayList<ArrayList<String>>)request.getSession().getAttribute("ARUserlist");
//    String authority = (String) request.getSession().getAttribute("authority");
//    request.getSession().setAttribute("authority",authority);
    if (!ARUserlist.isEmpty()) {
        out.print("<table class='table'><tr class='tbl-header'><td>username</td></tr>");
        for (int i=0;i<ARUserlist.size();i++) {
            out.print("<form action='search' method='post'>");
            out.print("<tr id='"+i+"'class='tbl-content hidden' id='searchActivity'><td><button class='searchActivitybtn' type='submit' name='ARUserHyperlink' value='" + ARUserlist.get(i).get(0) + "'>"+ARUserlist.get(i).get(0)+"</button></td></tr>");
            out.print("</form>");
        }
        out.print("</table>");
        out.println("<button style='width: 50px;' id='prev'>prev</button>\n" +
                "    <button style='width: 50px;' id='next'>next</button>");
        out.print("<button class='SearchBanReturn'><span>Return </span></button>");
    }
%>
<script>
    $(document).ready(function(){
        $(".SearchBanReturn").click(function(){
            window.location.href='AdminSearch.jsp';
        });
        var click = 0;
        function showww(click){
            for(var i=click*10;i<(click+1)*10;i++) {
                $("table tr").eq(i+1).removeClass("hidden");
            }
        }
        function hideee_prev(click){
            for(var n=(click)*10;n<(click+1)*10;n++){
                $("table tr").eq(n+1).addClass("hidden");
            }
        }
        //next and prev button
        //get total number of results
        var ppp = parseInt($("table tr").eq(-1).attr("id"),10);
        showww(click);
        $("#next").click(function () {
            $("#prev").removeClass("btn-disable");
            click += 1;
            if (click <= (ppp / 10)) {
                hideee_prev(click - 1);
                showww(click);
                if(click+1>(ppp/10)){
                    $("#next").addClass("btn-disable");
                }
            }
        });
        $("#prev").click(function () {
            $("#next").removeClass("btn-disable");
            click-=1;
            if(click>=0) {
                hideee_prev(click + 1);
                showww(click);
                if (click - 1 < 0) {
                    $("#prev").addClass("btn-disable");
                }
            }
        });
    });
</script>
</body>
</html>
