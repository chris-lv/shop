package com.chris.manager.service;

import com.chris.common.result.BaseResult;
import com.chris.manager.pojo.Goods;

/**
 * ClassName:GoodsService
 * Package:com.chris.manager.service
 * Description:商品服务
 *
 * @Date:2021/3/15 15:05
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
public interface GoodsService {

    /**
     * 商品新增-保存
     * @param goods
     * @return
     */
    BaseResult saveGoods(Goods goods);
}
