package com.ynding.cloud.route.gateway.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ynding.cloud.route.gateway.admin.entity.GatewayRoute;
import com.ynding.cloud.route.gateway.admin.model.query.GatewayRouteQuery;

import java.util.List;

/**
 * @author Administrator
 */
public interface IGatewayRouteService extends IService<GatewayRoute> {
    /**
     * 获取网关路由
     *
     * @param id
     * @return
     */
    GatewayRoute get(String id);

    /**
     * 新增网关路由
     *
     * @param gatewayRoute
     * @return
     */
    long add(GatewayRoute gatewayRoute);

    /**
     * 查询网关路由
     *
     * @return
     */
    List<GatewayRoute> query(GatewayRouteQuery gatewayRouteQuery);

    /**
     * 更新网关路由信息
     *
     * @param gatewayRoute
     */
    void update(GatewayRoute gatewayRoute);

    /**
     * 根据id删除网关路由
     *
     * @param id
     */
    void delete(String id);

    /**
     * 重新加载网关路由配置到redis
     * @return 成功返回true
     */
    boolean overload();
}
