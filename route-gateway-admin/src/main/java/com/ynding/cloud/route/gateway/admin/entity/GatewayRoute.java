package com.ynding.cloud.route.gateway.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("gateway_route")
public class GatewayRoute implements Serializable {
    private static final long serialVersionUID = -1055234991858345351L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(name = "id", notes = "ID", dataType = "string")
    private String id;

    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer orders = 0;
    private String status = "Y";

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
}
