package shop.snowballclass.view.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {
        // application.yaml의 설정값으로 Redisson 구성 로드
        // spring.redis.redisson.config: 에 대응
        Config config = Config.fromYAML(getClass().getClassLoader().getResource("redisson-config.yaml"));
        return Redisson.create(config);
    }
}
