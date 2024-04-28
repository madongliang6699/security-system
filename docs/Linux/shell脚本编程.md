
## 高级编程语言

编译类语言：高级语言->编译器->机器代码文件->执行。 如：c、c++、java
解释类语言：高级语言->执行->解释器->机器代码，如：shell，python，php，JavaScript，perl

编译类的语言就是先开发完所有的代码，然后整个编译成一个可直接执行的文件，给机器执行。
解释类的语言就是，写一句或读取一句代码就解释一句代码，然后就机器执行语句代码，然后再读取一句，再解释再机器执行。

用吃饭的方式做类比的话：  
编译类的语言就像是先把所有菜和主食都做好后，然后一起吃。
解释类语言就像是吃火锅，拿一个食物涮一个吃一个，然后再哪一个涮一个吃一个。

因此，编译类的语言在开发的时候效率慢，因为写一句代码要测试的话都要编译一下才能执行。但是整个开发完之后，上线执行就效率高了，因为整个都已经编译成机器码给机器执行。不需要每次执行都编译了。
而解释类的语言是刚好反过来的，开发的时候很快，因为写一句代码就可以执行一下立马看结果，很快。但是上线之后还是要执行一句就要解释一句，效率慢。

## shell 脚本语言的基本用法
### shell 脚本的用途
将简单的命令组合完成复杂的工作,自动化执行命令,提高工作效率  
减少手工命令的重复输入，一定程度上避免人为错误  
将软件或应用的安装及配置实现标准化  
用于实现日常性的,重复性的运维工作,如:文件打包压缩备份,监控系统运行状态并实现告警等  

### shell 脚本基本结构
shell脚本编程：是基于过程式、解释执行的语言

编程语言的基本结构：  
各种系统命令的组合  
数据存储：变量、数组  
表达式：a + b  
控制语句：if  

**shell脚本：包含一些命令或声明，并符合一定格式的文本文件**  

**格式要求：首行shebang机制：**（就是声明是哪种语言写的，用#!加语言程序类型来声明。比如下面的三种）
~~~
#!/bin/bash
#!/usr/bin/python
#!/usr/bin/perl
~~~
因为#在英语里读she，!读bang，所以叫shebang（舍棒）机制。  

其实上面指定的语言类型比如/bin/bash，实际上就是bash程序，bash程序就是用来解释shell脚本的程序。我们登录Linux终端命令行，实际上就是默认给我们开启的一个bash程序交互。

### 第一个脚本  

新建一个文件 hello.sh， shell脚本一般以.sh为文件后缀，不写后缀也是可以，但是一般规范都写。
给hello.sh写入以下内容：
~~~shell
#!/bin/bash
# ------------------------------------------
# Filename: hello.sh
# Version:  1.0
# Date: 2017/06/01
# Author: wang
# Email: 29308620@qq.com
# Website: www.wangxiaochun.com
# Description: This is the first script
# Copyright: 2017 wang
# License: GPL
# ------------------------------------------

echo 'Hello, world!'
~~~
保存，就可以执行该第一个shell脚本了。

执行方法：
~~~shell
#执行方法1
[root@centos8 ~]#bash /data/hello.sh
#执行方法2（管道的方式）
[root@centos8 ~]#cat /data/hello.sh | bash
#执行方法3（这种方法是前面学的标准输入重定向的方式，从文件中获取参数数据，给bash命令程序去执行）
[root@centos8 ~]#bash < /data/hello.sh
#执行方法4（如果不用bash或sh命令，直接去执行的话，会提示没有权限，可以加权限）
[root@centos8 ~]#chmod +x /data/hello.sh
#加权限后就可以把脚本直接当作命令执行了。可以说脚本就是命令了。
#绝对路径方式执行
[root@centos8 ~]#/data/hello.sh
#相对路径方式执行
[root@centos8 ~]#cd /data/
[root@centos8 ~]#./hello.sh

#这种方式可以执行远程的shell脚本，比如你的远程服务器写好了一个xxx.sh批量安装mysql，redis，docker，zk，nginx的脚本，
# 当你有一个新服务器或去客户方做部署的时候，你可以这样一键执行远程写好的该脚本，实现一键安装生产环境。甚至可以一键部署好所有服务。
[root@centos8 ~]#curl http://10.0.0.8/xxx.sh |bash
~~~

范例:备份脚本：
~~~shell
#!/bin/bash
#
#********************************************************************
#Author: wangxiaochun
#QQ: 29308620
#Date: 2019-12-20
#FileName： backup.sh
#URL: http://www.magedu.com
#Description： The test script
#Copyright (C): 2019 All rights reserved
#********************************************************************
echo -e "\033[1;32mStarting backup...\033[0m"
sleep 2
cp -av /etc/ /data/etc`date +%F`/
echo -e "\033[1;32mBackup is finished\033[0m"
~~~



## shell 脚本调试

只检测脚本中的语法错误，但无法检查出命令错误，但不真正执行脚本：
bash -n /path/to/some_script

调试并执行:就类似debug，执行一行显示一行的执行结果，检查大脚本很有用，用于检查脚本写的逻辑错误：
bash -x /path/to/some_script

也可以在脚本中通过cat或echo，阶段性的打印一些结果，观察是否有问题，就像java开发中的system.out.println();

### 总结：脚本错误常见的有三种
语法错误: 会导致后续的命令不继续执行，可以用bash -n 检查错误，提示的出错行数不一定是准确的.  
命令错误: 命令不小心写错了，默认后续的命令还会继续执行，用bash -n 无法检查出来 ，可以使用 bash -x 进行观察.  
逻辑错误: 只能使用 bash -x 进行观察.