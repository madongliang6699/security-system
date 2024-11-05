
## man：获得命令帮助信息
基本语法:
man [命令或配置文件]


#### 安装 man 页面 中文翻译
yum install man-pages-zh-CN.noarch  
安装完之后，如果该命令的man介绍有中文翻译页面就显示中文的，否则显示英文的。有点命令好像中英文都没有，提示无该命令手册。


## type：查看命令的类型【内部命令|外部命令】
一部分基础功能的系统命令是直接内嵌在 shell 中的，系统加载启动之后会随着 shell 一起加载，常驻系统内存中。这部分命令被称为“内置（built-in） 命令”； 相应的其它命令被称为“外部命令”。

基本语法：
type 命令

~~~shell
[root@testx ~]# type cd
cd 是 shell 内嵌
[root@testx ~]# type ls
ls 是 `ls --color=auto' 的别名
[root@testx ~]# type mkdir
mkdir 是 /usr/bin/mkdir
~~~


## help：获取shell内置命令帮助信息

查看内部命令帮助的基本语法：
help 命令

查看外部命令的帮助信息 语法：
命令 --help   例如： ls --help


## clear：清屏 等价于 ctrl + l 的快捷键
但是这种清屏只是将的内容向上滚动了而已，并没有真正意义上的清屏，如果要完全清屏，需要用reset命令。

执行reset命令后，命令窗口会被重启，会彻底清屏。

常用快捷键：
ctrl+c	停止进程
ctrl+l	清屏，等同于clear；彻底清屏是：reset
善于用tab键	提示（更重要的是可以防止敲错）
上下键	查找执行过的命令

