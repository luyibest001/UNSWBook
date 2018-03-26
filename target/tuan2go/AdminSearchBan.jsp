<%@ page import="java.util.ArrayList" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %><%--
  Created by IntelliJ IDEA.
  User: miaogd
  Date: 24/9/17
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="Admin.css">
<head>
    <title>AdminSearchBan</title>
</head>
<body style="font-size: 0px;">
<%!
    public String encryption(String pass)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pass.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
%>
<%
    ArrayList<ArrayList<String>> userlist = (ArrayList<ArrayList<String>>)request.getSession().getAttribute("userlist");
    if (!userlist.isEmpty()){
        out.print("<table class='table' cellpadding='0' cellspacing='0' border='0'><tr class='tbl-header'><td>username</td><td>password</td><td>email</td><td>gender</td><td>dob</td><td>name</td><td>photo</td><td>ban</td><td>Action</td><td>Active</td></tr>");
        for (int i=0;i<userlist.size();i++){
            if (userlist.get(i).get(7).equalsIgnoreCase("false")){
                String photo = userlist.get(i).get(6)==""?"false":"true";
                out.print("<form action='search' method='post'><tr class='tbl-content hidden'>");
                out.print("<td id='"+i+"'>"+userlist.get(i).get(0)+"</td>"+
                        "+<td id='"+i+"'>"+encryption(userlist.get(i).get(1))+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(2)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(3)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(4)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(5)+"</td>"+
                        "+<td id='"+i+"'>"+photo+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(7)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(8)+"</td>"+
//                        "<textarea>"+
                        "+<td><button class='banallowbtn' type='submit' name='banButton' value='"+userlist.get(i).get(0)+"'>BAN</button></td>");
                out.print("</tr></form>");
            }else {
                String photo = userlist.get(i).get(6)==""?"false":"true";
                out.print("<form action='search' method='post'><tr class='tbl-content'>");
                out.print("<td id='"+i+"'>"+userlist.get(i).get(0)+"</td>"+
                        "+<td id='"+i+"'>"+encryption(userlist.get(i).get(1))+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(2)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(3)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(4)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(5)+"</td>"+
                        "+<td id='"+i+"'>"+photo+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(7)+"</td>"+
                        "+<td id='"+i+"'>"+userlist.get(i).get(8)+"</td>"+

                        "+<td id='"+i+"'><button class='banallowbtn' type='submit' name='allowButton' value='"+userlist.get(i).get(0)+"'>ALLOW</button></td>");
                out.print("</tr></form>");
            }
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

