
package org.intsmaze.business.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.groups.Default;

import org.intsmaze.business.service.ICodeValueService;
import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.business.springmvc.util.DatatableTo;
import org.intsmaze.business.springmvc.util.JsonUtil;
import org.intsmaze.business.user.service.IPermitService;
import org.intsmaze.business.user.service.IRoleService;
import org.intsmaze.business.user.service.IUserProfileService;
import org.intsmaze.business.user.service.IUserRoleService;
import org.intsmaze.business.user.service.IUserService;
import org.intsmaze.business.user.service.OrganizationService;
import org.intsmaze.business.user.vo.FDFBUserProfileVo;
import org.intsmaze.business.user.vo.FDFBUserRoleVo;
import org.intsmaze.business.user.vo.FDFBUserVo;
import org.intsmaze.core.exception.FDFBRuntimeException;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.FDFBMD5;
import org.intsmaze.core.util.Result;
import org.intsmaze.core.util.ResultGenerator;
import org.intsmaze.core.util.SqlInjectFilterUtil;
import org.intsmaze.core.util.StringUtil;
import org.intsmaze.core.util.Util;
import org.intsmaze.persistence.common.PageContext;
import org.intsmaze.persistence.pojos.Organization;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/User")
public class FDFBUserController {
	
	private static Logger log = Logger.getLogger(FDFBUserController.class); 

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICodeValueService codeValueService;

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserProfileService userProfileService;
	
	@Autowired
	private IPermitService permitService;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private OrganizationService orgService;
	
	

			
			
			
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView userList() {
		ModelAndView mav = new ModelAndView();
		Map map = codeValueService.getAllCodeValue();
		mav.addObject("codeValue", map);
		mav.setViewName("userList");
		return mav;
	}
	
	@RequestMapping(value="/listEditor", method=RequestMethod.GET)
	public ModelAndView userlistEditor() {
		ModelAndView mav = new ModelAndView();
		Map map = codeValueService.getAllCodeValue();
		
		Organization organization = new Organization();
		
		organization.setStatus("1");
		
		List list = orgService.selectAll(organization);
		
		mav.addObject("codeValue", map);
		mav.addObject("orgList",list);
		
		mav.setViewName("userListEditor");
		return mav;
	}
	
