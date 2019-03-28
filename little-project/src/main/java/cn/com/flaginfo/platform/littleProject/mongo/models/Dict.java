package cn.com.flaginfo.platform.littleProject.mongo.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Dict")
public class Dict extends BaseMongoDbModel{

    @Indexed
    private String code;
    private String name;
    private String parentId;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
                ", parentId='" + parentId + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
