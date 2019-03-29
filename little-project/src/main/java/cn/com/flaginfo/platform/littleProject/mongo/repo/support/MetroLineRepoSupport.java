package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.MetroLine;
import cn.com.flaginfo.platform.littleProject.mongo.repo.MetroLineRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MetroLineRepoSupport extends BaseMongoDbRepoSupport<MetroLine> implements MetroLineRepo {
    private final static Logger log = LoggerFactory.getLogger(MetroLineRepoSupport.class);

    @Override
    public List<MetroLine> listByCity(String city) {
        Query query=new Query(Criteria.where("city").is(city)).with(new Sort(Sort.Direction.ASC,"seq"));
        return this.list(query);
    }
}
