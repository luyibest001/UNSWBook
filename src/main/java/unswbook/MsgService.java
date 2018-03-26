package unswbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MsgService
 */
@WebServlet( displayName = "msgfunc", urlPatterns ="/msgfunc")
public class MsgService extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MsgService() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		

        try {
        	Connection connection = (Connection) getServletContext().getAttribute("msgconnect");
			String sql = "select count(*) from notification where username= ? and state = 'false'";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1,userName);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				String count=resultSet.getString(1);
				response.getWriter().print(count);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
