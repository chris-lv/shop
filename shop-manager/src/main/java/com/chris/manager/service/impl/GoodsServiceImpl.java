package com.chris.manager.service.impl;

import com.chris.common.result.BaseResult;
import com.chris.manager.mapper.GoodsMapper;
import com.chris.manager.pojo.Goods;
import com.chris.manager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

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
}
