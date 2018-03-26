package unswbook;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@WebServlet( displayName = "login_register", urlPatterns ="/login_register")
public class LoginRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass().getName());

    private User currentUser;
    private String activate_code;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nextPage = "welcome.jsp";
        Connection connection=(Connection) getServletContext().getAttribute("dbconnect");
        postgre db = new postgre();

        if(action!=null) {

            //if user want to log in with username and password
            if (action.equals("check_login")) {

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                System.out.println(username+" "+password);
                SearchDatabase sd = new SearchDatabase();
                try {
                    if (sd.userIsExisted(username,connection)) {
                        if (sd.checkPassword(username, password,connection)) {
                            currentUser = sd.getUserProfile(request.getParameter("username"),connection);
                            System.out.println(currentUser.isBanned());
                            if(currentUser.isBanned().equals("false") && currentUser.isActive().equals("true")){
                                /*
                                   if the user is not banned and has an active account,
                                   then allow user to "log in"
                                   1. write to "log" table
                                   2. session...
                                */
                            	Date dNow = new Date( );
                    			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                    			String time = ft.format(dNow).toString();
                                
                                String sql = "insert into log (username,time,action,information) " +
                                        "values('"+username+"','"+time+"','login','null');";
                                Statement insertstatement = connection.createStatement();
                                System.out.println(sql);
                                int r =insertstatement.executeUpdate(sql);
                                
                               // .setAttribute("currentUser",currentUser);
                                getServletContext().setAttribute("username",username);
                            	getServletContext().setAttribute("posttype", "all");

                                //JUMP TO myhome
                                nextPage = "infofunc";

                            }else{
                                if(currentUser.isBanned().equals("true")){ // if the user is banned
                                	currentUser = new User();
                                	currentUser.setUsername(username);
                                    nextPage = "banned_account.jsp";
                                }else if(currentUser.isActive().equals("false")){
                                	currentUser = new User();
                                	currentUser.setUsername(username);
                                    nextPage = "resend_confirm_email.jsp";
                                }
                            }
                        } else {
                            request.setAttribute("error_info","password is incorrect");
                         
                            nextPage = "welcome.jsp";
                        }
                    } else {
                        request.setAttribute("error_info","username doesn't exist.");
                        nextPage = "welcome.jsp";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            } else if (action.equals("forget_password")) { //if user forget password and wanna find it back by email

                nextPage = "reset_password.jsp";

            } else if (action.equals("reset_info_entered")) {
                // if the user has already entered the username or email userd for password reset
                String info = request.getParameter("password_info");
                boolean flag;
                SearchDatabase sd = new SearchDatabase();
                //1. check whether the information user entered is username or email
                //2. check if the info exists in database
              
                try {
                    if (sd.userIsExisted(info,connection)) {//if exists
                        //find out the email
                        //send email containing reset info
                        //EmailOperation eo = new EmailOperation();
                        //eo.sendResetPasswordEmail(email);
                    } else {
                        //return "Sorry, username does not exist."
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (action.equals("register")) { //if the user want to register an account
                String username = request.getParameter("register_username");
                String day = request.getParameter("register_dob_day");
                String month = request.getParameter("register_dob_month");
                String year = request.getParameter("register_dob_year");
                String dob = day+"/"+month+"/"+year;
                String name = request.getParameter("register_full_name");
                String password = request.getParameter("register_password");
                String email = request.getParameter("register_email");
                String gender = request.getParameter("register_gender");
                String photourl = request.getParameter("register_profile_photo");
                System.out.println("upload: "+photourl);
                //1. check if everything entered is valid
                SearchDatabase sd = new SearchDatabase();
                try {
                    if(username.contains(" ")){
                        request.setAttribute("register_error_info","username cannot contain space.");
                        nextPage = "register.jsp";
                    }else{
                        if (sd.userIsExisted(username, connection)) {
                            if (sd.emailIsExisted(email, connection) == 2) {
                                request.setAttribute("register_error_info", "username exists.\n Please sign up with your UNSW Zmail");
                                nextPage = "register.jsp";
                            } else if (sd.emailIsExisted(email, connection) == 3) {
                                request.setAttribute("register_error_info", "username exists.\n Email exists.");
                                nextPage = "register.jsp";
                            } else if (sd.emailIsExisted(email, connection) == 1) {
                                request.setAttribute("register_error_info", "username exists.");
                                nextPage = "register.jsp";
                            }
                        } else {
                            if (sd.emailIsExisted(email, connection) == 2) {
                                request.setAttribute("register_error_info", "Please sign up with your UNSW Zmail");
                                nextPage = "register.jsp";
                            } else if (sd.emailIsExisted(email, connection) == 3) {
                                request.setAttribute("register_error_info", "Email exists");
                                nextPage = "register.jsp";
                            } else if (sd.emailIsExisted(email, connection) == 1) {
                                //2. write user info into "profile" and "log" tables

                                Date dNow = new Date();
                                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String time = ft.format(dNow).toString();

                                String sql = "insert into log (username,time,action,information) " +
                                        "values('" + username + "','" + time + "','create','null');";
                                Statement insertstatement = connection.createStatement();
                                System.out.println(sql);
                                int r = insertstatement.executeUpdate(sql);
                                System.out.println("register in " + r);

                                //upload profile photo
                                FileInputStream pic = null;
                                if (photourl != null&&!"".equals(photourl)) {
                                    System.out.println(photourl);
                                    photourl = new String(photourl.getBytes());
                                    if (photourl.contains(".png")) {
                                       db.pngTojpg(photourl);
                                    }
                                    photourl = db.zippicture(photourl, 100, 100, 0.9f);
                                    pic = new FileInputStream(photourl);

                                    sql = "insert into profile (username,password,email,gender,dob,name,photo,ban,active) values (?,?,?,?,?,?,?,?,?);";
                                    
                                    try {
                                        PreparedStatement pStatement = connection.prepareStatement(sql);
                                        pStatement.setString(1, username);
                                        pStatement.setString(2, password);
                                        pStatement.setString(3, email);
                                        pStatement.setString(4, gender);
                                        pStatement.setString(5, dob);
                                        pStatement.setString(6, name);
                                        pStatement.setBinaryStream(7, pic, pic.available());
                                        pStatement.setString(8, "false");
                                        pStatement.setString(9, "false");
                                        pStatement.executeUpdate();
                                        sql = "insert into friends (username1,username2) values (?,?)";
                                        pStatement = connection.prepareStatement(sql);
                                        pStatement.setString(1,username);
                                        pStatement.setString(2, username);
                                        pStatement.executeUpdate();                                      
                                    } catch (SQLException e1) {
                                        System.out.println("insert profile failed");
                                        e1.printStackTrace();
                                    }
                                    System.out.println("picure path:"+photourl);
                                } else {
                                    sql = "insert into profile (username,password,email,gender,dob,name,photo,ban,active) " +
                                            "values ('" + username + "','" + password + "','" + email + "','" + gender + "','" + dob + "','" + name + "','null','false','false');";

                                    System.out.println(sql);
                                    r = insertstatement.executeUpdate(sql);
                                    System.out.println(r);

                                }

                                //3. if all valid, send a confirmation email
                                SendEmail se = new SendEmail(email);
                                String code = username + "*";

                                //generate activate code
                                int ram = (int) (Math.random() * 97);
                                for (int i = 0; i < 30; i++) {
                                    ram = (int) (Math.random() * 26 + 65);
                                    code += Integer.toString(ram);
                                }
                                activate_code = code;
                                getServletContext().setAttribute("activate_code", activate_code);
                                se.sendConfirmationEmail(code);


                                currentUser = new User(username, password);
                                currentUser.setEmail(email);
                                currentUser.setFullname(name);
                                currentUser.setDob(dob);
                                currentUser.setGender(gender);
                                currentUser.setActive("false");
                                currentUser.setBanned("false");
                                currentUser.setPhotoUrl(photourl);

                                getServletContext().setAttribute("currentUser", currentUser);
                                nextPage = "register_test.jsp";
                            }
                        }
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }

            }else if(action.equals("activateAccount")){
            	activate_code = (String) getServletContext().getAttribute("activate_code");
                if(request.getParameter("code").equals(activate_code)) {
                    currentUser.setActive("true");
                    String sql = "update profile set active ='true' where username = ? ";
                    PreparedStatement pstatement;
                    try {
                        pstatement = connection.prepareStatement(sql);
                        pstatement.setString(1, currentUser.getUsername());
                        pstatement.executeUpdate();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println("active " + sql);
                }else{
                    String activate_error = "Something goes wrong. Please click on the link in the confirmation" +
                            "email we sent you and try again.";
                    request.setAttribute("activate_error",activate_error);
                }
                nextPage = "active_test.jsp";

            }else if(action.equals("resend_confirm_email")){
            	SearchDatabase sd = new SearchDatabase();
            	String email;
            	try {
					email = sd.searchEmail(currentUser.getUsername(), connection);
					currentUser.setEmail(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	String code = currentUser.getUsername() + "*";
            	//generate activate code
                int ram = (int) (Math.random() * 97);
                for (int i = 0; i < 30; i++) {
                    ram = (int) (Math.random() * 26 + 65);
                    code += String.valueOf(ram);
                }
                activate_code = code;
                SendEmail se = new SendEmail(currentUser.getEmail());
                se.sendConfirmationEmail(activate_code);
                getServletContext().setAttribute("currentUser", currentUser);
                System.out.println(currentUser.getUsername());
                nextPage = "register_test.jsp";
               // nextPage = "active_test.jsp";
            }else if(action.equals("profile")){
            	String user = (String)request.getParameter("username");
            	request.setAttribute("username", user);
            	SearchDatabase sDatabase = new SearchDatabase();
            	try {
					currentUser = sDatabase.getUserProfile(user, connection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	request.setAttribute("currentUser", currentUser);
            	nextPage="profile.jsp";
            }else if (action.equals("edit_profile")) {
                String username = currentUser.getUsername();
                String day = request.getParameter("edit_dob_day");
                String month = request.getParameter("edit_dob_month");
                String year = request.getParameter("edit_dob_year");
                String dob = currentUser.getDob();
                String name = request.getParameter("edit_full_name");
                String password = request.getParameter("edit_password");
                String email = request.getParameter("edit_email");
                String gender = request.getParameter("edit_gender");
                String photourl = request.getParameter("edit_profile_photo");
                

                System.out.println(photourl);
                //write into database
                SearchDatabase sd = new SearchDatabase();
                try {
                    if(email!=null && !"".equals(email)) {
                        if (sd.findEmail(username, email, connection)) { //if email exists
                            request.setAttribute("edit_error_info", "Email exists");
                            nextPage = "edit_profile.jsp";
                        } else {
                            //update email in database
                            String sql = "update profile set email ='" + email + "' where username = ? ";
                            PreparedStatement pstatement;
                            pstatement = connection.prepareStatement(sql);
                            pstatement.setString(1, currentUser.getUsername());
                            pstatement.executeUpdate();

                            currentUser.setEmail(email);
                            /*3. if all valid, send a confirmation email
                            SendEmail se = new SendEmail(email);
                            String code = username + "*";

                            //generate activate code
                            int ram = (int) (Math.random() * 97);
                            for (int i = 0; i < 30; i++) {
                                ram = (int) (Math.random() * 26 + 65);
                                code += String.valueOf(ram);
                            }
                            activate_code = code;
                            se.sendConfirmationEmail(code);*/

                        }
                    }else {
                    	currentUser.setEmail(currentUser.getEmail());
                    }

                    if(gender!=null && !"".equals(gender)){
                        currentUser.setGender(gender);
                        String sql = "update profile set gender ='" + gender + "' where username = ? ";
                        PreparedStatement pstatement;
                        pstatement = connection.prepareStatement(sql);
                        pstatement.setString(1, currentUser.getUsername());
                        pstatement.executeUpdate();
                    }else {
                    	currentUser.setGender(currentUser.getGender());
                    }

                   
                    
                    if(!day.equals("1")||!year.equals("1900")||!month.equals("1")){
                        String arr[] = dob.split("/");
                        if(!day.equals("1")){
                            if(!month.equals("1")){
                                if(!year.equals("1900")) { //if day!=null, month!=null, year!=null
                                    dob = day + "/" + month + "/" + year;

                                }else{ //if day!=null, month!=null, year==null
                                    dob = day + "/" + month + "/" + "1900";
                                }
                            }else{
                                if(!year.equals("1900")){ //if day!=null, month==null, year!=null
                                    dob = day + "/" + "1" + "/" + year;
                                }else { //if day!=null, month==null, year==null
                                    dob = day + "/" + "1" + "/" + "1900";
                                }
                            }
                            System.out.println("======: "+dob);
                        }else{
                            if(!month.equals("1")){
                                if(!year.equals("1900")){  //if day==null, month!=null, year!=null
                                    dob = "1"+"/"+month+"/"+year;

                                }else{ //if day==null, month!=null, year==null
                                    dob = "1"+"/"+month+"/"+"1900";
                                }
                            }else{
                                if(!year.equals("1900")){ // if day==null,month==null,year!=null
                                    dob = "1"+"/"+"1"+"/"+year;
                                }
                            }
                        }

                        currentUser.setDob(dob);
                        String sql = "update profile set dob ='" + dob + "' where username = ? ";
                        PreparedStatement pstatement;
                        pstatement = connection.prepareStatement(sql);
                        pstatement.setString(1, currentUser.getUsername());
                        pstatement.executeUpdate();
                    }else {
                    	currentUser.setDob(currentUser.getDob());
                    }

                    if(name!=null&&!"".equals(name)){
                        currentUser.setFullname(name);
                        //update email in database
                        String sql = "update profile set name ='" + name + "' where username = ? ";
                        PreparedStatement pstatement;
                        pstatement = connection.prepareStatement(sql);
                        pstatement.setString(1, currentUser.getUsername());
                        pstatement.executeUpdate();
                    }else {
                    	currentUser.setFullname(currentUser.getFullname());
                    }

                    if(password!=null&&!"".equals(password)){
                        //update email in database
                        String sql = "update profile set password ='" + password + "' where username = ? ";
                        PreparedStatement pstatement;
                        pstatement = connection.prepareStatement(sql);
                        pstatement.setString(1, currentUser.getUsername());
                        pstatement.executeUpdate();
                        currentUser.setPassword(password);
                    }else {
                    	//currentUser.setPassword(currentUser.getPassword());
                    }

                    if(photourl != null&&!"".equals(photourl)) {
                        //upload profile photo

	                	FileInputStream pic = null;
	                   
	                    System.out.println(photourl);
	                    photourl = new String(photourl.getBytes());
	                    if (photourl.contains(".png")) {
	                       db.pngTojpg(photourl);
	                    }
	                    photourl = db.zippicture(photourl, 100, 100, 0.9f);
	                    pic = new FileInputStream(photourl);


                        String sql = "update profile set photo =? where username = ? ";
                        System.out.println(sql);
                        PreparedStatement pStatement = connection.prepareStatement(sql);
                        pStatement.setBinaryStream(1, pic, pic.available());
                        pStatement.setString(2, username);
                        pStatement.executeUpdate();
                        currentUser.setPhotoUrl(photourl);
                    }else {
                    	currentUser.setPhotoUrl(currentUser.getPhotoUrl());
                    }
                    
                   
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                nextPage = "profile.jsp";

                request.setAttribute("currentUser",currentUser);
                request.setAttribute("username", currentUser.getUsername());
                
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
