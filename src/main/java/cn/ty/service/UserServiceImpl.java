package cn.ty.service;

import cn.ty.dao.UserDao;
import cn.ty.feignconfig.UserFeignClient;
import cn.ty.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired//自动装载feign url接口拼接技术
    private UserFeignClient userFeignClient;

    @Override
    public User queryById(int id) {
        User user = userFeignClient.queryById(id);
        return  user;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public String findAll(int pageNum, int pageSize, String name) {
        return userDao.findAll(pageNum, pageSize, name);
    }
}
