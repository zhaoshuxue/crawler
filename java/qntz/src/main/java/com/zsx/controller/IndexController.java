package com.zsx.controller;

import com.zsx.json.JsonTable;
import com.zsx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            Integer pageSize,
            @RequestParam(value = "success", defaultValue = "") String success
    ) {
        return userService.getArticlePageList(pageNum, pageSize, success);
    }


    @PostMapping("/imageList")
    public JsonTable imageList(
            Integer pid
    ) {
        return userService.getImageList(pid);
    }

}




