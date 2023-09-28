package com.example.dto;/*
 *ClassName:
 *UserName:86189
 *Time:2021/11/29/15:37
 */


import com.example.entity.MallCart;

import java.util.Date;

public class CatDto extends MallCart {

    /**
     * 购物车编号
     */
    private Long cartId;

    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 产品编号
     */
    private Long productId;

    /**
     * 商品数量	            默认：1
     */
    private Integer cartNum;

    public String getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(String productImgs) {
        this.productImgs = productImgs;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 加入购物车时间
     */
    private Date cartTime;

    /**
     * 产品图片
     */
    private String productImgs;
    /**
     * 产品市场价
     */
    private Float productPrice;
    /**
     * 产品名称
     */
    private String productName;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public Integer getCartNum() {
        return cartNum;
    }

    @Override
    public void setCartNum(Integer cartNum) {
        this.cartNum = cartNum;
    }

    @Override
    public Date getCartTime() {
        return cartTime;
    }

    @Override
    public void setCartTime(Date cartTime) {
        this.cartTime = cartTime;
    }
    @Override
    public Long getCartId() {
        return cartId;
    }

    @Override
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
