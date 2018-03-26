package unswbook;
import java.sql.*;


public class initialdb {
    protected Statement statement;
    protected void startdb() throws ClassNotFoundException, SQLException{
        Connection connection;
        postgre postgre=new postgre();
        connection = postgre.getConnection();
        statement = connection.createStatement();
    }
    protected boolean checkfri(String u1,String u2){
        String username=null;
        String sql = "SELECT * FROM friends WHERE username1 = '" + u1 + "' AND username2 = '" + u2 + "'";
        System.out.println(sql);
        try {
            ResultSet result=statement.executeQuery(sql);
            while (result.next()){
                username=result.getString("username1");
                System.out.println(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (username==null){
            System.out.println(11111111);
            return false;

        }
        System.out.println(username);
        return true;
    }
    protected void closedb(Statement statement){
        try{
            if (statement.getConnection() != null){
                statement.getConnection().close();
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
        try{
            if(statement != null){
                statement.close();
            }
        }catch(SQLException se2){}
    }
}
