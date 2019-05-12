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


    JSONObject addArticle(Integer n);


    JSONObject loadArticleContent(int num);


    JSONObject analysisArticleContent(Integer size);



    JsonTable getArticlePageList(Integer pageNum, Integer pageSize);

    JsonTable getImageList(Integer pid);

}
