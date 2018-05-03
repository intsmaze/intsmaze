package org.intsmaze.business.user.vo.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.intsmaze.business.user.service.IUserService;
import org.intsmaze.business.user.vo.FDFBUserVo;
import org.intsmaze.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-23 上午8:40:36
 * @version :
 * 
 */
public class FDFBUserValidator implements ConstraintValidator<IFDFBUserValidator, FDFBUserVo> {

	@Autowired
	private IUserService userService;
	  
      
    public void initialize(IFDFBUserValidator constraintAnnotation) {  
        //初始化，得到注解数据  
    }  
  
      
    public boolean isValid(FDFBUserVo record, ConstraintValidatorContext context) {  

    	boolean isValid = true;
		if(StringUtils.hasText(record.getSeqno())) {//修改
			
			int count = userService.getUserCountByUsername(record.getUsername());
			if(count > 0)
			{
				List userList = userService.getUserByUsername(record.getUsername());
				FDFBUserVo userVo = (FDFBUserVo)userList.get(0);
				if(!userVo.getSeqno().equals(record.getSeqno()))//不是自己，则说明用户名重复
				{
					context.disableDefaultConstraintViolation();
			        context.buildConstraintViolationWithTemplate("该工号已存在！")
			                .addPropertyNode("username")
			                .addConstraintViolation();
			        isValid = false;
				}
			}
	    }
		else//新增
		{
			int count = userService.getUserCountByUsername(record.getUsername());
			if(count > 0)
			{
				context.disableDefaultConstraintViolation();
		        context.buildConstraintViolationWithTemplate("该工号已存在")
		                .addPropertyNode("username")
		                .addConstraintViolation();
		        isValid = false;
			}
			if("".equals(StringUtil.isNullString(record.getRoleids())))
			{
				context.disableDefaultConstraintViolation();
		        context.buildConstraintViolationWithTemplate("角色不能为空！")
		                .addPropertyNode("roleids")
		                .addConstraintViolation();
		        isValid = false;
			}
		}
		
        return isValid;  
    }

}
