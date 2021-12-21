package cn.ty.controller;

import cn.ty.pojo.User;
import cn.ty.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    ObjectMapper jsonMapper = new ObjectMapper();
    @Autowired
    private UserService userService;

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/user/user_add";
    }

    @RequestMapping("/add")
    public String add(User user){
        int i =0;


        userService.add(user);

        return "redirect:/user/findAll";
    }


    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(defaultValue = "1")int pageNum, String name){
        int pageSize = 2;
        String all = userService.findAll(pageNum, pageSize, name);


        return "/user/user_list";
    }



    @RequestMapping("/queryById")
    @ResponseBody
    public List<User> queryById(String ids){
        List<User> users = new ArrayList<>();

        GsonJsonParser gsonJsonParser = new GsonJsonParser();
        List<Object> objects = gsonJsonParser.parseList(ids);//解析 多个id的集合为List集合
//        for (int i = 0; i < objects.size(); i++) {
//            float f = Float.parseFloat(objects.get(i).toString());
//            int id = (int)f;
//            User user = userService.queryById(id);//通过eureka注册中心找到user-server服务，查询id对应的user对象，返回来
//            users.add(user);//收集之
//        }
        //通过lanmoda表达式
        objects.forEach(sid->{
            float f = Float.parseFloat(sid.toString());
            int id = (int) f;
            User user = userService.queryById(id);//通过eureka注册中心找到user-server服务，查询id对应的user对象，返回来
            users.add(user);//收集之
        });

        return users;
    }


}
