package cn.ty.service;

import cn.ty.pojo.User;

public interface UserService {

    public User queryById(int id);

    public void add(User user);

    public String findAll(int pageNum, int pageSize, String name);

}
