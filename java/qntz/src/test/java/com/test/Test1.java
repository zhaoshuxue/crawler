package com.test;

/**
 * @RunWith(SpringRunner.class)
 * @SpringBootTest(classes = SampleController.class)
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.zsx.App;
import com.zsx.dao.TarticleDao;
import com.zsx.dao.TimageDao;
import com.zsx.entity.Tarticle;
import com.zsx.entity.Timage;
import com.zsx.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Test1 {

    @Autowired
    UserService userService;

    @Autowired
    private TarticleDao tarticleDao;


    @Autowired
    private TimageDao timageDao;

    @Test
    public void t1() {

        Integer n = 2;
        boolean flag = true;

        while (flag) {
            System.out.println(8899);
            System.out.println("开始下载第几页：" + n);
            JSONObject object = userService.addArticle(n);
            System.out.println(object.toJSONString());
            System.out.println();
            Integer success = object.getInteger("success");
            Integer fail = object.getInteger("fail");

            if (success == 25 && fail == 0) {
                n++;
            } else {
                flag = false;
            }
        }
    }


    @Test
    public void t2() {
        JSONObject object = userService.loadArticleContent();
    }

    @Test
    public void t3() {
        Tarticle tarticle = tarticleDao.selectByPrimaryKey(57);

        System.out.println(JSON.toJSONString(tarticle));

        String content2 = tarticle.getContent();

        Document document = Jsoup.parse(content2);


        //          获取内容主体
        Element content = document.getElementById("page-content");

        Elements postContentClass = content.getElementsByClass("post-content");

        if (postContentClass != null && postContentClass.size() == 1) {
            Element postContent = postContentClass.get(0);


            Elements elements = postContent.getElementsByTag("p");

            for (Element element : elements) {
                System.out.println(element.html()); // 这个要存
//
                System.out.println(element.text());
                Elements imgs = element.getElementsByTag("img");
                System.out.println("图片个数:" + imgs.size());
                for (Element img : imgs) {
                    String src = img.attr("src");
                    System.out.println(src);
                }

                System.out.println();
            }

        }


    }

    @Test
    public void t4() {


        Timage timage = new Timage();
        timage.setPid(111);
        timage.setContent("");
        timage.setTitle("");
        timage.setMore(1);
        timage.setSrc("");
        timage.setStatus(0);
        timage.setDisk("");
        timage.setPath("");
        timage.setFileSize("");
        timage.setFileType("");
        timage.setRemark("");
        timage.setCreateTime("");

        int insert = timageDao.insert(timage);
        System.out.println();
        System.out.println(insert);
        System.out.println();
    }

    @Test
    public void t5() {
        JSONObject object = userService.analysisArticleContent(42);
    }

    @Test
    public void t6() {
        JSONObject object = userService.addArticle(1);
        System.out.println(object.toJSONString());
    }

    @Test
    public void t7() {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("success", "1");

        int size = 10;

        while(size > 0){
            List<Tarticle> article = this.getArticle(params);
            if (CollectionUtils.isEmpty(article)){
                size = 0;
            }else{
                for (Tarticle tarticle : article) {
                    userService.analysisArticleContentById(tarticle.getId());
                }
                size = article.size();
            }
        }
    }


    private List<Tarticle> getArticle(Map<String, Object> params) {
        PageHelper.startPage(1, 100, "id");
        return tarticleDao.selectByParams(params);
    }

    @Test
    public void t8() {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("imgs", "");
        int size = 1;

        while(size > 0){
            List<Tarticle> tarticles = this.getArticle(params);
            if (CollectionUtils.isEmpty(tarticles)){
                size = 0;
            }else{
                tarticles.parallelStream().forEach(tarticle -> {
                    System.out.println("开始更新文章id： " + tarticle.getId());
                    userService.updateArticleImageNum(tarticle.getId());
                });
                size = tarticles.size();
            }
        }
    }

}

