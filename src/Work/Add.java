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
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Add {    //此方法和服务器进行两次交互

	 static String get(String name,File file) throws URISyntaxException, ClientProtocolException, IOException {
		 
	    //创建人物
     	HttpClient httpClient = new DefaultHttpClient();    
         URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/a/persons");

         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);

         request.setHeader("Content-Type", "application/json");     //String格式的实体，用 application/json****
         request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

         String body = "{ \"name\":\""+name+"\",\"userData\":\"a star\" }";  //代入用户名，创建一个person

         StringEntity reqEntity = new StringEntity(body);
         request.setEntity(reqEntity);

         HttpResponse response = httpClient.execute(request);
         HttpEntity entity = response.getEntity();         //得到服务器响应

         String personId = null;
         String s=EntityUtils.toString(entity);        
         if (entity != null)
         {
        	int a = s.indexOf("personId");         //剪切字符串
        	int b = s.indexOf("\"}");
         	personId= s.substring(a+11, b);
         	System.out.println(personId);
          }
        

         
         
	     //上传人物的照片
         HttpClient httpClient2 = new DefaultHttpClient();
         URIBuilder builder2 = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/a/persons/"+personId+"/persistedFaces");

         URI uri2 = builder2.build();
         HttpPost request2 = new HttpPost(uri2);

         request2.setHeader("Content-Type", "application/octet-stream");      //*****上传二进制流形式的图片，需要改申请头
         request2.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

         FileEntity reqEntity2 = new FileEntity(file);              //这个格式的实体可移植性不高
         request2.setEntity(reqEntity2);

         HttpResponse response2 = httpClient2.execute(request2);
         HttpEntity entity2 = response2.getEntity();

         if (entity2 != null)
         {
        	 System.out.println("add成功");
        	 System.out.println(EntityUtils.toString(entity2));
         }
         
         return personId;
     
	 }
    
}

