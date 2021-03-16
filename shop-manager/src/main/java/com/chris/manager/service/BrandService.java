package com.chris.manager.service;

import com.chris.manager.pojo.Brand;

import java.util.List;

/**
 * ClassName:BrandService
 * Package:com.chris.manager.service
 * Description:品牌service
 *
 * @Date:2021/3/15 11:59
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> selectBrandList();
}
