# redisson-lock-plugin

#### 介绍
自己写的一个Redisson加锁注解插件，让用户直接用注解的方式使用Redisson的加锁功能，轮子已经造好了，大家尽情使用吧！

#### 软件架构
软件架构说明


#### 安装教程

1. 下载jar包
2. 打包进本地仓库
    mvn install:install-file -Dfile=redisson-lock-plugin-1.0-SNAPSHOT.jar -DgroupId=com.dang14 -DartifactId=redisson-lock-plugin -Dversion=1.0-SNAPSHOT -Dpackaging=jar

#### 使用说明
1.  引入依赖

            <dependency>
                <groupId>com.dang14</groupId>
                <artifactId>redisson-lock-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>


3.  开启注解
    springboot启动类增加注解 @EnableRedisson

4.  方法使用
   @RedissonLock(value = "方法名",leaseTime = 60L)

