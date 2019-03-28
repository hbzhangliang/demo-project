package cn.com.flaginfo.platform.littleProject.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/")
@RestController
public class IndexController {

    @RequestMapping(value = "/health/info")
    public Object health(){
        JSONObject jsonObject=new JSONObject(2);
        jsonObject.put("name","little-project");
        jsonObject.put("status","up");
        jsonObject.put("remark","小程序服务");
        return jsonObject;
    }

}
