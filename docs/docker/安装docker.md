

### 1）先卸载旧版本
yum list installed | grep docker 列出当前所有docker的包  
yum -y remove docker的包名称 卸载docker包  
rm -rf /var/lib/docker 删除docker的所有镜像和容器  

### 2）安装docker之前，先安装必要的软件包
yum install -y yum-utils device-mapper-persistent-data lvm2  
运行安装上面三个软件包：  
yum-utils 是yum的一些工具包；    
device-mapper-persistent-data 和 lvm2 是docker的一些必要依赖。


### 3）设置下载的镜像仓库（阿里云）
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

### 4）列出需要安装的版本列表
yum list docker-ce --showduplicates | sort -r

如果上面的命令列不错可用的docker-ce的软件包，那么：  
更新你的软件包列表，确保所有的仓库都是最新的:  
yum makecache fast  
如果你的系统是CentOS 7，你可能需要启用docker-ce仓库:  
yum-config-manager --enable docker-ce-edge  
然后再尝试上面的命令列出安装版本。


### 5）安装指定版本（这里使用18.0.1版本）
yum install docker-ce-18.06.1.ce 
或使用这个版本:
yum install docker-ce-3:20.10.14-3.el7 docker-ce-cli-3:20.10.14-3.el7 containerd.io

### 6）查看版本
docker -v 简单查看docker版本；  

docker version ：查看docker的客户端版本和docker服务版本，如果只显示客户端版本，
说明还有启动服务，按下面的命令启动dockr服务就行了。

### 7）启动Docker
systemctl start docker 启动   
systemctl enable docker 设置开机启动

### 8）添加阿里云镜像下载地址
vim /etc/docker/daemon.json  
这个daemon.json是需要自己创建的，原本没有。  
在里面添加内容如下：
```json
{
"registry-mirrors": ["https://xxxxx.mirror.aliyuncs.com"]
}
```
注意：上面的地址是需要去阿里云官网开通的，具体可以百度一下“阿里云容器镜像加速服务”的关键字。
自己个人阿里云账户其实已经开通了：https://lhh2ku35.mirror.aliyuncs.com


### 9）重启Docker
systemctl daemon-reload  
systemctl restart docker