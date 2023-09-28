package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.UserInfo;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT user_name,user_nickname,user_sex,user_tel,user_brithdate,role_name  \n" +
            "                       FROM sys_user INNER JOIN sys_role ON sys_user.user_id = #{userId}\n" +
            "            AND sys_user.role_id = sys_role.role_id")
    List<UserInfo> getUser(@Param("userId") Long userId);
}
