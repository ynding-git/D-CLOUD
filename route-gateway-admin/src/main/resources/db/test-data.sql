--用户
INSERT INTO gateway_route (id, route_id, uri, predicates, filters, orders, description, status, created_time, updated_time, created_by, updated_by)
VALUES
(101,
 'auth-server-admin',
 'lb://auth-server-admin:10401',
 '[{"name":"Path","args":{"pattern":"/auth-server-admin/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '授权认证服务网关注册',
 'Y', now(), now(), 'system', 'system'),
(102,
 'auth-server-authentication',
 'lb://auth-server-authentication:10402',
 '[{"name":"Path","args":{"pattern":"/auth-server-authentication/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '签权服务网关注册',
 'Y', now(), now(), 'system', 'system');