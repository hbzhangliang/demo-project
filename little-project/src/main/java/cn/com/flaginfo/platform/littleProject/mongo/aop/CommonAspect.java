package cn.com.flaginfo.platform.littleProject.mongo.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//@Order(20)
public class CommonAspect {

    private static final Logger log = LoggerFactory.getLogger(CommonAspect.class);

    @Pointcut("execution(* cn.com.flaginfo.platform.littleProject.mongo.repo.*Repo.*(..))")
    public void pointcut(){
    }

    @Around("pointcut()")
    public Object pointcut(ProceedingJoinPoint pjp) throws Throwable {
        Long start=System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        Object result = null;
        try {
            result = pjp.proceed(args);
        } catch (Throwable ex) {
            log.error("Exception : {}", ex);
            throw ex;
        }
        Long end=System.currentTimeMillis();
        log.info("signature is [{}] args is [{}]take [{}],result is [{}]",pjp.getSignature().toShortString(), JSONObject.toJSONString(args),end-start,JSONObject.toJSONString(result));
        return result;
    }

}
