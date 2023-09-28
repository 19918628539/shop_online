package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.MallAddress;
import com.example.mapper.MallAddressMapper;
import com.example.service.MallAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商城用户收货地址 服务实现类
 * </p>
 *
 * @author liang
 * @since 2021-11-30
 */
@Service
public class MallAddressServiceImpl extends ServiceImpl<MallAddressMapper, MallAddress> implements MallAddressService {

}
