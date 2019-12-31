package com.ybl.demo.annotation;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.annotation
 * @ClassName: AnnotationAspect
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2019/12/30 16:13
 * @Version: 1.0
 */
@Aspect
@Component
public class AnnotationAspect {

    @Pointcut("@annotation(com.ybl.demo.annotation.MyFirstAnnotation)")
    public void eventPointcut() {
    }

    @Around("eventPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("====Interceptor====");
        System.out.println("通知之开始");
        Object resMsg = null;
        try {
            resMsg = joinPoint.proceed();
            System.err.println("++++++++" + resMsg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("通知之结束 +resMsg+" + resMsg);

        Object result = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            MyFirstAnnotation myFirstAnnotation = this.getMyFirstAnnotation(joinPoint);
            System.out.println(myFirstAnnotation.name()+myFirstAnnotation.content()+JSON.toJSONString(args));
            MySecondAnnotation mySecondAnnotation = this.getMySecondAnnotation(joinPoint);
            if (Objects.isNull(mySecondAnnotation)){
                System.out.println("mySecondAnnotation是空");
            }
        }
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    @Before("eventPointcut()")
    public void before(JoinPoint joinPoint) {

    }

    @After("eventPointcut()")
    public void after() {
        System.out.println("after方法执行");
    }

    private MyFirstAnnotation getMyFirstAnnotation(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        MyFirstAnnotation annotation = method.getAnnotation(MyFirstAnnotation.class);
        return annotation;
    }

    private MySecondAnnotation getMySecondAnnotation(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        MySecondAnnotation annotation = method.getAnnotation(MySecondAnnotation.class);
        return annotation;
    }
}


