package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.MallProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商城产品 Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2021-11-25
 */
public interface MallProductMapper extends BaseMapper<MallProduct> {
    @Update("update mall_product set product_num_sale=product_num_sale+#{num},product_num=product_num-#{num} where product_id=#{productId}")
    int updateNum(@Param("productId") Long productId, @Param("num") Integer num);
}
