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
    <title>AdminSearchActivityDetail</title>
</head>
<body>
<%
    ArrayList<ArrayList<String>> ARDetaillist = (ArrayList<ArrayList<String>>)request.getSession().getAttribute("ARDetaillist");
    //time,action,information,username
//    String authority = (String) request.getSession().getAttribute("authority");
    if (!ARDetaillist.isEmpty()) {
//        if (authority.equalsIgnoreCase("standard")){
            out.print("<table class='table'><tr class='tbl-header'><td>time</td><td>activity</td><td>"+ARDetaillist.get(0).get(3)+"</td></tr>");
            for (int i=0;i<ARDetaillist.size();i++) {
                out.print("<form action='search' method='post'>");

                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("create")){
                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>started UNSWbook career</td><td></td></tr>");
                }
                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("post")) {
//                    ARDetaillist.get(i).get(0).toString().replace(" ","");
//                    ARDetaillist.get(i).get(0).toString().replace(":","");
//                    ARDetaillist.get(i).get(0).toString().replace("-","");
                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td><button class='searchActivitybtn1' type='submit' name='SADetailHyperlink' value='"+ARDetaillist.get(i).get(2)+"'>posted something</button></td><td></td></tr>");
//                    out.print("<tr id='" + i + "' class='tbl-content hidden'><td>" + ARDetaillist.get(i).get(0) + "</td><td>post " + ARDetaillist.get(i).get(2) + "</td></tr>");
                }
                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("friendrequest")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>request "+ARDetaillist.get(i).get(2)+"</td></tr>");
                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>sent a friend request</td><td></td></tr>");
                }
                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("accept")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>request "+ARDetaillist.get(i).get(2)+"</td></tr>");
                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>friend request had been accepted</td><td></td></tr>");
                }
                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("login")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>request "+ARDetaillist.get(i).get(2)+"</td></tr>");
                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>logged into unswbook</td><td></td></tr>");
                }
//                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("like")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>like "+ARDetaillist.get(i).get(2)+"</td></tr>");
//                }
//                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("unlike")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>unlike "+ARDetaillist.get(i).get(2)+"</td></tr>");
//                }
                out.print("</form>");
            }
            out.print("</table>");
            out.println("<button style='width: 50px;' id='prev'>prev</button>\n" +
                    "    <button style='width: 50px;' id='next'>next</button>");
            out.print("<button class='SearchBanReturn'><span>Return </span></button>");
//        }
//        else if (authority.equalsIgnoreCase("super")){
//            out.print("<table class='table'><tr class='tbl-header'><td>time</td><td>activity</td><td>Action</td></tr>");
//            for (int i=0;i<ARDetaillist.size();i++) {
//                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("create")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>joined the UNSWBook Website</td></tr>");
//                }
//                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("post")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>post "+ARDetaillist.get(i).get(2)+"</td><button class='banallowbtn' type='submit' name='removeActivityButton' value='"+ARDetaillist.get(i).get(0)+"'>Remove</button></tr>");
//                }
//                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("add")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>add "+ARDetaillist.get(i).get(2)+"</td></tr>");
//                }
//                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("like")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>like "+ARDetaillist.get(i).get(2)+"</td></tr>");
//                }
//                if (ARDetaillist.get(i).get(1).equalsIgnoreCase("unlike")){
//                    out.print("<tr id='"+i+"' class='tbl-content hidden'><td>"+ARDetaillist.get(i).get(0)+"</td><td>unlike "+ARDetaillist.get(i).get(2)+"</td></tr>");
//                }
//            }
//            out.print("</table>");
//            out.println("<button style='width: 50px;' id='prev'>prev</button>\n" +
//                    "    <button style='width: 50px;' id='next'>next</button>");
//            out.print("<button class='SearchBanReturn'><span>Return </span></button>");
//        }
    }
%>
<script>
    $(document).ready(function(){
        $(".SearchBanReturn").click(function(){
            window.location.href='AdminSearchActivity.jsp';
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
