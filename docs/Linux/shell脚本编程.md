
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



## 变量

####  命名要求
区分大小写  
不能使程序中的保留字和内置变量：如：if, for  
只能使用数字、字母及下划线，且不能以数字开头，注意：不支持短横线 “ - ”，和主机名相反

#### 命名习惯
见名知义，用英文单词命名，并体现出实际作用，不要用简写，如：ATM  
变量名大写  
局部变量小写  
函数名小写  
大驼峰StudentFirstName  
小驼峰studentFirstName  
下划线: student_name  

####  变量定义和引用

- 普通变量：生效范围为当前shell进程；对当前shell之外的其它shell进程，包括当前shell的子shell进程均无效
- 环境变量：生效范围为当前shell进程及其子进程，所以当你写的脚本中又启动执行了其他脚本，那就是你的子进程，但是默认子进程没法使用父进程的普通变量， 如果想使用父进程的变量，那父进程可以定义一个环境变量。
- 本地变量：生效范围为当前shell进程中某代码片断，通常指函数


变量定义或赋值：name='value'

value 可以是以下多种形式：  
直接字串：name='root'  
变量引用：name="$USER"  或 变量引用：name=$USER
命令引用：name=`COMMAND` 或者 name=$(COMMAND)  

变量引用：$name  或  ${name}

弱引用和强引用
"$name" 弱引用，其中的变量引用会被替换为变量值
'$name' 强引用，其中的变量引用不会被替换为变量值，而保持原字符串

~~~shell
[root@centos8 ~]#TITLE='cto'
[root@centos8 ~]#echo $TITLE
cto
[root@centos8 ~]#echo I am $TITLE
I am cto
[root@centos8 ~]#echo "I am $TITLE"
I am cto
[root@centos8 ~]#echo 'I am $TITLE'
I am $TITLE
[root@centos8 ~]#NAME=$USER
[root@centos8 ~]#echo $NAME
root
[root@centos8 ~]#USER=`whoami`
[root@centos8 ~]#echo $USER
root
[root@centos8 ~]#FILE=`ls /run`
[root@centos8 ~]#echo $FILE


[root@centos8 ~]#NUM=`seq 10`
[root@centos8 ~]#echo $NUM
1 2 3 4 5 6 7 8 9 10
[root@centos8 ~]#echo "$NUM"
1
2
3
4
5
6
7
8
9
10
~~~
上面这里注意的是：echo $NUM 和 echo "$NUM" 一般情况下是加不加双引号是一样的结果， 但是结果有换行的时候就有细节区别了。


显示已定义的所有变量：
set

set | grep name1

删除变量：
unset 变量名


####  环境变量
环境变量： 
上面说了，子进程是没法使用父进程的普通变量的，但是可以使用父进程的环境变量。
环境变量就是可以使子进程（包括孙子进程）继承父进程的变量，但是无法让父进程使用子进程的变量   
一旦子进程修改从父进程继承的变量，将会新的值传递给孙子进程  
一般只在系统配置文件中使用，在脚本中较少使用  

【查看父子进程关系可以使用pstree -p命令查看，或在脚本中打印出当前进程id和父进程id查看。】
~~~shell
#声明并赋值
export name=VALUE
declare -x name=VALUE
#或者分两步实现
name=VALUE
export name
~~~

但是实际生产中，脚本中很少使用环境变量，环境变量一般都是配置文件中使用。脚本中使用环境变量的话会很容易混淆。

#### 只读变量
只读变量：只能声明定义，但后续不能修改和删除，即常量

声明：  
readonly name  
declare -r name

查看只读变量：  
readonly [-p]  
declare -r


#### 位置变量
位置变量：在bash shell中内置的变量, 在脚本代码中调用通过命令行传递给脚本的参数
~~~
$1, $2, ... 对应第1个、第2个等参数，shift [n]换位置
$0 命令本身,包括路径
$* 传递给脚本的所有参数，全部参数合为一个字符串
$@ 传递给脚本的所有参数，每个参数为独立字符串
$# 传递给脚本的参数的个数
注意：$@ $* 只在被双引号包起来的时候才会有差异
~~~

清空所有位置变量：set --

范例：
~~~shell
下面是一个案例脚本：
[root@centos8 ~]#cat /data/scripts/arg.sh 
#!/bin/bash

echo "1st arg is $1"
echo "2st arg is $2"
echo "3st arg is $3"
echo "10st arg is ${10}"
echo "11st arg is ${11}"
echo "The number of arg is $#"
echo "All args are $*"
echo "All args are $@"
echo "The scriptname is `basename $0`"

执行脚本的结果：
[root@centos8 ~]#bash /data/scripts/arg.sh {a..z}
1st arg is a
2st arg is b
3st arg is c
10st arg is j
11st arg is k
The number of arg is 26
All args are a b c d e f g h i j k l m n o p q r s t u v w x y z
All args are a b c d e f g h i j k l m n o p q r s t u v w x y z
The scriptname is arg.sh
~~~
其实位置变量就是执行脚本的时候可以带参数（就像执行命令后面可以带参数一样），参数会自动赋值给脚本内部的
$1 和 $2 和 $3 等变量，第一个参数赋值给$1,第二个参数给$2，以此类推。


####  退出状态码变量
当我们浏览网页时，有时会看到404，500的数字，表示网页的错误信息，我们称为状态码，在shell脚
本中也有相似的技术表示程序执行的相应状态。
进程执行后，将使用变量 $? 保存状态码的相关数字，不同的值反应成功或失败，$?取值范例 0-255

案例：
[root@centos8 ~]#curl -fs http://www.wangxiaochun.com >/dev/null
[root@centos8 ~]#echo $?
0


用户可以在脚本中使用以下命令自定义退出状态码：exit [n]

注意： 
脚本中一旦遇到exit命令，脚本会立即终止；终止退出状态取决于exit命令后面的数字

如果exit后面无数字,终止退出状态取决于exit命令前面命令执行结果

如果没有exit命令, 即未给脚本指定退出状态码，整个脚本的退出状态码取决于脚本中执行的最后一条命令的状态码



