package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.MallCart;
import com.example.mapper.MallCartMapper;
import com.example.service.MallCartService;
import com.example.session.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 * 商城购物车 前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/api/mall-cart")
public class MallCartController {
    public  final static Logger logg = LoggerFactory.getLogger(MallCartController.class);
    @Autowired
    private MallCartService mallCartService;
    @Autowired(required = false)
    private MallCartMapper mallCartMapper;
    @PostMapping
    public void add(Long productId, Integer cartNum, HttpSession session) throws Exception {
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null){
            throw new RuntimeException("请先登录");
        }
        Thread.sleep(2000);//模拟延时

        MallCart cart = null;
        QueryWrapper<MallCart> q = new QueryWrapper<>();
        q.eq("user_id",user.getUserId()).eq("product_id",productId);
        cart = mallCartService.getOne(q);
        if (cart == null){
            cart = new MallCart();
            cart.setUserId(user.getUserId());
            cart.setProductId(productId);
            cart.setCartNum(cartNum);
            cart.setCartTime(new Date());
            mallCartService.save(cart);
        }else {
            cart.setCartNum(cart.getCartNum()+cartNum);
            cart.setCartTime(new Date());
            mallCartService.updateById(cart);
        }
    }

    @GetMapping
    public Object listCart(HttpSession session)throws Exception {
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        return mallCartMapper.listCart(user.getUserId());


    }


    @PutMapping
    public void update(@RequestBody MallCart mallCart) {
        try {
            mallCartService.updateById(mallCart);
        } catch (Exception e) {
            throw new RuntimeException("修改失败", e);
        }
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        mallCartService.removeById(id);
    }
    @GetMapping("QueryRemove")
    public void QueryRemove(@RequestParam Long userId,@RequestParam Long productId){
        //删除购物车中的商品
        QueryWrapper<MallCart> q1 = new QueryWrapper<>();
        q1.eq("user_id", userId);
        q1.eq("product_id", productId);
        mallCartService.remove(q1);
    }
}

