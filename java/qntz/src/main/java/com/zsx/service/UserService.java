package com.zsx.service;

import com.alibaba.fastjson.JSONObject;
import com.zsx.entity.Tpage;
import com.zsx.json.JsonTable;

import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
public interface UserService {

    List<Tpage> getAll();


    /**
     * 根据页码获取文章集合
     *
     * @param n
     * @return
     */
    JSONObject addArticle(Integer n);


    /**
     * 根据url把网页内容拉下来
     *
     * @return
     */
    JSONObject loadArticleContent();


    JSONObject analysisArticleContent(Integer size);


    /**
     * 根据id解析网页内容
     *
     * @param id
     * @return
     */
    JSONObject analysisArticleContentById(Integer id);


    void updateArticleImageNum(Integer id);


    JsonTable getArticlePageList(Integer pageNum, Integer pageSize);

    JsonTable getImageList(Integer pid);

}
