package cn.com.flaginfo.platform.littleProject.mongo.repo.support;

import cn.com.flaginfo.platform.littleProject.mongo.models.User;
import cn.com.flaginfo.platform.littleProject.mongo.repo.UserRepo;
import org.springframework.stereotype.Service;


@Service
public class UserRepoSupport extends BaseMongoDbRepoSupport<User> implements UserRepo {
}
