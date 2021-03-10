package com.chris.manager.controller;

import com.chris.common.result.BaseResult;
import com.chris.manager.pojo.GoodsCategory;
import com.chris.manager.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String categoryList(Model model) {
        model.addAttribute("gcvList",goodsCategoryService.selectCategoryListForView());
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

    /**
     * 商品分类-新增分类-级联查询
     * @param parentId
     * @return
     */
    @RequestMapping("category/{parentId}")
    @ResponseBody
    public List<GoodsCategory> selectCategoryList(@PathVariable Short parentId) {
        return goodsCategoryService.selectCategoryByParentId(parentId);
    }

    /**
     * 商品分类-新增分类-保存分类
     * @param goodsCategory
     * @return
     */
    @RequestMapping("category/save")
    @ResponseBody
    public BaseResult categorySave(GoodsCategory goodsCategory) {
        int result = goodsCategoryService.goodsCategorySave(goodsCategory);
        return result>0?BaseResult.success():BaseResult.error();
    }

//    /**
//     * 跳转-商品分类-编辑页
//     * @param id
//     * @return
//     */
//    @RequestMapping("category/update/{id}")
//    public String categoryUpdate(@PathVariable Short id, Model model) {
//        GoodsCategory gc = goodsCategoryService.selectCategory(id);
//        model.addAttribute("gc",gc);
//        return "goods/category/category-add";
//    }
//
//    /**
//     * 商品分类-删除分类
//     * @param id
//     * @return
//     */
//    @RequestMapping("category/delete/{id}")
//    public BaseResult categoryDelete(@PathVariable Short id) {
//        int result = goodsCategoryService.goodsCategoryDelete(id);
//        return result>0?BaseResult.success():BaseResult.error();
//    }

}
