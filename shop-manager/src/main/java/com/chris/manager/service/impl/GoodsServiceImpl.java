package com.chris.manager.service.impl;

import com.chris.common.result.BaseResult;
import com.chris.manager.mapper.GoodsMapper;
import com.chris.manager.pojo.Goods;
import com.chris.manager.pojo.GoodsExample;
import com.chris.manager.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * ClassName:GoodsServiceImpl
 * Package:com.chris.manager.service.impl
 * Description:商品服务实现类
 *
 * @Date:2021/3/15 15:07
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public BaseResult saveGoods(Goods goods) {
        if (goods.getGoodsId() != null) {
            return BaseResult.error();
        }
        // 判断商品详情是否为空，如果非空则进行转义否则不转义
        if (!StringUtils.isEmpty(goods.getGoodsContent())){
            goods.setGoodsContent(HtmlUtils.htmlEscape(goods.getGoodsContent(),"UTF-8"));
        }
        int result = goodsMapper.insertSelective(goods);
        BaseResult baseResult;
        if (result > 0) {
            baseResult = BaseResult.success();
            baseResult.setMessage(String.valueOf(goods.getGoodsId()));
        } else {
            baseResult = BaseResult.error();
        }
        return baseResult;
    }

    @Override
    public BaseResult selectGoodsListByPage(Goods goods, Integer pageNum, Integer pageSize) {
        // 构建分页对象
        PageHelper.startPage(pageNum,pageSize);
        // 创建查询对象
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        // 设置查询条件
        // 分类参数
        if (goods.getCatId() != null && goods.getCatId() != 0) {
            criteria.andCatIdEqualTo(goods.getCatId());
        }
        // 品牌参数
        if (goods.getBrandId() != null && goods.getBrandId() != 0) {
            criteria.andBrandIdEqualTo(goods.getBrandId());
        }
        // 关键字
        if (!StringUtils.isEmpty(goods.getGoodsName())) {
            criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
        }
        // 判断查询结果是否为空，不为空就放入分页对象
        List<Goods> list = goodsMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(list)) {
            PageInfo<Goods> pageInfo = new PageInfo<>();
            return BaseResult.success(pageInfo);
        }
        return BaseResult.error();
    }
}
