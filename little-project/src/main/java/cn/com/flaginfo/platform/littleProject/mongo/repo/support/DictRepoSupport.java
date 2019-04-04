package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.Dict;
import cn.com.flaginfo.platform.littleProject.mongo.models.item.DictItem;
import cn.com.flaginfo.platform.littleProject.mongo.repo.DictRepo;
import cn.com.flaginfo.platform.littleProject.utils.exception.BarberException;
import org.apache.commons.lang3.StringUtils;
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
    public Boolean singleDel(String code, String childCode, String childName) {
        Dict dict=this.getByCode(code);
        List<DictItem> list=new ArrayList<>(10);
        if(StringUtils.isNotBlank(childCode)){
            dict.getItemList().forEach(p->{
               if(!p.getCode().equals(childCode)){
                   list.add(p);
               }
            });
        }
        else if(StringUtils.isNotBlank(childName)){
            dict.getItemList().forEach(p->{
                if(!p.getName().equals(childName)){
                    list.add(p);
                }
            });
        }
        dict.setItemList(list);
        this.save(dict);
        return true;
    }

    @Override
    public Boolean singleSave(String code, String childCode, String childName,String childRemark) {
        if(StringUtils.isBlank(childCode)&&StringUtils.isBlank(childName)){
            throw new BarberException("参数不完整");
        }
        Dict dict=this.getByCode(code);
        if(dict==null){
            dict=new Dict();
            dict.setCode(code);
        }
        DictItem item=new DictItem();
        item.setCode(childCode);
        item.setName(childName);
        item.setRemark(childRemark);
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
