package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 商城订单详情
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@TableName("mall_order_detail")
public class MallOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "MallOrderDetail{" +
        "orderDetailId=" + orderDetailId +
        ", productId=" + productId +
        ", orderId=" + orderId +
        ", orderDetailNum=" + orderDetailNum +
        ", orderDetailMoney=" + orderDetailMoney +
        "}";
    }
}
