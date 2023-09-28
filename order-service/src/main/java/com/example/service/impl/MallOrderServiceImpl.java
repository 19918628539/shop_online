package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.OrderDetailDto;
import com.example.entity.MallOrder;
import com.example.mapper.MallOrderMapper;
import com.example.service.MallOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商城订单 服务实现类
 * </p>
 *
 * @author liang
 * @since 2021-12-02
 */
@Service
public class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {
    @Override
    public List<OrderDetailDto> listOrderDetail(Long orderId) {
        return baseMapper.listOrderDetail(orderId);
    }
}
