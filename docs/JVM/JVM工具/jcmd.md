
## jcmd pid VM.uptime
查看java程序运行了多长时间

## 直接 jcmd 命令
直接jcmd命令回车,打印出有哪些jvm进程, 和jps -l 差不多.

## jcmd [pid] help
查询jcmd 对该jvm进程可用的命令 (其中包括上面的 VM.uptime)

查询出之后,可以通过:jcmd [pid] 具体命令  来对当前jvm进程执行命令.

~~~shell
[root@demo ~]# jcmd 12645 help
12645:
The following commands are available:
JFR.stop
JFR.start
JFR.dump
JFR.check
VM.native_memory
VM.check_commercial_features
VM.unlock_commercial_features
ManagementAgent.stop
ManagementAgent.start_local
ManagementAgent.start
VM.classloader_stats
GC.rotate_log
Thread.print
GC.class_stats
GC.class_histogram
GC.heap_dump
GC.finalizer_info
GC.heap_info
GC.run_finalization
GC.run
VM.uptime
VM.dynlibs
VM.flags
VM.system_properties
VM.command_line
VM.version
help
~~~
上面命令的个别解释:
jcmd <pid> GC.heap_dump 生成堆转储, eg: jcmd 12345 GC.heap_dump /path/to/heapdump.hprof (和jmap -dump:live命令一样的功能吧) 
jcmd <pid> GC.run 手动执行垃圾回收 eg: jcmd 12345 GC.run
jcmd <pid> VM.uptime 查看进程运行时间
jcmd <pid> VM.command_line 打印 JVM 命令行参数
jcmd <pid> Thread.print 打印线程栈跟踪
jcmd <pid> GC.class_histogram 获取类直方图 (和 jmap -histo 一样的的功能)