	/**
	 * jquery Database对应的分页方法
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value="/list1", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public String userList1(@RequestBody String aoData, HttpServletRequest request) {
		log.debug(aoData.toString());
		DatatableTo dto = (DatatableTo) JsonUtil.jqueryStrToBean(aoData, new DatatableTo());
		FDFBUserVo user = (FDFBUserVo) JsonUtil.jqueryStrToBean(aoData, new FDFBUserVo());
		user.setOrderByStr(SqlInjectFilterUtil.filterSqlInject(dto.getOrderStr()));//排序条件,对于mybatis动态sql中使用$的字段必须做sql注入检查
		int userCount = userService.getAllUserWithCount(user);
		
		PageContext page = PageContext.getContext();
		page.setPageStartRow(dto.getiDisplayStart());//int参数，无法进行sql注入
		page.setPageSize(dto.getiDisplayLength());//int参数，无法进行sql注入
		page.setPagination(true);//false不分页，true分页，该属性基本无用，只要select方法不以ByPage结尾就不会分页
		
		List resultList = userService.getAllUserWithOrgByPage(user);
		log.debug("totalCount===="+page.getTotalRows());
		
		if(resultList != null)
		{
			for(int i=0,n=resultList.size(); i<n; i++)
			{
				user = (FDFBUserVo)resultList.get(i);
				user.setDT_RowId(Constant.DT_ROWID_PREFIX+user.getSeqno());
			}
		}
		else
		{
			resultList = new ArrayList(0);
		}
		
		Map obj = new HashMap();  
		obj.put("sEcho", dto.getsEcho());  
        obj.put("iTotalRecords", userCount);  
        obj.put("iTotalDisplayRecords", userCount);  
//        obj.put("recordsFiltered", userList.size());  
        obj.put("data", resultList);  
//        response.getWriter().println(obj.toJSONString()); 
		
		String result =  JsonUtil.toJson(obj);
		
		return result;
		
//		FDFBJsonView view = new FDFBJsonView();
//		Map attributes = new HashMap();
//		attributes.put("userList", userList);
//		attributes.put("SUCCESS", true);
//		view.setAttributesMap(attributes);
//		mav.setView(view);
//		mav.setViewName("userList");
//		return userList;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public ModelAndView gotoAdd() {
		ModelAndView mav = new ModelAndView();
		List roleList = roleService.selectAll();
		mav.addObject("rolelist", roleList);
		mav.setViewName("addUser");
		return mav;
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseBody
//	public ModelAndView addUser(@Valid UserVo user, BindingResult result, HttpServletRequest request) {
	public ModelAndView addUser(@Validated @ModelAttribute(Constant.COMMON_UI_ERROR_NAME) FDFBUserVo user, BindingResult result, HttpServletRequest request) {//都可以，但是验证错误信息返回必须使用这个
		ModelAndView mav = new ModelAndView();
		log.debug(user.getName());
//		user.setSeqno(UUID.randomUUID().toString());
		// 如果用户名已存在，返回添加用户的页面  
//	    if (isUserNameExist) {  
//	        // 向BindingResult添加用户已存在的校验错误  
//	        bindingResult.rejectValue("userName", "该用户名已存在", "该用户名已存在");  
//	        return "/user/create";  
//	    }  
		if(result.hasErrors()) {  
			mav.setViewName("addUser");
			return mav;
        }  
		
		user.setPassword(FDFBMD5.stringMD5(Constant.DEFAULT_PASSWORD));
		String userid = userService.addUser(user);
		
		
		String roleids = user.getRoleids();
		String[] roleidArray = roleids.split(",");
		userRoleService.deleteUserRolesByUserid(userid);
		for(int i=0,n=roleidArray.length; i<n; i++)
		{
			String roleidTemp = roleidArray[i];
			FDFBUserRoleVo urv = new FDFBUserRoleVo();
			urv.setUserid(userid);
			urv.setRoleid(roleidTemp);
			urv.setCreateby(BusinessUtil.getCurrentUserName(request));
			urv.setModifyby(BusinessUtil.getCurrentUserName(request));
			userRoleService.addUserRole(urv);
		}
//		log.debug("resultcount==================" + resultcount);
		mav.setViewName("redirect:listEditor");
		return mav;
	}
	
	@RequestMapping(value="/reteiveUser", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public FDFBUserVo reteiveUser(@RequestBody String seqno, HttpServletRequest request) {
		
		FDFBUserVo user = new FDFBUserVo();
		user = (FDFBUserVo)JsonUtil.jqueryStrToBean(seqno, user);
		user = userService.selectByPrimaryKey(user.getSeqno());
//		log.debug("resultcount==================" + resultcount);
//		Map obj = new HashMap();  
//        obj.put("data", user);  
//        response.getWriter().println(obj.toJSONString()); 
//		String result =  JsonUtil.toJson(obj);
		return user;
	}
	
	/**
	 * jquery Database对应的行内修改方法
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value="/updateByAjax", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public FDFBUserVo userUpdateByAjax(@RequestBody String aoData, HttpServletRequest request) {
		try {
			aoData = URLDecoder.decode(aoData, Constant.ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug(aoData.toString());
		
		FDFBUserVo user = new FDFBUserVo();
		user = (FDFBUserVo) JsonUtil.copyDetailEditStrToBean(aoData, user);
		
		/*数据数据验证*/		
		FDFBUserVo userDB = userService.selectByPrimaryKey(user.getSeqno());
		Util.copyPropertiesIgnoreNull(user, userDB);
		String validResult = BusinessUtil.baseCheckInputVO(userDB, request);
		if(!"".equals(StringUtil.isNullString(validResult)))
		{
			user.setValidMsg(validResult);
			return user;
		}
		/*数据验证结束*/
		
		user = userService.updateUserDynamic(user);
		user.setDT_RowId(Constant.DT_ROWID_PREFIX+user.getSeqno());
		
		Map attributes = new HashMap(1);
		attributes.put("data", user);
		
		String result =  JsonUtil.toJson(attributes);
		
