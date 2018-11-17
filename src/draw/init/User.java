package draw.init;

public class User {
	private String QNumber;
	private String QName;
	private int Count;
	
	public void setNum(String num){
		QNumber=num;
	}
	public void setName(String name){
		QName=name;
	}
	public void setTount(int count){
		Count=count;
	}
	public String getTum(){
		return QNumber;
	}
	public String getTame(){
		return QName;
	}
	public int getCount(){
		return Count;
	}

}
