package unswbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NotificationService
 */
@WebServlet( displayName = "ntffunc", urlPatterns ="/ntffunc")
public class NotificationService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("nottttt");
		String username = (String) getServletContext().getAttribute("username");
		getServletContext().setAttribute("username", username);
		String nextpage = "/notification.jsp";
		Connection connection = (Connection) getServletContext().getAttribute("dbconnect");
		postgre db = new postgre();
		String sql = "select * from notification where username= ? order by time desc";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1,username);
			ResultSet resultSet = pStatement.executeQuery();
			ArrayList<ntfTableBean> results = new ArrayList<ntfTableBean>();
			results = db.ntfEntity(resultSet,connection);
			getServletContext().setAttribute("notifications", results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String picturespath = getServletContext().getRealPath("./");
		
		String userface = db.getface(connection, username, picturespath);
		
		request.setAttribute("username",username);
		request.setAttribute("userface",userface);
		RequestDispatcher rd = request.getRequestDispatcher(nextpage);
		   rd.forward(request, response);
	}

}
