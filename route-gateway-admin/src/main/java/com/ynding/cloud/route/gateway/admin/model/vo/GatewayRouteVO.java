package com.ynding.cloud.route.gateway.admin.model.vo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynding.cloud.route.gateway.admin.entity.GatewayRoute;
import com.ynding.cloud.route.gateway.admin.model.po.FilterDefinitionPO;
import com.ynding.cloud.route.gateway.admin.model.po.PredicateDefinitionPO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@Slf4j
public class GatewayRouteVO implements Serializable {
    private static final long serialVersionUID = -3876128248692741056L;

    private String id;
    private String uri;
    private Integer order;
    private List<FilterDefinitionPO> filters = new ArrayList<>();
    private List<PredicateDefinitionPO> predicates = new ArrayList<>();

    public GatewayRouteVO(GatewayRoute gatewayRoute) {
        this.id = gatewayRoute.getId();
        this.uri = gatewayRoute.getUri();
        this.order = gatewayRoute.getOrders();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.filters = objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinitionPO>>() {
            });
            this.predicates = objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinitionPO>>() {
            });
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
    }
}
