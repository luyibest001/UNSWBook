package unswbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet( displayName = "center", urlPatterns ="/center")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// TODO 
	private String postfunc = "/postfunc";// the servlet for posting function
	private String infofunc = "/infofunc";// the servlet for getting more infomation function
	private String moreinfofunc = "/moreinfofunc";
	private String isgoodfunc = "/isgoodfunc";
	private String notification = "/ntffunc";
	private String welcomePage = "/welcome.jsp"; //the page for welcome
	private String loginfunc ="/login_register";
	private String searchfunc = "/search.jsp";
	
	

 
    public controller() {}
    public void init()  throws ServletException{
    	postgre db =new postgre();
    	Connection connection;
    	Connection msgconnection;
		try {
			connection = db.getConnection();
			getServletContext().setAttribute("dbconnect", connection);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
		try {
			msgconnection = db.getConnection();
			getServletContext().setAttribute("msgconnect", msgconnection);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Database connection for message failed");
			e.printStackTrace();
		}

        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = "";
		System.out.println("action: "+action);
		String nextpage ="";
		if(action == null) {
/*			String username = "liling zhang";
			getServletContext().setAttribute("username",username);*/
			nextpage = welcomePage;
		}
		else if(action.equals("posttext")) {
			getServletContext().setAttribute("posttype", "all");
			username = request.getParameter("username");
			getServletContext().setAttribute("username", username);
			nextpage = postfunc;	// go to post function servlet
		}else if (action.equals("moreinfo")) {
			getServletContext().setAttribute("posttype", "all");
			username = request.getParameter("username");
			getServletContext().setAttribute("username", username);
			nextpage = infofunc;
		}else if (action.equals("5moreinfo")) {
			getServletContext().setAttribute("posttype", "all");
			username = request.getParameter("username");
			getServletContext().setAttribute("username", username);
			nextpage = moreinfofunc;
		}else if (action.equals("selfinfo")) {
			getServletContext().setAttribute("posttype", "self");
			username = request.getParameter("username");
			getServletContext().setAttribute("username", username);
			nextpage = infofunc;
		}else if(action.equals("dolike")) {
			username = request.getParameter("username");
			getServletContext().setAttribute("username", username);
			getServletContext().setAttribute("isgood", "likes");
			String likepostid = request.getParameter("likepostid");
			getServletContext().setAttribute("likepostid",likepostid);
			nextpage = isgoodfunc;
		}else if(action.equals("dounlike")) {
			username = request.getParameter("username");
			getServletContext().setAttribute("username", username);
			getServletContext().setAttribute("isgood", "unlikes");
			String likepostid = request.getParameter("likepostid");
			getServletContext().setAttribute("likepostid",likepostid);
			nextpage = isgoodfunc;
		}else if (action.equals("notification")) {
			username = request.getParameter("username");
			getServletContext().setAttribute("username", username);
			nextpage = notification;
		}else if(action.equals("check_login")){
			nextpage = loginfunc;
		}else if(action.equals("register")) {
			nextpage = loginfunc;
		}else if (action.equals("search")) {
			nextpage = searchfunc;
		}else if(action.equals("test")) {
			System.out.println("test");
			nextpage = loginfunc;
		}
		
		// TODO 
		//response.sendRedirect(nextpage);
		RequestDispatcher rd = request.getRequestDispatcher(nextpage);
		   rd.forward(request, response);
	}

}
