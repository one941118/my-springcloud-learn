package com.ybl.demo.test;

import com.ybl.demo.annotation.MyFirstAnnotation;
import com.ybl.demo.query.UserQuery;
import lombok.Data;
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
    @MyFirstAnnotation(name = "小刚",content = "tel")
    @GetMapping("test")
    public String test(UserQuery userQuery){
        System.out.println(userQuery.getName());
        System.out.println(userQuery.getTel());
        return "aabb";
    }

    private volatile static  String demoStr = "hello demo";

    public static void main(String[] args) {
//            String s1 = "Programming";
//            String s2 = new String("Programming");
//            String s3 = "Program";
//            String s4 = "ming";
//            String s5 = "Program" + "ming";
//            String s6 = s3 + s4;
//            String s7 = s3 + "ming";
//            System.out.println(s1 == s2);
//            System.out.println(s1 == s5);
//            System.out.println(s1 == s6);
//            System.out.println(s1 == s6.intern());
//            System.out.println(s2 == s2.intern());
//        System.out.println(s1 == s7);
//        System.out.println();
//        System.out.println(isContainChinese("hello world"));
//
//        System.out.println("，hello，world".replace("，",","));
//        System.out.println(Integer.MAX_VALUE);

//       Double bb = 1.0 / 0 ;
//        System.out.println(bb.isInfinite());
//        System.out.println(bb.isNaN());
//
//        String aabb = "s112555s-s11807615s";
//        System.out.println(aabb.indexOf("7777"));
//        System.out.println(Base64Utils.encodeToString(aabb.getBytes()));
//        ConcurrentSkipListMap<String,String>  concurrentSkipListMap = new ConcurrentSkipListMap();
//       concurrentSkipListMap.put("aabb0","aabb0");
//       concurrentSkipListMap.put("aabb1","aabb1");
//       concurrentSkipListMap.put("aabb2","aabb2");
//       concurrentSkipListMap.put("aabb3","aabb3");
//       concurrentSkipListMap.put("aabb4","aabb4");
//       concurrentSkipListMap.forEach((key,value)->{
//           System.out.println(key + ":" + value);
//       });
//
//       Map<String,String> map01 = new HashMap<>();
//        map01.put("aabb0","aabb0");
//        map01.put("aabb1","aabb1");
//        map01.put("aabb2","aabb2");
//        map01.put("aabb3","aabb3");
//        map01.put("aabb4","aabb4");
//        map01.forEach((key,value)->{
//            System.out.println(key + ":" + value);
//        });

        String aabb = "s0s-s20s-s9430425s-s3606266s-s4026706s-s4128724s-s4250229s-s5150906s-s5234189s-s11882881s"
            + "-s11919242s";
        System.out.println(aabb.replace("s4026706s-",""));

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
