
一次maven打jar包的经历，一直打出来的jar包只有几十kb，最初找原因是往怎么打jar包的方向去找， 比如打包的插件，springboot怎么打jar包，
springcloud父子工程怎么打jar包，cloud怎么打fat jar包等，都不行。打出来的还是几十kb。

后来又不断的尝试修改 spring-boot-maven-plugin 插件的配置参数，也不行。
比如给该插件加：<goal>repackage</goal> 配置。
但是加了之后还是没能打出完整的fat jar包，还是几十kb。

就算完成模仿视频教程中的pom编写方式还是不行。

后来受chatGPT的提醒，看报错日志。配置完上面的插件的配置，再执行报错后，认真看报错的全部信息的时候，有这样的报错信息：
The POM for org.apache.commons:commons-compress:jar:1.21 is invalid, transit xxxxx

想着是不是这个commons-compress 的jar包缺失或版本有问题，或本地仓库下载的包是有问题的，就重新删除所有这样报错的jar包，再下载，
之后打包就成功打出了fat jar包，使用java -jar命令就可以执行了。

因此，最初的打包失败并不是插件配置的问题，而是仓库内的jar包有损坏的问题，导致打包失败。
只是最初没有认真看报错日志，以为报错是因为插件的配置不对，就各种改插件配置。

不过也接这个机会复习了maven知识点的细节。