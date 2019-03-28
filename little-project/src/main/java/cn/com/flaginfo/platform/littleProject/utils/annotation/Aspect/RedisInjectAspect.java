package cn.com.flaginfo.platform.littleProject.utils.annotation.Aspect;

import cn.com.flaginfo.platform.littleProject.utils.RedisUtils;
import cn.com.flaginfo.platform.littleProject.utils.UtilHelper;
import cn.com.flaginfo.platform.littleProject.utils.annotation.RedisInject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Liang.Zhang on 2019/2/20.
 **/
@Aspect
@Component
public class RedisInjectAspect {
    private static final Logger log = LoggerFactory.getLogger(RedisInjectAspect.class);

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 支持持线性参数，如果是对象参数，有问题
     * @param pjp
     * @param redisInject
     * @return
     * @throws Throwable
     */
    @Around("@annotation(redisInject)")
    public Object around(ProceedingJoinPoint pjp, RedisInject redisInject) throws Throwable{
        Object[] args = pjp.getArgs();
        Object result = null;
        String redisKey= null;
        //逻辑处理  添加缓存
        String redisTimeKey=redisInject.redisTimeKey();
        redisKey= UtilHelper.generateRedisKey(args,redisTimeKey);
        log.info("redisKey is [{}]",redisKey);
        if (StringUtils.isNotEmpty(redisKey)) {
            Object obj=redisUtils.getObj(redisKey);
            if(null!=obj){
                log.info("get result from redis");
                return obj;
            }
        }
        try {
            result = pjp.proceed(args);
        } catch (Throwable ex) {
            log.error("Exception : {}", ex);
            throw ex;
        }
        //逻辑处理
        redisUtils.setObj(redisKey,result,redisTimeKey);
        return result;
    }


}
