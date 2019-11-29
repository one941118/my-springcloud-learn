package com.ybl.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.test
 * @ClassName: TestDemo
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2019/11/29 14:34
 * @Version: 1.0
 */
@RequestMapping("/go")
@RestController
public class TestDemo {

    @GetMapping("test")
    public String test(){
        return "aabb";
    }
}
