package cn.com.chenxin.controller;


import cn.com.chenxin.entity.CommonResult;
import cn.com.chenxin.entity.TStorage;
import cn.com.chenxin.mapper.TStorageMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxl
 * @since 2020-11-30
 */
@RestController
@RequestMapping("/storage")
public class TStorageController {

    @Autowired
    private TStorageMapper storageMapper;

    @PostMapping("/updateStorage")
    public CommonResult updateStorage(@RequestParam("productId") String productId, @RequestParam("count") Integer count){
        try{
            TStorage tStorage = new LambdaQueryChainWrapper<>(storageMapper).eq(TStorage::getProductId, productId).one();
            new LambdaUpdateChainWrapper<>(storageMapper).eq(TStorage::getProductId,productId)
                    .set(TStorage::getUsed,tStorage.getUsed().add(new BigDecimal(count)))
                    .set(TStorage::getResidue,tStorage.getResidue().subtract(new BigDecimal(count))).update();
            return new CommonResult(200,"库存调用服务成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(400,"库存调用服务失败！");
        }

    }

}
