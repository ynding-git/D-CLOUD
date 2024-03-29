
-- 网关路由表
DROP TABLE IF EXISTS gateway_route;
CREATE TABLE gateway_route
(
    id           VARCHAR(32) PRIMARY KEY COMMENT 'id',
    route_id     VARCHAR(100) NOT NULL COMMENT '路由id',
    uri          VARCHAR(100) NOT NULL COMMENT 'uri路径',
    predicates   TEXT         NOT NULL COMMENT '判定器',
    filters      TEXT COMMENT '过滤器',
    orders       INT COMMENT '排序',
    description  VARCHAR(500) COMMENT '描述',
    status       VARCHAR(1)            DEFAULT 'Y' COMMENT '状态：Y-有效，N-无效',
    create_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    create_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    update_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '网关路由表';
CREATE UNIQUE INDEX ux_gateway_routes_uri ON gateway_route (uri);
