<html>
<head>
    <title>Welcome!</title>
    <link type="text/css" rel="stylesheet" href="login_register.css">
</head>
<body style="background-color: #dddddd">

<div class="ue-bar">


    <div class="ue-bar-warp">
        <div class="ue-bar-logo">
           <img src="unsw.png" width="100" alt="postwall" title="postwall">
            
    </div>

        <div class="ue-bar-nav">

        </div>
    </div>
</div>

<div class="form-style-8">
    <h2>Login to your account</h2>
    <%
        if(request.getAttribute("error_info")!=null){
            out.println("<p style='color:red'>"+request.getAttribute("error_info")+"</p>");
        }
    %>
    <form action="center" method="post">
        <input type="text" name="username" placeholder="Username" />
        <input type="password" name="password" placeholder="Password" />
        <input type="hidden" name="action" value="check_login">
        <input type="submit" value="Log in" />
        <br><br>
        <a href="/unswbook/login_register?action=forget_password" class="commonHyperLink">Forget password?</a>
        <br><br>
        <a href="/unswbook/AdminLogin.jsp" class="commonHyperLink">Administration Log in</a>
    </form>
</div>
<div class="form-style-8">
    <h1>New to UNSWBook?</h1>
    <h3 style="color: #4D4D4D">Sign up now to start your journey at UNSWBook!</h3>
    <a style="margin-top:10px;" href="register.jsp" class="button_sign_up">Sign up</a>
</div>
</body>
</html>
