package com.zsx.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zsx.dao.TarticleDao;
import com.zsx.dao.TimageDao;
import com.zsx.dao.TpageDao;
import com.zsx.entity.Tarticle;
import com.zsx.entity.Timage;
import com.zsx.entity.Tpage;
import com.zsx.json.JsonTable;
import com.zsx.service.UserService;
import com.zsx.util.GetPageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZSX
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TpageDao tpageDao;
    @Autowired
    private TarticleDao tarticleDao;
    @Autowired
    private TimageDao timageDao;

    @Override
    public List<Tpage> getAll() {
        return tpageDao.selectAll();
    }


    @Override
    public JSONObject addArticle(Integer page) {
        List<String> successList = Lists.newArrayList();
        List<String> failList = Lists.newArrayList();

        String url = "http://qingniantuzhai.com/page/" + page + "/";
        List<Tarticle> tarticles = GetPageUtil.parsePage(url);
        if (CollectionUtils.isNotEmpty(tarticles)) {
            HashMap<Object, Object> params;
            for (Tarticle tarticle : tarticles) {
                params = Maps.newHashMap();
                params.put("href", tarticle.getHref());
                params.put("title", tarticle.getTitle());

                List<Tarticle> tarticleList = tarticleDao.selectByParams(params);
                if (CollectionUtils.isNotEmpty(tarticleList)) {
                    failList.add(tarticle.getTitle());
                    continue;
                }
                tarticleDao.insert(tarticle);
                successList.add(tarticle.getTitle());
            }
        }
        JSONObject json = new JSONObject();
        json.put("成功条数：", successList.size());
        json.put("success", successList.size());
        json.put("失败条数：", failList.size());
        json.put("fail", failList.size());
        json.put("成功：", successList);
        json.put("失败：", failList);
        return json;
    }


    @Override
    public JSONObject loadArticleContent(int num) {

        HashMap<Object, Object> params;
        params = Maps.newHashMap();
        params.put("success", "0");

        PageHelper.startPage(1, 10, "id");
        List<Tarticle> tarticleList = tarticleDao.selectByParams(params);
        if (CollectionUtils.isNotEmpty(tarticleList)) {
            PageInfo pageInfo = new PageInfo(tarticleList);
            int totalPages = pageInfo.getPages();
            for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
                System.out.println("开始加载第几页：" + pageNum);
                if (pageNum > 1) {
                    PageHelper.startPage(pageNum, 10, "id");
                    tarticleList = tarticleDao.selectByParams(params);
                }

                for (Tarticle tarticle : tarticleList) {
                    Integer id = tarticle.getId();
                    System.out.println("开始下载网页内容：id= " + id);
                    String href = tarticle.getHref();

                    String content = GetPageUtil.loadArticleContent(href);
                    if (content != null && content.length() > 0) {
                        tarticle.setContent(content);
                        tarticle.setSuccess("1");
                        System.out.println("8899 开始更新id= " + id + " 的网页：" + tarticle.getTitle());
                        int i = tarticleDao.updateByPrimaryKeySelective(tarticle);
//                        if (i != 1) {
//                            break;
//                        }
                    }
                }
            }
        }
        return null;
    }


    @Override
    public JSONObject analysisArticleContent(Integer size) {

        HashMap<String, Object> params = Maps.newHashMap();
        params.put("success", "1");
        PageHelper.startPage(1, size, "id");
        List<Tarticle> tarticles = tarticleDao.selectByParams(params);
        if (CollectionUtils.isEmpty(tarticles)) {
            return null;
        }

        for (Tarticle tarticle : tarticles) {
            Integer pid = tarticle.getId();
            String content = tarticle.getContent();

            Document document = Jsoup.parse(content);

            // 获取内容主体
            Elements postContentClass = document
                    .getElementById("page-content")
                    .getElementsByClass("post-content");

            if (postContentClass != null && postContentClass.size() == 1) {
                Element postContent = postContentClass.get(0);
                Elements elements = postContent.getElementsByTag("p");

                ArrayList<Timage> timages = Lists.newArrayList();
                Timage timage;
                for (Element element : elements) {
                    timage = new Timage();

                    timage.setPid(pid);
                    timage.setContent(element.html());
                    timage.setTitle(element.text());

                    Elements imgs = element.getElementsByTag("img");
                    if (imgs.size() > 0) {
                        timage.setMore(imgs.size());
                        StringBuffer srcStr = new StringBuffer();
                        for (Element img : imgs) {
                            String src = img.attr("src");
                            System.out.println(src);
                            srcStr.append("!@#");
                            srcStr.append(src);
                        }
                        timage.setSrc(srcStr.substring(3));
                        timage.setStatus(0);
                        timage.setDisk("");
                        timage.setPath("");
                        timage.setFileSize("");
                        timage.setFileType("");
                        timage.setRemark("");
                        timage.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                        timages.add(timage);
                    }
                }
                if (CollectionUtils.isNotEmpty(timages)){
                    int i = timageDao.insertBatch(timages);
                    System.out.println("批量插入条数为： " + i);
                    if (i > 0) {
                        Tarticle updateTarticle = new Tarticle();
                        updateTarticle.setId(pid);
                        updateTarticle.setSuccess("2");
                        int j = tarticleDao.updateByPrimaryKeySelective(updateTarticle);
                        System.out.println("更新状态：" + j);
                    }
                }else{
                    Tarticle updateTarticle = new Tarticle();
                    updateTarticle.setId(pid);
                    updateTarticle.setSuccess("22");
                    int j = tarticleDao.updateByPrimaryKeySelective(updateTarticle);
                    System.out.println("更新状态：" + j);
                }
            }
        }
        return null;
    }


    @Override
    public JsonTable getArticlePageList(Integer pageNum, Integer pageSize) {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("success", "2");
        PageHelper.startPage(pageNum, pageSize, "id");
        List<Tarticle> tarticles = tarticleDao.selectByParams(params);
        if (CollectionUtils.isEmpty(tarticles)) {
            return new JsonTable(null, 0L);
        }
        PageInfo<Tarticle> pageInfo = new PageInfo<Tarticle>(tarticles);

        return JsonTable.toTable(pageInfo.getTotal(), tarticles);
    }


    @Override
    public JsonTable getImageList(Integer pid) {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("pid", pid);
        List<Timage> timages = timageDao.selectByParams(params);
        if (CollectionUtils.isEmpty(timages)) {
            return new JsonTable(null, 0L);
        }
        return JsonTable.toTable(Long.parseLong(String.valueOf(timages.size())), timages);
    }
}
