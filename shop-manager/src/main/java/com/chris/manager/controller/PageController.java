package com.chris.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:PageController
 * Package:com.chris.manager.controller
 * Description:
 *
 * @Date:2021/3/9 10:50
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@Controller
public class PageController {

    /**
     * 页面跳转 Restful风格
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        System.out.println(page);
        return page;
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
