package Work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Save {
	
	public static void get(HashMap<String, String> map,File file) {

		 try {
			 FileOutputStream outStream = new FileOutputStream(file);
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
			 objectOutputStream.writeObject(map);                  //��hashmap��ʵ�����洢���ļ�
			 outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        System.out.println("successful");

		
	}

}
