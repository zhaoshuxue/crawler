package com.zsx.util;

import com.google.common.collect.Lists;
import com.zsx.entity.Tarticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by highness on 2018/5/31 0031.
 */
public class GetPageUtil {


    public static List<Tarticle> parsePage(String url) {

        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .timeout(30000)
                    .validateTLSCertificates(false)
                    .get();

//          获取内容主体
            Element content = document.getElementById("page-content");
//            得到内容列表
            Elements elements = content.getElementsByTag("article");

            ArrayList<Tarticle> articleList = Lists.newArrayList();
            Tarticle tarticle;
            for (Element article : elements) {
                tarticle = new Tarticle();
//
                Elements elementsByClass = article.getElementsByClass("posts-gallery-img");

                Element element1 = elementsByClass.get(0);

//                获取链接
                Elements a = element1.getElementsByTag("a");

                boolean href = a.hasAttr("href");
                boolean title = a.hasAttr("title");
                if (href && title) {

                    tarticle.setHref(a.attr("href").trim());
                    tarticle.setTitle(a.attr("title").trim());
                    tarticle.setSuccess("0");
                    tarticle.setContent(element1.html());
                    tarticle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                    articleList.add(tarticle);
                }
            }
            return articleList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String loadArticleContent(String url) {
        url = "https://qingniantuzhai.com" + url;
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .timeout(30000)
                    .validateTLSCertificates(false)
                    .get();
            return document.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
