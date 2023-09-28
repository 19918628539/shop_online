package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商城购物车
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@TableName("mall_cart")
public class MallCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 购物车编号
     */
    @TableId
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

    /**
     * 加入购物车时间
     */
    private Date cartTime;


    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCartNum() {
        return cartNum;
    }

    public void setCartNum(Integer cartNum) {
        this.cartNum = cartNum;
    }

    public Date getCartTime() {
        return cartTime;
    }

    public void setCartTime(Date cartTime) {
        this.cartTime = cartTime;
    }

    @Override
    public String toString() {
        return "MallCart{" +
        "cartId=" + cartId +
        ", userId=" + userId +
        ", productId=" + productId +
        ", cartNum=" + cartNum +
        ", cartTime=" + cartTime +
        "}";
    }
}
