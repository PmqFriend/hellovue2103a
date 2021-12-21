package cn.ty.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLeve() {
        return Logger.Level.FULL;//返回feigh的日志级别  FULL表示请求和相应等所有信息
    }
}
                                               