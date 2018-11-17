package draw.algorithm;


import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.Iterator;

public class First {
	private Set<String> namelist=new TreeSet<>();
	private int repeat=1;
	private String list[];
	public void getlist() {}
	public void put_Qname()
	{
		while(repeat!=0)
		{
			repeat--;
			 int l=0;
			 Iterator<String> iter = namelist.iterator();
			 while (iter.hasNext()) {
				 list[l]= iter.next();
			     l++;
				 }
			 Random rand = new Random();
			 int cur=(int)(Math.random()*500);   
			 cur=cur%l;
			 String QName=list[cur];
			 System.out.print(QName);
			 namelist.remove(QName);
		}
	}
}
