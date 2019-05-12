package com.zsx.config.timer;

import com.zsx.entity.Tuser;
import com.zsx.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by highness on 2017/8/27 0027.
 */
@Component
public class Timer {


    @Autowired
    private UserService userService;

//                    秒  分  时  天  月 星期 年
    @Scheduled(cron = "0 0/1 * * * ?") // 1分钟一次
//    @Scheduled(cron = "0/30 * * * * ?") // 30秒一次
    public void test(){
        System.out.println(new Date());
        Tuser user = new Tuser();
        user.setUsername(new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date()));
        user.setNickname(ping("http://www.zhaoshuxue.xyz"));
        user.setPassword(String.valueOf(new Random().nextInt(99999999)));

        userService.addUser(user);
    }


    public static String ping(String imgUrl){
//        HttpHead httpHead = new HttpHead(imgUrl);
        HttpGet httpGet = new HttpGet(imgUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
//        	new HttpPost().setEntity(entity);
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(1 * 1000) // 创建连接的最长时间
                    .setConnectionRequestTimeout(1 * 1000)// 从连接池中获取到连接的最长时间
                    .setSocketTimeout(10 * 1000) // 数据传输的最长时间
                    .build();

            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            //获取HTTP状态码
            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != 200) throw new Exception("资源不存在!");
//            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
//            System.out.println(content);
            if (statusCode == 200)
                return "1";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }

}
/*
一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。
按顺序依次为
秒（0~59）
分钟（0~59）
小时（0~23）
天（月）（0~31，但是你需要考虑你月的天数）
月（0~11）
天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
7.年份（1970－2099）
 */

/**
 cron表达式：
 // 每天早八点到晚八点，间隔2分钟执行任务
 @Scheduled(cron="0 0/2 8-20 * * ?")

 每两秒执行一次
 0/2 * * * * ?
*/