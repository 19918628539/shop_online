package com.example.dto;/*
 *ClassName:
 *UserName:86189
 *Time:2021/12/1/22:40
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserInfo {
    /**
     * 用户编号
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 用户名	唯一
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String userNickname;
    /**
     * 用户性别
     */
    private Boolean userSex;
    /**
     * 用户电话
     */
    private String userTel;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 用户出生日期
     */
    @DateTimeFormat(pattern = "yyyy-M-d")//传统的传参指定日期格式
    @JsonFormat(pattern = "yyyy-MM-dd")// JSON的传入传出的日期格式
    private Date userBrithdate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getUserBrithdate() {
        return userBrithdate;
    }

    public void setUserBrithdate(Date userBrithdate) {
        this.userBrithdate = userBrithdate;
    }
}
