package com.ynding.cloud.route.gateway.admin.model.po;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网关断言
 * @author Administrator
 */
@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredicateDefinitionPO {
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();
}
