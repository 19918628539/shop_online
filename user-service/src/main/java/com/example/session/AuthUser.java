package com.example.session;/*
 *ClassName:
 *UserName:86189
 *Time:2021/11/16/14:39
 */

import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class AuthUser implements Serializable{
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 用户名	唯一
     */
    private String userName;

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
}
