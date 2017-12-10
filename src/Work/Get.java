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

           //  请求参数。它们都是可选的。 
           builder.setParameter("returnFaceId", "true");
           builder.setParameter("returnFaceLandmarks", "false");
           builder.setParameter("returnFaceAttributes", "age,gender,smile,glasses,emotion");//可以返回的数据还有很多，在这里用不到就删掉了

           // Prepare the URI for the REST API call.准备URI
           URI uri = builder.build();
           HttpPost request = new HttpPost(uri);

           // 请求的头标识
           request.setHeader("Content-Type", "application/octet-stream");    //请求头
           request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

           // 请求体 标识
            FileEntity reqEntity=new FileEntity(file);
            
           request.setEntity(reqEntity);

           //执行REST API调用并获得响应的实体。 
           HttpResponse response = httpclient.execute(request);
           HttpEntity entity = response.getEntity();               //得到JSON响应

           String jsonString="" ;
           if (entity != null)
           {
               System.out.println("成功得到jsonString");   // JSON格式和显示的响应。 
                jsonString = EntityUtils.toString(entity).trim();  //得到一行json转换文本
           }
           return jsonString;
      
   }
   
   
   static String getId(String jsonString) {
		int a = jsonString.indexOf("top");
	    int b = jsonString.lastIndexOf("top");
	    
	    if(a!=-1) {                   //a=-1,不存在人脸
	    	if(a==b) {                //a！=b，存在多张人脸
	    		int a1 = jsonString.indexOf("faceId");
	    	    int b1 = jsonString.indexOf("faceRectangle");
	    		return jsonString.substring(a1+9, b1-3);
					
				}
	    	else {
	    		return "请拍单人自拍";
	    	}
	    }else {
	    	return "图片输入错误";
	     }
		
	
		
	}


      //得到照片的情感，这个软件中没有用到
	 static String getEmotion(String jsonString,String s) {
		int a = jsonString.indexOf("top");
	    int b = jsonString.lastIndexOf("top");
	    String glass,contemt,surprise,happiness,neutral,sadness,disgust,anger,fear,sex,age,smile;
	    
	    if(a!=-1) {
	    	if(a==b) {
	    		glass = jsonString.substring(135, 144);   //NoGlasses       //这是一段不成熟的代码，不应该用绝对位置来截取字符串
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
					return "输入错误";
					
				}
	    	}else {
	    		return "请拍单人自拍";
	    	}
	    }else {
	    	return "图片输入错误";
	     }
		
	
		
	}
   
   
}
