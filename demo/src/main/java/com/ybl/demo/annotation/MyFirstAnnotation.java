package com.ybl.demo.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.annotation
 * @ClassName: MyFirstAnnotation
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2019/12/30 15:56
 * @Version: 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyFirstAnnotation {

    String content() default "undefined";

    String name() default "undefined";

}
