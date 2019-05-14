package com.zsx.controller;

import com.alibaba.fastjson.JSONObject;
import com.zsx.entity.Tpage;
import com.zsx.json.JsonTable;
import com.zsx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by highness on 2018/6/23 0023.
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @PostMapping("/articleList")
    public JsonTable getUserList(
            Integer pageNum,
            Integer pageSize
    ) {
        return userService.getArticlePageList(pageNum, pageSize);
    }


    @PostMapping("/imageList")
    public JsonTable imageList(
            Integer pid
    ) {
        return userService.getImageList(pid);
    }

}




