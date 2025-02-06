package com.example.learningcaffeine.config;

import com.example.learningcaffeine.model.DataObject;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class LocalCacheConfig {
    /**
     * Time based
     * <a href="https://github.com/ben-manes/caffeine/wiki/Eviction#time-based">...</a>
     */
    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().maximumSize(10_000).expireAfterWrite(3, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    public Cache<String, DataObject> cache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }
}
