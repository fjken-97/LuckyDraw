package draw.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import draw.text.Mysql;

public class Inputtext {
	public static List<String> list;
	
	public static void setFileContext(String path) throws Exception {
		FileReader fileReader =new FileReader(path);
        @SuppressWarnings("resource")
		BufferedReader bufferedReader =new BufferedReader(fileReader);
        list =new ArrayList<String>();
        String str=null;
        String term="";
        Pattern pattern = Pattern.compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}");
        while((str=bufferedReader.readLine())!=null) {
        	if(str.trim().length()>=0) {
        		term+=str+" ";
        		Matcher matcher = pattern.matcher(str);
        		if(!matcher.find()) {
        			list.add(term + "\n");
        			term="";
        		}
        	}
    		for(String item:Inputtext.list.toArray(new String[Inputtext.list.size()])) {
    				Mysql.matchSort(item);
    		}
        }
//        System.out.println(list);
   }
}
	
//}
