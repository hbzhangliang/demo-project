package cn.com.flaginfo.platform.littleProject.mongo.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Boss")
public class Boss extends BaseMongoDbModel{
    @Indexed
    private String wxId;
    private String wxName;
    @Indexed
    private String mobile;
    private String name;
    private String nickName;
    private String password;
    // 0 女  1 男
    private Boolean gender;
    private int leftCount;
    private List<String> barberList;



}
