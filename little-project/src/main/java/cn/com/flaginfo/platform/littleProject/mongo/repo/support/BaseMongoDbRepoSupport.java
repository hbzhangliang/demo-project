package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.BaseMongoDbModel;
import cn.com.flaginfo.platform.littleProject.mongo.repo.BaseMongoDbRepo;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import com.mongodb.WriteResult;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by liang_zhang on 2017/9/19.
 */
public abstract class BaseMongoDbRepoSupport<T extends BaseMongoDbModel> implements BaseMongoDbRepo<T> {

    private int BatchLength=1000;
//    private static final Logger log =
//            LoggerFactory.getLogger(BaseMongoDbRepoSupport.class);
    @Autowired
    protected MongoTemplate mongoTpl;
    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public BaseMongoDbRepoSupport() {
        type = (Class<T>) GenericTypeResolver.resolveTypeArgument(
                getClass(), BaseMongoDbRepoSupport.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(Query query) {
        return mongoTpl.findOne(query, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(String id) {
        return mongoTpl.findById(new ObjectId(id), type);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> list(Query query) {
        return mongoTpl.find(query, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> list(Collection<?> keys) {
        return list(new Query(Criteria.where("id").in(keys)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> list() {
        return mongoTpl.findAll(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count(Query query) {
        return mongoTpl.count(query, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insert(T ... models) {
        for (T model : models) {
            if (model.getDateCreated() == null) {
                model.setDateCreated(new Date());
            }
            mongoTpl.insert(model);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Collection<T> models) {
        if (models != null && models.isEmpty()) {
            for (T model : models) {
                if (model.getDateCreated() == null) {
                    model.setDateCreated(new Date());
                }
                mongoTpl.insert(model);
            }
        }
        else {
//            log.warn("No Models Insert.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void save(T ... models) {
        for (T model : models) {
            if (StringUtils.isBlank(model.getId())) {
                if (model.getDateCreated() == null) {
                    model.setDateCreated(new Date());
                }
            }
            mongoTpl.save(model);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Collection<T> models) {
        if (models != null && !models.isEmpty()) {
            for (T model : models) {
                if (StringUtils.isBlank(model.getId())) {
                    if (model.getDateCreated() == null) {
                        model.setDateCreated(new Date());
                    }
                }
                mongoTpl.save(model);
            }
        }
        else {
//            log.warn("No Model To Save.");
        }
    }


    @Override
    public void saveBatch(List<T> list) {
        if (list != null && !list.isEmpty()) {
            if(list.size()>this.BatchLength){
                int i=1;
                while (i*this.BatchLength<list.size()){
                    List<T> tmp=list.subList(this.BatchLength*(i-1),this.BatchLength*i);
                    this.save(tmp);
                    i++;
                }
                List<T> tmp=list.subList(this.BatchLength*(i-1),list.size());
                this.save(tmp);
            }
            else {
                this.save(list);
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(Query query, Update update) {
        return mongoTpl.findAndModify(query, update, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Query query) {
        mongoTpl.remove(query, type);
    }


    @Override
    public void remove(String id){
        mongoTpl.remove(this.find(id));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public WriteResult updateOrInsert(Query query, Update update) {
        return mongoTpl.upsert(query, update, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WriteResult updateFirst(Query query, Update update) {
        return mongoTpl.updateFirst(query, update, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WriteResult updateMulti(Query query, Update update) {
        return mongoTpl.updateMulti(query, update, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(T model) {
        mongoTpl.remove(model);
        return model;
    }

    protected String table() {
        String table = null;
        Document doc = type.getAnnotation(Document.class);
        if (doc != null) {
            table = doc.collection();
        }
        else {
            table = StringUtils.uncapitalize(
                    type.getSimpleName());
        }
        return table;
    }


    @Override
    public void saveOrUpdate(T bean) {
        this.save(bean);
    }

    @Override
    public T getById(String id) {
        return this.find(id);
    }

    @Override
    public PageParams<T> list(PageParams<T> pageParams) {
        Query query=this.createQuery(pageParams);
        Long total=this.count(query);
        int start=(pageParams.getPage()-1)*pageParams.getPageSize();
        int limit=pageParams.getPageSize();
        int totalRows=total==null?0:total.intValue();
        int totalPage=totalRows/limit+(totalRows%limit==0?0:1);
        pageParams.setTotalPage(totalPage);
        pageParams.setTotalRows(totalRows);
        pageParams.setData(this.list(query.with(new Sort(Sort.Direction.fromString(pageParams.getDirection()),pageParams.getOrderBy())).skip(start).limit(limit)));
        return pageParams;
    }

    @Override
    public List<T> listAll() {
        return this.list();
    }

    @Override
    public Boolean del(List<String> ids) {
        Query query = new Query(Criteria.where(BaseMongoDbModel.ID).in(ids));
        this.remove(query);
        return true;
    }



    /**
     *
     * @param pageParams
     * @return
     */
    private Query  createQuery(PageParams<T> pageParams){
        Map<String,Object> params=pageParams.getParams();
        if(params==null||params.isEmpty()){
            return null;
        }
        Query query=new Query();
        Criteria criteria=new Criteria();
        Map<String, Pair<Object,Object>> btParams=new HashMap<>(2);
        for(Map.Entry<String,Object> entity:params.entrySet()) {
            String key = entity.getKey();
            Object value = entity.getValue();
            if(value==null|| StringUtils.isBlank(value.toString())) continue;
            if(key.startsWith("eq_")){
                String tmp=key.substring(3);
                criteria.and(tmp).is(value);
            }
            else if(key.startsWith("lk_")){
                String tmp=key.substring(3);
                Pattern pattern=Pattern.compile(".*?" + value + ".*");
                criteria.and(tmp).regex(pattern);
            }
            else if(key.startsWith("bt_")){
                String tmp=key.substring(3);
                if(tmp.endsWith("0")){
                    tmp=tmp.substring(0,tmp.length()-1);
                    if(btParams.get(tmp)==null){
                        btParams.put(tmp,new Pair<>(value,null));
                    }
                    else {
                        btParams.put(tmp,new Pair<>(value,btParams.get(tmp).getValue()));
                    }
                }
                else if(tmp.endsWith("1")){
                    tmp=tmp.substring(0,tmp.length()-1);
                    if(btParams.get(tmp)==null){
                        btParams.put(tmp,new Pair<>(null,value));
                    }
                    else {
                        btParams.put(tmp,new Pair<>(btParams.get(tmp).getKey(),value));
                    }
                }
                else {
                    continue;
                }
            }
            else {
                continue;
            }
        }
        if(!btParams.isEmpty()){
            for(Map.Entry<String,Pair<Object,Object>> entity:btParams.entrySet()) {
                Pair<Object,Object> tmp=entity.getValue();
                if(tmp.getKey()==null){
                    criteria.and(entity.getKey()).lt(tmp.getValue());
                }
                else if(tmp.getValue()==null){
                    criteria.and(entity.getKey()).gte(tmp.getKey());
                }
                else {
                    criteria.and(entity.getKey()).gte(tmp.getKey()).lt(tmp.getValue());
                }
            }
        }
        return query.addCriteria(criteria);

    }
}
