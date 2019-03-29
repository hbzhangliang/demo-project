package cn.com.flaginfo.platform.littleProject.mongo.models;

import cn.com.flaginfo.platform.littleProject.mongo.models.item.DictItem;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Dict")
public class Dict extends BaseMongoDbModel{

    @Indexed
    private String code;
    private String name;
    private List<DictItem> itemList;
    private String value;
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DictItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<DictItem> itemList) {
        this.itemList = itemList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemList=" + itemList +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
