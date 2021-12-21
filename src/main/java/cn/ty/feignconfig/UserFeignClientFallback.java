package cn.ty.feignconfig;

import cn.ty.pojo.User;
import org.springframework.stereotype.Component;

@Component  //添加元件注解
public class UserFeignClientFallback implements UserFeignClient{
    @Override
    public User queryById(int id) {
        User user = new User();
        user.setId(id);
        user.setUname("用户信息查询出现异常");

        return user;
    }
}
