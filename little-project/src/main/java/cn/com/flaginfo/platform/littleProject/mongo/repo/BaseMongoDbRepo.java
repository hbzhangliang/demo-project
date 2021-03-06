package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * Created by liang_zhang on 2017/9/19.
 */
public interface BaseMongoDbRepo<T> {

    /**
     * find one by query
     * @param query
     * @return
     */
    T find(Query query);

    /**
     * find one by id
     * @param id
     * @return
     */
    T find(String id);

    /**
     * list by query
     * @param query
     * @return
     */
    List<T> list(Query query);

    /**
     * list in
     * @param keys
     * @return
     */
    List<T> list(Collection<?> keys);

    /**
     * list all
     * @return
     */
    List<T> list();

    /**
     * count by query
     * @param query
     * @return
     */
    long count(Query query);

    /**
     * insert models
     * @param models
     */
    @SuppressWarnings("unchecked")
    void insert(T... models);

    /**
     * insert models
     * @param models
     */
    void insert(Collection<T> models);

    /**
     * save models
     * @param models
     */
    @SuppressWarnings("unchecked")
    void save(T... models);

    /**
     * save models
     * @param models
     */
    void save(Collection<T> models);

    /**
     * 批量保存，防止保存时超过内存限制
     * @param
     */
    void saveBatch(List<T> list);

    /**
     * update model
     * @param query
     * @param update
     * @return
     */
    T update(Query query, Update update);

    /**
     * update or insert model
     * @param update
     */
    WriteResult updateOrInsert(Query query, Update update);

    /**
     * update first
     * @param query
     * @param update
     * @return
     */
    WriteResult updateFirst(Query query, Update update);

    /**
     * update multi
     * @param query
     * @param update
     * @return
     */
    WriteResult updateMulti(Query query, Update update);

    /**
     * remove
     * @param query
     */
    void remove(Query query);


    void remove(String id);
    /**
     * remove model
     * @param model
     * @return
     */
    T remove(T model);



    //服务下沉
    void saveOrUpdate(T bean);

    T getById(String id);

    PageParams<T> list(PageParams<T> pageParams);

    List<T> listAll();

    Boolean del(List<String> ids);


}
