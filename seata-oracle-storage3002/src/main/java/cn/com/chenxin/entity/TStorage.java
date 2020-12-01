package cn.com.chenxin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("T_STORAGE")
public class TStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("PRODUCT_ID")
    private String productId;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("USED")
    private BigDecimal used;

    @TableField("RESIDUE")
    private BigDecimal residue;


}
