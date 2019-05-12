package com.zsx.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by highness on 2018/6/24 0024.
 */
@Controller
public class ViewController {

    private void getBasePath(HttpServletRequest request, Model model){
        String getContextPath = request.getContextPath();
        String scheme = request.getHeader("X-Forwarded-Scheme");
//        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(scheme)){
            scheme = request.getScheme();
        }
        String port = request.getServerPort() == 80 || request.getServerPort() == 443 ? ""
                : ":" + request.getServerPort();
        String basePath = scheme + "://" + request.getServerName() + port + getContextPath;
        model.addAttribute("basePath", basePath);
    }

    @RequestMapping("/")
    public String toIndex(HttpServletRequest request, Model model) {
        this.getBasePath(request, model);
        return "articlePage";
    }

    @RequestMapping("imgPage")
    public String gotoImgPage(HttpServletRequest request, Model model) {
        this.getBasePath(request, model);
        model.addAttribute("pid", request.getParameter("id"));
        return "imagePage";
    }

//    @RequestMapping("imageList")
//    public String gotoImageList(HttpServletRequest request, Model model) {
//        this.getBasePath(request, model);
//        return "imageList";
//    }

//
}
