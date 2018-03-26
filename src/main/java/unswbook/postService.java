package unswbook;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet( displayName = "postfunc", urlPatterns ="/postfunc")
public class postService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
   public postService() {
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean ispicture = true;
		boolean istext = true;
		String nextpage ="/infofunc";
		postgre db = new postgre();

		//update content
		String content = request.getParameter("inputtext");
		if ("".equals(content)||content ==null) {
			istext = false;
		}
		
		//update picture
		String picturename = request.getParameter("inputpic");
		FileInputStream pic = null;
		if ("".equals(picturename)||picturename == null) {
			ispicture = false;
		}else {
			System.out.println(picturename);
			picturename = new String(picturename.getBytes());
			if (picturename.contains(".png")) {
				picturename = db.pngTojpg(picturename);
			}
			picturename=db.zippicture(picturename, 150, 150, 0.9f);
			pic=new FileInputStream(picturename);
		}
		
		// content and picture is not null, do post function
		if (ispicture || istext) {	
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat ftid = new SimpleDateFormat ("yyyyMMddhhmmss");
			String username = (String) getServletContext().getAttribute("username");
			String time = ft.format(dNow).toString();
			String id = username+ftid.format(dNow).toString();
			String action = "post";
			
			// connection to databse
			Connection connection = (Connection) getServletContext().getAttribute("dbconnect");
			String sql ="";
			if (ispicture) {
				sql = "insert into post (id,username,content,time,picture) values (?,?,?,?,?)";
				try {
					PreparedStatement pStatement = connection.prepareStatement(sql);
					pStatement.setString(1, id);
					pStatement.setString(2, username);
					pStatement.setString(3, content);
					pStatement.setString(4, time);
					pStatement.setBinaryStream(5, pic, pic.available());
					pStatement.executeUpdate();
				} catch (SQLException e1) {
					System.out.println("post context/picture failed");
					e1.printStackTrace();
				}
				pic.close();
	
			}else {
				sql = "insert into post (id,username,content,time) values (?,?,?,?)";
				try {
					PreparedStatement pStatement = connection.prepareStatement(sql);
					pStatement.setString(1, id);
					pStatement.setString(2, username);
					pStatement.setString(3, content);
					pStatement.setString(4, time);
					pStatement.executeUpdate();
				} catch (SQLException e1) {
					System.out.println("post context/picture failed");
					e1.printStackTrace();
				}
			}
			

			try (Statement insertstatement = connection.createStatement()) {
				//insertstatement.executeUpdate(sql);
				sql = "insert into log (username,time,action,information) values('"+username+"','"+time+"','"+action+"','"+id+"');";
				insertstatement.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("log failed");
					e.printStackTrace();
				}

			System.out.println("insert post to database");
			
		}else {
			System.out.println("post nothing");
		}
		
		new File(picturename).delete();
		

		
		
	/*	String sql = "insert into post (id,username,content,time) values ('"+id+"','"+username+"','"
				+content+"','"+time.toString()+"');";*/

		
		RequestDispatcher rd = request.getRequestDispatcher(nextpage);
		   rd.forward(request, response);
	}
}
