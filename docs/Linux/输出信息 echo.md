

##  基本用法
echo 命令可以将后面跟的字符进行输出
功能：显示字符，echo会将输入的字符串送往标准输出。输出的字符串间以空白字符隔开, 并在最后加上换行号.

语法：echo [-neE][字符串]

选项：  
-E （默认）不支持 \ 解释功能  
-n 不自动换行  
-e 启用 \ 字符的解释功能  

显示变量:  
echo "$VAR_NAME”  #用变量值替换，弱引用  
echo '$VAR_NAME’  #变量不会替换，强引用

启用命令选项-e，若字符串中出现以下字符，则特别加以处理，而不会将它当成一般文字输出:  
\a 发出警告声  
\b 退格键  
\c 最后不加上换行符号  
\e escape，相当于\033  
\n 换行且光标移至行首  
\r 回车，即光标移至行首，但不换行  
\t 插入tab  
\\ 插入\字符  
\0nnn 插入nnn（八进制）所代表的ASCII字符  
\xHH插入HH（十六进制）所代表的ASCII数字（man 7 ascii）  

范例：  
~~~shell
root@centos8 ~]#echo -e 'a\x0Ab'
a
b
[root@centos8 ~]#echo -e '\033[43;31;1;5mmagedu\e[0m'
magedu
[root@centos8 ~]#echo -e '\x57\x41\x4E\x47'
WANG
[root@centos8 ~]#echo \$PATH
$PATH
[root@centos8 ~]#echo \
[root@centos8 ~]#echo \\
\
[root@centos8 ~]#echo \\\
> 
[root@centos8 ~]#echo \\\\
\\
[root@centos8 ~]#echo "$PATH"
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin
[root@centos8 ~]#echo '$PATH'
$PATH
~~~


## echo 高级用法
在终端中，ANSI定义了用于屏幕显示的Escape屏幕控制码
具有颜色的字符，其格式如下:
\033[30m -- \033[37m 设置前景色
\033[40m -- \033[47m 设置背景色