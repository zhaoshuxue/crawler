import com.alibaba.fastjson.JSON;
import com.zsx.entity.Tarticle;
import com.zsx.util.GetPageUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by highness on 2018/5/30 0030.
 */
public class UnitTest1 {

    public static void main(String[] args) {
        String url = "http://qingniantuzhai.com/page/2/";

        List<Tarticle> tarticles = GetPageUtil.parsePage(url);

        System.out.println(JSON.toJSONString(tarticles));
    }

    public static void main2(String[] args) throws Exception {
        String url = "http://qingniantuzhai.com/page/2/";

        Document document = Jsoup.parse(new File("D:\\git\\zhaoshuxue\\crawler\\java\\springboot-qntz\\src\\test\\java\\page2.html"), "utf-8");

//        System.out.println(document.html());

        Element content = document.getElementById("page-content");

//        System.out.println(content.html());

        Elements elements = content.getElementsByTag("article");

        int i=1;
        for (Element element : elements) {
//            System.out.println(element.html());

            Elements elementsByClass = element.getElementsByClass("posts-gallery-img");

            Element element1 = elementsByClass.get(0);

            Elements a = element1.getElementsByTag("a");

            boolean href = a.hasAttr("href");
            if (href){
                System.out.println(a.attr("href"));
            }
            boolean title = a.hasAttr("title");
            if (title){
                System.out.println(a.attr("title"));
            }
//            System.out.println(a.outerHtml());

            System.out.println();
            System.out.println(i++);
            System.out.println();
        }

//

//        Document document = Jsoup.connect(url)
//                .timeout(30000)
////                .userAgent()
//                .validateTLSCertificates(false)
//                .get();
//
//        System.out.println(document.html());




    }

}
