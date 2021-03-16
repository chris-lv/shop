package com.chris.manager.service.impl;

import com.chris.common.result.BaseResult;
import com.chris.manager.mapper.GoodsImagesMapper;
import com.chris.manager.pojo.GoodsImages;
import com.chris.manager.service.GoodsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:GoodsImagesServiceImpl
 * Package:com.chris.manager.service.impl
 * Description:商品相册服务实现类
 *
 * @Date:2021/3/16 15:29
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@Service
public class GoodsImagesServiceImpl implements GoodsImagesService {

    @Autowired
    private GoodsImagesMapper goodsImagesMapper;

    @Override
    public BaseResult saveGoodsImages(GoodsImages goodsImages) {
        int result = goodsImagesMapper.insertSelective(goodsImages);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }
}
