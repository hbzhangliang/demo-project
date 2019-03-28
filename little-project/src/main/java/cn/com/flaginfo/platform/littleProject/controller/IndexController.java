package cn.com.flaginfo.platform.littleProject.controller;

import cn.com.flaginfo.platform.littleProject.mongo.repo.UserRepo;
import cn.com.flaginfo.platform.littleProject.utils.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/")
@RestController
public class IndexController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping(value = "/health/info")
    public Object health(){
        JSONObject jsonObject=new JSONObject(2);
        jsonObject.put("name","little-project");
        jsonObject.put("status","up");
        jsonObject.put("remark","小程序服务");
        return jsonObject;
    }

    @RequestMapping(value = "/redis")
    public Object redis(){
        String key="COMMON";
        redisUtils.setObj("aa","kkkkkk",key);
        return redisUtils.getObj("aa");
    }


    @RequestMapping(value = "/mongo")
    public Object mongo(){
        return userRepo.list();
    }


}
