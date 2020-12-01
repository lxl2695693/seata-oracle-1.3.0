package cn.com.chenxin.controller;


import cn.com.chenxin.entity.CommonResult;
import cn.com.chenxin.entity.TAccount;
import cn.com.chenxin.mapper.TAccountMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxl
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/account")
public class TAccountController {
    @Autowired
    private TAccountMapper accountMapper;

    @PostMapping("/updateAccount")
    public CommonResult updateAccount(@RequestParam("userId") String userId, @RequestParam("money") BigDecimal money){
        try{
            TAccount tAccount = new LambdaQueryChainWrapper<>(accountMapper).eq(TAccount::getUserId, userId).one();
            TimeUnit.SECONDS.sleep(20);
            new LambdaUpdateChainWrapper<>(accountMapper).eq(TAccount::getUserId,userId)
                    .set(TAccount::getUsed,tAccount.getUsed().add(money))
                    .set(TAccount::getResidue,tAccount.getResidue().subtract(money)).update();
            return new CommonResult(200,"账户操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(400,"账户操作失败");
        }

    }
}
