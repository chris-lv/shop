package com.chris.manager.service.impl;

import com.chris.manager.mapper.BrandMapper;
import com.chris.manager.pojo.Brand;
import com.chris.manager.pojo.BrandExample;
import com.chris.manager.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:BrandServiceImpl
 * Package:com.chris.manager.service.impl
 * Description:
 *
 * @Date:2021/3/15 12:00
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> selectBrandList() {
        BrandExample example = new BrandExample();
        return brandMapper.selectByExample(example);
    }
}
