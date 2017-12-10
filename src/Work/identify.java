package Work;
import java.io.IOException;
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class identify
{
    public static String get(String faceId) throws ParseException, IOException, URISyntaxException
    {
        HttpClient httpClient = new DefaultHttpClient();
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/identify");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);
            
            //将一个待验证图片Id和训练的person们进行对比，返回相似度
            
            String body = "{  \"personGroupId\":\"a\", \"faceIds\":[\""+faceId+"\" ],  \"maxNumOfCandidatesReturned\":1, \"confidenceThreshold\": 0.5}";

            StringEntity reqEntity = new StringEntity(body);
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            String s=EntityUtils.toString(entity);
            System.out.println(s);
            if(s.indexOf("confidence")!=-1) {
            	 int a = s.indexOf("confidence");
         	    int b = s.indexOf("}]");
         		return s.substring(a+12, b);
            }
            
            else 
            	return "0";
            
           
       
    }
    
    public static String getId(String faceId) throws ParseException, IOException, URISyntaxException
    {
        HttpClient httpClient = new DefaultHttpClient();
      
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/identify");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);
            
          //将一个待验证图片Id和训练的person们进行对比，返回candidate的id
            
            String body = "{  \"personGroupId\":\"a\", \"faceIds\":[\""+faceId+"\" ],  \"maxNumOfCandidatesReturned\":1, \"confidenceThreshold\": 0.5}";

            StringEntity reqEntity = new StringEntity(body);
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            String s=EntityUtils.toString(entity);
            if(s.indexOf("confidence")!=-1) {
            	 int a = s.indexOf("personId");
         	    int b = s.indexOf("confidence");
         		return s.substring(a+11, b-3);
            }
            
            else 
            	return "0";
            
           
       
    }
    
}
