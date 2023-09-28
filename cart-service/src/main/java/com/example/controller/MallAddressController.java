package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.R;
import com.example.entity.MallAddress;
import com.example.service.MallAddressService;
import com.example.session.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商城用户收货地址 前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/api/mall-address")
@Slf4j
public class MallAddressController {
    @Autowired
    private MallAddressService addressService;

    @GetMapping("list")
    public List<MallAddress> list(HttpSession session
                           )  {
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null){
            throw new RuntimeException("请先登录");
        }
        QueryWrapper<MallAddress> q = new QueryWrapper<>();
        q.eq("user_id",user.getUserId());
        q.orderByDesc("address_default");
        q.orderByAsc("address_id");
        return addressService.list(q);
    }
    @GetMapping("{id}")
    public Object get(@PathVariable Long id) {
        return addressService.getById(id);
    }
    @PostMapping("add")
    public R add(@RequestBody MallAddress address, HttpSession session){
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null){
            return R.error().setMessage("请先登录");
        }
        List<MallAddress> list = new ArrayList<>();
        QueryWrapper<MallAddress> q = new QueryWrapper<>();
        q.eq("user_id",user.getUserId());
        list = addressService.list(q);
        if (list == null){
            address.setUserId(user.getUserId());
            address.setAddressDefault(true);
            addressService.save(address);
            return R.ok().setMessage("添加地址成功");
        }
        else {
            address.setUserId(user.getUserId());
            address.setAddressDefault(false);
            addressService.save(address);
            return R.ok().setMessage("添加地址成功");
        }
    }
    @PutMapping("update")
    public void update(@RequestBody MallAddress mallAddress) {
        try {
            addressService.updateById(mallAddress);
        } catch (Exception e) {
            throw new RuntimeException("修改失败", e);
        }
    }
    @DeleteMapping("{id}")
    public R delete(@PathVariable Long id,HttpSession session){
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null){
            return R.error().setMessage("请先登录");
        }
        addressService.removeById(id);
        return R.ok().setMessage("删除成功");

    }
    @PutMapping("updatepuls/{addressId}")
    public void updatepuls(@PathVariable("addressId") Long addressId,HttpSession session){
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null){
            throw new RuntimeException("请先登录");
        }
        UpdateWrapper<MallAddress> u = new UpdateWrapper<>();
        u.eq("user_id",user.getUserId());
        u.set("address_default",false);
        addressService.update(u);
        u = new UpdateWrapper<>();
        u.eq("address_id",addressId);
        u.set("address_default",true);
        addressService.update(u);
    }
    @GetMapping("getAddress/{id}")
    public MallAddress getAddress(@PathVariable Long id){
        return  addressService.getById(id);
    }

}

