package com.example.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.session.AuthUser;
import com.example.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
        public  final static Logger logg = LoggerFactory.getLogger(UserController.class);
        @Autowired
        private UserService userService;
        @Autowired(required = false)
        private UserMapper mapper;
        @Autowired
        private RedisUtils redisUtils;
        //查询多个用户
        @GetMapping("list")
        public List<User> list(String key,
                               @RequestParam(defaultValue = "10") Integer len, Integer roleId
        )  {
            Page<User> p = new Page<>(1, len,false);
            return page(p,key,null,roleId).getRecords();
        }
    @GetMapping("page")
    public Page<User>page(Page<User> p,String key,
                          @RequestParam(defaultValue = "10") Integer len,Integer roleId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();//new一个条件构造器出来
        if (roleId != null) {
            // and role_id = #{role_Id}
            queryWrapper.eq("role_id",roleId);//等于
            //and role_id !=#{role_id}
            queryWrapper.ne("role_id",roleId);
        }
        // if (key != null && !key.trim().equals("")) {
        if (StrUtil.isNotBlank(key/*非空判断*/)){
            // and user_name like '%${key}%' or user_nickname '${key}%'
            // queryWrapper.like("user_name",key).or().likeRight("user_nickname",key);
            //and (user_name like '%${key}%' or user_nickname '${key}%')
            queryWrapper.like("user_name",key).or().like("user_nickname",key);
        }
        return userService.page(p,queryWrapper);
    }
    @GetMapping("userinfo")//获取一个用户，通过id获取
    public Object getUser(
            HttpSession session
    ){
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        return mapper.getUser(user.getUserId());
    }

    //添加一个用户
    @PostMapping("add")
    public R add(User user){
        try {
            userService.save(user);
            return R.ok();
        }catch (Exception e) {
            logg.debug("添加失败", e);
            return R.error().setMessage("添加失败");

        }
    }
    //json格式添加
    @PostMapping("addJson")
    public void addJson(@RequestBody User user){
        try {
            userService.save(user);

        }catch (Exception e) {
            throw new RuntimeException("添加失败",e);

        }
    }

    @PutMapping("update")
    public void update(@RequestBody User user){
        try {

            userService.updateById(user);

        }catch (Exception e) {
            throw new RuntimeException("修改失败",e);

        }
    }
    @PutMapping("updateuser")
    public void updateUser(@RequestBody User u,HttpSession session){
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        try {
            u.setUserId(user.getUserId());
             userService.updateById(u);
        }catch (Exception e) {
            throw new RuntimeException("修改失败",e);

        }
    }

    //删除
    @DeleteMapping("delete/{userId}")
    public boolean delete(@PathVariable("userId") Long userId){
        try {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
            userService.remove(userQueryWrapper.eq("user_id",userId));
            R.ok().setMessage("删除成功");
            return true;
        }catch (Exception e){
            logg.debug("删除失败",e);
            return false;
        }

    }
    @GetMapping("validate")
    public boolean validateUsername(String userName){
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("user_name",userName);
        return userService.count(q)>0;

    }

    @PostMapping("login")
    public Object login (String userName, String userPassword, HttpSession session){
        if(StrUtil.isBlank(userName)){
            throw new RuntimeException("请输入账号");
        }
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("user_name",userName);
        User user = userService.getOne(q);
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(user.getUserName()+userPassword);
        if (user.getUserName() == null||!user.getUserPassword().equals(digestHex)){
            throw new  RuntimeException("用户名或密码错误");
        }if (user.getUserStatus()){
            throw new RuntimeException("账号已冻结");
        }
        AuthUser authUser = new AuthUser();
        BeanUtil.copyProperties(user,authUser);
        session.setAttribute("user",authUser);
        return authUser;
    }
    @GetMapping("auth-user")
    public Object getAuthUser(HttpSession session){
        return (AuthUser) session.getAttribute("user");
    }

    @GetMapping("logout")
    public void logout(HttpSession session){
        session.invalidate();//注销会话
    }

    @PutMapping("updatedpwd")
    public R updatedpwd(@RequestBody User u, HttpSession session){
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        try {
            u.setUserId(user.getUserId());
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            String digestHex = md5.digestHex(user.getUserName()+u.getUserPassword());
            u.setUserPassword(digestHex);
            userService.updateById(u);
            return R.ok().setMessage("修改成功");
        }catch (Exception e) {
            logg.debug("修改失败",e);
            return R.error();
        }
    }
    @PostMapping("adminlogin")
    public Object adminlogin(String userName, String userPassword, HttpSession session){

        //根据用户名查找用户
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("user_name", userName).eq("role_id","1");
        User user = userService.getOne(q);
        if(user==null){
            throw new RuntimeException("管理员账户不存在");
        }
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(user.getUserName()+userPassword);
        if(!user.getUserPassword().equals(digestHex)){
            throw new RuntimeException("密码错误");
        }
        if(user.getUserStatus()==true){
            throw new RuntimeException("该账号被冻结了了");
        }
        AuthUser authUser = new AuthUser();
        BeanUtil.copyProperties(user, authUser);
        session.setAttribute("user", authUser);
        return authUser;
    }
}

