package com.chris.manager.service;

import com.chris.manager.pojo.GoodsCategory;

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
}
