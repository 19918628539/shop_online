package com.example.dto;/*
 *ClassName:
 *UserName:86189
 *Time:2021/12/2/13:52
 */



import com.example.entity.MallOrderDetail;

import java.util.List;

public class OrderDto {
    /**
     * 商城用户收货地址编号
     */
    private Long addressId;
    /**
     * 订单实付金额
     */
    private Float orderMoney;
    /**
     * 留言备注
     */
    private String orderMemo;


    private List<MallOrderDetail> list;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public List<MallOrderDetail> getList() {
        return list;
    }

    public void setList(List<MallOrderDetail> list) {
        this.list = list;
    }
}
