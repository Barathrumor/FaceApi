package Work;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
//�ĵ��μ�  https://azure.microsoft.com/zh-cn/try/cognitive-services/?apiSlug=face-api&country=China&allowContact=true

public class Main {

	public static final String subscriptionKey = "c82cbe7b9xxxxxxxx5a4aa3c9bd84a0a";
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		
		File keys=new File("keys.txt");       //�����ļ��������棬�û���Ϣ��ֵ��

		File file2=new File("f:\\faces\\pan1.jpg"); //�ϴ���Ҫ����ͼƬ
		String jsonString=Get.getString(file2);    //��ͼƬ�ķ���String
		String faceid=Get.getId(jsonString);       //��ȡ��ͼƬId
		System.out.println("fan1:"+faceid);
		
		//ÿ�ν���signUp������person�����ѵ��һ��
		trainPersonGroup.trainGroup();   //�������ѵ������
		
		String value=identify.get(faceid);     //���жԱȣ��õ��ԱȽ��
		String theId=identify.getId(faceid);   //�õ�ƥ����ͼƬ��Id
		System.out.println(value);
		
		if(Double.parseDouble(value)>=0.6) {
			Judge.get(theId, keys);                   //����ƥ�䣬�ҵ����ͼƬId��Ӧ������
		}
		else {
			System.out.println("δ��ȷʶ��");
		}
	}
	
	//�ṹ��face ���� facelist ����person ����personGroup
	

}
