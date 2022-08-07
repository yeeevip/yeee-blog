package vip.yeee.app.common.domain.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yeeee
 * @since 2022-08-07
 */
@Data
@Accessors(chain = true)
@TableName("t_common_data_point")
public class CommonDataPoint {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主体类型
     */
    private Integer subjectType;

    /**
     * 主体主键
     */
    private Long subjectId;

    /**
     * 操作事件
     */
    private Integer event;

    /**
     * 事件描述
     */
    private String eventDesc;

    /**
     * 批次
     */
    private Integer batchNum;

    /**
     * 数量
     */
    private Integer markNum;

    /**
     * 值1
     */
    private String markVal1;

    private String markVal1Desc;

    /**
     * 值2
     */
    private String markVal2;

    private String markVal2Desc;

    /**
     * 值3
     */
    private String markVal3;

    private String markVal3Desc;

    private String markTextVal;

    private String markTexDesc;

    /**
     * 来源渠道
     */
    private String oprSource;

    /**
     * 时间
     */
    private LocalDateTime markTime;

    /**
     * int值1
     */
    private Integer intVal1;

    /**
     * int值1描述
     */
    private String intVal1Desc;

    /**
     * int值2
     */
    private Integer intVal2;

    /**
     * int值2描述
     */
    private String intVal2Desc;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    /**
     * 有效标志 0:有效，1:无效
     */
    private Boolean status;


}
