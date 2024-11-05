# 使用基础镜像
FROM registry.cn-hangzhou.aliyuncs.com/mdl_study/openjdk:8

# 设置标签（工作中一般不写这个，或不写自己的信息，写开源镜像的时候可以写上自己的信息）
LABEL maintainer="madongliang"
LABEL email="mdl6699@163.com"

# 设置构建时变量
ARG VERSION=1.0

# 设置工作目录
WORKDIR /app/service

# 添加文件当前文件夹下的jar包到镜像中，改名字为myApp.jar
ADD security-order-1.0.0-SNAPSHOT.jar /app/service/myApp.jar
ADD *.properties /app/service/


# 设置工作目录
WORKDIR /app/service

# 暴露端口
EXPOSE 8081/tcp

# 创建数据卷
VOLUME /app/service/log

# 设置启动命令
ENTRYPOINT ["nohup","java","-Xms56m","-Xmx56m","-Xmn56m","-Dfile.encoding=UTF-8","-jar","/app/service/myApp.jar", ">", "/app/service/log/app.log", "2>&1", "&"]

