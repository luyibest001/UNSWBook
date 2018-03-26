package unswbook;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addfriend extends initialdb {
    public String impadd(String u1,String u2) throws ClassNotFoundException, SQLException{
        startdb();
        if(checkfri(u1,u2)){
            return "you are already friends";
        }
        Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String timeStamp = ft.format(dNow).toString();
        String sql = "INSERT INTO log (username,time,action,information) "+ "VALUES ('"+u2+"','"+timeStamp+"','"+"accept"+"',"+"'"+u1+"');";
        String sql2 = "INSERT INTO notification (username,time,content,state,username2) "+ "VALUES ('"+u1+"','"+timeStamp+"','"+"accept your request"+" "+u2+"'"+",'false','"+u2+"');";
        String sql3 = "INSERT INTO friends (username1,username2) "+ "VALUES ('"+u1+"','"+u2+"');";
        String sql4 = "INSERT INTO friends (username1,username2) "+ "VALUES ('"+u2+"','"+u1+"');";
        String sql5 = "INSERT INTO log (username,time,action,information) "+ "VALUES ('"+u1+"','"+timeStamp+"','"+"accept"+"',"+"'"+u2+"');";
        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
            statement.executeUpdate(sql4);
            statement.executeUpdate(sql5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "request accept";


    }
}
