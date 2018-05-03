
package org.intsmaze.business.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.intsmaze.business.Interfaces.IUserApi;
import org.intsmaze.business.service.ICodeValueService;
import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.business.springmvc.util.DatatableTo;
import org.intsmaze.business.springmvc.util.JsonUtil;
import org.intsmaze.business.user.service.IDepartmentService;
import org.intsmaze.business.user.service.IRolePermitService;
import org.intsmaze.business.user.service.IRoleService;
import org.intsmaze.business.user.service.IUserService;
import org.intsmaze.business.user.vo.FDFBRolePermitVo;
import org.intsmaze.business.user.vo.FDFBRoleVo;
import org.intsmaze.business.vo.validator.InsertGroup;
import org.intsmaze.core.exception.FDFBRuntimeException;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.core.util.Result;
import org.intsmaze.core.util.ResultGenerator;
import org.intsmaze.core.util.SqlInjectFilterUtil;
import org.intsmaze.core.util.Util;
import org.intsmaze.persistence.common.HandlerResult;
import org.intsmaze.persistence.common.PageContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@Controller
@RequestMapping("/User")
public class FDFBRoleController {
	
	private static Logger log = Logger.getLogger(FDFBRoleController.class); 

	@Autowired
	private ICodeValueService codeValueService;

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IDepartmentService depService;
	
	@Autowired
	private IRolePermitService rolePermitService;
		
	@Autowired
	IUserApi userApi;
	
//		try{
//			String str=null;
//			str.substring(1);
//		}catch (Exception e) {
//			// TODO: handle exception
//			throw new FDFBMonitorException("XX鏂囦欢鐢熸垚澶辫触");
//		}
	
	@RequestMapping(value="/roleAdd", method=RequestMethod.GET)
	public ModelAndView gotoAdd() {
		ModelAndView mav = new ModelAndView();
		List depList = depService.selectAll();//涓轰簡鍒濆鍖杁epartment涓嬫媺妗�
		mav.addObject("depList",depList);
		mav.setViewName("addRole");
		log.debug(codeValueService.getValueByKeyAndCode("key_sex", "1"));
		log.debug(codeValueService.getValueByKeyAndCode("key_sex", "2"));
		return mav;
	}
	
	@RequestMapping(value="/addRoleProcess", method=RequestMethod.POST)
	@ResponseBody
//	public ModelAndView addUser(@Valid UserVo user, BindingResult result, HttpServletRequest request) {
	public ModelAndView addUser(@Validated @ModelAttribute(Constant.COMMON_UI_ERROR_NAME) FDFBRoleVo role, BindingResult result, HttpServletRequest request) {//閮藉彲浠ワ紝浣嗘槸楠岃瘉閿欒淇℃伅杩斿洖蹇呴』浣跨敤杩欎釜
		ModelAndView mav = new ModelAndView();
//		role.setSeqno(UUID.randomUUID().toString());
		
		List depList = depService.selectAll();//鏇存崲鎴恉ep
		mav.addObject("depList",depList);
		// 濡傛灉鐢ㄦ埛鍚嶅凡瀛樺湪锛岃繑鍥炴坊鍔犵敤鎴风殑椤甸潰  
//	    if (isUserNameExist) {  
//	        // 鍚態indingResult娣诲姞鐢ㄦ埛宸插瓨鍦ㄧ殑鏍￠獙閿欒  
//	        bindingResult.rejectValue("userName", "璇ョ敤鎴峰悕宸插瓨鍦�", "璇ョ敤鎴峰悕宸插瓨鍦�");  
//	        return "/user/create";  
//	    }  
		if(result.hasErrors()) {  
			mav.setViewName("addRole");
			return mav;
        }  
		role.setCreateby(BusinessUtil.getCurrentUserName(request));
		role.setModifyby(BusinessUtil.getCurrentUserName(request));
		roleService.addFDFBRole(role);
//		log.debug("resultcount==================" + resultcount);
		mav.setViewName("redirect:roleList");
		return mav;
	}
	
	@RequestMapping(value="/roleList", method=RequestMethod.GET)
	public ModelAndView gotoRoleList() {
		ModelAndView mav = new ModelAndView();
		List depList = depService.selectAll();//鏇存崲鎴恉ep
		mav.addObject("depList",depList);
		mav.setViewName("roleList");
		return mav;
	}
	
	/**YangLiu
	 * @param request
	 * @return杩斿洖鎵�鏈夌殑瑙掕壊鍒楄〃
	 */
	@RequestMapping(value="/roleListJson", method=RequestMethod.GET)
	@ResponseBody
	public Result roleList(HttpServletRequest request)
	{
		List roleList = roleService.selectAll();
		log.debug(roleList);
		return ResultGenerator.genSuccessResult(roleList);
	}
	
	
	/**YangLiu
	 * @param request
	 * @return	select fu.username,fu.seqno,fu.name, fr.rolename from  fdfb_user fu LEFT JOIN fdfb_user_role fur on fu.seqno=fur.userid 
			LEFT JOIN fdfb_role fr on fr.seqno=fur.roleid
	 */
	@RequestMapping(value="/look/user/list", method=RequestMethod.POST)
	@ResponseBody
	public Result userList(HttpServletRequest request,@RequestBody String message)
	{
		JSONObject jsonMessage = JSON.parseObject(message);
		Map mapBean = JSON.toJavaObject(jsonMessage, Map.class);
		PageHelper.startPage(Integer.valueOf(jsonMessage.get("page") + ""), 10);
		List<Map> list = roleService.selectUserList(mapBean);
		PageInfo p = new PageInfo(list);
		return ResultGenerator.genSuccessResult(p);
	}
	

