<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: miaogd
  Date: 29/9/17
  Time: 01:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="Admin.css">

<head>
    <title>AdminSearchPostDetail</title>
</head>
<body>
    <%
        ArrayList<ArrayList<String>> postdetail = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("postdetail");
        //id,username,content,time,unlike,like,picture
        // 0,       1,      2,   3,     4,   5,      6
        if (!postdetail.isEmpty()){
//            String year = postdetail.get(0).get(0).length()>=14?postdetail.get(0).get(0).substring(-14,-10):null;
//            String month = postdetail.get(0).get(0).substring(-10,-8);
//            String day = postdetail.get(0).get(0).substring(-8,-6);
//            String hour = postdetail.get(0).get(0).substring(-7,-5);
//            String minute = postdetail.get(0).get(0).substring(-5,-3);
//            String second = postdetail.get(0).get(0).substring(-3,-1);
//            String time = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
            out.print("<table class='table'>");
            out.print("<tr class='tbl-content'><td>Time</td><td>"+postdetail.get(0).get(3)+"</td></tr>");
            out.print("<tr class='tbl-content'><td>Username</td><td>"+postdetail.get(0).get(1)+"</td></tr>");
            if (!postdetail.get(0).get(2).isEmpty()){//content
                out.print("<tr class='tbl-content'><td>Content</td><td>"+postdetail.get(0).get(2)+"</td></tr>");
            }else {
                out.print("<tr class='tbl-content'><td>Content</td><td>No text Posted</td></tr>");
            }
            if (postdetail.get(0).get(6)!=""){//picture
                out.print("<tr class='tbl-content'><td>Picture</td><td>Posted pictures</td></tr>");
            }else {
                out.print("<tr class='tbl-content'><td>Picture</td><td>No picture posted</td></tr>");
            }
            if (postdetail.get(0).get(4)!=null){//unlike
                out.print("<tr class='tbl-content'><td>Unlike</td><td>"+postdetail.get(0).get(4)+" unliked your post</td></tr>");
            }else {
                out.print("<tr class='tbl-content'><td>Unlike</td><td>No one unliked this post</td></tr>");
            }
            if (postdetail.get(0).get(5)!=null){//like
                out.print("<tr class='tbl-content'><td>Like</td><td>"+postdetail.get(0).get(5)+" liked your post</td></tr>");
            }else {
                out.print("<tr class='tbl-content'><td>Like</td><td>No one liked this post</td></tr>");
            }
            out.print("</table>");
            out.print("<button class='SearchBanReturn'><span>Return </span></button>");
        }else {
//            out.print("<h1>0"+postdetail.get(0).get(0)+"1"+postdetail.get(0).get(1)+"2"+postdetail.get(0).get(2)+"3"+postdetail.get(0).get(3)+"4"+postdetail.get(0).get(4)+"</h1>");
            out.print("This record has been cleaned");
        }
    %>
</body>
<script>
    $(document).ready(function() {
        $(".SearchBanReturn").click(function () {
            window.location.href = 'AdminSearchActivityDetail.jsp';
        });
    });
</script>
</html>
