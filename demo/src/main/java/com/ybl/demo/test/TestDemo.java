package com.ybl.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void main(String[] args) {
            String s1 = "Programming";
            String s2 = new String("Programming");
            String s3 = "Program";
            String s4 = "ming";
            String s5 = "Program" + "ming";
            String s6 = s3 + s4;
            String s7 = s3 + "ming";
            System.out.println(s1 == s2);
            System.out.println(s1 == s5);
            System.out.println(s1 == s6);
            System.out.println(s1 == s6.intern());
            System.out.println(s2 == s2.intern());
        System.out.println(s1 == s7);
        System.out.println();
        System.out.println(isContainChinese("hello world"));

        System.out.println("，hello，world".replace("，",","));

    }

    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\\uff0c]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
