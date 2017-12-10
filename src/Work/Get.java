package Work;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class Get
{

   public static String getString(File file) throws ClientProtocolException, IOException, URISyntaxException
   {
       HttpClient httpclient = new DefaultHttpClient();

   
           URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect");

           //  ������������Ƕ��ǿ�ѡ�ġ� 
           builder.setParameter("returnFaceId", "true");
           builder.setParameter("returnFaceLandmarks", "false");
           builder.setParameter("returnFaceAttributes", "age,gender,smile,glasses,emotion");//���Է��ص����ݻ��кܶ࣬�������ò�����ɾ����

           // Prepare the URI for the REST API call.׼��URI
           URI uri = builder.build();
           HttpPost request = new HttpPost(uri);

           // �����ͷ��ʶ
           request.setHeader("Content-Type", "application/octet-stream");    //����ͷ
           request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

           // ������ ��ʶ
            FileEntity reqEntity=new FileEntity(file);
            
           request.setEntity(reqEntity);

           //ִ��REST API���ò������Ӧ��ʵ�塣 
           HttpResponse response = httpclient.execute(request);
           HttpEntity entity = response.getEntity();               //�õ�JSON��Ӧ

           String jsonString="" ;
           if (entity != null)
           {
               System.out.println("�ɹ��õ�jsonString");   // JSON��ʽ����ʾ����Ӧ�� 
                jsonString = EntityUtils.toString(entity).trim();  //�õ�һ��jsonת���ı�
           }
           return jsonString;
      
   }
   
   
   static String getId(String jsonString) {
		int a = jsonString.indexOf("top");
	    int b = jsonString.lastIndexOf("top");
	    
	    if(a!=-1) {                   //a=-1,����������
	    	if(a==b) {                //a��=b�����ڶ�������
	    		int a1 = jsonString.indexOf("faceId");
	    	    int b1 = jsonString.indexOf("faceRectangle");
	    		return jsonString.substring(a1+9, b1-3);
					
				}
	    	else {
	    		return "���ĵ�������";
	    	}
	    }else {
	    	return "ͼƬ�������";
	     }
		
	
		
	}


      //�õ���Ƭ����У���������û���õ�
	 static String getEmotion(String jsonString,String s) {
		int a = jsonString.indexOf("top");
	    int b = jsonString.lastIndexOf("top");
	    String glass,contemt,surprise,happiness,neutral,sadness,disgust,anger,fear,sex,age,smile;
	    
	    if(a!=-1) {
	    	if(a==b) {
	    		glass = jsonString.substring(135, 144);   //NoGlasses       //����һ�β�����Ĵ��룬��Ӧ���þ���λ������ȡ�ַ���
	    		contemt = jsonString.substring(181, 188);	 //contempt
	    		surprise = jsonString.substring(206, 213);      //surprise
	    		happiness = jsonString.substring(232, 239);      //happiness
	    		neutral= jsonString.substring(257, 262);      //neutral
	    		sadness = jsonString.substring(266, 270);      //sadness
	    		disgust = jsonString.substring(280, 287);      //disgust
	    		anger= jsonString.substring(305, 311);       //anger
	    		fear = jsonString.substring(326, 333);       //fear
	    		sex = jsonString.substring(372, 378);      //female
	    		age = jsonString.substring(391, 397);		//age
	    		smile = jsonString.substring(410, 418);      //smile
	    		switch (s) {
				case "glass":
					return glass;
				case "contemt":
					return contemt;
				case "surprise":
					return surprise;
				case "happiness":
					return happiness;
				case "neutral":
					return neutral;
				case "sadness":
					return sadness;
				case "disgust":
					return disgust;
				case "anger":
					return anger;
				case "fear":
					return fear;
				case "sex":
					return sex;
				case "age":
					return age;
				case "smile":
					return smile;
				
				default:
					return "�������";
					
				}
	    	}else {
	    		return "���ĵ�������";
	    	}
	    }else {
	    	return "ͼƬ�������";
	     }
		
	
		
	}
   
   
}
