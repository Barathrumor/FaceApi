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

public class httpGet {          //httpGet的使用例子，这个程序中没有用到

	public static String getId() throws URISyntaxException, ClientProtocolException, IOException  {

		URIBuilder builder = new URIBuilder("http://pi.icecream.ml/xhb/DbServlet?method=info");

        URI uri = builder.build();
        HttpGet request = new HttpGet(uri);

		HttpResponse response = new DefaultHttpClient().execute(request);    //此服务器没有设置验证，不需要请求体
       
        HttpEntity entity = response.getEntity(); //得到JSON响应

        String jsonString=EntityUtils.toString(entity).trim();
     
       System.out.println(jsonString);
       int a = jsonString.indexOf("humidity");
        int b = jsonString.indexOf("}");
        String signId= jsonString.substring(a+10, b);
       return signId;
            
	}
	
	

}
