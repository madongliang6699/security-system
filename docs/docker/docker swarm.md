
## Docker swarm
Docker swarm 是docker自带的,不需要安装,可以直接使用.


## 搭建docker swarm集群要求
Docker Swarm 集群在生产环境中的最佳实践建议是使用至少 3 台机器 来确保高可用性（HA）。
然而，从技术上讲，Swarm 集群可以在两台机器上运行，尽管这种配置会有一些限制和风险。

为什么建议至少 3 台机器？
高可用性 (High Availability)：
Docker Swarm 使用 Raft 协议来管理集群的状态和数据一致性。Raft 协议要求至少过半数节点可用，才能达成共识（即更新集群状态）。
当 Swarm 集群只有 2 个节点时，如果其中一个管理节点发生故障，剩下的节点将无法继续管理集群，因为无法达到多数派投票（quorum）。
这会导致集群无法正常工作。

故障恢复能力：
在有 3 个或更多管理节点的 Swarm 集群中，即使一个节点出现故障，剩下的节点仍然能够维持多数投票，从而保持集群的正常运行。
在生产环境中，通常会配置奇数个管理节点（例如 3、5、7 个），以避免因网络分区或单节点故障导致整个集群失效。

为什么在两台机器上也能搭建 Swarm？
虽然最佳实践是 3 台或更多机器，但 Docker Swarm 的设计允许你在只有 2 台机器的情况下初始化和运行集群。这种配置在小规模开发环境或测试环境中是可行的，并且能够提供基本的 Swarm 功能。
但是，使用 2 台机器意味着你必须承受没有高可用性保障的风险。如果其中一台机器（尤其是管理节点）不可用，整个集群将失效，无法继续进行服务调度和管理。


## 搭建docker swarm集群
首先每台机器必须先安装docker

选择一台机器作为 Swarm 管理节点（manager），在该机器上执行以下命令：

sudo docker swarm init --advertise-addr 124.222.246.77

ip地址就是本机的ip,供work节点连接使用.

执行该命令后，你将看到类似以下的输出：

~~~shell
Swarm initialized: current node (xxxxxx) is now a manager.

To add a worker to this swarm, run the following command:

    docker swarm join --token SWMTKN-1-xxxxxxxxx 124.222.246.77:2377

To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.

~~~
这里的 docker swarm join --token SWMTKN-1-xxxxxxxxx 124.222.246.77:2377 是将其他节点（worker）加入 Swarm 所需的命令。


在第二台机器上，执行在上一步中生成的 docker swarm join 命令。例如：
docker swarm join --token SWMTKN-1-2mifyeylwlpqhkm61f49wngjsk1sxrpa7kag6g7eiu43915csq-8zvjf1y1on8vv08ev7yiwe37r 124.222.246.77:2377

如果找不到了上面的这个命令,也可以使用:docker swarm join-token worker 命令再次查看.

