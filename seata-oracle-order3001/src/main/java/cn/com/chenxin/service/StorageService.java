package cn.com.chenxin.service;

import cn.com.chenxin.config.FeignConfiguration;
import cn.com.chenxin.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-storage-service",configuration = FeignConfiguration.class)
public interface StorageService {
    @PostMapping("/storage/updateStorage")
    CommonResult updateStorage(@RequestParam("productId") String productId, @RequestParam("count") BigDecimal count);
}
