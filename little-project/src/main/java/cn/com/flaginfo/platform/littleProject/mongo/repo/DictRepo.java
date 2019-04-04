package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.models.Dict;

public interface DictRepo extends BaseMongoDbRepo<Dict>{

    Dict getByCode(String code);

    Boolean singleDel(String code,String childCode,String childName);

    Boolean singleSave(String code,String childCode,String childName,String childRemark);

}
