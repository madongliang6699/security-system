1. 使用root或具有sudo权限的用户登录到CentOS系统。
2. 打开网络配置文件，命令为`sudo vi /etc/sysconfig/network-scripts/ifcfg-<interface_name>`，其中`<interface_name>`为要修改的网络接口的名称，如eth0、ens33等。
3. 将BOOTPROTO的值改为static，将ONBOOT的值改为yes，同时在文字下方添加新的IP地址、子网掩码和网关等信息，保存并关闭文件。
4. 重启网络服务以应用更改，命令为`sudo systemctl restart network`。
5. 使用`ip addr`

hostname -I 或 hostname -i 也可以显示ip信息