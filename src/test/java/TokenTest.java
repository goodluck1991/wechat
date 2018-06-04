import com.jgj.wechat.course.util.MyX509TrustManager;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;

/**
 * 测试获取凭证(HTTPS GET请求)
 * Created by guojun.jiao on 2018/5/16.
 */
public class TokenTest {
    public static void main(String[] args) throws Exception{
//        获取凭证接口地址
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_" +
                "credential&appid=wx4bf356a02a40f532&secret=44e23377e77bdc74e7ab2f7ec3639cbf";
//        建立连接
        URL url = new URL(tokenUrl);
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
//        使用自定义的信任管理器
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
        sslContext.init(null,tm,new SecureRandom());
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        connection.setSSLSocketFactory(ssf);
        connection.setDoInput(true);
//        设置请求方式
        connection.setRequestMethod("GET");
//        取得输入流
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        读取响应内容
        StringBuffer buffer = new StringBuffer();
        String str = null;
        while((str = bufferedReader.readLine())!=null){
            buffer.append(str);
        }
//        关闭/释放资源
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        connection.disconnect();
//        输出返回结果
        System.out.println(buffer.toString());

        JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
        String accessToken = jsonObject.getString("access_token");
        String expiresIn = jsonObject.getString("expires_in");
        System.out.println(accessToken+"...."+expiresIn);

    }
}
