package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.MallProduct;

/**
 * <p>
 * 商城产品 服务类
 * </p>
 *
 * @author liang
 * @since 2021-11-25
 */
public interface MallProductService extends IService<MallProduct> {
    int updateNum( Long productId, Integer num);
}
