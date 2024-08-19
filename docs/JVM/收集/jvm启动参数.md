
## 测试参数

nohup java -XX:NewSize=56m -XX:MaxNewSize=56m -XX:InitialHeapSize=128m -XX:MaxHeapSize=128m -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log -Dfile.encoding=utf-8 -jar security-order-1.0.0-SNAPSHOT.jar > app.log 2>&1 &


上面"-XX:nitialHeapSize" 和"-XX:MaxHeapSize"就是初始堆大小和最大堆大小，

”-XX:NewSize"和”-XX:MaxNewSize"是初始新生代大小和最大新生代大小，

"-XX:PretenureSizeThreshold=10m”指定了大对象阈值是10MB。

相当于给堆内存分配128MB内存空间，其中新生代是56MB内存空间，
新生代中Eden区占44.8MB（上面的8代表新时代中Eden 区与一个 Survivor 区的大小比为 8:1。由于有两个 Survivor 区，
总体新生代比例为 Eden:Survivor = 8:1:1）， 每个Survivor区占5.6MB,
大对象必须超过10MB才会直接进入老年代，
年轻代使用ParNew垃圾回收器，老年代使用CMS垃圾回收器,

GC日志的打印选型，如下所示:
1. -XX:+PrintGCDetils:打印详细的gc日志.
2. -XX:+ PrintGCTimeStamps:这个参数可以打印出来每次GC发生的时间
3. -Xloggc:gc.log:这个参数可以设置将gc日志写入一个磁盘文件gc.log文件。


在jstat测试order服务查询所有订单（5000条数据）的时候，每次查询最后需要返回给前端的都有2m大小的数据，查询总耗时15秒左右，
因为查出5000条订单数据后，遍历5000条订单数据，给每个订单数据从数据库查询（5000次查数据库）出订单详情并填充到订单对象中。
在循环遍历查询的时候，会创建很多临时对象，比如查询条件对象，大量的订单详情转DTO之前的Entity对象，并且Eden区空间本来就小，空间占用率也一直很高，
因此每次查询都会触发多次yong GC， 因此这期间的多次yang GC，每次yongGC后新生代会清理一部分临时对象，但是新生代剩下的订单详情的DTO对象在一直增长，
因此多次yongGC后新生代还是越来越多，因此，每次yangGC都会把1-2m的一直需要长期驻留的对象放进了老年代。
因此每次请求会进入老年代2多m的对象。老年代一共也就60m左右的空间， 在多次发送改查询请求后，最后触发了fullGC。

奇怪的是，每次触发fullGC都连续触发两次，不知道是什么原因。
chatGPT说可能是第一次fullGC晋升了很多存活对象到老年代，导致老年代没法清理，不过老年代里本身有上几次请求存的垃圾对象可以清理啊。
还说可能是老年代内存碎片化严重，导致第一次fullGC清理之后，整理不出太多空间，就进行了第二次fullGC。二次fullGC之后，确实老年代清理出一半的空间。

总之，从上面的测试的压力来看，新生代的空间是比较小的，应该多分配一些堆空间，给新生代多一些空间，或许每次请求就不会有对象进老年代了。