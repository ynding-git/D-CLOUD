## 启动基础服务
postgres、redis、rabbitmq
`docker-compose up -d`

## 启动core组件
`docker-compose -f docker-compose.yml -f docker-compose.core.yml up`

## 启动devops组件
 先启动elasticsearch，在启动其他服务：
`docker-compose -f docker-compose.yml -f docker-compose.devops.yml up elasticsearch`
`docker-compose -f docker-compose.yml -f docker-compose.devops.yml up`

## 启动Apollo配置中心

`docker-compose -f docker-compose.yml -f docker-compose.config.yml up`

## 启动阿里注册中心

`docker-compose -f docker-compose.yml -f docker-compose.nacos.yml up`
访问http://localhost:8848/nacos ， 用户名/密码: nacos/nacos

## 启动seata 事务管理服务
 先运行脚本，将seata配置写入nacos：
 sh nacos-config.sh -h localhost -u nacos -w nacos -t {nacos空间id}
`docker-compose -f docker-compose.yml -f docker-compose.seata.yml up seata-server`

## 启动auth中心

`docker-compose -f docker-compose.yml -f docker-compose.auth.yml up`

## 启动db中心

`docker-compose -f docker-compose.yml -f docker-compose.db.yml up`

## 启动physical中心

`docker-compose -f docker-compose.yml -f docker-compose.physical.yml up`