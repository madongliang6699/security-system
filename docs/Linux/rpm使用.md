## RPM 概述

RPM（RedHat Package Manager）， RedHat软件包管理工具， 类似windows里面的setup.exe，
是Linux这系列操作系统里面的打包安装工具， 它虽然是RedHat的标志， 但理念是通用的。

RPM包的名称格式： Apache-1.3.23-11.i386.rpm
“apache” 软件名称
“1.3.23-11”软件的版本号， 主版本和此版本
“i386”是软件所运行的硬件平台， Intel 32位处理器的统称
“rpm”文件扩展名， 代表RPM包

## rpm 查询

rpm -q 程序包名称	 查询已经安装的包，或者说查询指定程序包是否安装，没有安装的话会提示未安装。软件名称一定要写完整。
rpm -qa 查询所有已经安装的rpm包
rpm -qa | grep jenkins   -a 是all的意思，查询已经安装的jenkins包，这种方法不需要写完整软件名，比较方便记不清软件名的情况。
rpm -ql jenkins   -l 是list的意思， 查询已经安装的Jenkins包，并列出所有涉及到的安装目录，这个命令应该时能查询到该软件“服务文件（eg：jenkins.service）”的位置。
rpm -qi 程序包名称	查看指定程序包的名称、版本、许可协议、用途描述等详细信息
rpm -qpi Linux-1.4-6.i368.rpm　查看rpm包package信息，–query–package–install
rpm -qf 文件名或目录	查看指定的文件或目录是由哪个程序包所安装的.

由于软件包比较多，一般都会采取过滤，rpm -qa|grep rpm软件包

## rpm 安装

rpm -ivh jenkins-2.319.2-1.1.noarch.rpm 安装Jenkins包

* -i 是安装的意思 install
* -v 显示安装过程 verbose
* -h 显示进度 hash
*  —nodeps	就是安装时不检查依赖关系，比如你这个rpm需要A，但是你没装A，这样你的包就装不上，用了—nodeps你就能装上了
*  —force	就是强制安装，比如你装过这个rpm的版本1，如果你想装这个rpm的版本2，就需要用—force强制安装

rpm -ivh --test jenkins-2.319.2-1.1.noarch.rpm 用来检查依赖关系，并不是真正的安装

## rpm 升级

rpm -Uvh jenkins-2.319.2-1.1.noarch.rpm 升级Jenkins包

* -U 是更新的意思
* -v 显示安装过程
* -h 显示进度

rpm -Uvh --oldpackage gaim-1.3.0-1.fc4.i386.rpm 新版本降级为旧版本

## rpm 反安装：卸载

rpm -e jenkins-2.319.2-1.1.noarch.rpm  -e是erase：清除

rpm -e —nodeps jenkins-2.319.2-1.1.noarch.rpm  :  
—nodeps	卸载软件时，不检查依赖，这样的话，那些使用该软件包的软件在此之后可能就不能正常工作了。


## rpm安装和tar压缩包安装的区别
