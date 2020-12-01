package cn.com.chenxin.controller;


import cn.com.chenxin.entity.CommonResult;
import cn.com.chenxin.entity.TOrder;
import cn.com.chenxin.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxl
 * @since 2020-11-30
 */
@RestController
@RequestMapping("/order")
public class TOrderController {

    @Autowired
    private ITOrderService orderService;
    @GetMapping("/createOrder")
    public CommonResult createOrder(TOrder order){
        orderService.createOrder(order);
        return new CommonResult(200,"订单创建成功！");
    }

}
