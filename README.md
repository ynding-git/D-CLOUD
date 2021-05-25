##查看日志

zipkin访问地址：http://localhost:9411，可以看到请求的耗时与路径

kibana访问地址：http://localhost:5601，可以看到请求打印的日志

grafana访问地址：http://localhost:3000，可以新增es数据源，出可视化的图表和监控

## 分支版本
master  主干, 使用nacos替代eureka
v2.0  使用 eureka
v2.0.1 引入docker


##端口
core-server-eureka 10101
core-server-config 10102

hystrix-dashboard 10104
turbine 10105

physical-book-meta 10201
physical-person-meta 10202

route-server-zuul 10103

auth-server-authentication 10402

monitor-springboot-admin 10501

