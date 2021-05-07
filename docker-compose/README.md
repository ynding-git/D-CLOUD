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

## 启动auth中心

`docker-compose -f docker-compose.yml -f docker-compose.auth.yml up`

## 启动db中心

`docker-compose -f docker-compose.yml -f docker-compose.db.yml up`

## 启动physical中心

`docker-compose -f docker-compose.yml -f docker-compose.physical.yml up`