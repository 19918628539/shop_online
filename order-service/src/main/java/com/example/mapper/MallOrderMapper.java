package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.OrderDetailDto;
import com.example.entity.MallOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商城订单 Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2021-12-02
 */
public interface MallOrderMapper extends BaseMapper<MallOrder> {
    @Select("SELECT * from mall_order_detail a INNER JOIN mall_product b " +
            "ON a.product_id=b.product_id AND a.order_id=#{orderId}")
    List<OrderDetailDto> listOrderDetail(@Param("orderId") Long orderId);
}
