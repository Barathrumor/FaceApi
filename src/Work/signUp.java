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
			File file=new File("f:\\faces\\pan.jpg");         //用作训练的图片
			 FileInputStream freader;
			freader = new FileInputStream(keys);
			@SuppressWarnings("resource")
			ObjectInputStream objectInputStream = new ObjectInputStream(freader);
			map=(HashMap<String, String>) objectInputStream.readObject();
			
			String name="panjiangfan";              //训练的person的name
			String personId=Add.get(name,file);     //创建这样的一个person，返回id
			map.put( personId,name);                 //把一个键值对存入map，注意顺序
			                                         //这样做的好处：同一张图片上传一次get一个id，而进行判断的时候，只会返回第一次上传的id
			                                          //这样做不会把第一次上传得到的id覆盖。（hashmap可以有相同的value）
			Save.get(map, keys);                    //把map存入文档中
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
