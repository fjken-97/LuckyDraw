package draw.text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import draw.text.Inputtext;
import draw.algorithm.SetLiveness;

public class Mysql {
	
	public static String name;
	public static String qqnum;
	public static String time;
	public static String content;
	
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/luckydraw";
	static String user = "root";
	static String password = "root";
	
	public static void connectSql() {	
	    Connection con;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date dateTime;
	    
	    try {
	        con = getCon();
	        if(!con.isClosed())
	            System.out.println("Succeeded connecting to the Database!");
	        Statement statement = con.createStatement();
	        String select_SQL = "select * from user";
	        String insert_SQL = "insert into user(qq, name) values(?, ?)";
	        //插
	        PreparedStatement stmt = con.prepareStatement(insert_SQL);   //会抛出异常
	        for(String item:Inputtext.list.toArray(new String[Inputtext.list.size()])) {
	        	matchSort(item);
	        	stmt.setString(1, name);         //设置SQL语句第一个“？”的值
		        stmt.setString(2, qqnum);    //设置SQL语句第二个“？”的值
//		        stmt.setInt(3, SetLiveness.setLiveness(formatter.parse(time)));        //设置SQL语句第三个“？”的值
		        stmt.executeUpdate();
	        }
	        //查
	        ResultSet rs = statement.executeQuery(select_SQL);
	        System.out.println("-----------------");
	        System.out.println("执行结果如下所示:");  
	        System.out.println("-----------------");  
	        System.out.println("qq" + "\t" + "姓名");  
	        System.out.println("-----------------");         
	        String qq = null;
	        String name = null;
	        while(rs.next()){
	            qq = rs.getString("qq");
	            name = rs.getString("name");
	            System.out.println(qq + " " + name);
	        }
	        rs.close();
	        con.close();
	    } catch(Exception e) {   
	        System.out.println("Sorry,can`t find the Driver!");   
	        e.printStackTrace();   
	    }finally{
	        System.out.println("数据库数据成功获取！！");
	    }
	}
	
	public static Connection getCon() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
        Connection con = DriverManager.getConnection(url,user,password);
		return con;	
	}
	
	public static void matchSort(String str) throws SQLException, ClassNotFoundException {	 
		Connection con = getCon();
        if(!con.isClosed())
            System.out.println("Succeeded connecting to the Database!");
        Statement statement = con.createStatement();
        String insert_SQL = "insert into user(qq, name) values(?, ?)";
        String insert_contentSQL = "insert into postcomment(postTime,postContent,userId) values(?, ?, ?)";
        //插
        PreparedStatement stmt_user = con.prepareStatement(insert_SQL);   //会抛出异常
        PreparedStatement stmt_comment = con.prepareStatement(insert_contentSQL);   //会抛出异常

		String rName = "(?:\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} )(.{0,16})(?:\\(|<)";
	    String rQNum = "[\\(|<](.{1,30})[\\)|>]";
	    String rTime = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
	    String rTopic = "(?:\\)|>)(.*)";
	    try {
	    	Pattern pattern1 = Pattern.compile(rName);
	    	Pattern pattern2 = Pattern.compile(rQNum);
	    	Pattern pattern3 = Pattern.compile(rTime);
	    	Pattern pattern4 = Pattern.compile(rTopic);
	
	    	Matcher matcher1 = pattern1.matcher(str);
	    	Matcher matcher2 = pattern2.matcher(str);
	    	Matcher matcher3 = pattern3.matcher(str);
	    	Matcher matcher4 = pattern4.matcher(str);
	    
	    	matcher1.find();
	    	matcher2.find();
	    	matcher3.find();
	    	matcher4.find();
	    	
	    	String name = matcher1.group(1);
	    	String num = matcher2.group(1);
	    	String time = matcher3.group();
	    	String content = matcher4.group(1);
	    	
	    	stmt_user.setString(1, name);
	    	stmt_user.setString(2, num);
	    	
	    	stmt_comment.setString(1,time);
	    	stmt_comment.setString(2,content);
	    	stmt_comment.setString(3,num);
	    	
	    	stmt_user.setString(1, name);
	    	stmt_user.setString(2, num);
	    	
	    	stmt_comment.setString(1,time);
	    	stmt_comment.setString(2,content);
	    	stmt_comment.setString(3,num);
		}catch(Exception e){
	    	
	    }
	    con.close();
	}
	
}
