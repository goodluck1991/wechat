import com.jgj.wechat.course.menu.*;
import com.jgj.wechat.course.util.MyX509TrustManager;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

/**
 * Created by guojun.jiao on 2018/5/31.
 */
public class ButtonTest {
    public static void main(String[] args) throws Exception{
        ClickButton clickButton = new ClickButton();
        clickButton.setName("今日歌曲");
        clickButton.setType("click");
        clickButton.setKey("V1001_TODAY_MUSIC");

        ViewButton viewButton = new ViewButton();
        viewButton.setName("歌手简介");
        viewButton.setType("view");
        viewButton.setUrl("http://www.qq.com/");

        ClickButton clickButton1 = new ClickButton();
        clickButton1.setName("hello word");
        clickButton1.setType("click");
        clickButton1.setKey("V1001_HELLO_WORD");

        ClickButton clickButton2 = new ClickButton();
        clickButton2.setName("赞我们一下");
        clickButton2.setType("click");
        clickButton2.setKey("V1001_GOOD");

        ComplexButton complexButton = new ComplexButton();
        complexButton.setName("菜单");
        complexButton.setSub_button(new Button[]{clickButton1,clickButton2});

        Menu menu = new Menu();
        menu.setButton(new Button[]{clickButton,viewButton,complexButton});

        String jsonMenu = JSONObject.fromObject(menu).toString();

        System.out.println(jsonMenu);

        String accessToken = "10_isxmXdg7n_hcVqGgWSZalx5NnrEOIXPogG8DiRbSYV7lq6qdXoFHVsh_pzEw2OQkZiDdB_Dg-yevYTgzyIVs4ysf1HNvaOYJtj0KKSk_zkVbORphF6gXF7nc3BlO73qJaqXqk2QH0fycfLwUUXEgAGAMPW";
        String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;

        URL url = new URL(menuCreateUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
        sslContext.init(null,tm,new SecureRandom());
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        httpsURLConnection.setSSLSocketFactory(ssf);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setRequestMethod("POST");
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        outputStream.write(jsonMenu.getBytes("UTF-8"));
        outputStream.close();

        InputStream inputStream = httpsURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer stringBuffer = new StringBuffer();
        String str = null;
        while ((str=bufferedReader.readLine())!=null){
            stringBuffer.append(str);
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        httpsURLConnection.disconnect();
        System.out.println(stringBuffer);


    }

}
