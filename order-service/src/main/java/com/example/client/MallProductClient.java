package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shop-service",path = "/api/mall-product",contextId = "shopApi")
public interface MallProductClient {
    @GetMapping("/updateNum")
    void updateNum(@RequestParam Long productId,@RequestParam Integer OrderDetailNum);
}
