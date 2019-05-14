package com.zsx.controller;

import com.alibaba.fastjson.JSONObject;
import com.zsx.entity.Tpage;
import com.zsx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<Tpage> getUserList() {
        return userService.getAll();
    }


    @GetMapping("/page")
    public JSONObject loadPage(Integer n) {
        return userService.addArticle(n);
    }

}
