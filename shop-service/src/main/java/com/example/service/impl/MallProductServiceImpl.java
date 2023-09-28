package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.MallProductMapper;
import com.example.pojo.MallProduct;
import com.example.service.MallProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商城产品 服务实现类
 * </p>
 *
 * @author liang
 * @since 2021-11-25
 */
@Service
public class MallProductServiceImpl extends ServiceImpl<MallProductMapper, MallProduct> implements MallProductService {

    @Override
    public int updateNum(Long productId, Integer num) {
        return baseMapper.updateNum(productId, num);
    }
}
