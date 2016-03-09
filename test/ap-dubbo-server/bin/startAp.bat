@echo off

set LOCALCLASSPATH=../lib/*;../config
   
start "ap-dubbo-server" java -Xmx1g -XX:PermSize=64M -XX:MaxPermSize=512M -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8787 -DMyAppHome=.. -Ddubbo.container=spring -Ddubbo.spring.config=classpath*:/app-config/*.xml  -cp %LOCALCLASSPATH% com.alibaba.dubbo.container.Main
exit


