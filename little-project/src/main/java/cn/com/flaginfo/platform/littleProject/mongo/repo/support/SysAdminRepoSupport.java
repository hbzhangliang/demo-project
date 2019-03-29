package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.SysAdmin;
import cn.com.flaginfo.platform.littleProject.mongo.repo.SysAdminRepo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SysAdminRepoSupport extends BaseMongoDbRepoSupport<SysAdmin> implements SysAdminRepo {


    @Override
    public SysAdmin getByMobile(String mobile) {
        Query query=new Query(Criteria.where("mobile").is(mobile));
        return this.find(query);
    }
}
