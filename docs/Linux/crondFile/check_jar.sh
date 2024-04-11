#!/bin/sh

#定义java环境
JAVA_HOME=/app/software/cyb-java
export JAVA_HOME
PATH=$JAVA_HOME/bin:$PATH
export PATH

# 定义URL和端口
URL="http://192.168.0.173/riskWeb/secureBaseApp/tenant/tenantByUser"
PORT="9019"

# 检查HTTP请求的状态码
HTTP_STATUS=$(curl -o /dev/null -s -w %{http_code} "$URL")

# 检查端口是否在监听
LISTENING_STATUS=$(ss -an | grep ":$PORT" | awk '$1 == "tcp" && $2 == "LISTEN" {print $0}' | wc -l)

# 根据条件执行相应的脚本
if [ "$HTTP_STATUS" == "200" ] && [ "$LISTENING_STATUS" -gt 0 ]; then
    # 条件满足，执行启动脚本
   echo "服务正常运行"
else
    # 条件不满足，先执行停止脚本
  sh  /app/service/cyb-secureBase/stopcyb-secureBase.sh > /dev/null
    # 停止脚本执行后，尝试重新启动
  sh  /app/service/cyb-secureBase/startcyb-secureBase.sh > /dev/null
fi
