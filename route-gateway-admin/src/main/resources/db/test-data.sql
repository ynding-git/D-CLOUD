--用户
INSERT INTO gateway_route (id, route_id, uri, predicates, filters, orders, description, status, create_time, update_time, create_by, update_by)
VALUES
(101,
 'auth-server-admin',
 'lb://auth-server-admin:10401',
 '[{"name":"Path","args":{"pattern":"/authorization/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '授权认证服务网关注册',
 'Y', now(), now(), 'system', 'system'),
(102,
 'auth-server-authentication',
 'lb://auth-server-authentication:10402',
 '[{"name":"Path","args":{"pattern":"/authentication/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '签权服务网关注册',
 'Y', now(), now(), 'system', 'system'),
(103,
 'route-gateway-admin',
 'lb://route-gateway-admin:10105',
 '[{"name":"Path","args":{"pattern":"/route/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '路由配置服务',
 'Y', now(), now(), 'system', 'system')
 ;