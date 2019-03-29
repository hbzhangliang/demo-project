package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.MetroLine;
import cn.com.flaginfo.platform.littleProject.mongo.repo.MetroLineRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class MetroLineRepoSupport extends BaseMongoDbRepoSupport<MetroLine> implements MetroLineRepo {
    private final static Logger log = LoggerFactory.getLogger(MetroLineRepoSupport.class);
}
