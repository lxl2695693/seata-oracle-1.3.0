package cn.com.chenxin.service.impl;

import cn.com.chenxin.entity.CommonResult;
import cn.com.chenxin.entity.TOrder;
import cn.com.chenxin.mapper.TOrderMapper;
import cn.com.chenxin.service.AccountService;
import cn.com.chenxin.service.ITOrderService;
import cn.com.chenxin.service.StorageService;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxl
 * @since 2020-11-30
 */
@Primary
@Service
@Slf4j
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {
    @Autowired
    private TOrderMapper orderMapper;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    /**
     * 下订单-》减库存-》减余额-》改状态
     * @param order
     */
    @Override
    @GlobalTransactional(name = "chenxin-create-order",rollbackFor = Exception.class)
    public void createOrder(TOrder order) {
        log.info("----->开始新建订单");
        orderMapper.insert(order);
        log.info("-----> 库存微服务开始减库存");
        CommonResult commonResult = storageService.updateStorage(order.getProductId(), order.getCount());
        System.out.println(commonResult.getMessage());
        log.info("----> 库存微服务结束减库存 END====");
        log.info("----> 账户微服务扣减金额");
        CommonResult result = accountService.updateAccount(order.getUserId(), order.getMoney());
        System.out.println(result.getMessage());
        log.info("----> 账户微服务扣减金额 END====");
        log.info("----> 修改订单状态开始");
        new LambdaUpdateChainWrapper<TOrder>(orderMapper).eq(TOrder::getUserId, order.getUserId())
                .eq(TOrder::getStatus, 0).set(TOrder::getStatus, 1).update();
        log.info("----> 修改订单状态 END====");
    }
}