	/**
	 * jquery Database瀵瑰簲鐨勫垎椤垫柟娉�
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value="/rolelist", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public String rolelistForDatatable(@RequestBody String aoData, HttpServletRequest request) {
		
		log.debug(aoData.toString());
		DatatableTo dto = (DatatableTo) JsonUtil.jqueryStrToBean(aoData, new DatatableTo());
		FDFBRoleVo user = (FDFBRoleVo) JsonUtil.jqueryStrToBean(aoData, new FDFBRoleVo());
		user.setOrderByStr(SqlInjectFilterUtil.filterSqlInject(dto.getOrderStr()));//鎺掑簭鏉′欢,瀵逛簬mybatis鍔ㄦ�乻ql涓娇鐢�$鐨勫瓧娈靛繀椤诲仛sql娉ㄥ叆妫�鏌�
		int userCount = roleService.getAllFDFBRolesCount(user);
		
		PageContext page = PageContext.getContext();
		page.setPageStartRow(dto.getiDisplayStart());//int鍙傛暟锛屾棤娉曡繘琛宻ql娉ㄥ叆
		page.setPageSize(dto.getiDisplayLength());//int鍙傛暟锛屾棤娉曡繘琛宻ql娉ㄥ叆
		page.setPagination(true);//false涓嶅垎椤碉紝true鍒嗛〉锛岃灞炴�у熀鏈棤鐢紝鍙select鏂规硶涓嶄互ByPage缁撳熬灏变笉浼氬垎椤�
		
		List resultList = roleService.selectAllByPage(user);
		log.debug("totalCount===="+page.getTotalRows());
		
		if(resultList != null)
		{
			for(int i=0,n=resultList.size(); i<n; i++)
			{
				user = (FDFBRoleVo)resultList.get(i);
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
	
	/**
	 * jquery Database瀵瑰簲鐨勮鍐呬慨鏀规柟娉�
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value="/updateRoleByAjax", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public FDFBRoleVo updateRoleByAjax(@RequestBody String aoData, HttpServletRequest request) {
		try {
			aoData = URLDecoder.decode(aoData,Constant.ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug(aoData.toString());
		
		FDFBRoleVo user = new FDFBRoleVo();
		user = (FDFBRoleVo) JsonUtil.copyDetailEditStrToBean(aoData, user);
		
		user = roleService.updateRoleDynamic(user);
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
	
	@RequestMapping(value="/modifyRolePermitAction", method=RequestMethod.POST)
	public ModelAndView modifyRolePermit(String allPermit, String roleId, HttpServletRequest request)
	{
		//鏇存柊鍚庢棤闇�鏇存柊session锛屽洜涓鸿鑹叉潈闄愬垎閰嶅彧鏈夌壒瀹氭潈闄愮殑浜哄憳鎵嶈兘鎿嶄綔
		Map user = (Map) request.getSession().getAttribute(Constant.USERINFO_SESSION);
		rolePermitService.updateRolePermitByRoleid(roleId, allPermit, user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:roleList");
		return mav;
	}
	
	@RequestMapping(value="/rolePermit", method=RequestMethod.GET)
	public ModelAndView rolePermit(HttpServletRequest request)
	{
		log.debug("===============================================rolePermit================================================");
		ModelAndView mav = new ModelAndView();
		String seqno = (String)request.getParameter("seqno");
		log.debug("========================ABCDEFG=====================FDFBRoleController==========================");

		String menuInThread = userApi.getPermitByPermitTypeAndRole(Constant.PERMITTYPE_MENU, "", Constant.PERMIT_ORDER_COLUMN, Constant.INIT_TYPE_ALL, request);
		mav.addObject("allMenu", menuInThread);
		mav.addObject("roleId",seqno);
		
		List rolePermitList = rolePermitService.getRolePermitListByRoleid(seqno);
		if(rolePermitList == null || rolePermitList.size() < 1)
		{
			rolePermitList = new ArrayList(0);
		}
		
		mav.addObject("rolePermitList", rolePermitList);
		
		mav.setViewName("modifyRolePermit");
		return mav;
	}

	/**
	 * jquery Database瀵瑰簲鐨勮鍐呬慨鏀规柟娉�
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value="/deleteRoleByAjax", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Map deleteRoleByAjax(@RequestBody String seqno, HttpServletRequest request) {
		
		log.debug(seqno);
		if(seqno!=null)
		{
			seqno = seqno.substring(seqno.indexOf("=")+1,seqno.length());
		}
		Map result = new HashMap(1);
		try{
			if(roleService.deleteUserBySeqno(seqno) > 0)
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
}
