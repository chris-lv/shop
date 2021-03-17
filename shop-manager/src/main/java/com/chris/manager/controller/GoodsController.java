package com.chris.manager.controller;

import com.chris.common.result.BaseResult;
import com.chris.common.result.FileResult;
import com.chris.manager.pojo.Brand;
import com.chris.manager.pojo.Goods;
import com.chris.manager.pojo.GoodsCategory;
import com.chris.manager.pojo.GoodsImages;
import com.chris.manager.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class GoodsController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsImagesService goodsImagesService;
    @Autowired
    private UploadService uploadService;

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
     * 跳转-商品分类-编辑页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/category/update/{id}")
    public String goodsUpdate(@PathVariable Short id,Model model) {
        // 根据传入id查询要修该的分类原信息
        GoodsCategory goodsCategory = goodsCategoryService.selectCategory(id);
        model.addAttribute("goodsCategory",goodsCategory);

        List<GoodsCategory> gcList = goodsCategoryService.selectCategoryTopList();
        model.addAttribute("gcList",gcList);
        return "goods/category/category-add";
    }

    /**
     * 商品分类-编辑
     * @param goodsCategory
     * @return
     */
    @RequestMapping("category/update")
    @ResponseBody
    public BaseResult updateCategory(GoodsCategory goodsCategory) {
        GoodsCategory selectCategory = goodsCategoryService.selectCategory(goodsCategory.getId());
        if (selectCategory != null) {
            BeanUtils.copyProperties(goodsCategory,selectCategory);
            return BaseResult.success();
        } else {
            return BaseResult.error();
        }
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

    /**
     * 商品-列表-页面跳转
     * @return
     */
    @RequestMapping("list")
    public String goodsList(Model model){
        // 返回所有商品分类
        model.addAttribute("gcList",goodsCategoryService.selectCategoryList());
        // 返回所有商品品牌
        model.addAttribute("brandList",brandService.selectBrandList());
        return "goods/goods-list";
    }

    /**
     * 商品-新增-页面跳转
     * @return
     */
    @RequestMapping("add")
    public String goodsAdd(Model model){
        //查询顶级分类
        List<GoodsCategory> gcList = goodsCategoryService.selectCategoryTopList();
        model.addAttribute("gcList",gcList);
        List<Brand> brandList = brandService.selectBrandList();
        model.addAttribute("brandList",brandList);
        return "goods/goods-add";
    }

    /**
     * 商品新增-保存
     * @param goods
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public BaseResult saveGoods(Goods goods) {
        return goodsService.saveGoods(goods);
    }

    /**
     * 商品分类-删除分类
     * @param id
     * @return
     */
    @RequestMapping("category/delete/{id}")
    @ResponseBody
    public BaseResult categoryDelete(@PathVariable Short id) {
        int result = goodsCategoryService.goodsCategoryDelete(id);
        return result>0?BaseResult.success():BaseResult.error();
    }

    /**
     * 商品相册-保存
     * @param file
     * @param goodsId
     * @return
     * @throws IOException
     */
    @RequestMapping("images/save")
    @ResponseBody
    public BaseResult saveImages(MultipartFile file, Integer goodsId) throws IOException {
        String filename = file.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
        filename = date + System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
        FileResult result = uploadService.upload(file.getInputStream(), filename);
        //判断是否上传成功 fileUrl非空表名上传成功
        if(!StringUtils.isEmpty(result.getFileUrl())) {
            GoodsImages goodsImages = new GoodsImages();
            goodsImages.setImageUrl(result.getFileUrl());
            goodsImages.setGoodsId(goodsId);
            BaseResult baseResult = goodsImagesService.saveGoodsImages(goodsImages);
            return baseResult;
        } else {
            return BaseResult.error();
        }
    }

    @RequestMapping("listForPage")
    @ResponseBody
    public BaseResult selectGoodsListByPage(Goods goods,Integer pageNum,Integer pageSize) {
        return goodsService.selectGoodsListByPage(goods,pageNum,pageSize);
    }

}
