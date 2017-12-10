package Work;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class trainPersonGroup
{
	
    static void trainGroup()         //����PersonGroup����a��������ѵ������personGroup�ô�������������ֻѵ��һ��group
    {

        try
        {
        	HttpClient httpClient = new DefaultHttpClient();
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/a");

            URI uri = builder.build();
            HttpPut request = new HttpPut(uri);

            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

            String body = "{ \"name\":\"My Group\",\"userData\":\"User-provided data attached to the person group.\" }";

            StringEntity reqEntity = new StringEntity(body);
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println("PersonGroup�Ѿ��������");
            
            if (entity != null)
            {
            	System.out.println("PersonGroup�����ظ�����");
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        try
        {
        	HttpClient httpClient = new DefaultHttpClient();
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/a/train");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", Main.subscriptionKey);

            String body = "";                    //����Ҫ������

            StringEntity reqEntity = new StringEntity(body);
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println("PersonGroupѵ���ɹ�");
            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        
        
        
        
    }
}
