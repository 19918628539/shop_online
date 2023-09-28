package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 系统基础	树形结构基本信息表	系统其它数据都依赖于本表
 * </p>
 *
 * @author liang
 * @since 2021-11-17
 */
@TableName("mall_product_type")
public class MallProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品类别编号
     */
    @TableId
    private Long productTypeId;

    /**
     * 产品类别
     */
    private String productType;


    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "MallProductType{" +
        "productTypeId=" + productTypeId +
        ", productType=" + productType +
        ", productBrand=" +
        "}";
    }
}
