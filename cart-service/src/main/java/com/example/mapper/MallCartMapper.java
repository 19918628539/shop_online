package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.CatDto;
import com.example.entity.MallCart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商城购物车 Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
public interface MallCartMapper extends BaseMapper<MallCart> {
    @Select("SELECT * from mall_cart INNER JOIN mall_product ON " +
            "mall_cart.user_id=#{userId} AND " +
            "mall_cart.product_id= mall_product.product_id ORDER BY mall_cart.cart_time desc")
    List<CatDto> listCart(@Param("userId") Long userId);

}
