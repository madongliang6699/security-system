
## Docker Compose
Compose 简介
Compose,是用于定义和运行多容器 Docker 应用程序的工具。
通过 Compose，您可以使用 YML文件来配置应用程序需要的所有服务。
然后，使用一个命令，就可以从 YML 文件配置中创建并启动所有服务。


## Compose 安装
Compose版本与docker版本的大致对应:  
![img.png](img.png)

下载方式:  
sudo curl -L "https://github.com/docker/compose/releases/download/v2.10.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

将可执行权限应用于下载的二进制文件:  
sudo chmod +x /usl/local/bin/docker-compose


创建软链,这样就可以在任何地方使用了:
sudo ln -s /usr/local/bin/docker-compose  /usr/bin/docker-compose



测试是否安装成功:
docker-compose --version

注意:对于 alpine，需要以下依赖包:py-pip，python-dev,libffi-dev,openss-dev，gcc,libc-dev，和 make.


这样, docker-compose就可以直接使用了,不需要安装重启或配置什么.