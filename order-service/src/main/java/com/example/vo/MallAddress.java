package com.example.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 商城用户收货地址
 * </p>
 *
 * @author liang
 * @since 2021-11-30
 */

public class MallAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商城用户收货地址编号
     */

    private Long addressId;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 商城用户收货人
     */
    private String addressName;

    /**
     * 商城用户收货人电话
     */
    private String addressTel;

    /**
     * 商城用户收货详细地址
     */
    private String addressInfo;

    /**
     * 是默认收货地址吗
     */
    private Boolean addressDefault;


    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressTel() {
        return addressTel;
    }

    public void setAddressTel(String addressTel) {
        this.addressTel = addressTel;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public Boolean getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(Boolean addressDefault) {
        this.addressDefault = addressDefault;
    }

    @Override
    public String toString() {
        return "MallAddress{" +
        "addressId=" + addressId +
        ", userId=" + userId +
        ", addressName=" + addressName +
        ", addressTel=" + addressTel +
        ", addressInfo=" + addressInfo +
        ", addressDefault=" + addressDefault +
        "}";
    }
}
