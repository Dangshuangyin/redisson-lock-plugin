package com.dang14.config;

import com.dang14.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Date;


@ComponentScan("com.dang14")
@Configuration
@Slf4j
public class RedissionConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        String redisUrl = String.format("redis://%s:%s", redisProperties.getHost() + "", redisProperties.getPort() + "");
        if (StringUtils.isEmpty(redisProperties.getPassword())) {
            config.useSingleServer().setAddress(redisUrl);
        } else {
            config.useSingleServer().setAddress(redisUrl).setPassword(redisProperties.getPassword());
        }
        log.info("RedissonLock连接成功 -->: {}", DateUtils.formatDateTime(new Date()));
        return Redisson.create(config);
    }
}
