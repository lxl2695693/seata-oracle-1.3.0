package cn.com.chenxin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxl
 * @since 2020-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_ORDER")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("USER_ID")
    private String userId;

    @TableField("PRODUCT_ID")
    private String productId;

    @TableField("COUNT")
    private BigDecimal count;

    @TableField("MONEY")
    private BigDecimal money;

    @TableField("STATUS")
    private String status;

    @TableField("CREATE_DATE")
    private LocalDateTime createDate;


}
