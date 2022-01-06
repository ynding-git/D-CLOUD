package com.ynding.cloud.route.gateway.admin.model.po;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网关过滤器
 * @author Administrator
 */
@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDefinitionPO {
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();
}
