package com.example.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.R;
import com.example.pojo.MallProductBrand;
import com.example.service.MallProductBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-11-18
 */
@RestController
@RequestMapping("/api/mall-product-brand/")
public class MallProductBrandController {
    public  final static Logger logg = LoggerFactory.getLogger(MallProductBrandController.class);
    @Autowired
    private MallProductBrandService brandService;
    //查询多个用户
    @GetMapping("list")
    public List<MallProductBrand> list(String key,
                                       @RequestParam(defaultValue = "10") Integer len
    )  {
        /*Thread.sleep(new Random().nextInt(2000)+1000L);//模拟延时
        if(new Random().nextBoolean()){//随机模拟异常
            throw new Exception("模拟异常");
            }*/
        Page<MallProductBrand> p = new Page<>(1, len,false);
        return page(p,key).getRecords();
    }
    @GetMapping("page")
    public Page<MallProductBrand> page(Page<MallProductBrand> p, String key){
        QueryWrapper<MallProductBrand> queryWrapper = new QueryWrapper<>();//new一个条件构造器出来
        if (StrUtil.isNotBlank(key/*非空判断*/)){
            queryWrapper.like("product_brand",key);
        }
        return brandService.page(p,queryWrapper);
    }
    @GetMapping("{productBrandId}")//
    public Object gettype(
            @PathVariable Long  productBrandId
    ){
        return brandService.getById(productBrandId);
    }


    //json格式添加
    @PostMapping("add")
    public void addJson(@RequestBody MallProductBrand productBrand){
        try {
            brandService.save(productBrand);

        }catch (Exception e) {
            throw new RuntimeException("添加失败",e);

        }
    }

    @PutMapping("update")
    public void update(@RequestBody MallProductBrand productBrand){
        try {
            brandService.updateById(productBrand);

        }catch (Exception e) {
            throw new RuntimeException("添加失败",e);

        }
    }

    //删除
    @DeleteMapping("delete/{productBrandId}")
    public boolean delete(@PathVariable("productBrandId") Long productBrandId){
        try {
            QueryWrapper<MallProductBrand> QueryWrapper = new QueryWrapper<>();
            brandService.remove(QueryWrapper.eq("product_brand_id",productBrandId));
            R.ok().setMessage("删除成功");
            return true;
        }catch (Exception e){
            logg.debug("删除失败",e);
            return false;
        }

    }
}

