import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by highness on 2017/9/24 0024.
 */
public class HttpTest {

    public static void main(String[] args) {
        HttpGet httpGet = new HttpGet("http://zhaoshuxue.xyz");
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            System.out.println(statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
