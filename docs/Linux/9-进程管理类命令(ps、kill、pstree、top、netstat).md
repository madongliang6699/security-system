
## ps：查看当前系统进程状态
ps aux	查看系统中所有进程  
ps -ef	可以查看父子进程之间的关系

a	列出带有终端的所有用户的进程  
x	列出当前用户的所有进程，包括没有终端的进程  
u	面相用户友好的显示风格  
-e	列出所有进程  
-u	列出某个用户关联的所有进程  
-f	显示完整格式的进程列表  


ps aux：查看进程CPU、内存占用率:

USER： 该进程是由哪个用户产生的  
PID： 进程的 ID 号  
%CPU： 该进程占用 CPU 资源的百分比， 占用越高， 进程越耗费资源  
%MEM： 该进程占用物理内存的百分比， 占用越高， 进程越耗费资源    
VSZ： 该进程占用虚拟内存的大小， 单位 KB  
RSS： 该进程占用实际物理内存的大小， 单位 KB  
TTY： 该进程是在哪个终端中运行的。 对于 CentOS 来说， tty1 是图形化终端，tty2-tty6 是本地的字符界面终端。 pts/0-255 代表虚拟终端。  
STAT： 进程状态，常见的状态有：R： 运行状态  S：睡眠状态  T： 暂停状态 Z： 僵尸状态   s： 包含子进程  l： 多线程  +： 前台显示  
START： 该进程的启动时间  
TIME： 该进程占用 CPU 的运算时间， 注意不是系统时间  
COMMAND： 产生此进程的命令名  




ps -ef：显示父子进程信息:

UID： 用户 ID  
PID： 进程 ID  
PPID： 父进程 ID  
C： CPU 用于计算执行优先级的因子。 数值越大， 表明进程是 CPU 密集型运算，执行优先级会降低； 数值越小， 表明进程是 I/O 密集型运算， 执行优先级会提高  
STIME： 进程启动的时间  
TTY： 完整的终端名称  
TIME： CPU 时间  
CMD： 启动进程所用的命令和参数  

如果想查看进程的 CPU 占用率和内存占用率， 可以使用 ps aux  
如果想查看进程的父进程 ID 可以使用 ps ef


ps -Lf pid：查看某个进程的所有内核线程:




## kill：终止进程
kill [选项] 进程号	通过进程号杀死进程  
killall 进程名称	通过进程名称杀死进程，也支持通配符，这在系统因负载过大而变得很慢时很有用

-9	表示强迫进程立即执行

关闭火狐浏览器:
ps -ef | grep firefox   查询到进程号  
kill -9 1855 强制杀死进程


通过进程名称杀死进程  killall firefox


## pstree：查看树进程
pstree命令以树状图的方式展现进程之间的派生关系，显示效果比较直观。

有的系统默认没有pstree，可以安装：yum -y install psmisc  

pstree [选项]

-p	显示进程的PID  
-u	显示进程的所属用户  
-s [PID]  仅显示特定进程的父级和子级信息

pstree -sap 1855


## top：实时监控系统状态
top [选项]

-d 秒数	指定top命令每隔几秒刷新一下结果，默认是3秒在top命令的交互模式当中可以执行命令  
-i	使top不显示任何闲置或者僵死进程  
-p	通过指定监控进程ID来监控某个进程的状态  
-c	显示整个命令行而不只是显示命令名  


在top命令结果中可以执行下面操作参与交互：  

| 操作 | 功能               |
|----|------------------|
| P  | 以CPU使用率排序，默认就是此项 |
| M  | 以内存的使用率排序        |
| N  | 以PID排序           |
| q  | 退出top            |


~~~shell
top - 15:53:32 up 20 days,  3:39,  2 users,  load average: 0.00, 0.01, 0.05
Tasks: 199 total,   1 running, 198 sleeping,   0 stopped,   0 zombie
%Cpu(s):  8.5 us,  0.5 sy,  0.0 ni, 90.8 id,  0.2 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem :  1883928 total,   148312 free,  1237016 used,   498600 buff/cache
KiB Swap:  2097148 total,  2041952 free,    55196 used.   453992 avail Mem 

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND                                                                                                                                                     
14482 jenkins   20   0 3360628 875912  10572 S  16.6 46.5  37:00.30 java                                                                                                                                                        
14990 root      20   0  475740  19940   2888 S   1.0  1.1 177:50.20 docker-containe                                                                                                                                             
 1496 root      20   0  162132   2384   1588 R   0.3  0.1   0:00.07 top                                                                                                                                                         
    1 root      20   0  193896   4912   2880 S   0.0  0.3   4:33.08 systemd                                                                                                                                                     
    2 root      20   0       0      0      0 S   0.0  0.0   0:00.18 kthreadd                                                                                                                                                    
    3 root      20   0       0      0      0 S   0.0  0.0   0:00.96 ksoftirqd/0                                                                                                                                                 
    7 root      rt   0       0      0      0 S   0.0  0.0   0:00.07 migration/0
~~~

