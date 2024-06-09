
## jps(JVM Process Status Tool):虚拟机进程状态工具
可以列出正在运行的虚拟机进程，并显示虚拟机执行主类（Main Class，main()函数所在的类）的名称，
以及这些进程的本地虚拟机的唯一ID（LVMID,Local Vitual Machine Identifier）,
它是使用频率最高的JDK命令行工具，因为其他JDK工具大多需要输入它查询到的LVMID来确定要监控的是哪一个虚拟机进程。

对于本地虚拟机进程来说，LVMID与操作系统进程ID（PID，Process Identifier）是一致的。
如果同时启动了多个虚拟机进程，无法根据进程名称定位时，那就只能依靠jps命令显示主类的功能才能区分了。


## jps命令格式
jps [options] [hostid]

-q	只输出LVMID,省略主类的名称  
-m	输出虚拟机进程启动时传递给main()函数的参数  
-l	输出主类的全名，如果进程执行的是jar包，输出jar路径  
-v	输出虚拟机进程启动时JVM参数  

jcmd 命令和 jps -l 有同样的效果。

例如：jps -l
~~~shell
[root@VM-4-2-centos ~]# jps
15521 jar
5041 QuorumPeerMain
15906 Jps
26631 jar
26972 jar

[root@VM-4-2-centos ~]# jps -l
15521 security-order-1.0.0-SNAPSHOT.jar
5041 org.apache.zookeeper.server.quorum.QuorumPeerMain
26631 security-market-1.0.0-SNAPSHOT.jar
15945 sun.tools.jps.Jps
26972 security-inventory-1.0.0-SNAPSHOT.jar
[root@VM-4-2-centos ~]# 

[root@VM-4-2-centos ~]# jps -v
18112 Jps -Dapplication.home=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.372.b07-1.el7_9.x86_64 -Xms8m
15521 jar -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8091 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Xms64m -Xmx128m -Xmn64m -Dfile.encoding=utf-8
5041 QuorumPeerMain -Dzookeeper.log.dir=/app/software/zookeeper-3.6.2/bin/../logs -Dzookeeper.log.file=zookeeper-root-server-VM-4-2-centos.log -Dzookeeper.root.logger=INFO,CONSOLE -XX:+HeapDumpOnOutOfMemoryError -XX:OnOutOfMemoryError=kill -9 %p -Xmx1000m -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.local.only=false
26631 jar -Xms64m -Xmx128m -Xmn64m -Dfile.encoding=utf-8
26972 jar -Xms64m -Xmx128m -Xmn64m -Dfile.encoding=utf-8
[root@VM-4-2-centos ~]# 


~~~