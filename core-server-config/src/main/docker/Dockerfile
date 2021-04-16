# 该镜像需要依赖的基础镜像
FROM java:8
VOLUME /tmp
#将targer目录下的jar包复制到docker容器
ADD core-server-config.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