第1行信息为任务队列信息：  
15:53:32	系统当前时间  
up 20 days,  3:39	系统的运行时间，本金以运行20天3小时39分  
2 users	当前登录了2个用户  
load average: 0.00, 0.01, 0.05	系统在之前 1 分钟， 5 分钟， 15 分钟的平均负载。 一般认为小于 1 时， 负载较小。 如果大于 1， 系统已经超出负荷


第2行为进程信息：   
Tasks: 199 total	系统中的进程总数    
1 running	正在运行的进程数  
198 sleeping	睡眠的进程数  
0 stopped	正在停止的进程数  
0 zombie	僵尸进程。 如果不是 0， 需要手工检查僵尸进程  

第3行为CPU信息： 
us	用户空间占用的 CPU 百分比  
sy	内核空间占用的 CPU 百分比  
ni	改变过优先级的用户进程占用的 CPU 百分比  
id	空闲 CPU 百分比  
wa	等待输入/输出的进程的占用 CPU 百分比  
hi	硬中断请求服务占用的 CPU 百分比  
si	软中断请求服务占用的 CPU 百分比  
st	st（ Steal time） 虚拟时间百分比。 就是当有虚拟 机时， 虚拟 CPU 等待实际 CPU 的时间百分比。  


第4行为物理内存信息：  
total	物理内存的总量， 单位 KB  
used	已经使用的物理内存数量  
free	空闲的物理内存重量  
buffers	作为缓冲的内存数量  


第5行为交换分区信息：   
Swap: 524280k total	交换分区（虚拟内存） 的总大小  
0k used	已经使用的交换分区的大小  
524280k free	空闲交换分区的大小  
409280k cached	作为缓存的交换分区的大小  

 


进程信息区：  
统计信息区域的下方显示了各个进程的详细信息，首先来认识一下各列的含义。

PID 进程id  
PPID 父进程id  
USER Real user name  
UID 进程所有者的用户id  
USER 进程所有者的用户名  
GROUP 进程所有者的组名  
TTY 启动进程的终端名。不是从终端启动的进程则显示为 ?  
PR 优先级  
NI nice值。负值表示高优先级，正值表示低优先级  
P 最后使用的CPU，仅在多CPU环境下有意义  
%CPU 上次更新到现在的CPU时间占用百分比  
TIME 进程使用的CPU时间总计，单位秒  
TIME+ 进程使用的CPU时间总计，单位1/100秒  
%MEM 进程使用的物理内存百分比  
VIRT 进程使用的虚拟内存总量，单位kb。VIRT=SWAP+RES  
SWAP 进程使用的虚拟内存中，被换出的大小，单位kb  
RES 进程使用的、未被换出的物理内存大小，单位kb，RES=CODE+DATA  
CODE 可执行代码占用的物理内存大小，单位kb  
DATA 可执行代码以外的部分(数据段+栈)占用的物理内存大小，单位kb  
SHR 共享内存大小，单位kb  
nFLT 页面错误次数  
nDRT 最后一次写入到现在，被修改过的页面数。  
S 进程状态。D=不可中断的睡眠状态R=运行 S=睡眠 T=跟踪/停止 Z=僵尸进程  
COMMAND 命令名/命令行  


#### top案例：

top -p 111  显示进程111的状态信息

top -b -d 2.5 -n 5 > performace.txt  2500 毫秒刷新一次 TOP 内容，总共 5 次，输出内容存放到 performace.txt 文件中;  注：要将内容输出到文件中，必须使用 - b，表示批处理选项

top -i  只显示活动中的进程



## netstat：显示网络状态和端口占用信息

netstat -anp | grep 进程号	查看该进程网络信息  
netstat –nlp | grep 端口号	查看网络端口号占用情况

-a	显示所有正在监听（listen） 和未监听的套接字（socket）  
-n	拒绝显示别名， 能显示数字的全部转化成数字  
-l	仅列出在监听的服务状态  
-p	表示显示哪个进程在调用  

连接状态详解:  
LISTEN： 侦听来自远方的TCP端口的连接请求，【大概意思就是这个端口一直在监听其他客户端的连接，可随时连接】  
SYN-SENT： 再发送连接请求后等待匹配的连接请求  
SYN-RECEIVED：再收到和发送一个连接请求后等待对方对连接请求的确认  
ESTABLISHED： 代表一个打开的连接,【大概意思就是这个端口正在被客户端连接】  
FIN-WAIT-1： 等待远程TCP连接中断请求，或先前的连接中断请求的确认  
FIN-WAIT-2： 从远程TCP等待连接中断请求  
CLOSE-WAIT： 等待从本地用户发来的连接中断请求  
CLOSING： 等待远程TCP对连接中断的确认  
LAST-ACK： 等待原来的发向远程TCP的连接中断请求的确认  
TIME-WAIT： 等待足够的时间以确保远程TCP接收到连接中断请求的确认  
CLOSED： 没有任何连接状态  


案例实操：  
（1） 通过进程号查看sshd进程的网络信息
[root@testx java]# netstat -anp | grep sshd

（2） 查看22端口号是否被占用
[root@testx java]# netstat -nltp | grep 22

该命令的实用用法可能很多：  
https://blog.csdn.net/dviewer/article/details/51340587   
https://blog.csdn.net/weixin_45729831/article/details/122152112