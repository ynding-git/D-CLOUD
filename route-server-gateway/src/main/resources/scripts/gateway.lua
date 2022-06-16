redis.call('set','gateway_routes::101','{"uri": "lb://auth-server-admin:10401", "id": "auth-server-admin", "predicates": [{ "name": "Path", "args": { "pattern": "/authorization/**" } }], "filters":[{ "name": "StripPrefix", "args": { "parts": "1" } }] }');
redis.call('set','gateway_routes::102','{"uri": "lb://auth-server-authentication:10402", "id": "auth-server-authentication", "predicates": [{ "name": "Path", "args": { "pattern": "/authentication/**" } }], "filters":[{ "name": "StripPrefix", "args": { "parts": "1" } }] }');
redis.call('set','gateway_routes::103','{"uri": "lb://route-gateway-admin:10105", "id": "route-gateway-admin", "predicates": [{ "name": "Path", "args": { "pattern": "/route/**" } }], "filters":[{ "name": "StripPrefix", "args": { "parts": "1" } }] }');

return 3