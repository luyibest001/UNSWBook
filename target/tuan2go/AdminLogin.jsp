<html>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="Admin.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<head>
    <title>AdminLogin</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal update" action="search" method="get">
                <span class="heading">Administrator Login</span>
                <div class="form-group">
                    <input type="text" name="adminUsername" class="form-control" id="inputEmail3" placeholder="Admin username">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" name="adminPassword" class="form-control" id="inputPassword3" placeholder="Admin password">
                    <i class="fa fa-lock"></i>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Login</button>
                    <a href='/unswbook/welcome.jsp' class="userLogin">Login as Users</a>
                </div>
            </form>
        </div>
    </div>
</div>
<%
    String UncorrectLoginInfo = (String) request.getSession().getAttribute("UncorrectLoginInfo");
    UncorrectLoginInfo = UncorrectLoginInfo==null?"":UncorrectLoginInfo;
    out.print("<center><span class='uncorrectUser'>"+UncorrectLoginInfo+"</span></center>");
    session.invalidate();
%>
</body>
<!-- <script>
    $(document).ready(function(){
        $(".userLogin").click(function(){
            window.location.href='/unswbook/welcome.jsp';
        });
    });
</script> -->
</html>