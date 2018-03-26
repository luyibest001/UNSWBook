<%--
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
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<link type="text/css" rel="stylesheet" href="Admin.css">
<head>
    <title>AdminSearch</title>
</head>
<body>
<%--<div id="loading" class="se-pre-con"></div>--%>

    <div class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal" action="search" method="post">
                    <span class="heading">SearchBan</span>
                    <div class="form-group">
                        <input type="text" name="SearchBanUsername" class="form-control textSearchBan" placeholder="Enter username">
                        <input type="hidden" name="findBanUsername">
                        <button class="submitSearchBan" type="submit" name="login">Submit</button>
                    </div>
                </form>
                <form class="form-horizontal" action="search" method="post">
                    <span class="heading">ActivityReport</span>
                    <div class="form-group">
                        <input type="text" name="ActivityReportUsername" class="form-control" placeholder="Enter username">
                        <input type="hidden" name="findActivityReportUsername">
                        <button class="submitSearchBan" type="submit" name="login">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%
        String noRecordFound = (String) request.getSession().getAttribute("noRecordFound");
        noRecordFound = noRecordFound==null?"":noRecordFound;
        out.print("<center><span class='uncorrectUser'>"+noRecordFound+"</span></center>");
        session.invalidate();
//        String authority = (String) request.getSession().getAttribute("authority");
//        request.getSession().setAttribute("authority",authority);
    %>
    <button class="submitSearchBan submitSearchBanUpdate">Logout</button>
    <script>
        $(document).ready(function(){
            $(".submitSearchBan").click(function(){
                window.location.href='AdminLogin.jsp';
            });
        });
    </script>
<%--<script>--%>
    <%--$(window).load(function() {--%>
        <%--$(".se-pre-con").fadeOut("slow");;--%>
    <%--});--%>
<%--//    $(document).ready(function(){--%>
<%--//        $("#loading").addClass("hidden");--%>
<%--//        $("#loginbtnn").click(function(){--%>
<%--//            $("#loading").removeClass("hidden");--%>
<%--//        });--%>
<%--//    });--%>


<%--</script>--%>
</body>
</html>
