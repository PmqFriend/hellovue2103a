package cn.ty.dao;

import cn.ty.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component  //把UserDao作为元件组件注入到spring容器中
public class UserDao {
    String baseUrl = "http://user-server/user";

    @Autowired
    private RestTemplate restTemplate;//实现跨系统访问的httpclient请求的模板的一个类

    @Autowired
    private DiscoveryClient discoveryClient;// Eureka客户端，可以获取到服务实例信息

    private Logger logger = LoggerFactory.getLogger(UserDao.class);//得到打印日志的对象，专业级别

    @HystrixCommand(fallbackMethod = "queryByIdFallback")//配置熔断控制的注解  fallbackMethod指定熔断后的回调函数
    public User queryById(int id) {
//        User user = restTemplate.getForObject("http://192.168.0.68:8081/user/"+id, User.class);

        // 根据服务名称，获取服务实例
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-server");
//        // 因为只有一个UserServer,因此我们直接get(0)获取
//        ServiceInstance instance = instances.get(0);
//        // 获取ip和端口信息
//        String baseUrl = "http://"+instance.getHost() + ":" + instance.getPort()+"/user/";
//        User user = restTemplate.getForObject(baseUrl+id, User.class);

        //通过ribbon负载均衡的方式 地址直接写服务名称即可，不能再写ip
        Long start = System.currentTimeMillis();//得到当前系统时间，毫秒级
        User user = this.restTemplate.getForObject(baseUrl + "/" + id, User.class);
        Long end = System.currentTimeMillis();//得到当前系统时间，毫秒级  请求结束后的时间

        logger.info(id+"--访问时：{}",end-start);//打印访问时间，便于探讨和理解，上线时不需要
        return user;
    }

    public User queryByIdFallback(int id){//id访问失败被熔断的那个用户的 id
        User user = new User();
        user.setId(id);
        user.setUname("用户信息查询出现异常");
        return user;
    }



    public void add(User user) {

        Date createdate = user.getCreatedate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(createdate);
//        String str = restTemplate.getForObject(baseUrl + "/add?uname=" + user.getUname() + "&upass=" + user.getUpass(), String.class);
        restTemplate.delete(baseUrl + "/add?uname=" + user.getUname() + "&upass=" + user.getUpass()+"&createdate="+format, 100);
        int i = 0;
    }

    public String findAll(int pageNum, int pageSize, String name){
        String json = this.restTemplate.getForObject(baseUrl + "/findAll?pageNum=" + pageNum + "&pageSize=" + pageSize + "&name=" + name, String.class);

        return json;
    }


}
