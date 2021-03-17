package com.chris.manager.service;

import com.chris.manager.pojo.GoodsCategory;
import com.chris.manager.vo.GoodsCategoryVo;

import java.util.List;

/**
 * ClassName:GoodsCategoryService
 * Package:com.chris.manager.service
 * Description:
 *
 * @Date:2021/3/9 19:17
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
public interface GoodsCategoryService {

    /**
     * 商品分类-新增分类-查询所有顶级分类
     * @return
     */
    List<GoodsCategory> selectCategoryTopList();

    /**
     * 商品分类-新增分类-级联查询
     * @param parentId
     * @return
     */
    List<GoodsCategory> selectCategoryByParentId(Short parentId);

    /**
     * 商品分类-新增分类-保存分类
     * @param goodsCategory
     * @return
     */
    int goodsCategorySave(GoodsCategory goodsCategory);

    /**
     * 商品分类-删除
     * @param id
     * @return
     */
    int goodsCategoryDelete(Short id);

    GoodsCategory selectCategory(Short id);

    /**
     * 商品分类-列表
     * @return
     */
    List<GoodsCategoryVo> selectCategoryListForView();

    /**
     * 商品分类-查询所有商品分类
     * @return
     */
    List<GoodsCategory> selectCategoryList();

}
