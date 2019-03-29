package cn.com.flaginfo.platform.littleProject.controller;

import cn.com.flaginfo.platform.littleProject.mongo.models.Barber;
import cn.com.flaginfo.platform.littleProject.mongo.models.Boss;
import cn.com.flaginfo.platform.littleProject.mongo.repo.BarBerRepo;
import cn.com.flaginfo.platform.littleProject.mongo.repo.BossRepo;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import cn.com.flaginfo.platform.littleProject.utils.annotation.RedisInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/boss")
@RestController
public class BossController {


    @Autowired
    private BossRepo bossRepo;

    @RequestMapping(value = "/saveOrUpdate")
    public Object saveOrUpdate(@RequestBody Boss bean){
        bossRepo.saveOrUpdate(bean);
        return true;
    }

    @RequestMapping(value = "/get")
    @RedisInject(redisTimeKey = "BOSS_INFO")
    public Object get(@RequestBody Map<String,String> map){
        String mobile=map.get("mobile");
        return bossRepo.getByMobile(mobile);
    }

    @RequestMapping(value = "/list")
    public Object list(@RequestBody PageParams<Boss> pageParams){
        return bossRepo.list(pageParams);
    }

    @RequestMapping(value = "/listAll")
    @RedisInject(redisTimeKey = "BOSS_ALL")
    public Object listAll(){
        return bossRepo.listAll();
    }

    @RequestMapping(value = "/del")
    public Object del(@RequestBody Map<String,Object> map){
        List<String> ids=(List) map.get("ids");
        return bossRepo.del(ids);
    }

}
