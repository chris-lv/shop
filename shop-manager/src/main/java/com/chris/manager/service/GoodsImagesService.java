package com.chris.manager.service;

import com.chris.common.result.BaseResult;
import com.chris.manager.pojo.GoodsImages;

/**
 * ClassName:GoodsImagesService
 * Package:com.chris.manager.service
 * Description:商品相册服务
 *
 * @Date:2021/3/16 15:28
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
public interface GoodsImagesService {

    /**
     * 商品相册-保存
     * @param goodsImages
     * @return
     */
    BaseResult saveGoodsImages(GoodsImages goodsImages);
}
