package cn.com.flaginfo.platform.littleProject.controller;

import cn.com.flaginfo.platform.littleProject.mongo.models.Boss;
import cn.com.flaginfo.platform.littleProject.mongo.models.SysAdmin;
import cn.com.flaginfo.platform.littleProject.mongo.repo.BossRepo;
import cn.com.flaginfo.platform.littleProject.mongo.repo.SysAdminRepo;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import cn.com.flaginfo.platform.littleProject.utils.annotation.RedisInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/admin")
@RestController
public class SysAdminController {


    @Autowired
    private SysAdminRepo sysAdminRepo;

    @RequestMapping(value = "/saveOrUpdate")
    public Object saveOrUpdate(@RequestBody SysAdmin bean){
        sysAdminRepo.saveOrUpdate(bean);
        return true;
    }

    @RequestMapping(value = "/get")
    @RedisInject(redisTimeKey = "SYSADMIN_INFO")
    public Object get(@RequestBody Map<String,String> map){
        String mobile=map.get("mobile");
        return sysAdminRepo.getByMobile(mobile);
    }

    @RequestMapping(value = "/list")
    public Object list(@RequestBody PageParams<SysAdmin> pageParams){
        return sysAdminRepo.list(pageParams);
    }

    @RequestMapping(value = "/listAll")
    @RedisInject(redisTimeKey = "SYSADMIN_ALL")
    public Object listAll(){
        return sysAdminRepo.listAll();
    }

    @RequestMapping(value = "/del")
    public Object del(@RequestBody Map<String,Object> map){
        List<String> ids=(List) map.get("ids");
        return sysAdminRepo.del(ids);
    }

}
