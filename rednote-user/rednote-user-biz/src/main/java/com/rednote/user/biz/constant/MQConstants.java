package com.rednote.user.biz.constant;

/**
 * MQ 主题常量类
 *
 * @author Jason Feng
 * @since 2026-11-21 19:23
 */
public interface MQConstants {

    /**
     * Topic 主题：延迟双删 Redis 用户缓存
     */
    String TOPIC_DELAY_DELETE_USER_REDIS_CACHE = "DelayDeleteUserRedisCacheTopic";
}
