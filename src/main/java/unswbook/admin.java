package unswbook;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class admin {
    public Connection conninit()
            throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException,IOException,ServletException{
        System.out.println("Connecting to database...");
        String instanceConnectionName = "unswbook-179504:australia-southeast1:unswbook";
        String databaseName = "postgres";
        String username = "postgres";
        String password = "youneversleepwell";
        String jdbcUrl = String.format(
                "jdbc:postgresql://google/%s?socketFactory=com.google.cloud.sql.postgres.SocketFactory"
                        + "&socketFactoryArg=%s",
                databaseName,
                instanceConnectionName);
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connected!");
        return conn;
    }

    //get the profile information of user
    public ArrayList<ArrayList<String>> GetUser(String username,Connection conn)
            throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException,IOException,ServletException,NullPointerException{
        ArrayList<String> user;
        ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();
        String query = "SELECT * FROM profile WHERE username LIKE '%"+username+"%' ORDER BY name;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            user = new ArrayList<String>();
            user.add(rs.getString(1));
            user.add(rs.getString(2));
            user.add(rs.getString(3));
            user.add(rs.getString(4));
            user.add(rs.getString(5));
            user.add(rs.getString(6));
            user.add(rs.getString(7));
            user.add(rs.getString(8));
            user.add(rs.getString(9));
            userlist.add(user);
        }
        return userlist;
    }

    public ArrayList<ArrayList<String>> GetAllUser(Connection conn)
            throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException,IOException,ServletException,NullPointerException{
        ArrayList<String> user;
        ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();
        String query = "SELECT * FROM profile ORDER BY name;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            user = new ArrayList<String>();
            user.add(rs.getString(1));
            user.add(rs.getString(2));
            user.add(rs.getString(3));
            user.add(rs.getString(4));
            user.add(rs.getString(5));
            user.add(rs.getString(6));
            user.add(rs.getString(7));
            user.add(rs.getString(8));
            user.add(rs.getString(9));
            userlist.add(user);
        }
        return userlist;
    }

    //get username in log
    public ArrayList<ArrayList<String>> GetActivityUser(String username,Connection conn)
            throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException,IOException,ServletException,NullPointerException{
        ArrayList<String> user;
        ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();
        String query = "SELECT DISTINCT username FROM log WHERE username LIKE '%"+username+"%';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            user = new ArrayList<String>();
            user.add(rs.getString(1));
            userlist.add(user);
        }
        return userlist;
    }

    public ArrayList<ArrayList<String>> GetAllActivityUser(String username,Connection conn)
            throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException,IOException,ServletException,NullPointerException{
        ArrayList<String> user;
        ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();
        String query = "SELECT DISTINCT username FROM log;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            user = new ArrayList<String>();
            user.add(rs.getString(1));
            userlist.add(user);
        }
        return userlist;
    }

    //get detailed log of username
    public ArrayList<ArrayList<String>> GetActivityDetail(String username,Connection conn)//ARDetaillist
            throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException,IOException,ServletException,NullPointerException{
        ArrayList<String> user;
        ArrayList<ArrayList<String>> userlist = new ArrayList<ArrayList<String>>();
        String query = "SELECT time,action,information,username FROM log WHERE username = '"+username+"' ORDER BY time;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            user = new ArrayList<String>();
            user.add(rs.getString(1));
            user.add(rs.getString(2));
            user.add(rs.getString(3));
            user.add(rs.getString(4));
            userlist.add(user);
        }
        return userlist;
    }

    public ArrayList<ArrayList<String>> GetPostActivitydetail(String id,Connection conn) throws SQLException{
//        String post ="";//ARDetaillist
        ArrayList<String> post = new ArrayList<String>();

        ArrayList<ArrayList<String>> postdetail = new ArrayList<ArrayList<String>>();
        String query = "SELECT * FROM post WHERE id = '"+id+"';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            post.add(rs.getString(1));//id(username+time)
            post.add(rs.getString(2));//username
            post.add(rs.getString(3));//content
            post.add(rs.getString(4));//time
            post.add(rs.getString(5));//unlike
            post.add(rs.getString(6));//like
            post.add(rs.getString(7));//picture
            postdetail.add(post);
//            postdetail.add(post);
        }
        return postdetail;
    }

    public String Login(String adminusername, String adminpassword, Connection conn) throws SQLException{
        String query = "SELECT * FROM admin;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            if (adminusername.equalsIgnoreCase(rs.getString(1)) && adminpassword.equalsIgnoreCase(rs.getString(2)) && rs.getString(3).equalsIgnoreCase("standard")){
                return "standard";
            }else if (adminusername.equalsIgnoreCase(rs.getString(1)) && adminpassword.equalsIgnoreCase(rs.getString(2)) && rs.getString(3).equalsIgnoreCase("super")){
                return "super";
            }
        }
        return "false";
    }

    public void banUser(String username, Connection conn) throws SQLException{
        String query = "UPDATE profile SET ban = 'true' WHERE username='"+username+"';";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

//    public void removeActivity(String username,String time, Connection conn) throws SQLException{
//        String query="DELETE information FROM log WHERE username='"+username+"' AND time='"+time+"';";
//        Statement st = conn.createStatement();
//        st.executeQuery(query);
//    }

    public void allowUser(String username, Connection conn) throws SQLException{
        String query = "UPDATE profile SET ban = 'false' WHERE username='"+username+"';";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

//    public String encryption(String pass)
//    {
//        String generatedPassword = null;
//        try {
//            // Create MessageDigest instance for MD5
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            //Add password bytes to digest
//            md.update(pass.getBytes());
//            //Get the hash's bytes
//            byte[] bytes = md.digest();
//            //This bytes[] has bytes in decimal format;
//            //Convert it to hexadecimal format
//            StringBuilder sb = new StringBuilder();
//            for(int i=0; i< bytes.length ;i++)
//            {
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//            }
//            //Get complete hashed password in hex format
//            generatedPassword = sb.toString();
//        }
//        catch (NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//        return generatedPassword;
//    }


}
