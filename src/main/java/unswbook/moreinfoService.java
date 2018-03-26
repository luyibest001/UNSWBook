package unswbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( displayName = "moreinfofunc", urlPatterns ="/moreinfofunc")
public class moreinfoService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public moreinfoService() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextpage ="postwall.jsp";
		String posttype = (String) getServletContext().getAttribute("posttype");
		String username = (String) getServletContext().getAttribute("username");
		String sql ="";
		String userface = "";
		String picturespath = getServletContext().getRealPath("./");
		System.out.println("get "+username+"'s post from database,type= "+posttype);
		//System.out.println(picturespath);
		if(posttype.equals("self")) {
			sql = "select * from post where username ='"+username+ "' order by time desc;";
			//nextpage = "/unswbook/selfprofile.jsp";
			nextpage = "selfprofile.jsp";
		}else{
			
			sql = "select distinct post.id,post.username,post.content,post.time,post.unlikes,post.likes,post.picture from friends,post "
					+ "where (post.username=friends.username2 and friends.username1 = '"+username+"') or post.username='"+username+"' order by time desc;";
		}
		postgre db = new postgre();
		ArrayList<PostTableBean> results = new ArrayList<PostTableBean>();

		Connection connection = (Connection) getServletContext().getAttribute("dbconnect");
		try (Statement querystatement = connection.createStatement()) {
		      ResultSet resultSet = querystatement.executeQuery(sql);
		      results = db.postEntity(resultSet,picturespath,connection,username);
		      getServletContext().setAttribute("posts",results);
		      //System.out.println("the length of the arraylist:"+results.size()+" content:"+results.get(0).getContent());

		    } catch (SQLException e1) {
		    	System.out.println("can not query");
				e1.printStackTrace();
			}
		userface = db.getface(connection, username, picturespath);
		request.setAttribute("username",username);
  		request.setAttribute("userface", userface);
  		System.out.println("current user: "+username);
  		
	}
	

}