		return user;
		
		
//		FDFBJsonView view = new FDFBJsonView();
//		Map attributes = new HashMap();
//		attributes.put("userList", userList);
//		attributes.put("SUCCESS", true);
//		view.setAttributesMap(attributes);
//		mav.setView(view);
//		mav.setViewName("userList");
//		return userList;
	}
	
	
	@RequestMapping(value="/mbfeAdd", method=RequestMethod.GET)
	public ModelAndView gotoMbfeAdd() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("mbfeAdd");
		return mav;
	}
	
	/**
	 * jquery Database对应的行内修改方法
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value="/deleteByAjax", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Map deleteByAjax(@RequestBody String seqno, HttpServletRequest request) {
		
		log.debug(seqno);
		if(seqno!=null)
		{
			seqno = seqno.substring(seqno.indexOf("=")+1,seqno.length());
		}
		Map result = new HashMap(1);
		try{
			if(userService.deleteUserBySeqno(seqno) > 0)
			{
				result.put("result", "success");
			}
			else
			{
				result.put("result", "fail");
			}
		}catch (FDFBRuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("result", "fail");
			return result;
		}

		return result;
//		FDFBJsonView view = new FDFBJsonView();
//		Map attributes = new HashMap();
//		attributes.put("userList", userList);
//		attributes.put("SUCCESS", true);
//		view.setAttributesMap(attributes);
//		mav.setView(view);
//		mav.setViewName("userList");
//		return userList;
	}
	
	@RequestMapping(value="/gotoUserProfile", method=RequestMethod.GET)
	public ModelAndView gotoUserProfile(HttpServletRequest request)
	{
		Map user = (Map) request.getSession().getAttribute(Constant.USERINFO_SESSION);
		List leafPermit = permitService.getLeafPermit(Constant.PERMITTYPE_MENU, "seqno", (String)user.get(Constant.USER_ROLE_SESSION));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("UserInfo", user);
		mav.addObject("LeafPermitMap", leafPermit);
		mav.setViewName("userProfile");
		return mav;
	}
	
	@RequestMapping(value="/roleListForUser", method=RequestMethod.GET)
	public ModelAndView roleListForUser(HttpServletRequest request)
	{
		List roleList = roleService.selectAll();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("roleList", roleList);
		mav.setViewName("roleListForUser");
		return mav;
	}
	
	@RequestMapping(value="/userProfileEdit", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView userProfileEdit(@Validated @ModelAttribute(Constant.COMMON_UI_ERROR_NAME) FDFBUserProfileVo upv, BindingResult result, HttpServletRequest request)
	{
		Map user = (Map) request.getSession().getAttribute(Constant.USERINFO_SESSION);
		String userid = (String)user.get(Constant.USER_ID_SESSION);
		upv.setUserId(userid);
		FDFBUserProfileVo fpv = userProfileService.getUserProfileByUserid(userid);
		if(fpv!=null)
		{
			upv.setSeqno(fpv.getSeqno());
			fpv = null;
		}
		upv.setCreateby((String)user.get(Constant.USER_NAME_SESSION));
		upv.setModifyby((String)user.get(Constant.USER_NAME_SESSION));
		upv.setUserId(userid);
		upv.setUserHomepage("redirect:/" + upv.getUserHomepage());
		if("2".equals(upv.getUserStyle()))
			upv.setUserStyle("/bootstrap/dist/css/sb-admin-green.css");
		else if("3".equals(upv.getUserStyle()))
			upv.setUserStyle("/bootstrap/dist/css/sb-admin-black.css");
		else
			upv.setUserStyle("/bootstrap/dist/css/sb-admin-2.css");
		
		userProfileService.saveOrUpdateUserProfile(upv);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(upv.getUserHomepage());
		return mav;
	}
	
	
	@RequestMapping(value="/modifyRoleListForUser", method=RequestMethod.GET)
	public ModelAndView modifyRoleListForUser(HttpServletRequest request)
	{
		List roleList = roleService.selectAll();
		String userid = request.getParameter("seqno");
		List userrole = userRoleService.getRolesByUserid(userid);
		if(userrole==null)
		{
			userrole = new ArrayList(0);
		}
		StringBuffer roleids = new StringBuffer("");
		for(int i=0,n=userrole.size(); i<n; i++)
		{
			FDFBUserRoleVo roleVo = (FDFBUserRoleVo)userrole.get(i);
			roleids.append(roleVo.getRoleid()).append(",");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("roleids", roleids.toString());
		mav.addObject("userid", userid);
		mav.addObject("roleList", roleList);
		mav.setViewName("modifyRoleListForUser");
		return mav;
	}
	
	
	
	@RequestMapping(value="/modifyRoleList", method=RequestMethod.GET)
	public ModelAndView modifyRoleList(HttpServletRequest request) throws FDFBRuntimeException
	{
		String userid = request.getParameter("seqno");
		String roleids = request.getParameter("roleids");
		
		userRoleService.deleteUserRolesByUserid(userid);
		String[] roleidArray = roleids.split(",");
		for(int i=0,n=roleidArray.length; i<n; i++)
		{
			FDFBUserRoleVo userRoleVo = new FDFBUserRoleVo();
			userRoleVo.setCreateby(BusinessUtil.getCurrentUserName(request));
			userRoleVo.setModifyby(BusinessUtil.getCurrentUserName(request));
			userRoleVo.setRoleid(roleidArray[i]);
			userRoleVo.setUserid(userid);
			userRoleService.addUserRole(userRoleVo);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:listEditor");
		return mav;
	}
	
}
