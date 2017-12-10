package Work;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Judge {

	@SuppressWarnings("unchecked")
	public static void get(String theId,File file) {

		 FileInputStream freader; 
		try {
			freader = new FileInputStream(file);
			 @SuppressWarnings("resource")
			ObjectInputStream objectInputStream = new ObjectInputStream(freader);
		     HashMap<String,String> map = new HashMap<String,String>();                 //把储存键值对的信息从文件中取出来再进行修改
		            
		     map=(HashMap<String, String>) objectInputStream.readObject();
		     String theOne=map.get(theId);                                //储存时 name作为value储存，这样一个id作为一个唯一的key。
		     System.out.println("您好："+theOne+",西安欢迎你");
		     
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	     

	}

}
