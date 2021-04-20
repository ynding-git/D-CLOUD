# 基于哪个镜像
FROM java:alpine
#将本地⽂件夹挂载到当前容器
VOLUME /tmp
# 拷⻉⽂件到容器
ADD core-server-eureka.jar app.jar
# 开放 10101 端⼝
EXPOSE 10101
# 配置容器启动后执⾏的命令,-c为清除以前启动的数据
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=local","-c"]
