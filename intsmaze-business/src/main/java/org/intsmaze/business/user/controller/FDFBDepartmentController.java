package org.intsmaze.business.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.business.springmvc.util.DatatableTo;
import org.intsmaze.business.springmvc.util.JsonUtil;
import org.intsmaze.business.user.service.IDepartmentService;
import org.intsmaze.business.user.vo.FDFBDepartmentVo;
import org.intsmaze.core.exception.FDFBRuntimeException;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.SqlInjectFilterUtil;
import org.intsmaze.persistence.common.PageContext;
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
public class FDFBDepartmentController {

	private static Logger log = Logger.getLogger(FDFBDepartmentController.class);

	@Autowired
	private IDepartmentService depService;

	@RequestMapping(value = "/depAdd", method = RequestMethod.GET)
	public ModelAndView gotoAdd() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addDep");
		return mav;
	}

	@RequestMapping(value = "/addDepProcess", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView addUser(
			@Validated @ModelAttribute(Constant.COMMON_UI_ERROR_NAME) FDFBDepartmentVo dep,
			BindingResult result, HttpServletRequest request) {// 都可以，但是验证错误信息返回必须使用这个
		ModelAndView mav = new ModelAndView();
//		dep.setSeqno(UUID.randomUUID().toString());
		// 如果用户名已存在，返回添加用户的页面
		// if (isUserNameExist) {
		// // 向BindingResult添加用户已存在的校验错误
		// bindingResult.rejectValue("userName", "该用户名已存在", "该用户名已存在");
		// return "/user/create";
		// }
		if (result.hasErrors()) {
			mav.setViewName("addDep");
			return mav;
		}
		dep.setCreateby(BusinessUtil.getCurrentUserName(request));
		dep.setModifyby(BusinessUtil.getCurrentUserName(request));
		depService.addFDFBDep(dep);
		mav.setViewName("redirect:depList");
		return mav;
	}

	@RequestMapping(value = "/depList", method = RequestMethod.GET)
	public ModelAndView gotoDepList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("depList");
		return mav;
	}

	/**
	 * jquery Database对应的分页方法
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value = "/deplist", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String deplistForDatatable(@RequestBody String aoData,
			HttpServletRequest request) {

		log.debug(aoData.toString());
		DatatableTo dto = (DatatableTo) JsonUtil.jqueryStrToBean(aoData,
				new DatatableTo());
		FDFBDepartmentVo user = (FDFBDepartmentVo) JsonUtil.jqueryStrToBean(
				aoData, new FDFBDepartmentVo());
		user.setOrderByStr(SqlInjectFilterUtil.filterSqlInject(dto
				.getOrderStr()));// 排序条件,对于mybatis动态sql中使用$的字段必须做sql注入检查
		int userCount = depService.getAllFDFBDepsCount(user);

		PageContext page = PageContext.getContext();
		page.setPageStartRow(dto.getiDisplayStart());// int参数，无法进行sql注入
		page.setPageSize(dto.getiDisplayLength());// int参数，无法进行sql注入
		page.setPagination(true);// false不分页，true分页，该属性基本无用，只要select方法不以ByPage结尾就不会分页

		List resultList = depService.selectAllByPage(user);
		
		if (resultList != null) {
			for (int i = 0, n = resultList.size(); i < n; i++) {
				user = (FDFBDepartmentVo) resultList.get(i);
				user.setDT_RowId(Constant.DT_ROWID_PREFIX + user.getSeqno());
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
		// obj.put("recordsFiltered", userList.size());
		obj.put("data", resultList);
		// response.getWriter().println(obj.toJSONString());

		String result = JsonUtil.toJson(obj);

		return result;
	}

	/**
	 * jquery Database对应的行内修改方法
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value = "/updateDepByAjax", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public FDFBDepartmentVo updateDepByAjax(@RequestBody String aoData,
			HttpServletRequest request) {
		try {
			aoData = URLDecoder.decode(aoData, Constant.ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		log.debug(aoData.toString());

		FDFBDepartmentVo user = new FDFBDepartmentVo();
		user = (FDFBDepartmentVo) JsonUtil
				.copyDetailEditStrToBean(aoData, user);

		user = depService.updateDepDynamic(user);
		user.setDT_RowId(Constant.DT_ROWID_PREFIX + user.getSeqno());

		return user;
	}
	
	/**
	 * jquery Database对应的行内修改方法
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value="/deleteDepartmentByAjax", method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Map deleteDepartmentByAjax(@RequestBody String seqno, HttpServletRequest request) {
		if(seqno!=null)
		{
			seqno = seqno.substring(seqno.indexOf("=")+1,seqno.length());
		}
		Map result = new HashMap(1);
		try{
			if(depService.deleteDepBySeqno(seqno) > 0)
			{
				result.put("result", "success");
			}
			else
			{
				result.put("result", "fail");
			}
		}catch (FDFBRuntimeException e) {
			e.printStackTrace();
			result.put("result", "fail");
			return result;
		}

		return result;
	}
}
