package com.example.client;

import com.example.vo.MallAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cart-service",path = "/api/mall-address",contextId = "addressApi")
public interface MallAddressClient {
    @GetMapping("/getAddress/{id}")
    MallAddress getMallAddressByid(@PathVariable Long id);
}
