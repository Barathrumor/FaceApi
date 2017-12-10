package Work;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class httpGet {          //httpGet��ʹ�����ӣ����������û���õ�

	public static String getId() throws URISyntaxException, ClientProtocolException, IOException  {

		URIBuilder builder = new URIBuilder("http://pi.icecream.ml/xhb/DbServlet?method=info");

        URI uri = builder.build();
        HttpGet request = new HttpGet(uri);

		HttpResponse response = new DefaultHttpClient().execute(request);    //�˷�����û��������֤������Ҫ������
       
        HttpEntity entity = response.getEntity(); //�õ�JSON��Ӧ

        String jsonString=EntityUtils.toString(entity).trim();
     
       System.out.println(jsonString);
       int a = jsonString.indexOf("humidity");
        int b = jsonString.indexOf("}");
        String signId= jsonString.substring(a+10, b);
       return signId;
            
	}
	
	

}
