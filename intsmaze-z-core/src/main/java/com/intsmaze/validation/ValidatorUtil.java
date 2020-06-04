package com.intsmaze.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidatorUtil {
    //创建静态校验工厂
    private static Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    public static <T> Map<String,StringBuffer> validate(T obj){
        //校验过程
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        //校验结果输出，可以自定义
        Map<String,StringBuffer> errorMap = null;
        if(set != null && set.size() >0 ){
            errorMap = new HashMap<String,StringBuffer>();
            String property = null;
            for(ConstraintViolation<T> cv : set){
                //这里循环获取错误信息，可以自定义格式
                property = cv.getPropertyPath().toString();
                if(errorMap.get(property) != null){
                    errorMap.get(property).append("," + cv.getMessage());
                }else{
                    StringBuffer sb = new StringBuffer();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap;
    }


}
