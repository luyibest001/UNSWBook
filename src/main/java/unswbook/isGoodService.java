package unswbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( displayName = "isgoodfunc", urlPatterns ="/isgoodfunc")
public class isGoodService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public isGoodService() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String nextpage ="/postwall.jsp";

		String username = (String) getServletContext().getAttribute("username");
		String isgood = (String) getServletContext().getAttribute("isgood");
		String likepostid = (String) getServletContext().getAttribute("likepostid");
		
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String time = ft.format(dNow).toString();
		postgre postgre = new postgre();
		
		System.out.println(username+"like/unlike the post");
		
		String sql = "";
		String values ="";
		Connection connection = (Connection) getServletContext().getAttribute("dbconnect");
		
		try (Statement statement = connection.createStatement()) {
			sql = "select username,"+isgood+" from post where id = '"+likepostid+"';";
			System.out.println(sql);
			
			ResultSet resultSet =statement.executeQuery(sql);
			if (resultSet.next()) {
				values= resultSet.getString(isgood);
				if(isgood.equals("likes")) {
				//add to notification table
				String tousername = resultSet.getString("username");
				String content = time + " "+ username +" likes your post!";
				
				sql = "select * from notification where username = '"+tousername+"' and content = '"+content+"';";
				ResultSet resultSet2=statement.executeQuery(sql);
				
				if (resultSet2.next()) {
					System.out.println("this notification exists");
				}else {
					
					
					sql = "insert into notification (username,time,content,username2,state) values ('"
							+tousername+"','"+time+"','"+content+"','"+username+"','false');";
					statement.executeUpdate(sql);
				}
				}
				//update post table
				if (values == null ||"".equals(values)) {
					values = username;
					response.getWriter().print(true);
				}else {
					if (postgre.isExists(values, username)) { 
						System.out.println(values);
						//once like/unlike, twice cancel like/unlike
						String rex = "\\b"+username+"\\b";
						Pattern pattern=Pattern.compile(rex);
						Matcher matcher = pattern.matcher(values);
						values = matcher.replaceFirst("");
						//values=values.replace(rex, "");
						values=values.replaceFirst(",", "");
						System.out.println(values);
						response.getWriter().print(false);
					}else{
						values = username+","+values;
						response.getWriter().print(true);
					}
				}
				sql = "update post set "+isgood+" = '"+values+"' where id = '"+likepostid+"';";
				statement.executeUpdate(sql);
				
			}else {
				System.out.println("the id is not exist");
			}
		    } catch (SQLException e1) {
		    	System.out.println("can not update for like");
				e1.printStackTrace();
			}
		
	}

}
