package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.OrderDetailDto;
import com.example.entity.MallOrder;

import java.util.List;

/**
 * <p>
 * 商城订单 服务类
 * </p>
 *
 * @author liang
 * @since 2021-12-02
 */
public interface MallOrderService extends IService<MallOrder> {
    List<OrderDetailDto> listOrderDetail(Long orderId);
}
