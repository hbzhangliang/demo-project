package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.models.MetroLine;
import cn.com.flaginfo.platform.littleProject.mongo.vo.PageParams;

import java.util.List;

public interface MetroLineRepo extends BaseMongoDbRepo<MetroLine> {


    void saveOrUpdate(MetroLine bean);

    MetroLine getById(String id);

    PageParams<MetroLine> list(PageParams<MetroLine> pageParams);

    List<MetroLine> listAll();

    Boolean del(List<String> ids);

}
