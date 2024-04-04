package com.axing.demo.config;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.*;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class RedissonConfig {

    @SneakyThrows
    @Bean
    public RedissonClient redissonClient() {


//
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setDatabase(6);

        JsonJacksonCodec codec = new JsonJacksonCodec();
//        Codec codec = new Kryo5Codec();
//        Codec codec = new SnappyCodecV2();

        config.setCodec(codec);

//        RedissonClient redisson = Redisson.create();

        RedissonClient redisson = Redisson.create(config);
//
//        Config config = Config.fromYAML(new File("classpath:redisson.yml"));
////        Config config = Config.fromYAML("classpath:redisson.yml");
//        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }
}
