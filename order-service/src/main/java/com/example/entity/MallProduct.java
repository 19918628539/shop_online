package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 商城产品
 * </p>
 *
 * @author liang
 * @since 2021-11-25
 */
@TableName("mall_product")
public class MallProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品编号
     */
    @TableId
    private Long productId;

    /**
     * 产品类别编号
     */
    private Long productTypeId;

    /**
     * 品牌
     */
    private Long productBrandId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品市场价
     */
    private Float productPrice;

    /**
     * 产品库存数量
     */
    private Integer productNum;

    /**
     * 产品图片
     */
    private String productImgs;

    /**
     * 产品评论
     */
    private String productContent;

    /**
     * 卖出数量
     */
    private Integer productNumSale;

    /**
     * 商品状态       0下架               1上架
     */
    private Boolean productStatus;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(Long productBrandId) {
        this.productBrandId = productBrandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(String productImgs) {
        this.productImgs = productImgs;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public Integer getProductNumSale() {
        return productNumSale;
    }

    public void setProductNumSale(Integer productNumSale) {
        this.productNumSale = productNumSale;
    }

    public Boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "MallProduct{" +
        "productId=" + productId +
        ", productTypeId=" + productTypeId +
        ", productBrandId=" + productBrandId +
        ", productName=" + productName +
        ", productPrice=" + productPrice +
        ", productNum=" + productNum +
        ", productImgs=" + productImgs +
        ", productContent=" + productContent +
        ", productNumSale=" + productNumSale +
        ", productStatus=" + productStatus +
        "}";
    }
}
