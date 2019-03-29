package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.Barber;
import cn.com.flaginfo.platform.littleProject.mongo.repo.BarBerRepo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class BarBerRepoSupport extends BaseMongoDbRepoSupport<Barber> implements BarBerRepo {


    @Override
    public Barber getByMobile(String mobile) {
        Query query=new Query(Criteria.where("mobile").is(mobile));
        return this.find(query);
    }
}
