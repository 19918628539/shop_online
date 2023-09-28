package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.MallCart;
import com.example.mapper.MallCartMapper;
import com.example.service.MallCartService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商城购物车 服务实现类
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@Service
public class MallCartServiceImpl extends ServiceImpl<MallCartMapper, MallCart> implements MallCartService {

}
