package com.chris.manager.service.impl;

import com.chris.manager.mapper.GoodsCategoryMapper;
import com.chris.manager.pojo.GoodsCategory;
import com.chris.manager.pojo.GoodsCategoryExample;
import com.chris.manager.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:GoodsCategoryServiceImpl
 * Package:com.chris.manager.service.impl
 * Description:
 *
 * @Date:2021/3/9 19:20
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<GoodsCategory> selectCategoryTopList() {
        //创建查询对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        //设置查询条件
        example.createCriteria().andParentIdEqualTo((short)0);
        return goodsCategoryMapper.selectByExample(example);
    }

    @Override
    public List<GoodsCategory> selectCategoryByParentId(Short parentId) {
        //创建查询条件
        GoodsCategoryExample example = new GoodsCategoryExample();
        //设置查询条件
        example.createCriteria().andParentIdEqualTo(parentId);
        return goodsCategoryMapper.selectByExample(example);
    }
}
