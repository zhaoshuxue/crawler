package com.zsx.crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by highness on 2017/8/30 0030.
 */
public class DownloadUtil {

    public static void downloadImage(String imgUrl, String filePath) throws IOException {
        URL url = new URL(imgUrl);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(5 * 1000);
        InputStream is = connection.getInputStream();

        byte[] bytes = new byte[1024];
        int len;
        FileOutputStream os = new FileOutputStream(filePath);
        while ((len = is.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }

    public static void writeTxt(String text, String filePath) throws IOException {
        FileOutputStream os = new FileOutputStream(filePath);
        os.write(text.getBytes("GBK"));
//        os.write(text.getBytes("UTF-8"));
        os.close();
    }
}
