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
		     HashMap<String,String> map = new HashMap<String,String>();                 //�Ѵ����ֵ�Ե���Ϣ���ļ���ȡ�����ٽ����޸�
		            
		     map=(HashMap<String, String>) objectInputStream.readObject();
		     String theOne=map.get(theId);                                //����ʱ name��Ϊvalue���棬����һ��id��Ϊһ��Ψһ��key��
		     System.out.println("���ã�"+theOne+",������ӭ��");
		     
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	     

	}

}
