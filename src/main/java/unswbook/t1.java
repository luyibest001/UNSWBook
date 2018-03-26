package unswbook;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class t1 extends initialdb{
    private static Session session;


    public String sendActivationEmail(String fuser, String touser) throws ClassNotFoundException, SQLException{
        startdb();
        init();
        if(this.checkfri(fuser,touser)){
            return "you are already friends";

        }
        String fn = null,tn=null,fe=null,te=null;
        String se="SELECT * FROM profile WHERE  username ='" +fuser +"';";
        try {
            ResultSet results = statement.executeQuery(se);
            while(results.next()){
                fn=results.getString("name");


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        se="SELECT * FROM profile WHERE  username ='" +touser +"';";

        try {
            ResultSet results = statement.executeQuery(se);
            while(results.next()){

                te=results.getString("email");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("unswbooktest0927@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(te));
            message.setSubject("unswbook friend request");
            message.setText("user: "+ fn + " wants to be your friend, click the link to accept "+ "http://localhost:8080/unswbook/register?action=accfri&fuser=" + fuser + "&touser="+touser);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String timeStamp = ft.format(dNow).toString();
        String sql = "INSERT INTO log (username,time,action,information) "+ "VALUES ('"+fuser+"','"+timeStamp+"','"+"friendrequest"+"',"+"'"+touser+"');";
        String sql2 = "INSERT INTO notification (username,time,content,state,username2) "+ "VALUES ('"+touser+"','"+timeStamp+"','"+"friendrequest from"+" "+fuser+"'"+",'false','"+fuser+"');";

        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            System.out.println(sql+" "+sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "request send";
    }


    private static void init(){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("nanxdong@gmail.com", "nimagebi11");
                    }
                });
    }
}
