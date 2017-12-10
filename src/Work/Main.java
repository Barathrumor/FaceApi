package Work;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
//文档参见  https://azure.microsoft.com/zh-cn/try/cognitive-services/?apiSlug=face-api&country=China&allowContact=true

public class Main {

	public static final String subscriptionKey = "c82cbe7b9xxxxxxxx5a4aa3c9bd84a0a";
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		
		File keys=new File("keys.txt");       //建立文件用来储存，用户信息键值对

		File file2=new File("f:\\faces\\pan1.jpg"); //上传需要检测的图片
		String jsonString=Get.getString(file2);    //该图片的返回String
		String faceid=Get.getId(jsonString);       //截取该图片Id
		System.out.println("fan1:"+faceid);
		
		//每次进行signUp，创建person后必须训练一次
		trainPersonGroup.trainGroup();   //创建人物，训练人物
		
		String value=identify.get(faceid);     //进行对比，得到对比结果
		String theId=identify.getId(faceid);   //得到匹配结果图片的Id
		System.out.println(value);
		
		if(Double.parseDouble(value)>=0.6) {
			Judge.get(theId, keys);                   //进行匹配，找到结果图片Id对应的人名
		}
		else {
			System.out.println("未正确识别");
		}
	}
	
	//结构：face 属于 facelist 属于person 属于personGroup
	

}
