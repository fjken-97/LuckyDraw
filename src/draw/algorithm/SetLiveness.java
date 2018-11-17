package draw.algorithm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.Date;
import draw.text.Mysql;

public class SetLiveness {
	public static int setLiveness(Date date){
		try {
			Connection con = Mysql.getCon();
//			if(!con.isClosed())
//	            System.out.println("Succeeded connecting to the Database!");
	        Statement statement = con.createStatement();     
			String select_SQL = "select * from  postcomment";
			String update_SQL="update user set weight=weight+1 where qq=?";
			PreparedStatement pstmt=con.prepareStatement(update_SQL);
			ResultSet rs = statement.executeQuery(select_SQL);
//	        System.out.println("-----------------");
//	        System.out.println("执行结果如下所示:");  
//	        System.out.println("-----------------");  
//	        System.out.println("commentId" + "\t" + "postTime" + "\t" + "postContent" + "\t" + "userId");  
//	        System.out.println("-----------------");         
	        String commentId = null;
	        Date postTime = null;
	        String postContent = null;
	        String userId=null;
	        while(rs.next()){
	        	commentId = rs.getString("commentId");
	        	postTime = rs.getDate("postTime");
	        	postContent = rs.getString("postContent");
	        	userId = rs.getString("userId");
	            System.out.println(commentId + " " + postTime+ " " + postContent+ " " + userId);
	            if(commentId.length()!=0|commentId!="") {
		            pstmt.setString(1, userId);
		            int res=pstmt.executeUpdate();
					if(res>0){
						System.out.println("更新数据成功");
					}
	            }
	        }
	        rs.close();
	        pstmt.close();
	        con.close();
	    } catch(Exception e) {   
	        System.out.println("Sorry,can`t find the Driver!");   
	        e.printStackTrace();   
	    }finally{
	        System.out.println("数据库数据成功获取！！");
	    }
			return 0;
		}
}
