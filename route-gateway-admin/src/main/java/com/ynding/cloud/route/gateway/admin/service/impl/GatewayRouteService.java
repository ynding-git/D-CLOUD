package com.ynding.cloud.route.gateway.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynding.cloud.route.gateway.admin.dao.GatewayRouteMapper;
import com.ynding.cloud.route.gateway.admin.entity.GatewayRoute;
import com.ynding.cloud.route.gateway.admin.model.query.GatewayRouteQuery;
import com.ynding.cloud.route.gateway.admin.model.vo.GatewayRouteVO;
import com.ynding.cloud.route.gateway.admin.service.IGatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class GatewayRouteService extends ServiceImpl<GatewayRouteMapper, GatewayRoute>
        implements IGatewayRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public long add(GatewayRoute gatewayRoute) {
        long gatewayId = gatewayRouteMapper.insert(gatewayRoute);
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES + gatewayRoute.getId(), toJson(new GatewayRouteVO(gatewayRoute)));
        return gatewayId;
    }

    @Override
    public void delete(String id) {
        gatewayRouteMapper.deleteById(id);
        stringRedisTemplate.delete(GATEWAY_ROUTES + id);
    }

    @Override
    public void update(GatewayRoute gatewayRoute) {
        gatewayRouteMapper.updateById(gatewayRoute);
        stringRedisTemplate.delete(GATEWAY_ROUTES + gatewayRoute.getId());
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES, toJson(new GatewayRouteVO(get(gatewayRoute.getId()))));
    }

    @Override
    public GatewayRoute get(String id) {
        return gatewayRouteMapper.selectById(id);
    }

    @Override
    public List<GatewayRoute> query(GatewayRouteQuery gatewayRouteQuery) {
        QueryWrapper<GatewayRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != gatewayRouteQuery.getCreatedTimeStart(), "created_time", gatewayRouteQuery.getCreatedTimeStart());
        queryWrapper.le(null != gatewayRouteQuery.getCreatedTimeEnd(), "created_time", gatewayRouteQuery.getCreatedTimeEnd());
        queryWrapper.eq(StringUtils.isNotBlank(gatewayRouteQuery.getUri()), "uri", gatewayRouteQuery.getUri());
        return gatewayRouteMapper.selectList(queryWrapper);
    }

    @Override
    public boolean overload() {
        List<GatewayRoute> gatewayRoutes = gatewayRouteMapper.selectList(new QueryWrapper<>());
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        gatewayRoutes.forEach(gatewayRoute ->
                opsForValue.set(GATEWAY_ROUTES + gatewayRoute.getId(), toJson(new GatewayRouteVO(gatewayRoute)))
        );
        return true;
    }

    /**
     * GatewayRoute转换为json
     *
     * @param gatewayRouteVo redis需要的vo
     * @return json string
     */
    private String toJson(GatewayRouteVO gatewayRouteVo) {
        String routeDefinitionJson = Strings.EMPTY;
        try {
            routeDefinitionJson = new ObjectMapper().writeValueAsString(gatewayRouteVo);
        } catch (JsonProcessingException e) {
            log.error("网关对象序列化为json String", e);
        }
        return routeDefinitionJson;
    }
}
