package com.example.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.pojo.MallProduct;
import com.example.service.MallProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商城产品 前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/api/mall-product")
public class MallProductController {

        public  final static Logger logg = LoggerFactory.getLogger(com.example.controller.MallProductController.class);
        @Autowired
        private MallProductService productService;
        //查询多个类别
        @GetMapping("list")
        public List<MallProduct> list(String key, Long productTypeId, Long[] productBrandId, Boolean productStatus, Boolean productNumSale,
                                      @RequestParam(defaultValue = "10") Integer len
        )  {

            Page<MallProduct> p = new Page<>(1, len,false);
            return page(p,key,productTypeId,productBrandId,productStatus,productNumSale).getRecords();
        }
        @GetMapping("page")
        public Page<MallProduct>page(Page<MallProduct> p,String key,Long productTypeId,Long[] productBrandId,Boolean productStatus,Boolean productNumSale){
            QueryWrapper<MallProduct> queryWrapper = new QueryWrapper<>();//new一个条件构造器出来
            if (StrUtil.isNotBlank(key/*非空判断*/)){
                queryWrapper.like("product_name",key);
            }
            if(productTypeId!=null){
                queryWrapper.eq("product_type_id", productTypeId);
            }
            if(productBrandId!=null){
                Object[] os = new Object[productBrandId.length];
                for(int i=0;i<os.length;i++){
                    os[i] = productBrandId[i];
                }
                queryWrapper.in("product_brand_id", os);
            }
            if(productStatus!=null){
                queryWrapper.eq("product_status", productStatus);
            }

            if(Boolean.TRUE.equals(productNumSale)){
                queryWrapper.orderByDesc("product_num_sale");
            }

            queryWrapper.orderByDesc("product_id");
            return productService.page(p, queryWrapper);
        }

        @GetMapping("{productId}")//
        public Object getproduct(
                @PathVariable Long  productId
        ){
            return productService.getById(productId);
        }


        //json格式添加
        @PostMapping("add")
        public void addJson(@RequestBody MallProduct product){
            try {
                product.setProductStatus(false);
                product.setProductNumSale(0);
                for (int i = 1600; i < 1800; i++) {
                    product.setProductId((long) i);
                    productService.save(product);
                }

            }catch (Exception e) {
                throw new RuntimeException("添加失败",e);

            }
        }

        @PutMapping("update")
        public void update(@RequestBody MallProduct product){
            try {
                productService.updateById(product);

            }catch (Exception e) {
                throw new RuntimeException("添加失败",e);

            }
        }

        //删除
        @DeleteMapping("delete/{productId}")
        public boolean delete(@PathVariable("productId") Long productId){
            try {
                productService.removeById(productId);
                R.ok().setMessage("删除成功");
                return true;
            }catch (Exception e){
                logg.debug("删除失败",e);
                return false;
            }
        }
    @GetMapping("updateNum")
    public void updateNum(@RequestParam Long productId,@RequestParam Integer OrderDetailNum){
        productService.updateNum(productId,OrderDetailNum);
    }
    }



