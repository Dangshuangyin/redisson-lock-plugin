package com.dang14.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class RedissonLockAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Pointcut(value = "@annotation(com.dang14.config.RedissonLock)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonLock annotation = method.getAnnotation(RedissonLock.class);
        String operation = annotation.value();
        //方法入参值
        Object[] args = joinPoint.getArgs();
        String param = "";
        if (annotation.index() != -1) {
            param = ":" + args[annotation.index() - 1].toString();
        }
        RLock lock = redissonClient.getLock("RedissonLock:" + operation + ":" + param);
        boolean lockFlag = false;
        try {
            lockFlag = lock.tryLock(annotation.leaseTime(), TimeUnit.SECONDS);
            if (!lockFlag) {
                log.warn("加锁失败: {}", operation);
                throw new RuntimeException("Failed to acquire lock for operation: " + operation);
            }
            log.info("加锁成功:{}", operation);
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("加锁报错:{}", e);
            throw new RuntimeException(operation + " Error!");
        } finally {
            if (lockFlag && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("解锁成功:{}", operation);
            }
        }
    }

}