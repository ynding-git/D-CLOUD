package com.ynding.cloud.route.gateway.admin.rest;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.route.gateway.admin.domain.GatewayRouteDO;
import com.ynding.cloud.route.gateway.admin.entity.GatewayRoute;
import com.ynding.cloud.route.gateway.admin.model.query.GatewayRouteQuery;
import com.ynding.cloud.route.gateway.admin.model.vo.GatewayRouteVO;
import com.ynding.cloud.route.gateway.admin.service.IGatewayRouteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")
@Slf4j
public class GatewayRouteController {

    @Autowired
    private IGatewayRouteService gatewayRoutService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @ApiImplicitParam(name = "gatewayRoutDO", value = "新增网关路由form表单", required = true, dataType = "GatewayRouteDO")
    @PostMapping
    public ResponseBean add(@Valid @RequestBody GatewayRouteDO gatewayRoutDO) {
        log.info("name:", gatewayRoutDO);
        GatewayRoute gatewayRout = gatewayRoutDO.toPo(GatewayRoute.class);
        return ResponseBean.ok(gatewayRoutService.add(gatewayRout));
    }

    @ApiOperation(value = "删除网关路由", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable String id) {
        gatewayRoutService.delete(id);
        return ResponseBean.ok();
    }

    @ApiOperation(value = "修改网关路由", notes = "修改指定网关路由信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "网关路由ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "gatewayRoutDO", value = "网关路由实体", required = true, dataType = "GatewayRouteDO")
    })
    @PutMapping(value = "/{id}")
    public ResponseBean update(@PathVariable String id, @Valid @RequestBody GatewayRouteDO gatewayRoutDO) {
        GatewayRoute gatewayRout = gatewayRoutDO.toPo(GatewayRoute.class);
        gatewayRout.setId(id);
        gatewayRoutService.update(gatewayRout);
        return ResponseBean.ok();
    }

    @ApiOperation(value = "获取网关路由", notes = "根据id获取指定网关路由信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public ResponseBean get(@PathVariable String id) {
        log.info("get with id:{}", id);
        return ResponseBean.ok(new GatewayRouteVO(gatewayRoutService.get(id)));
    }

    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "网关路由路径", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @GetMapping
    public ResponseBean getByUri(@RequestParam String uri) {
        GatewayRouteQuery query = GatewayRouteQuery.builder().uri(uri).build();
        List<GatewayRouteVO> gatewayRoutesVo = gatewayRoutService.query(query).stream().map(GatewayRouteVO::new).collect(Collectors.toList());
        return ResponseBean.ok(gatewayRoutesVo.stream().findFirst());
    }

    @ApiOperation(value = "搜索网关路由", notes = "根据条件查询网关路由信息")
    @ApiImplicitParam(name = "gatewayRoutQueryForm", value = "网关路由查询参数", required = true, dataType = "GatewayRouteQuery")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @PostMapping(value = "/conditions")
    public ResponseBean search(@Valid @RequestBody GatewayRouteQuery gatewayRouteQuery) {
        List<GatewayRoute> gatewayRoutes = gatewayRoutService.query(gatewayRouteQuery);
        List<GatewayRouteVO> gatewayRoutesVo = gatewayRoutes.stream().map(GatewayRouteVO::new).collect(Collectors.toList());
        return ResponseBean.ok(gatewayRoutesVo);
    }

    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @PostMapping(value = "/overload")
    public ResponseBean overload() {
        return ResponseBean.ok(gatewayRoutService.overload());
    }

}