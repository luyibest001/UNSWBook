package unswbook;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.naming.directory.DirContext;

import com.google.api.services.sqladmin.SQLAdmin.BackupRuns.List;
import com.sun.mail.handlers.image_gif;

/**
 * A sample app that connects to a Cloud SQL instance and lists all available tables in a database.
 */
public class postgre {
	ResultSet rs = null;
  public static void main(String[] args) throws IOException, SQLException {

  	}
  public Connection getConnection() throws SQLException, ClassNotFoundException {
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
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
      return connection;

  }
  
  	public ArrayList<PostTableBean> query(String sql,Connection connection) throws SQLException, IOException {
	
	    try (Statement querystatement = connection.createStatement()) {
	      ResultSet resultSet = querystatement.executeQuery(sql);
	      ArrayList<PostTableBean> list = new ArrayList<PostTableBean>();
	      //postEntity(resultSet);
	      return list;
	    }
  }
  	public int insert(String sql,Connection connection) throws SQLException {

  		
	    try (Statement insertstatement = connection.createStatement()) {
	      int resultSet =insertstatement.executeUpdate(sql);
		return resultSet;
	    }
  }
  	
  	public ArrayList<PostTableBean> postEntity(ResultSet rs,String picturespath, Connection connection,String username) throws SQLException, IOException{
  		ArrayList<PostTableBean> list = new ArrayList<PostTableBean>();
  		String sql = "select photo from profile where username = ?";
  		PreparedStatement pStatement = connection.prepareStatement(sql);
  		
  		while(rs.next()) {
  			PostTableBean row =new PostTableBean();
  			row.setId(rs.getString(1));
  			row.setUsername(rs.getString(2));
  			row.setContent(rs.getString(3));
  			row.setTime(rs.getString(4));
  			row.setUnlikes(rs.getString(5));
  			row.setLikes(rs.getString(6));
  			row.setPicture(rs.getBinaryStream(7));
  			row.setFacepath("");
  			row.setIslike("false");
  			row.setIsunlike("false");
  			String slike = row.getLikes();
  			

  			int count = 0;
  			if ("".equals(slike) || slike == null) {
				count = 0; 
			}else {
				if (isExists(slike,username)) {
					row.setIslike("true");
	  			}
				for(String s:slike.split(",")) {
	  				if (s!=null && !"".equals(s)) {
	  					count +=1;
					}
	  			}
			}
  			row.setNlike(count);
  			slike = row.getUnlikes();
  			count = 0;
  			if ("".equals(slike) || slike == null) {
				count = 0; 
			}else {
				if (isExists(slike, username)) {
					row.setIsunlike("true");
	  			}
				for(String s:slike.split(",")) {
	  				if (s!=null && !"".equals(s)) {
	  					count +=1;
					}
	  			}
			}
  			row.setNunlike(count);

  			pStatement.setString(1, row.getUsername());
  	  		ResultSet rset = pStatement.executeQuery();
	  	  	if(rset.next()) {
	  	  		InputStream is= rset.getBinaryStream(1);
	  	  		if (is != null) {
	  	  			row.setFacepath(tofile(is, row.getUsername(), picturespath));
	  						
	  	  		}else {
	  	  			row.setFacepath("");
	  	  		}
	  	   }
  			if (row.getPicture() != null) {
  				row.setPicturepath(tofile(row.getPicture(), row.getId(),picturespath));
  				
			}else {
				row.setPicturepath("");
			}
  			System.out.println(row.getFacepath());
  			list.add(row);
  			
  		}
  		
  		return list;
  	}
  	public ArrayList<ntfTableBean> ntfEntity(ResultSet rs,Connection connection) throws SQLException{
  		ArrayList<ntfTableBean> list = new ArrayList<ntfTableBean>();
  		String sql = "update notification set state = 'true' where username = ? and time = ? and username2 = ?";
  		PreparedStatement pStatement = connection.prepareStatement(sql);
  		while (rs.next()) {
			ntfTableBean row = new ntfTableBean();
			row.setUsername(rs.getString(1));
			row.setTime(rs.getString(2));
			row.setContent(rs.getString(3));
			row.setState(rs.getString(4));
			row.setUsername2(rs.getString(5));
			pStatement.setString(1, row.getUsername());
			pStatement.setString(2, row.getTime());
			pStatement.setString(3, row.getUsername2());
			pStatement.addBatch();
			list.add(row);
		}
  		pStatement.executeBatch();
		return list;
  	}
  	public String tofile(InputStream iStream,String picturename,String picturespath) throws IOException {
  		FileOutputStream fOutputStream = null;
  		File directory = null;
  		File file = null;
  		String filename = picturespath +File.separator+"pictures";
  		directory = new File(filename);
  		if (!directory.exists()) {
			directory.mkdirs();
		}
  		String path = filename+File.separator+picturename+".jpg";
  		file = new File(path);
  		if (!file.exists()) {
  			file.createNewFile();
  			if (!file.isFile()) {
  				System.out.println("file can not create");
  			}
		}
  		
  		fOutputStream = new FileOutputStream(file);
  		int ch = 0;
  		try {
  		while((ch = iStream.read())!=-1) {
  			fOutputStream.write(ch);
  		}
  		} finally {
  			fOutputStream.close();
  			iStream.close();
		}
  		return path;
 
  	}
  	public String pngTojpg(String picturename) throws IOException {
  		BufferedImage bufferedImage;
		bufferedImage = ImageIO.read(new File(picturename));
		BufferedImage newbf = new 
				BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		newbf.createGraphics().drawImage(bufferedImage, 0, 0,new Color(255,255,255),null);
		picturename =picturename.replace(".png",".jpg");
		ImageIO.write(newbf, "jpg", new File(picturename));
		
		return picturename;
  	}
  	
  	public String zippicture(String picturename,int width,int height,float quality) throws IOException {

  		Image spic = ImageIO.read(new File(picturename));
  		int w = spic.getWidth(null);
  		int h = spic.getHeight(null);
  		double ratio =0;
  		if(width>0) {
  			ratio=width/(double)w;
  			height = (int)(h*ratio);
  		}else if(height >0){
  			ratio = height/(double)h;
  			width = (int)(w*ratio);
  		}
  		picturename =picturename.replace(".jpg","1.jpg");
  		BufferedImage newbf = new 
				BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  		Graphics2D graphics = newbf.createGraphics();
  		graphics.setBackground(new Color(255, 255, 255));
  		graphics.setColor(new Color(255, 255, 255));
  		graphics.fillRect(0, 0, width, height);
  		graphics.drawImage(spic.getScaledInstance(width, height, Image.SCALE_SMOOTH),0,0,null);
  		ImageIO.write(newbf, "jpg", new File(picturename));
  		return picturename;
  	}
  	public boolean isExists(String likes,String username) {
  		if ("".equals(likes)||likes == null) {
			return false;
		}
  		for(String s:likes.split(",")) {
				if (s!=null && !"".equals(s)) {
					if (s.equals(username)) {
						return true;
					}
			}
			}
  		return false;
  	}
  	public String getface(Connection connection,String username,String picturespath) throws IOException {
  		String sql = "select photo from profile where username = ?";
  		String userface = "";
  		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, username);
  			ResultSet rset = pStatement.executeQuery();
  			if(rset.next()) {
  				InputStream is= rset.getBinaryStream(1);
  				if (is != null) {
					userface = tofile(is, username, picturespath);
				}
  			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		System.out.println(userface);
		return userface;
  	}
}