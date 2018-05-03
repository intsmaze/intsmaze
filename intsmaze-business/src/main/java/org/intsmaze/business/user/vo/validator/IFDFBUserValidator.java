package org.intsmaze.business.user.vo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;  
import static java.lang.annotation.RetentionPolicy.*; 

/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-23 上午8:36:47
 * @version :
 * 自定义验证规则，对于一些通用的验证可以自定义规则进行抽象
 */
@Target({ TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)  
//指定验证器  
@Constraint(validatedBy = FDFBUserValidator.class)  
@Documented 
public @interface IFDFBUserValidator {
	
	//默认错误消息  
    String message() default "该工号已存在";  
  
    //分组  
    Class<?>[] groups() default { };  
  
    //负载  
    Class<? extends Payload>[] payload() default { };  
  
    //指定多个时使用  
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })  
    @Retention(RUNTIME)  
    @Documented  
    @interface List {  
    	IFDFBUserValidator[] value();  
    }  

}