执行完该命令后，你应该会看到类似以下的确认消息：This node joined a swarm as a worker.
(注意:如果执行上面的命令因为网络或防火墙的问题,没能把当前节点成功添加到swarm的主节点下的话, 调整完网络问题后,再次执行添加work节点的命令会发现报错:
"Error response from daemon: This node is already part of a swarm. Use "docker swarm leave" to leave this swarm and join another one."
意思是当前节点已经是一个work节点,只不过这个节点没有成功添加到主节点下,需要先执行""docker swarm leave"从集群移除当前节点,才能重新添加.)

验证 Swarm 状态:
回到管理节点，执行以下命令查看 Swarm 的状态：
sudo docker node ls

你应该能看到两台机器的列表，其中一台为管理节点，另一台为工作节点。

## 部署服务:
现在你已经成功搭建了一个包含两台机器的 Swarm 集群，可以通过 docker service create 命令在 Swarm 集群中部署服务。
例如，部署一个简单的 Nginx 服务：
sudo docker service create --name my-nginx --replicas 2 -p 80:80 nginx

这将会在两台机器上各部署一个 Nginx 容器。


## swarm中的几个概念
swarm:
就是集群,可以理解成一个docker集群的整体,管理整个swarm集群的引擎. 其包含了使用swarm的命令.

node:
就是加入swarm集群的一台机器,服务器.  分为管理者节点(manager)和工作节点(worker).
管理者节点是用来管理和维护集群的,swarm的命令只能在管理者节点使用.
(节点退出集群的命令docker swarm leave是在worker节点使用的,表示当前节点退出该集群),
两种节点都可以有多台机器,管理节点可以有多个,但是leader只有一个.

service(服务):
服务(Services)是指一组任务的集合，服务定义了任务的属性。
服务有两种模式:
replicated services 按照一定规则在各个工作节点上运行指定个数的任务。

global services 每个工作节点上运行一个任务.

两种模式通过 docker service create 的--mode 参数指定.

服务就可以理解为我们的一个java服务,比如订单服务,或者一个Redis服务.
但是这个服务是可以部署多个实例的,每个实例用一个容器去运行,每个实例就是一个task任务.
所有service服务就像是一个项目模板, task任务是这个项目部署出的多个服务实例.
即:一个服务对应一个或多个任务.

task(任务):
任务就是要去做的一个事, swarm中最小的调度单位, 任务当然需要一个容器去执行的, 所有一个任务一般就是一个容器, 理解成一个容器就行了. 



## 常用命令 
### 部署服务
Docker Swarm 的一个核心功能是服务部署。你可以在 Swarm 集群上部署分布式服务。
1.创建服务:
使用以下命令创建一个服务，例如部署一个 Nginx 服务：
docker service create --name my-nginx -p 80:80 --replicas 3 nginx
`--name my-nginx：指定服务的名称。
-p 80:80：将 Swarm 节点的端口 80 映射到容器的端口 80。
--replicas 3：指定在集群中运行 3 个 Nginx 实例。
nginx：指定使用的镜像。`


2.查看服务状态
要查看服务的状态，可以运行：
docker service ls
这将显示集群中所有服务的状态。

要查看某个服务的任务的信息：
docker service ps my-nginx

3. 更新服务
可以使用以下命令更新服务，例如增加或减少副本数：
docker service scale my-nginx=5
这会将 my-nginx 服务的副本数增加到 5。

4. 删除服务
要删除一个服务，可以运行：
docker service rm my-nginx


### 管理 Swarm
 1. 离开 Swarm ,如果你想让某个节点离开 Swarm，可以在该节点上运行：
docker swarm leave
如果这是最后一个管理节点，使用 --force 选项强制离开：
docker swarm leave --force

2. 删除节点
要从 Swarm 中删除某个节点（不在该节点上运行命令），可以在管理节点上执行：
docker node rm <NODE-ID>


3. 节点可用性管理
可以管理节点的可用性状态，例如将某个节点设置为不可用：
docker node update --availability drain <NODE-ID>  
这将停止在该节点上运行的新任务，并将现有任务移到其他节点。



### 集群监控
你可以通过 Docker 提供的命令监控集群的状态，如：

docker node ls：列出所有节点及其状态。
docker service ls：列出所有服务及其状态。
docker service ps 服务名称 ：查看服务任务的状态。



###  备份与恢复
1. 备份
要备份 Swarm 的数据，可以备份 /var/lib/docker/swarm/ 目录。你可以使用常见的文件系统备份工具，如 tar：
sudo tar -czvf swarm-backup.tar.gz /var/lib/docker/swarm/

2. 恢复
要恢复 Swarm 配置，首先确保 Docker 停止运行，然后解压备份文件并将其复制到原位置：
sudo systemctl stop docker  
sudo tar -xzvf swarm-backup.tar.gz -C /  
sudo systemctl start docker  


## 总结
Docker Swarm 是一个功能强大的容器编排工具，提供了简单易用的命令来管理容器化应用。
通过 Swarm，用户可以轻松地部署、管理和扩展分布式应用程序，并且 Docker 的原生支持使得它特别适合现有 Docker 用户。
