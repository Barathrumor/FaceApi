package Work;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.util.HashMap;

public class signUp {

	
	static HashMap<String, String> map = new HashMap<String, String>();
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
	
		try {
			File keys=new File("keys.txt");
			File file=new File("f:\\faces\\pan.jpg");         //����ѵ����ͼƬ
			 FileInputStream freader;
			freader = new FileInputStream(keys);
			@SuppressWarnings("resource")
			ObjectInputStream objectInputStream = new ObjectInputStream(freader);
			map=(HashMap<String, String>) objectInputStream.readObject();
			
			String name="panjiangfan";              //ѵ����person��name
			String personId=Add.get(name,file);     //����������һ��person������id
			map.put( personId,name);                 //��һ����ֵ�Դ���map��ע��˳��
			                                         //�������ĺô���ͬһ��ͼƬ�ϴ�һ��getһ��id���������жϵ�ʱ��ֻ�᷵�ص�һ���ϴ���id
			                                          //����������ѵ�һ���ϴ��õ���id���ǡ���hashmap��������ͬ��value��
			Save.get(map, keys);                    //��map�����ĵ���
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
