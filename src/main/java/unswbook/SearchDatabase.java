package unswbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SearchDatabase {
	

    public SearchDatabase(){
    }

    /**
     * @method look into database checking if the user exists with username
     * @param  username
     * @return boolean
     */
    public boolean userIsExisted(String username,Connection connection) throws SQLException {
        boolean flag = false;
        String sql = "select * from profile where username='"+username+"';";
        Statement querystatement = connection.createStatement();
        ResultSet resultSet =
  	          querystatement.executeQuery(sql);
        if(resultSet.next()){
            flag = true;
        }else{
            flag = false;
        }

        return flag;
    }

    /**
     * @method look into database checking if an email exists and check if it is a zmail
     * @param  email
     * @return int if 1=valid email, 2=not a zmail, 3=email exists+ a zmail
     */
    public int emailIsExisted(String email,Connection connection) throws SQLException {
        int flag = 0;

        
        if(email.contains("unsw.edu.au")){
            //if it is a zmail
            //then search database
            String sql = "select * from profile where email='"+email+"';";
            Statement querystatement = connection.createStatement();
            ResultSet rs =
                    querystatement.executeQuery(sql);
            //if exists
            if(rs.next()){
                flag = 3;

            }else{ //if valid email

                flag = 1;
            }
        }else{
            // if it is not a zmail
            flag = 2;
        }
      

        return flag;
    }

    /**
     * @method look into database check if the password matches the username
     * @param username,password
     * @return boolean
     */
    public boolean checkPassword(String username, String password,Connection connection) throws SQLException {
        boolean flag = false;
        String sql = "select password from profile where username='"+username+"';";
        Statement querystatement = connection.createStatement();
        ResultSet rs =
  	          querystatement.executeQuery(sql);
        if(rs.next()) {
        	if(rs.getString(1).equals(password)){
        		flag = true;
        	}else{
        		flag = false;
        	}

        
        }
        return flag;
    }

    /**
     * @method get all information of a user from database
     * @param username
     * @return User object
     */
    public User getUserProfile(String username,Connection connection) throws SQLException {
        User user = new User();
        user.setUsername(username);
        String sql = "select password from profile where username='"+username+"';";
        Statement querystatement = connection.createStatement();
        ResultSet rs =querystatement.executeQuery(sql);
        if(rs.next()) {
	        user.setPassword(rs.getString(1));
	        sql= "select email from profile where username='"+username+"';";
	        rs =querystatement.executeQuery(sql);
	        if(rs.next()) {
	        	user.setEmail(rs.getString(1));
	        	
	        }
	        
	        sql = "select gender from profile where username='"+username+"';";
	        rs =querystatement.executeQuery(sql);
	        if(rs.next()) {
	        	user.setGender(rs.getString(1));
	        }
	        
	        sql = "select dob from profile where username='"+username+"';";
	        rs =querystatement.executeQuery(sql);
	        if(rs.next()) {
	        	user.setDob(rs.getString(1));
	        }
	        
	        sql = "select name from profile where username='"+username+"';";
	        rs =querystatement.executeQuery(sql);
	        if(rs.next()) {
	        	user.setFullname(rs.getString(1));
	        }
	        
	        sql = "select photo from profile where username='"+username+"';";
	        rs =querystatement.executeQuery(sql);
	        if(rs.next()) {
	        	user.setPhotoUrl(rs.getString(1));
	        }
	        
	        sql = "select ban from profile where username='"+username+"';";
	        rs =querystatement.executeQuery(sql);
	        if(rs.next()) {
	        	user.setBanned(rs.getString(1));
	        }
	        
	        sql = "select active from profile where username='"+username+"';";
	        rs =querystatement.executeQuery(sql);
	        if(rs.next()) {
	        	user.setActive(rs.getString(1));
	        }
	       
        }

        return user;
    }


    /**
     * @method
     * @param username
     * @return
     */
    public boolean findEmail(String username,String email,Connection connection) throws SQLException {
        String e = null;
        boolean flag = false;
        String sql = "select email from profile where username<>'"+username+"';";
        Statement querystatement = connection.createStatement();
        ResultSet rs =querystatement.executeQuery(sql);
        while(rs.next()) {
        	e = rs.getString(1);
        	if(e.equals(email)){
        	    flag = true;
        	    break;
            }
        }

        return flag;
    }
    
    /**
     * @method
     * @param username
     * @return
     */
    public String searchEmail(String username,Connection connection) throws SQLException {

    	String email = null;
        String sql = "select email from profile where username='"+username+"';";
        Statement querystatement = connection.createStatement();
        ResultSet rs =querystatement.executeQuery(sql);
        if(rs.next()) {
        	email = rs.getString(1);
        	
        }

        return email;


    }
    
    /**
     * @method
     * @param username
     * @return
     */
    public String searchName(String username,Connection connection) throws SQLException {

    	String email = null;
        String sql = "select email from profile where username='"+username+"';";
        Statement querystatement = connection.createStatement();
        ResultSet rs =querystatement.executeQuery(sql);
        if(rs.next()) {
        	email = rs.getString(1);
        	
        }

        return email;


    }
}
