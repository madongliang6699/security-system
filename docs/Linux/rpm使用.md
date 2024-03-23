## rpm 查询
rpm -qa 查询所有已经安装的rpm包  
rpm -qa | grep Jenkins -a 是all的意思，查询已经安装的Jenkins包  
rpm -ql | grep Jenkins -l 是list的意思， 查询已经安装的Jenkins包，并列出所有涉及到的安装目录  
rpm -qpi Linux-1.4-6.i368.rpm　查看rpm包package信息，–query–package–install 



## rpm 安装
 rpm -ivh jenkins-2.319.2-1.1.noarch.rpm 安装Jenkins包  
* -i 是安装的意思 install
* -v 显示安装过程 verbose
* -h 显示进度 hash

 rpm -ivh --test jenkins-2.319.2-1.1.noarch.rpm 用来检查依赖关系，并不是真正的安装



## rpm 升级
rpm -Uvh jenkins-2.319.2-1.1.noarch.rpm 升级Jenkins包
* -U 是更新的意思
* -v 显示安装过程
* -h 显示进度

rpm -Uvh --oldpackage gaim-1.3.0-1.fc4.i386.rpm 新版本降级为旧版本

## rpm 反安装：卸载
rpm -e jenkins-2.319.2-1.1.noarch.rpm  -e是erase：清除


## rpm安装和tar压缩包安装的区别

