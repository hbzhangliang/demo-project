package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.Dict;
import cn.com.flaginfo.platform.littleProject.mongo.repo.DictRepo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class DictRepoSupport extends BaseMongoDbRepoSupport<Dict> implements DictRepo {

    @Override
    public Dict getByCode(String code) {
        Query query=new Query(Criteria.where("code").is(code));
        return this.find(query);
    }
}
