package com.ynding.cloud.route.gateway.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ynding.cloud.route.gateway.admin.entity.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Mapper
@Repository
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {
}