package com.chris.manager.service.impl;

import com.chris.manager.mapper.GoodsCategoryMapper;
import com.chris.manager.pojo.GoodsCategory;
import com.chris.manager.pojo.GoodsCategoryExample;
import com.chris.manager.service.GoodsCategoryService;
import com.chris.manager.vo.GoodsCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public int goodsCategorySave(GoodsCategory goodsCategory) {
        return goodsCategoryMapper.insertSelective(goodsCategory);
    }


    @Override
    public int goodsCategoryDelete(Short id) {
        return goodsCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodsCategory selectCategory(Short id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsCategoryVo> selectCategoryListForView() {
//        //创建查询条件
//        GoodsCategoryExample example = new GoodsCategoryExample();
//        //设置查询条件 where parentId = 0 and level = 1
//        example.createCriteria().andParentIdEqualTo((short)0).andLevelEqualTo((byte)1);
//        //查询顶级分类
//        List<GoodsCategory> gcList = goodsCategoryMapper.selectByExample(example);
//        List<GoodsCategoryVo> gcvList = new ArrayList<>();
//        for (GoodsCategory gc01 : gcList) {
//            GoodsCategoryVo gcv01 = new GoodsCategoryVo();
//            //把GoodsCategory对象转成GoodsCategoryVo对象
//            BeanUtils.copyProperties(gc01,gcv01);
//            //清空查询条件
//            example.clear();
//            //设置查询条件
//            example.createCriteria().andParentIdEqualTo(gc01.getId()).andLevelEqualTo((byte)2);
//            //查询二级分类
//            List<GoodsCategory> gcList02 = goodsCategoryMapper.selectByExample(example);
//            List<GoodsCategoryVo> gcvList02 = new ArrayList<>();
//            for (GoodsCategory gc02 : gcList02) {
//                GoodsCategoryVo gcv02 = new GoodsCategoryVo();
//                BeanUtils.copyProperties(gc02,gcv02);
//                //清空查询对象
//                example.clear();
//                //设置查询条件
//                example.createCriteria().andParentIdEqualTo(gc02.getId()).andLevelEqualTo((byte)3);
//                //查询三级分类
//                List<GoodsCategory> gcList03 = goodsCategoryMapper.selectByExample(example);
//                List<GoodsCategoryVo> gcvList03 = new ArrayList<>();
//                for (GoodsCategory gc03 : gcList03) {
//                    GoodsCategoryVo gcv03 = new GoodsCategoryVo();
//                    BeanUtils.copyProperties(gc03,gcv03);
//                    gcvList03.add(gcv03);
//                }
//                //把三级分类放入二级分类的对象里
//                gcv02.setChildren(gcvList03);
//                gcvList02.add(gcv02);
//            }
//            //把二级分类放入一级分类的对象里
//            gcv01.setChildren(gcvList02);
//            gcvList.add(gcv01);
//        }

        //========================JDK8新特性==========================
        //创建查询对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        //查询所有商品分类
        List<GoodsCategory> list = goodsCategoryMapper.selectByExample(example);
        //将GoodsCategory对象转换成GoodsCategoryVo对象
        List<GoodsCategoryVo> gcvList = list.stream().map(e -> {
            GoodsCategoryVo gcv = new GoodsCategoryVo();
            BeanUtils.copyProperties(e,gcv);
            return gcv;
        }).collect(Collectors.toList());
        //将List<GoodsCategoryVo>转换成Map<parentId,List<GoodsCategoryVo>>
        //按parentId分组，key就是parentId，value就是List<GoodsCategoryVo>
        Map<Short, List<GoodsCategoryVo>> map = gcvList.stream().collect(Collectors.groupingBy(GoodsCategoryVo::getParentId));
        //循环，给children赋值
        gcvList.forEach(e -> e.setChildren(map.get(e.getId())));
        //拦截器，返回level为1的list，也就是顶级分类
        List<GoodsCategoryVo> gcvList01 = gcvList.stream().filter(e -> 1 == e.getLevel()).collect(Collectors.toList());
        //========================JDK8新特性==========================

        return gcvList01;
    }

    @Override
    public List<GoodsCategory> selectCategoryList() {
        return goodsCategoryMapper.selectByExample(new GoodsCategoryExample());
    }


}
