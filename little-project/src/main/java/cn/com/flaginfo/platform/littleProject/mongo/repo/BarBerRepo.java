package cn.com.flaginfo.platform.littleProject.mongo.repo;

import cn.com.flaginfo.platform.littleProject.mongo.models.Barber;

public interface BarBerRepo  extends BaseMongoDbRepo<Barber> {

    Barber getByMobile(String mobile);
}
