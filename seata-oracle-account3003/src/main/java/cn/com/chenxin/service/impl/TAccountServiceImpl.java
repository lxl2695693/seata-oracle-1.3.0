package cn.com.chenxin.service.impl;

import cn.com.chenxin.entity.TAccount;
import cn.com.chenxin.mapper.TAccountMapper;
import cn.com.chenxin.service.ITAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxl
 * @since 2020-11-30
 */
@Service
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements ITAccountService {

}
