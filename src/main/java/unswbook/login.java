package unswbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class login extends initialdb {
    public boolean acceptlog(String account,String pass) throws ClassNotFoundException, SQLException{
        startdb();
        String username=null;
        String sql = "SELECT * FROM profile WHERE username = '" + account + "' AND password = '" + pass + "'";
        try {
            ResultSet result=statement.executeQuery(sql);
            while (result.next()){
                username=result.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (username==null){
            this.closedb(statement);

            return false;
        }
        else {
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());



            sql = "INSERT INTO log (username,time,action,information) "+ "VALUES ('"+username+"','"+timeStamp+"','"+"accepted"+"',"+"'accepted')";
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.closedb(statement);

            return true;
        }
    }

}
