package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.models.Dict;

public interface DictRepo extends BaseMongoDbRepo<Dict>{

    Dict getByCode(String code);


    Boolean cityDel(String name);

    Boolean cityAdd(String name);

}
