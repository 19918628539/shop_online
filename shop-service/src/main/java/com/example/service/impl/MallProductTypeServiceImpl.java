package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.MallProductTypeMapper;
import com.example.pojo.MallProductType;
import com.example.service.MallProductTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统基础	树形结构基本信息表	系统其它数据都依赖于本表 服务实现类
 * </p>
 *
 * @author liang
 * @since 2021-11-17
 */
@Service
public class MallProductTypeServiceImpl extends ServiceImpl<MallProductTypeMapper, MallProductType> implements MallProductTypeService {

}
