@echo off

set LOCALCLASSPATH=../lib/*;../config2
   
start "ap-dubbo-server2" java -Xmx1g -XX:PermSize=64M -XX:MaxPermSize=512M -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8789 -DMyAppHome=.. -Ddubbo.container=spring -Ddubbo.spring.config=classpath*:/app-config/*.xml  -cp %LOCALCLASSPATH% com.alibaba.dubbo.container.Main
exit


