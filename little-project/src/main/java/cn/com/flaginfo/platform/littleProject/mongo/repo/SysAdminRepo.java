package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.models.SysAdmin;

public interface SysAdminRepo extends BaseMongoDbRepo<SysAdmin> {

    SysAdmin getByMobile(String mobile);
}
