package unswbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/search")

public class adminSearchServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{

        admin psql = new admin();
        Connection newconn = null;
        try{
            newconn = psql.conninit();
        }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException e){
            e.printStackTrace();
        }

        String adminUsername = request.getParameter("adminUsername");
        String adminPassword = request.getParameter("adminPassword");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

//        if (adminUsername.equalsIgnoreCase("root") && adminPassword.equalsIgnoreCase("root")) {
//            response.sendRedirect("AdminSearch.jsp");
//        } else {
        try{
            if (psql.Login(adminUsername,adminPassword,newconn).equalsIgnoreCase("standard")){
                request.getSession().setAttribute("authority","standard");
                response.sendRedirect("AdminSearch.jsp");
            }else if (psql.Login(adminUsername,adminPassword,newconn).equalsIgnoreCase("super")){
                request.getSession().setAttribute("authority","super");
                response.sendRedirect("AdminSearch.jsp");
            }else {
                String UncorrectLoginInfo = "Please input correct adminName and password!";
                request.getSession().setAttribute("UncorrectLoginInfo",UncorrectLoginInfo);
                response.sendRedirect("AdminLogin.jsp");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        admin psql = new admin();
        Connection newconn = null;
        try{
            newconn = psql.conninit();
        }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException e){
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();



        if (request.getParameter("findBanUsername")!=null){
//            out.println("<h1>123</h1>");
            String SearchBanUsername = request.getParameter("SearchBanUsername");
            request.getSession().setAttribute("keepSearchBanUsername", SearchBanUsername);
            ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();
            if (SearchBanUsername != ""){
                try{
                    userlist = psql.GetUser(SearchBanUsername,newconn);
                }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException|NullPointerException e){
                    e.printStackTrace();
                }
                if (userlist.isEmpty()){
                    String noRecordFound = "No record found, please re-input username.";
                    request.getSession().setAttribute("noRecordFound",noRecordFound);
                    response.sendRedirect("AdminSearch.jsp");
                }
                else {
                    request.getSession().setAttribute("userlist",userlist);
                    response.sendRedirect("AdminSearchBan.jsp");
                }
            }else {
//                String noRecordFound = "No record found, please re-input username.";
//                request.getSession().setAttribute("noRecordFound",noRecordFound);
//                response.sendRedirect("AdminSearch.jsp");
                try{
                    userlist = psql.GetAllUser(newconn);
                }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException|NullPointerException e){
                    e.printStackTrace();
                }
                if (userlist.isEmpty()){
                    String noRecordFound = "No record found, please re-input username.";
                    request.getSession().setAttribute("noRecordFound",noRecordFound);
                    response.sendRedirect("AdminSearch.jsp");
                }
                else {
                    request.getSession().setAttribute("userlist",userlist);
                    response.sendRedirect("AdminSearchBan.jsp");
                }
            }
        }

        if (request.getParameter("findActivityReportUsername")!=null){
            String ActivityReportUsername = request.getParameter("ActivityReportUsername");
            request.getSession().setAttribute("keepActivityReportUsername", ActivityReportUsername);
            ArrayList<ArrayList<String>> ARUserlist = new ArrayList<ArrayList<String>>();
            if (ActivityReportUsername != ""){
                try{
                    ARUserlist = psql.GetActivityUser(ActivityReportUsername,newconn);
                }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException|NullPointerException e){
                    e.printStackTrace();
                }
                if (ARUserlist.isEmpty()){
                    String noRecordFound = "No record found, please re-input username.";
                    request.getSession().setAttribute("noRecordFound",noRecordFound);
                    response.sendRedirect("AdminSearch.jsp");
                }
                else {
                    request.getSession().setAttribute("ARUserlist",ARUserlist);
                    response.sendRedirect("AdminSearchActivity.jsp");
                }
            }else {
//                String noRecordFound = "No record found, please re-input username.";
//                request.getSession().setAttribute("noRecordFound",noRecordFound);
//                response.sendRedirect("AdminSearch.jsp");
                try{
                    ARUserlist = psql.GetAllActivityUser(ActivityReportUsername,newconn);
                }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException|NullPointerException e){
                    e.printStackTrace();
                }
                if (ARUserlist.isEmpty()){
                    String noRecordFound = "No record found, please re-input username.";
                    request.getSession().setAttribute("noRecordFound",noRecordFound);
                    response.sendRedirect("AdminSearch.jsp");
                }
                else {
                    request.getSession().setAttribute("ARUserlist",ARUserlist);
                    response.sendRedirect("AdminSearchActivity.jsp");
                }
            }
        }

        if (request.getParameter("ARUserHyperlink")!=null){
            String ARUserHyperlink = request.getParameter("ARUserHyperlink");
//            System.out.println(ARUserHyperlink);
            request.getSession().setAttribute("keepARUserHyperlink", ARUserHyperlink);
            ArrayList<ArrayList<String>> ARDetaillist = new ArrayList<ArrayList<String>>();
            if (ARUserHyperlink != ""){
                try{
                    ARDetaillist = psql.GetActivityDetail(ARUserHyperlink,newconn);
                }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException|NullPointerException e){
                    e.printStackTrace();
                }
                if (ARDetaillist.isEmpty()){
                    out.println("<h1>isempty</h1>");
                }
                else {
                    request.getSession().setAttribute("ARDetaillist",ARDetaillist);
                    response.sendRedirect("AdminSearchActivityDetail.jsp");
                }
            }
        }

        if (request.getParameter("banButton")!=null){
            String banUsername = request.getParameter("banButton");
            try{
                psql.banUser(banUsername,newconn);
                String SearchBanUsername = (String) request.getSession().getAttribute("keepSearchBanUsername");
//                System.out.println(SearchBanUsername);
//                request.getSession().setAttribute("keepSearchBanUsername", SearchBanUsername);
                ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();

                try{
                    userlist = psql.GetUser(SearchBanUsername,newconn);
                }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException|NullPointerException e){
                    e.printStackTrace();
                }
                request.getSession().setAttribute("userlist",userlist);
                response.sendRedirect("AdminSearchBan.jsp");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if (request.getParameter("allowButton")!=null){
            String allowUsername = request.getParameter("allowButton");
            try{
                psql.allowUser(allowUsername,newconn);
                String SearchBanUsername = (String) request.getSession().getAttribute("keepSearchBanUsername");
//                System.out.println(SearchBanUsername);
//                request.getSession().setAttribute("keepSearchBanUsername", SearchBanUsername);
                ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();
                try{
                    userlist = psql.GetUser(SearchBanUsername,newconn);
                }catch (SQLException|InstantiationException|IllegalAccessException|ClassNotFoundException|NullPointerException e){
                    e.printStackTrace();
                }
                request.getSession().setAttribute("userlist",userlist);
                response.sendRedirect("AdminSearchBan.jsp");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if (request.getParameter("SADetailHyperlink")!=null){
            String SADetailHyperlink = request.getParameter("SADetailHyperlink");
            request.getSession().setAttribute("keepARUserHyperlink", SADetailHyperlink);
            ArrayList<ArrayList<String>> postdetail = new ArrayList<ArrayList<String>>();
            if (SADetailHyperlink != ""){
                try{
                    postdetail = psql.GetPostActivitydetail(SADetailHyperlink,newconn);
                }catch (SQLException|NullPointerException e){
                    e.printStackTrace();
                }
//                if (postdetail.isEmpty()){
//                    out.println("<h1>isempty</h1>");
//                }
//                else {
                    request.getSession().setAttribute("postdetail",postdetail);
                    response.sendRedirect("AdminSearchPostDetail.jsp");
//                }
            }
        }
    }
}
