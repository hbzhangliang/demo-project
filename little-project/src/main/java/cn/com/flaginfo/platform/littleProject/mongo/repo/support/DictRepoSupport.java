package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.Dict;
import cn.com.flaginfo.platform.littleProject.mongo.models.item.DictItem;
import cn.com.flaginfo.platform.littleProject.mongo.repo.DictRepo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictRepoSupport extends BaseMongoDbRepoSupport<Dict> implements DictRepo {

    private static String DICT_CITY="city";

    @Override
    public Dict getByCode(String code) {
        Query query=new Query(Criteria.where("code").is(code));
        return this.find(query);
    }


    @Override
    public Boolean cityDel(String name) {
        Dict dict=this.getByCode(DICT_CITY);
        List<DictItem> list=new ArrayList<>(10);
        dict.getItemList().forEach(p->{
            if(!p.getName().equals(name)){
                list.add(p);
            }
        });
        dict.setItemList(list);
        this.save(dict);
        return true;
    }

    @Override
    public Boolean cityAdd(String name) {
        Dict dict=this.getByCode(DICT_CITY);
        if(dict==null){
            dict=new Dict();
            dict.setCode(DICT_CITY);
        }
        DictItem item=new DictItem();
        item.setName(name);
        if(dict.getItemList()==null){
            List<DictItem> list=new ArrayList<>(1);
            list.add(item);
            dict.setItemList(list);
        }
        else {
            dict.getItemList().add(item);
        }
        this.save(dict);
        return true;
    }
}
