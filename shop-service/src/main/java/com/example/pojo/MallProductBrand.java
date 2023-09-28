package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liang
 * @since 2021-11-18
 */
@TableName("mall_product_brand")
public class MallProductBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌编号
     */
    @TableId
    private Long productBrandId;

    /**
     * 品牌
     */
    private String productBrand;

    /**
     * 品牌logo
     */
    private String productBrandLo;


    public Long getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(Long productBrandId) {
        this.productBrandId = productBrandId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductBrandLo() {
        return productBrandLo;
    }

    public void setProductBrandLo(String productBrandLo) {
        this.productBrandLo = productBrandLo;
    }

    @Override
    public String toString() {
        return "MallProductBrand{" +
        "productBrandId=" + productBrandId +
        ", productBrand=" + productBrand +
        ", productBrandLo=" + productBrandLo +
        "}";
    }
}
