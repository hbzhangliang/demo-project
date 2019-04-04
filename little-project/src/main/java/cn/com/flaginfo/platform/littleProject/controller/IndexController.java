package cn.com.flaginfo.platform.littleProject.controller;

import cn.com.flaginfo.platform.littleProject.mongo.models.Dict;
import cn.com.flaginfo.platform.littleProject.mongo.models.MetroLine;
import cn.com.flaginfo.platform.littleProject.mongo.repo.DictRepo;
import cn.com.flaginfo.platform.littleProject.mongo.repo.MetroLineRepo;
import cn.com.flaginfo.platform.littleProject.utils.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/")
@RestController
public class IndexController {

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
    public Object test(){
        Dict dict=dictRepo.getByCode("test");
        if(dict==null){
            dict=new Dict();
            dict.setCode("test");
            dict.setName("ddddddddd");
            dict.setRemark("这里是测试信息");
            dictRepo.save(dict);
        }
        return dictRepo.listAll();
    }

    @RequestMapping(value = "/metro")
    public Object metro(){
        List<MetroLine> list=new ArrayList<>();
        for(int i=0;i<36;i++){
            MetroLine tmp=new MetroLine();
            if(i<12) {
                tmp.setCity("上海");
            }
            else if(i<22){
                tmp.setCity("北京");
            }
            else {
                tmp.setCity("武汉");
            }
            tmp.setName("地铁"+i+"号线");
            list.add(tmp);
        }
        metroLineRepo.save(list);
        return metroLineRepo.list();
    }



    @Autowired
    private MetroLineRepo metroLineRepo;

    @Autowired
    private DictRepo dictRepo;


}
