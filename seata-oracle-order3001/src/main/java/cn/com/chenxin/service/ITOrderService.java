package cn.com.chenxin.service;

import cn.com.chenxin.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxl
 * @since 2020-11-30
 */
public interface ITOrderService extends IService<TOrder> {
    void createOrder(TOrder order);
}
