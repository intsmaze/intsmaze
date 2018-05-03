package org.intsmaze.business.login.vo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;  
import static java.lang.annotation.RetentionPolicy.*; 


@Target({ TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)  
//指定验证器  
@Constraint(validatedBy = ChangePasswordValidator.class)  
@Documented 
public @interface IChangePasswordValidator {
	
	//默认错误消息  
    String message() default "原话亭必须匹配";  
  
    //分组  
    Class<?>[] groups() default { };  
  
    //负载  
    Class<? extends Payload>[] payload() default { };  
  
    //指定多个时使用  
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })  
    @Retention(RUNTIME)  
    @Documented  
    @interface List {  
    	IChangePasswordValidator[] value();  
    }  

}
