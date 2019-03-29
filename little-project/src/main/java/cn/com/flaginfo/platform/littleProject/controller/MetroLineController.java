package cn.com.flaginfo.platform.littleProject.controller;

import cn.com.flaginfo.platform.littleProject.mongo.models.MetroLine;
import cn.com.flaginfo.platform.littleProject.mongo.repo.MetroLineRepo;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import cn.com.flaginfo.platform.littleProject.utils.annotation.RedisInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/metro-line")
@RestController
public class MetroLineController {

    @Autowired
    private MetroLineRepo metroLineRepo;

    @RequestMapping(value = "/saveOrUpdate")
    public Object saveOrUpdate(@RequestBody MetroLine bean){
        metroLineRepo.saveOrUpdate(bean);
        return true;
    }

    @RequestMapping(value = "/get")
    @RedisInject(redisTimeKey = "METRO_LINE")
    public Object get(@RequestBody Map<String,String> map){
        String id=map.get("id");
        return metroLineRepo.getById(id);
    }

    @RequestMapping(value = "/list")
    public Object list(@RequestBody PageParams<MetroLine> pageParams){
        return metroLineRepo.list(pageParams);
    }

    @RequestMapping(value = "/listAll")
    @RedisInject(redisTimeKey = "METRO_LINE_ALL")
    public Object listAll(){
        return metroLineRepo.listAll();
    }

    @RequestMapping(value = "/del")
    public Object del(@RequestBody Map<String,Object> map){
        List<String> ids=(List) map.get("ids");
        return metroLineRepo.del(ids);
    }

}
