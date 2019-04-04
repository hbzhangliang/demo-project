package cn.com.flaginfo.platform.littleProject.controller;

import cn.com.flaginfo.platform.littleProject.mongo.models.Dict;
import cn.com.flaginfo.platform.littleProject.mongo.repo.DictRepo;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import cn.com.flaginfo.platform.littleProject.utils.annotation.RedisInject;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/dict")
@RestController
public class DictController {

    @Autowired
    private DictRepo dictRepo;

    @RequestMapping(value = "/saveOrUpdate")
    public Object saveOrUpdate(@RequestBody Dict bean){
        dictRepo.saveOrUpdate(bean);
        return true;
    }

    @RequestMapping(value = "/get")
    @RedisInject(redisTimeKey = "DICT_INFO")
    public Object get(@RequestBody Map<String,String> map){
        String code=map.get("code");
        return dictRepo.getByCode(code);
    }

    @RequestMapping(value = "/list")
    public Object list(@RequestBody PageParams<Dict> pageParams){
        return dictRepo.list(pageParams);
    }

    @RequestMapping(value = "/listAll")
    @RedisInject(redisTimeKey = "DICT_ALL")
    public Object listAll(){
        return dictRepo.listAll();
    }

    @RequestMapping(value = "/del")
    public Object del(@RequestBody Map<String,Object> map){
        List<String> ids=(List) map.get("ids");
        return dictRepo.del(ids);
    }

    /**
     * 添加参数形式
     * code
     * name
     * item   code/nam
     * @param map
     * @return
     */
    @RequestMapping(value = "/singleDel")
    public Object cityDel(@RequestBody Map<String,Object> map){
        String code=map.get("code").toString();
        Map<String,String> itemMap=(Map<String,String>)map.get("item");
        String childCode=itemMap.get("code");
        String childName=itemMap.get("name");
        return dictRepo.singleDel(code,childCode,childName);
    }

    @RequestMapping(value = "/singleAdd")
    public Object cityAdd(@RequestBody Map<String,Object> map){
        String code=map.get("code").toString();
        Map<String,String> itemMap=(Map<String,String>)map.get("item");
        String childCode=itemMap.get("code");
        String childName=itemMap.get("name");
        String childRemark=itemMap.get("remark");
        return dictRepo.singleSave(code,childCode,childName,childRemark);
    }



}
