package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.dto.OrderDetailDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商城订单
 * </p>
 *
 * @author liang
 * @since 2021-12-02
 */
@TableName("mall_order")
public class MallOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId
    private Long orderId;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 订单收货人
     */
    private String orderAddressName;

    /**
     * 订单收货人电话
     */
    private String orderAddressTel;



    /**
     * 订单收货详细地址
     */
    private String orderAddressInfo;

    /**
     * 订单创建时间
     */
    private Date orderTime;

    /**
     * 订单实付金额
     */
    private Float orderMoney;

    /**
     * 订单客户说明
     */
    private String orderMemo;

    /**
     * 订单支付类型
     */
    private Integer orderPayTypeId;

    /**
     * 订单支付时间
     */
    private Date orderPayTime;

    /**
     * 订单支付订单号
     */
    private String orderPayId;

    /**
     * 订单签收时间
     */
    private Date orderCheckTime;

    /**
     * 订单状态	            -1 - 订单取消（超时未支付）	            0 - 默认，等待支付	            1 - 支付成功，等待商家发货	            2 - 发货完成，等待客户签收	            3 - 客户签收，订单完成	            4 - 客户拒收？？	            
     */
    private Integer orderStatus;

    @TableField(exist = false)
    private List<OrderDetailDto> list;

    public List<OrderDetailDto> getList() {
        return list;
    }

    public void setList(List<OrderDetailDto> list) {
        this.list = list;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderAddressName() {
        return orderAddressName;
    }

    public void setOrderAddressName(String orderAddressName) {
        this.orderAddressName = orderAddressName;
    }

    public String getOrderAddressTel() {
        return orderAddressTel;
    }

    public void setOrderAddressTel(String orderAddressTel) {
        this.orderAddressTel = orderAddressTel;
    }

    public String getOrderAddressInfo() {
        return orderAddressInfo;
    }

    public void setOrderAddressInfo(String orderAddressInfo) {
        this.orderAddressInfo = orderAddressInfo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Float orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    public Integer getOrderPayTypeId() {
        return orderPayTypeId;
    }

    public void setOrderPayTypeId(Integer orderPayTypeId) {
        this.orderPayTypeId = orderPayTypeId;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderPayId() {
        return orderPayId;
    }

    public void setOrderPayId(String orderPayId) {
        this.orderPayId = orderPayId;
    }

    public Date getOrderCheckTime() {
        return orderCheckTime;
    }

    public void setOrderCheckTime(Date orderCheckTime) {
        this.orderCheckTime = orderCheckTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "MallOrder{" +
        "orderId=" + orderId +
        ", userId=" + userId +
        ", orderAddressName=" + orderAddressName +
        ", orderAddressTel=" + orderAddressTel +
        ", orderAddressInfo=" + orderAddressInfo +
        ", orderTime=" + orderTime +
        ", orderMoney=" + orderMoney +
        ", orderMemo=" + orderMemo +
        ", orderPayTypeId=" + orderPayTypeId +
        ", orderPayTime=" + orderPayTime +
        ", orderPayId=" + orderPayId +
        ", orderCheckTime=" + orderCheckTime +
        ", orderStatus=" + orderStatus +
        "}";
    }
}
