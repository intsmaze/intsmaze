package com.intsmaze.validation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.MapBindingResult;

import javax.validation.Valid;
import java.util.Map;

@Component
public class ValidatorTest {

    public static void main(String[] args) {

        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("comintsmazebefore.xml");

        ValidatorTest bean = ctx.getBean(ValidatorTest.class);
        StudentInfo s = new StudentInfo();
        long startTime = System.currentTimeMillis();
        print(ValidatorUtil.validate(s));
        System.out.println("===============耗时(毫秒)=" + (System.currentTimeMillis() - startTime));

        s.setUserName("chenwb");
        s.setAge("10");
        s.setBirthday("2016-09-01");
        s.setSchool("intsmaze");
        startTime = System.currentTimeMillis();
        print(ValidatorUtil.validate(s));
        System.out.println("===============耗时(毫秒)=" + (System.currentTimeMillis() - startTime));
    }


    //这一步做不了，不纠结了，就下面的也是可以的。下面的不需要引入spring配置
    public String testAnnotation(@Valid StudentInfo studentInfo, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return studentInfo.getSchool();
    }

    private static void print(Map<String, StringBuffer> errorMap) {
        if (errorMap != null) {
            for (Map.Entry<String, StringBuffer> m : errorMap.entrySet()) {
                System.out.println(m.getKey() + ":" + m.getValue().toString());
            }
        }
    }

}
