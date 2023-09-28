package com.example.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override public boolean save(User entity) {
        if(StrUtil.isBlank(entity.getUserName())){
            throw new RuntimeException("用户名不能为空");
        }if(!entity.getUserName().matches("^[a-zA-Z]\\w{2,19}$")){
            throw new RuntimeException("用户名必须以字母开头,长度3-20");
        }
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("user_name", entity.getUserName());
        if(baseMapper.selectCount(q)>0){
            throw new RuntimeException("用户名重复");
        }if(StrUtil.isBlank(entity.getUserPassword())){
            throw new RuntimeException("密码不能为空");
        }if(!entity.getUserPassword().matches("^.{3,14}$")){
            throw new RuntimeException("密码只能为3-14位");
        }
        entity.setRoleId(2);
        entity.setUserStatus(false);
        //entity.setUserRegTime(new Date()); //密码加密=md5(userName+userPassword)
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(entity.getUserName()+entity.getUserPassword());
        entity.setUserPassword(digestHex);
        baseMapper.insert(entity);
        return true;
    }
}
