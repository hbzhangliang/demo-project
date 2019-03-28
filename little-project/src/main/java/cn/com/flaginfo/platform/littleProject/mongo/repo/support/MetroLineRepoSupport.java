package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.MetroLine;
import cn.com.flaginfo.platform.littleProject.mongo.repo.MetroLineRepo;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import cn.com.flaginfo.platform.littleProject.utils.annotation.RedisInject;
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

    private Query  createQuery(PageParams<MetroLine> pageParams){
        return null;
    }

    @Override
    public void saveOrUpdate(MetroLine bean) {
        this.save(bean);
    }

    @Override
    @RedisInject(redisTimeKey = "METRO_LINE")
    public MetroLine getById(String id) {
        return this.find(id);
    }

    @Override
    public PageParams<MetroLine> list(PageParams<MetroLine> pageParams) {
//        Query query=new Query().with(new Sort(Sort.Direction.ASC,"id")).skip(10).limit(10);
        pageParams.setData(this.list(this.createQuery(pageParams)));
        return pageParams;
    }

    @Override
    @RedisInject(redisTimeKey = "METRO_LINE_ALL")
    public List<MetroLine> listAll() {
        return this.list();
    }

    @Override
    public Boolean del(List<String> ids) {
        Query query = new Query(Criteria.where(MetroLine.ID).in(ids));
        this.remove(query);
        return true;
    }
}
