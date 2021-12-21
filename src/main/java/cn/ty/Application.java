package cn.ty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  // 开启Eureka客户端
@EnableHystrix //开启熔断器
@EnableFeignClients //开启feign功能
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean  //把返回的RestTemplate对象注入到spring的bean容器中
    @LoadBalanced  //配置ribbon负载均衡
    public RestTemplate restTemplate(){
//        return new RestTemplate();

        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
