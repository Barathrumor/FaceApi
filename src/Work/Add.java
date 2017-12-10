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

public class Add {    //�˷����ͷ������������ν���

	 static String get(String name,File file) throws URISyntaxException, ClientProtocolException, IOException {
		 
	    //��������
     	HttpClient httpClient = new DefaultHttpClient();    
         URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/a/persons");

         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);

         request.setHeader("Content-Type", "application/json");     //String��ʽ��ʵ�壬�� application/json****
         request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

         String body = "{ \"name\":\""+name+"\",\"userData\":\"a star\" }";  //�����û���������һ��person

         StringEntity reqEntity = new StringEntity(body);
         request.setEntity(reqEntity);

         HttpResponse response = httpClient.execute(request);
         HttpEntity entity = response.getEntity();         //�õ���������Ӧ

         String personId = null;
         String s=EntityUtils.toString(entity);        
         if (entity != null)
         {
        	int a = s.indexOf("personId");         //�����ַ���
        	int b = s.indexOf("\"}");
         	personId= s.substring(a+11, b);
         	System.out.println(personId);
          }
        

         
         
	     //�ϴ��������Ƭ
         HttpClient httpClient2 = new DefaultHttpClient();
         URIBuilder builder2 = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/a/persons/"+personId+"/persistedFaces");

         URI uri2 = builder2.build();
         HttpPost request2 = new HttpPost(uri2);

         request2.setHeader("Content-Type", "application/octet-stream");      //*****�ϴ�����������ʽ��ͼƬ����Ҫ������ͷ
         request2.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

         FileEntity reqEntity2 = new FileEntity(file);              //�����ʽ��ʵ�����ֲ�Բ���
         request2.setEntity(reqEntity2);

         HttpResponse response2 = httpClient2.execute(request2);
         HttpEntity entity2 = response2.getEntity();

         if (entity2 != null)
         {
        	 System.out.println("add�ɹ�");
        	 System.out.println(EntityUtils.toString(entity2));
         }
         
         return personId;
     
	 }
    
}

