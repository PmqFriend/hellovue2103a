package cn.ty.feignconfig;

import cn.ty.config.FeignConfig;
import cn.ty.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//配置feign客户端，通过接口拼接 url访问路径
//fallback = UserFeignClientFallback.class  配置熔断后的回调方法
@FeignClient(value = "user-server",fallback = UserFeignClientFallback.class,configuration = FeignConfig.class)
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    User queryById(@PathVariable("id") int id);

//    @GetMapping("/add")
//    void add(User user);
}

