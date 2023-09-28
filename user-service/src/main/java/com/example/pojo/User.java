package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId
    private Long userId;

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 用户名	唯一
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户性别
     */
    private Boolean userSex;
    /**
     * 用户电话
     */
    private String userTel;

    /**
     * 用户状态	            默认：0，	            0 - 正常	            1 - 锁定	            n - 更多
     */
    private Boolean userStatus;
    /**
     * 用户出生日期
     */
    @DateTimeFormat(pattern = "yyyy-M-d")//传统的传参指定日期格式
    @JsonFormat(pattern = "yyyy-MM-dd")// JSON的传入传出的日期格式
    private Date userBrithdate;

    public Date getUserBrithdate() {
        return userBrithdate;
    }

    public void setUserBrithdate(Date userBrithdate) {
        this.userBrithdate = userBrithdate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", roleId=" + roleId +
        ", userName=" + userName +
        ", userNickname=" + userNickname +
        ", userPassword=" + userPassword +
        ", userSex=" + userSex +
        ", userTel=" + userTel +
        ", userStatus=" + userStatus +
        "}";
    }
}
