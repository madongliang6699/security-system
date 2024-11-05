#!/bin/bash  
  
# 获取包含 "app.jar" 的进程 PID 列表  
pids=$(ps -ef | grep /app/service/cyb-secureBase/app.jar | grep -v grep | awk '{print $2}')  
  
# 逐个终止进程  
for pid in $pids  
do  
    kill $pid  
done  
  
echo "已终止所有包含 'app.jar' 的进程。"
