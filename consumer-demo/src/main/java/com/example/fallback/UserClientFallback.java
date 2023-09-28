package com.example.fallback;

import com.example.client.UserClient;
import com.example.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user  = new User();
        user.setId(id);
        user.setName("用户异常");
        return user;
    }
}
