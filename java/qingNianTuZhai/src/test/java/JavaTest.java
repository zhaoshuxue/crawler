import com.zsx.crawler.DownloadUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * Created by highness on 2017/8/27 0027.
 */
public class JavaTest {
    //  http://www.qingniantuzhai.com/home/?page=166
//    http://www.qingniantuzhai.com/posts/3360.html
    static String url = "http://www.qingniantuzhai.com/posts/3360.html";
    static String url2 = "http://www.qingniantuzhai.com/posts/421.html";

    public static void main2(String[] args) throws Exception {

        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        Element content = document.getElementById("content");
//        System.out.println(content.html());

        Elements elements = content.getElementsByTag("p"); // p  span
        System.out.println(elements.size());
        for (Element element : elements) {
            Elements imgs = element.getElementsByTag("img");
            if (imgs.size() == 0) {
//                标题
                System.out.println(element.html());
            } else {
//                图片
                System.out.println(imgs.size());
                for (Element img : imgs) {
                    String data_src = img.attr("data-src");
                    System.out.println(data_src);
                }
            }
            System.out.println();
        }


    }

    public static void main(String[] args) throws Exception {
//        downloadImg("http://ww3.sinaimg.cn/mw690/70e0a133gw1ergqhniti0j20c80i7q50.jpg");

//        downloadImage("http://wx2.sinaimg.cn/mw690/0067cwswgy1fizva5r7sqg30dw07thdv.gif",
//                "D:\\1\\img\\" + UUID.randomUUID().toString() + ".gif");

//        DownloadUtil.writeTxt("JAVA 创建TXT文件，写入文件内容，读取文件内容", "D:\\1\\img\\" + UUID.randomUUID().toString() + ".txt");
        String uu = "12345.igf";
        int i = uu.lastIndexOf(".");
        System.out.println(i);
        System.out.println(uu.substring(i+1));
    }

    public static void downloadImg(String imgUrl) throws Exception {
//        HttpHead httpHead = new HttpHead(imgUrl);
        HttpGet httpGet = new HttpGet(imgUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取HTTP状态码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) throw new Exception("资源不存在!");
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();

        File file = new File("D:\\1\\img\\"+ UUID.randomUUID().toString()+".jpg");
        FileOutputStream fileout = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int ch = 0;
        while ((ch = is.read(buffer)) != -1) {
            fileout.write(buffer, 0, ch);
        }
        is.close();
        fileout.flush();
        fileout.close();
    }

    public static void downloadImage(String imgUrl, String filePath) throws IOException {
        URL url = new URL(imgUrl);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(5*1000);
        InputStream is = connection.getInputStream();

        byte[] bytes = new byte[1024];
        int len;

        FileOutputStream os = new FileOutputStream(filePath);
        while ((len = is.read(bytes)) != -1){
            os.write(bytes, 0, len);
        }

        os.flush();
        os.close();
        is.close();
    }
}
