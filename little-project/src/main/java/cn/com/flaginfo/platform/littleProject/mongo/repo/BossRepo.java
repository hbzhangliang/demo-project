package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.models.Boss;

public interface BossRepo extends BaseMongoDbRepo<Boss>{

    Boss getByMobile(String mobile);
}
