package org.intsmaze.business.login.vo.validator;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.intsmaze.business.Interfaces.IUserApi;
import org.intsmaze.business.login.vo.FDFBChangePasswordVo;
import org.intsmaze.core.util.FDFBMD5;
import org.intsmaze.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @date:2016-5-23 上午8:40:36
 * @version :
 * 
 */
public class ChangePasswordValidator implements ConstraintValidator<IChangePasswordValidator, FDFBChangePasswordVo> {
	  
    public void initialize(IChangePasswordValidator constraintAnnotation) {  
        //初始化，得到注解数据  
    }  
    
    @Autowired
    private IUserApi userApi;
  
    public boolean isValid(FDFBChangePasswordVo record, ConstraintValidatorContext context) {  

    	boolean isValid = true;
		if(!StringUtils.hasText(record.getOldpassword())) {//新话亭id不能为空
	        context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("原密码不能为空")
	                .addPropertyNode("oldpassword")
	                .addConstraintViolation();
	        isValid = false;
	    }
		if(!StringUtils.hasText(record.getNewpassword())) {//新话亭id不能为空
	        context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("新密码不能为空")
	                .addPropertyNode("newpassword")
	                .addConstraintViolation();
	        isValid = false;
	    }
		if(!StringUtils.hasText(record.getConfirmpassword())) {//新话亭id不能为空
	        context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("确认密码不能为空")
	                .addPropertyNode("confirmpassword")
	                .addConstraintViolation();
	        isValid = false;
	    }
		if(StringUtils.hasText(record.getNewpassword()) && StringUtils.hasText(record.getConfirmpassword()) 
				&& !StringUtil.isNullString(record.getNewpassword()).equals(StringUtil.isNullString(record.getConfirmpassword())))
		{
			context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("新密码与确认密码必须一致")
	                .addPropertyNode("confirmpassword")
	                .addConstraintViolation();
	        isValid = false;
		}
		if(StringUtils.hasText(record.getOldpassword()))
		{
			Map map = userApi.checkUserNameAndPassword(record.getUsername(), FDFBMD5.stringMD5(record.getOldpassword()));
			if(map == null)
			{
				context.disableDefaultConstraintViolation();
		        context.buildConstraintViolationWithTemplate("原密码不正确")
		                .addPropertyNode("oldpassword")
		                .addConstraintViolation();
		        isValid = false;
			}
		}
    		
        return isValid;  
    }

}
