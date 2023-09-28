package com.example.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.pojo.MallProductType;
import com.example.service.MallProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统基础	树形结构基本信息表	系统其它数据都依赖于本表 前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-11-17
 */
@RestController
@RequestMapping("/api/mall-product-type")
public class MallProductTypeController {
    public  final static Logger logg = LoggerFactory.getLogger(MallProductTypeController.class);
    @Autowired
    private MallProductTypeService typeService;
    //查询多个类别
    @GetMapping("list")
    public List<MallProductType> list(String key,
                                      @RequestParam(defaultValue = "10") Integer len
    )  {
        Page<MallProductType> p = new Page<>(1, len,false);
        return page(p,key).getRecords();
    }
    @GetMapping("page")
    public Page<MallProductType>page(Page<MallProductType> p,String key){
        QueryWrapper<MallProductType> queryWrapper = new QueryWrapper<>();//new一个条件构造器出来
        if (StrUtil.isNotBlank(key/*非空判断*/)){
            queryWrapper.like("product_type",key);
        }
        return typeService.page(p,queryWrapper);
    }
    @GetMapping("{productTypeId}")//
    public Object gettype(
            @PathVariable Long  productTypeId
    ){
        return typeService.getById(productTypeId);
    }


    //json格式添加
    @PostMapping("add")
    public void addJson(@RequestBody MallProductType type){
        try {
            typeService.save(type);

        }catch (Exception e) {
            throw new RuntimeException("添加失败",e);

        }
    }

    @PutMapping("update")
    public void update(@RequestBody MallProductType type){
        try {
            typeService.updateById(type);

        }catch (Exception e) {
            throw new RuntimeException("添加失败",e);

        }
    }

    //删除
    @DeleteMapping("delete/{productTypeId}")
    public boolean delete(@PathVariable("productTypeId") Long productTypeId){
        try {
            QueryWrapper<MallProductType> QueryWrapper = new QueryWrapper<>();
            typeService.remove(QueryWrapper.eq("product_type_id",productTypeId));
            R.ok().setMessage("删除成功");
            return true;
        }catch (Exception e){
            logg.debug("删除失败",e);
            return false;
        }

    }

}

