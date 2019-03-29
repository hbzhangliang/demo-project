package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.models.MetroLine;

import java.util.List;

public interface MetroLineRepo extends BaseMongoDbRepo<MetroLine> {

    List<MetroLine> listByCity(String city);


}
