package com.dang14.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解用于 Redisson 分布式锁。
 * <p>
 * 此注解用于在方法级别实现 Redisson 分布式锁。
 * </p>
 */
@Target(ElementType.METHOD) //声明注解在方法上使用
@Retention(RetentionPolicy.RUNTIME) //声明注解在项目运行时生效
public @interface RedissonLock {
    /**
     * 锁的名称。
     * <p>
     * 用于标识锁的唯一名称。
     * </p>
     *
     * @return 锁的名称
     */
    String value();

    /**
     * 入参下标位置。
     * <p>
     * 指定方法入参中用于生成锁名称的参数下标位置。
     * 默认为 -1 表示不使用入参。
     * </p>
     *
     * @return 入参下标位置
     */
    int index() default -1;

    /**
     * 锁的过期时间，默认 6 秒。
     * <p>
     * 设置锁的过期时间（秒）。
     * </p>
     *
     * @return 锁的过期时间（秒）
     */
    long leaseTime() default 6;
}
