#! /bin/sh

# check user
if [ "$(whoami)" != 'blossom' ]; then
        echo "You have no permission to run $0 as non-blossom user."
        exit 1;
fi

# define variables
myAppHome=/data/ap/ap-dubbo-server
APP_NAME=ap-dubbo-server

DATE=`date +'%Y-%m-%d %H:%M:%S'`

# set LOCALCLASSPATH
LOCALCLASSPATH=${myAppHome}/lib/*:${myAppHome}/config

# kill before AP
PID=`ps -ef | grep -w ${myAppHome} | grep -v grep | grep -v startAp.sh | awk '{print $2}'`
if [ x$PID != x ]; then
        # old ap exist
        kill -9 $PID
        echo "[$DATE] [$APP_NAME] is killed!"

        # sleep
        sleep 5
else
        echo "[$DATE] [$APP_NAME] is stopped"
fi

# rm registry cache file
REGISTRY_CACHE_FILE=/tmp/dubbo-registryA-ap-dubbo-server.cache
if [ -f "$REGISTRY_CACHE_FILE" ]; then
   rm -f $REGISTRY_CACHE_FILE*
   echo "$REGISTRY_CACHE_FILE* is deleted"
fi

# background run ap
at now<<!
java  -server \
      -XX:PermSize=256m  -XX:MaxPermSize=512m  -Xss512k  -Xmx4G  -Xms4G \
      -XX:MaxTenuringThreshold=24  -XX:MaxHeapFreeRatio=60  -XX:MinHeapFreeRatio=30 -XX:NewRatio=2  -XX:SurvivorRatio=6 \
      -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC  -XX:MaxGCPauseMillis=100  -XX:GCPauseIntervalMillis=500 \
      -XX:InitiatingHeapOccupancyPercent=60 -XX:ParallelGCThreads=24 -XX:ConcGCThreads=24 -XX:G1ReservePercent=10  \
      -XX:G1HeapRegionSize=16m \
      -XX:ErrorFile=/log/dubboServer/dubbo_jvm_log/  \
      -XX:HeapDumpPath=/log/dubboServer/dubbo_jvm_log/ \
      -XX:+HeapDumpOnOutOfMemoryError \
      -Dcom.sun.management.jmxremote.port=10092 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false \
      -DMyAppHome=${myAppHome} -Ddubbo.container=spring -Ddubbo.spring.config=classpath*:/app-config/*.xml  \
      -Ddubbo.shutdown.hook=true -cp "$LOCALCLASSPATH" com.alibaba.dubbo.container.Main
!