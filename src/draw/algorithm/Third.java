package draw.algorithm;

import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.Iterator;

public class Third {
	private Set<String> namelist=new TreeSet<>();
	private int repeat=1;
	private String list[];
	private int frequent[];
	public void getlist() {}
	public void getfrequent() {}
	public void put_Qname()
	{
		while(repeat!=0)
		{
			repeat--;
			 int l=0;
			 int f=0;
			 Iterator<String> iter = namelist.iterator();
			 while (iter.hasNext()) {
				 String s=iter.next();
				 for(int i=0;i<frequent[f];i++) {
				 list[l]= s;
			     l++;
				 }
				 f++;
			 }
			 Random rand = new Random();
			 int cur=(int)(Math.random()*500);  
			 cur=cur%l;
			 for(int i=0;i<l;i++)
			 {
				 if(i==0 && list[i]==list[i+1])
				 {
					 list[i]=list[cur];
				 }
				 else if(list[i]==list[i+1] && list[i]!=list[i-1])
				 {
					 list[i]=list[cur];
				 }
			 }
			 rand = new Random();
			 cur=(int)(Math.random()*500);   
			 cur=cur%l;
			 String QName=list[cur];
			 System.out.print(QName);
			 namelist.remove(QName);
		}
	}
}
