package org.bk.cups.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.util.Map;

/**
 * Redis Configuration
 *
 * @author Biju Kunjummen
 */

@Configuration
public class RedisConfig {

    
    @Bean
    public RedisTemplate<String, Map<String, Object>> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Map<String, Object>> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Map.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
