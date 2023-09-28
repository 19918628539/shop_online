package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cart-service",path = "/api/mall-cart",contextId = "cartApi")
public interface MallCartClient {
    @RequestMapping("/QueryRemove")
    void QueryRemove(@RequestParam Long userId, @RequestParam Long productId);
}
