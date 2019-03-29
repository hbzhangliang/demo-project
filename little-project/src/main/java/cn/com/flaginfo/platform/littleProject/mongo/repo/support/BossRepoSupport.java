package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.Boss;
import cn.com.flaginfo.platform.littleProject.mongo.repo.BossRepo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class BossRepoSupport extends BaseMongoDbRepoSupport<Boss> implements BossRepo {

    @Override
    public Boss getByMobile(String mobile) {
        Query query=new Query(Criteria.where("mobile").is(mobile));
        return this.find(query);
    }
}
