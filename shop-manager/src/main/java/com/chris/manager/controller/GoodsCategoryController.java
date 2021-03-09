package com.chris.manager.controller;

import com.chris.manager.pojo.GoodsCategory;
import com.chris.manager.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ClassName:GoodsCategoryController
 * Package:com.chris.manager.controller
 * Description: 商品分类
 *
 * @Date:2021/3/9 15:21
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@Controller
@RequestMapping("goods")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 跳转 商品分类-列表页
     * @return
     */
    @RequestMapping("category/list")
    public String categoryList() {
        return "goods/category/category-list";
    }


    /**
     * 跳转 商品分类-新增页
     * @return
     */
    @RequestMapping("category/add")
    public String categoryAdd(Model model) {
        List<GoodsCategory> gcList = goodsCategoryService.selectCategoryTopList();
        model.addAttribute("gcList",gcList);
        return "goods/category/category-add";
    }

}
