package unswbook;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/register")
public class tttttt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("accfri")) {
            resp.setContentType("text/html");

            String fuser=req.getParameter("fuser");
            String touser=req.getParameter("touser");
            PrintWriter out = resp.getWriter();
            addfriend a=new addfriend();
            String mes;
			try {
				mes = a.impadd(fuser,touser);
	            out.append(mes);
	            out.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


        }
        else if(action.equals("sendreq")){
            String username = req.getParameter("username");
            String friname = req.getParameter("friname");
            System.out.println(username);
            System.out.println(friname);



            t1 te= new t1();
            String k;
			try {
				k = te.sendActivationEmail(username,friname);
	            req.setAttribute("added",k);
	            req.setAttribute("username",username);
	            req.getRequestDispatcher("search.jsp").forward(req, resp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
         if(action.equals("login")){
            String account=req.getParameter("username");
            System.out.println(account);

            String password=req.getParameter("password");
            login l=new login();
             

             try {
				if(l.acceptlog(account,password)){
				    req.setAttribute("username",account);
				    req.getRequestDispatcher("search.jsp").forward(req, resp);

				}
				else {
				    req.setAttribute("iflog","wrong password or account");
				    req.getRequestDispatcher("login.jsp").forward(req, resp);

				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(action.equals("gohome")){
             String account=req.getParameter("username");
             req.setAttribute("username",account);
             req.getRequestDispatcher("search.jsp").forward(req, resp);



         }
        else if(action.equals("search")){

             String year = req.getParameter("register_dob_year");
             String dob="#";
             if (!year.equals("year")){
                 dob=year;
             }


             Calendar now = Calendar.getInstance();   // Gets the current date and time
             int year1 = now.get(Calendar.YEAR);
             System.out.println(dob);

            String account=req.getParameter("username");
            System.out.println(account+" search");
            String name=req.getParameter("name");

            String gender=req.getParameter("gender");
            if (gender==null){
                gender="#";
            }
            System.out.println(gender);
            search re=new search();
            List<profile> l=re.impsearch(name,dob,gender);
            req.setAttribute("result",l);
            req.setAttribute("username",account);
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        }else if(action.equals("addreq")){
            String account=req.getParameter("username");
            String fri=req.getParameter("friname");
        }



    }
}