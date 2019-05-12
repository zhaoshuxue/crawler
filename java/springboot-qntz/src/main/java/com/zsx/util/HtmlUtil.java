package com.zsx.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by highness on 2017/8/30 0030.
 */
public class HtmlUtil {

    public static void main(String[] args) {
        try {
            test("http://www.qingniantuzhai.com/posts/3364.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String filePath = "D:\\1\\img\\";

    public static void test(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        Element content = document.getElementById("content");

        Elements elements = content.getElementsByTag("p"); // p  span
        int n = 1;
        for (Element element : elements) {
            Elements imgs = element.getElementsByTag("img");
            if (imgs.size() == 0) {
//                标题
                System.out.println(element.html());
                DownloadUtil.writeTxt(element.html(), filePath + "" + n + ".txt");
                n++;
            } else {
//                图片
                System.out.println(imgs.size());
                for (Element img : imgs) {
                    String data_src = img.attr("data-src");
                    System.out.println(data_src);
                    int indexOf = data_src.lastIndexOf(".");
                    String postfix = data_src.substring(indexOf);
                    DownloadUtil.downloadImage(data_src,
                            filePath + "" + n + "_" + UUID.randomUUID().toString() + "" + postfix);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println();
        }
    }

}
