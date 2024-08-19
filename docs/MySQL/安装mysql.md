
## 首先官网下载
mysql-5.7.36-1.el7.x86_64.rpm-bundle.tar

已经在阿里云盘。


## 在Linux上安装
tar -xvf mysql-5.7.36-1.el7.x86_64.rpm-bundle.tar
解压后有很多rpm包：
![img.png](img.png)

只安装这几个就行了：
rpm -ivh mysql-community-common-5.7.36-1.el7.x86_64.rpm  
rpm -ivh mysql-community-libs-5.7.36-1.el7.x86_64.rpm   
rpm -ivh mysql-community-client-5.7.36-1.el7.x86_64.rpm   
rpm -ivh mysql-community-server-5.7.36-1.el7.x86_64.rpm 

安装之前先卸载冲突的mariadb包：  
rpm -qa | grep mariadb  
rpm -e mariadb-libs-5.5.68-1.el7.x86_64 --nodeps

根据提示，可能还要安装一些基础包：libaio.x86_64  "perl(Data::Dumper)" 等。

上面安装没有指定安装的目录，就是安装到了mysql默认的目录。

安装完之后：
systemctl status mysqld
systemctl start mysqld

第一次启动mysql服务后，会自动给root生成一个密码，在：
cat /var/log/mysqld.log 日志文件里，可以看看启动的日志，
里面有一句：
A temporary password is generated for root@localhost: gI7zDjb<)vIr
告诉了你生成的临时密码：gI7zDjb<)vIr

登录：
mysql -uroot -p 回车输入密码

登录后修改密码：alter user 'root'@'localhost' identified by '123987456Mdl';  
会提示：ERROR 1819 (HY000): Your password does not satisfy the current policy requirements  
因为mysql5.6之后会校验密码安全，太简单的密码不给通过。

在 /etc/my.cnf的[mysqld]下面添加一行关闭安全校验的配置：validate_password=off  
重启mysql：systemctl restart mysqld

在登录MySQL后执行修改密码就可以了。

/etc/my.cnf配置文件里的配置可以看一下。



