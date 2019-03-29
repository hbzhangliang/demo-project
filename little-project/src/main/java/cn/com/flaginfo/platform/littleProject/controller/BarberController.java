package cn.com.flaginfo.platform.littleProject.controller;

import cn.com.flaginfo.platform.littleProject.mongo.models.Barber;
import cn.com.flaginfo.platform.littleProject.mongo.repo.BarBerRepo;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import cn.com.flaginfo.platform.littleProject.utils.annotation.RedisInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/barber")
@RestController
public class BarberController {


    @Autowired
    private BarBerRepo barBerRepo;

    @RequestMapping(value = "/saveOrUpdate")
    public Object saveOrUpdate(@RequestBody Barber bean){
        barBerRepo.saveOrUpdate(bean);
        return true;
    }

    @RequestMapping(value = "/get")
    @RedisInject(redisTimeKey = "BARBER_INFO")
    public Object get(@RequestBody Map<String,String> map){
        String mobile=map.get("mobile");
        return barBerRepo.getByMobile(mobile);
    }

    @RequestMapping(value = "/list")
    public Object list(@RequestBody PageParams<Barber> pageParams){
        return barBerRepo.list(pageParams);
    }

    @RequestMapping(value = "/listAll")
    @RedisInject(redisTimeKey = "BARBER_ALL")
    public Object listAll(){
        return barBerRepo.listAll();
    }

    @RequestMapping(value = "/del")
    public Object del(@RequestBody Map<String,Object> map){
        List<String> ids=(List) map.get("ids");
        return barBerRepo.del(ids);
    }

}
