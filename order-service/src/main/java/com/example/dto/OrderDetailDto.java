package com.example.dto;/*
 *ClassName:
 *UserName:86189
 *Time:2021/12/6/19:52
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.entity.MallProduct;

import java.io.Serializable;

public class OrderDetailDto extends MallProduct implements Serializable {
    /**
     * 订单详情编号
     */
    @TableId
    private Long orderDetailId;

    /**
     * 产品编号
     */
    private Long productId;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 订单详情商品数量
     */
    private Integer orderDetailNum;

    /**
     * 订单详情实际价格
     */
    private Float orderDetailMoney;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderDetailNum() {
        return orderDetailNum;
    }

    public void setOrderDetailNum(Integer orderDetailNum) {
        this.orderDetailNum = orderDetailNum;
    }

    public Float getOrderDetailMoney() {
        return orderDetailMoney;
    }

    public void setOrderDetailMoney(Float orderDetailMoney) {
        this.orderDetailMoney = orderDetailMoney;
    }
}
