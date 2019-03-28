package cn.com.flaginfo.platform.littleProject.utils.annotation;

import java.lang.annotation.*;

/**
 * Created by Liang.Zhang on 2019/2/20.
 *
 * 如果是
 *
 **/
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisInject {
    String redisTimeKey() default "COMMON";
}
